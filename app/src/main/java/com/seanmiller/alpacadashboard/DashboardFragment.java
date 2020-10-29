package com.seanmiller.alpacadashboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.robinhood.spark.SparkView;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.enums.Direction;
import net.jacobpeterson.alpaca.enums.OrderStatus;
import net.jacobpeterson.alpaca.enums.PortfolioPeriodUnit;
import net.jacobpeterson.alpaca.enums.PortfolioTimeFrame;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.domain.alpaca.calendar.Calendar;
import net.jacobpeterson.domain.alpaca.order.Order;
import net.jacobpeterson.domain.alpaca.position.Position;
import net.jacobpeterson.polygon.PolygonAPI;
import net.jacobpeterson.polygon.rest.exception.PolygonAPIRequestException;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import io.cabriole.decorator.ColumnProvider;
import io.cabriole.decorator.GridMarginDecoration;
import io.cabriole.decorator.LinearMarginDecoration;

import static com.seanmiller.alpacadashboard.Utils.THEME_DARK;
import static com.seanmiller.alpacadashboard.Utils.THEME_LIGHT;

public class DashboardFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener, View.OnClickListener {

    private SparkView sparkView;
    private StockAdapter selectedAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerOrders;
    public TickerView tickerView;
    public static AtomicReference<String> ticker;
    private RecyclerViewAdapter recycleAdapter;
    private RecyclerViewAdapterOrders recycleAdapterOrders;
    private Button percentChange;
    private String cash = null;
    private Properties props = new Properties();
    private SwipeRefreshLayout swipeRefresh;
    private ImageButton themeChange;
    private ArrayList<Order> orders;
    private MaterialButton oneDay;
    private MaterialButton oneWeek;
    private MaterialButton oneMonth;
    private MaterialButton threeMonth;
    private MaterialButton oneYear;
    private MaterialButton selectedButton;
    private AtomicInteger posOrNegColorLight;
    private StockAdapter oneDayAdapter;
    private StockAdapter oneWeekAdapter;
    private StockAdapter oneMonthAdapter;
    private StockAdapter threeMonthAdapter;
    private StockAdapter oneYearAdapter;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Utils.startTheme(getActivity(), new SharedPreferencesManager(getActivity()).retrieveInt("theme", Utils.THEME_DEFAULT));
        View mView = inflater.inflate(R.layout.dashboard_fragment, null);
        props.setProperties();

        // Set theme and icon
        themeChange = mView.findViewById(R.id.themeChange);
        themeChange.setOnClickListener(this);
        TypedValue outValue = new TypedValue();
        requireActivity().getTheme().resolveAttribute(R.attr.themeName, outValue, true);
        if ("light".equals(outValue.string)) {
            Drawable lightTheme = ContextCompat.getDrawable(requireActivity(), R.drawable.brightness_6);
            themeChange.setImageDrawable(lightTheme);
        } else {
            Drawable darkTheme = ContextCompat.getDrawable(requireActivity(), R.drawable.brightness_4);
            themeChange.setImageDrawable(darkTheme);
        }

        // Initializations
        ticker = new AtomicReference<>("NOTICKER");
        TypedValue typedValue = new TypedValue();
        requireActivity().getTheme().resolveAttribute(R.attr.color_positive_light, typedValue, true);
        AtomicInteger posColorLight = new AtomicInteger(ContextCompat.getColor(requireActivity(), typedValue.resourceId));
        requireActivity().getTheme().resolveAttribute(R.attr.color_negative_light, typedValue, true);
        AtomicInteger negColorLight = new AtomicInteger(ContextCompat.getColor(requireActivity(), typedValue.resourceId));
        negColorLight.set(ContextCompat.getColor(requireActivity(), typedValue.resourceId));
        PolygonAPI polygonAPI = new PolygonAPI();
        AlpacaAPI alpacaAPI = new AlpacaAPI();

        // Set title
        TextView totalEquity = mView.findViewById(R.id.stockTraded);
        totalEquity.setText("Total Equity");

        // Set button group for timeframe
        oneDay = mView.findViewById(R.id.oneDay);
        oneWeek = mView.findViewById(R.id.oneWeek);
        oneMonth = mView.findViewById(R.id.oneMonth);
        threeMonth = mView.findViewById(R.id.threeMonths);
        oneYear = mView.findViewById(R.id.oneYear);
        selectedButton = oneDay;

        // The sparkline graph data
        sparkView = mView.findViewById(R.id.sparkview);
        try {
            oneDayAdapter = new StockAdapter(ticker, 1, PortfolioPeriodUnit.DAY, PortfolioTimeFrame.ONE_MIN);
            oneWeekAdapter = new StockAdapter(ticker, 1, PortfolioPeriodUnit.WEEK, PortfolioTimeFrame.ONE_HOUR);
            oneMonthAdapter = new StockAdapter(ticker, 1, PortfolioPeriodUnit.MONTH, PortfolioTimeFrame.ONE_DAY);
            threeMonthAdapter = new StockAdapter(ticker, 3, PortfolioPeriodUnit.MONTH, PortfolioTimeFrame.ONE_DAY);
            oneYearAdapter = new StockAdapter(ticker, 1, PortfolioPeriodUnit.YEAR, PortfolioTimeFrame.ONE_DAY);
            selectedAdapter = oneDayAdapter;

        } catch (PolygonAPIRequestException | AlpacaAPIRequestException e) {
            e.printStackTrace();
        }
        sparkView.setAdapter(selectedAdapter);

        // Initalize all graphs
        initializeDashboardValues(1, PortfolioPeriodUnit.DAY, PortfolioTimeFrame.FIVE_MINUTE, oneDayAdapter);
        initializeDashboardValues(1, PortfolioPeriodUnit.WEEK, PortfolioTimeFrame.ONE_HOUR, oneWeekAdapter);
        initializeDashboardValues(1, PortfolioPeriodUnit.MONTH, PortfolioTimeFrame.ONE_DAY, oneMonthAdapter);
        initializeDashboardValues(3, PortfolioPeriodUnit.MONTH, PortfolioTimeFrame.ONE_DAY, threeMonthAdapter);
        initializeDashboardValues(1, PortfolioPeriodUnit.YEAR, PortfolioTimeFrame.ONE_DAY, oneYearAdapter);

        // Set colors on click, for toggle buttons
        requireActivity().getTheme().resolveAttribute(R.attr.color_positive_light, typedValue, true);
        posOrNegColorLight = new AtomicInteger(ContextCompat.getColor(requireActivity(), typedValue.resourceId));
        requireActivity().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        int colorPrimary = ContextCompat.getColor(requireActivity(), typedValue.resourceId);
        oneDay.setOnClickListener(v -> {
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(colorPrimary));
            selectedButton = oneDay;
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(posOrNegColorLight.get()));
            selectedAdapter = oneDayAdapter;
            sparkView.setAdapter(selectedAdapter);
            setDashboardValues();
        });
        oneWeek.setOnClickListener(v -> {
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(colorPrimary));
            selectedButton = oneWeek;
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(posOrNegColorLight.get()));
            selectedAdapter = oneWeekAdapter;
            sparkView.setAdapter(selectedAdapter);
            setDashboardValues();
        });
        oneMonth.setOnClickListener(v -> {
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(colorPrimary));
            selectedButton = oneMonth;
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(posOrNegColorLight.get()));
            selectedAdapter = oneMonthAdapter;
            sparkView.setAdapter(selectedAdapter);
            setDashboardValues();
        });
        threeMonth.setOnClickListener(v -> {
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(colorPrimary));
            selectedButton = threeMonth;
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(posOrNegColorLight.get()));
            selectedAdapter = threeMonthAdapter;
            sparkView.setAdapter(selectedAdapter);
            setDashboardValues();
        });
        oneYear.setOnClickListener(v -> {
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(colorPrimary));
            selectedButton = oneYear;
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(posOrNegColorLight.get()));
            selectedAdapter = oneYearAdapter;
            sparkView.setAdapter(selectedAdapter);
            setDashboardValues();
        });

        // Set percent change
        percentChange = mView.findViewById(R.id.percentChange);

        // Ticker information
        tickerView = mView.findViewById(R.id.tickerView);
        tickerView.setCharacterLists(TickerUtils.provideNumberList());

        // Set swipe refresh
        swipeRefresh = mView.findViewById(R.id.refresh);
        swipeRefresh.setTranslationZ(100);
        swipeRefresh.bringToFront();

        getActivity().getTheme().resolveAttribute(R.attr.colorPrimaryLight, typedValue, true);
        AtomicInteger color = new AtomicInteger(ContextCompat.getColor(getActivity(), typedValue.resourceId));

        // Scrub for chart
        sparkView.setSparkAnimator(null);
        sparkView.setBaseLineColor(color.get());

        sparkView.setScrubListener(value -> {

            // Format to add commas
            if (value != null) {

                double amount = Double.parseDouble(String.valueOf(value));
                DecimalFormat formatter = new DecimalFormat("#,###.00");
//                float floatNumEquity = (float) value;
////                try {
////                    floatNumEquity = Float.parseFloat(value);
////                } catch (NumberFormatException e) {
////                    System.out.println(numEquity);
////                    e.printStackTrace();
////                }
//                float temp = (floatNumEquity - selectedAdapter.baseline);
//                System.out.println(temp / selectedAdapter.baseline);
//                float percentageChange = (floatNumEquity - selectedAdapter.baseline) / selectedAdapter.baseline;
//
//                if (floatNumEquity > selectedAdapter.baseline) {
//                    arrow.setColorFilter(getColor(R.color.color_positive));
//                    arrow.setBackgroundDrawable(getResources().getActivity().getDrawable(R.drawable.arrow_top_right));
//                    percentChange.setText("+" + String.valueOf(percentageChange));
//
//                } else {
//                    arrow.setColorFilter(getColor(R.color.color_negative));
//                    arrow.setBackgroundResource(R.drawable.arrow_bottom_right);
//                    percentChange.setText(String.valueOf(percentageChange));
//                }

                tickerView.setText("$" + formatter.format(amount));

            }

        });

        String marketStatus = null;
        try {
            marketStatus = polygonAPI.getMarketStatus().getMarket();

        } catch (PolygonAPIRequestException e) {
            e.printStackTrace();
        }

        if (marketStatus.equals("open")) {

            Thread t3 = new Thread(() -> {

                // Run forever to get the new equities
                while (true) {
                    try {
                        String currentValue = null;
                        try {
                            currentValue = alpacaAPI.getAccount().getPortfolioValue();
                        } catch (AlpacaAPIRequestException e) {
                            e.printStackTrace();
                        }

                        // Format amount
                        double amount = Double.parseDouble(currentValue);
                        DecimalFormat formatter = new DecimalFormat("#,###.00");

                        String finalCurrentValue = currentValue;
                        getActivity().runOnUiThread(() -> {
                            tickerView.setText("$" + formatter.format(amount));
//                            selectedAdapter.addVal(Float.parseFloat(finalCurrentValue));
                        });
                        Thread.sleep(60000 * 5);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t3.start();

            setDashboardValues();

        } else {

            Thread thread = new Thread(() -> {

                String currentValue = null;
                try {
                    currentValue = alpacaAPI.getAccount().getPortfolioValue();
                } catch (AlpacaAPIRequestException e) {
                    e.printStackTrace();
                }

                // Format amount
                double amount = Double.parseDouble(currentValue);
                DecimalFormat formatter = new DecimalFormat("#,###.00");

                getActivity().runOnUiThread(() -> {
                    tickerView.setText("$" + formatter.format(amount));
                });

            });
            thread.start();
            setDashboardValues();

        }

        recyclerView = mView.findViewById(R.id.recyclerStocks);
        recyclerOrders = mView.findViewById(R.id.orders);

        // Threads for getting recycler data
        Thread thread = new Thread(() -> {

            // Fetch current positions
            ArrayList<Position> positions = new ArrayList<>();
            try {
                positions = alpacaAPI.getOpenPositions();
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            ArrayList<String> stocks = new ArrayList<>();
            for (Position i : positions) {
                stocks.add(i.getSymbol());
            }

            // Fetch the Recycler View
            recycleAdapter = new RecyclerViewAdapter(getActivity(), stocks);
            recycleAdapter.setClickListener(this);
            requireActivity().runOnUiThread(() -> recyclerView.setAdapter(recycleAdapter));

        });
        thread.start();

        Thread thread2 = new Thread(() -> {

            // Fetch curent orders
            orders = new ArrayList<>();
            try {
                orders = alpacaAPI.getOrders(OrderStatus.CLOSED, 10, null, ZonedDateTime.now().plusDays(1), Direction.DESCENDING, false);
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            recycleAdapterOrders = new RecyclerViewAdapterOrders(getActivity(), orders);
            requireActivity().runOnUiThread(() -> {
                recyclerOrders.setAdapter(recycleAdapterOrders);
            });

        });
        thread2.start();

        ColumnProvider col = () -> 3;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.addItemDecoration(new GridMarginDecoration(0, col, GridLayoutManager.VERTICAL, false, null));

        recyclerOrders.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerOrders.addItemDecoration(LinearMarginDecoration.create(0, LinearLayoutManager.VERTICAL, false, null));
//        onRefresh();


        // Set Recycle Adapter
//        recycleAdapter = new RecyclerViewAdapter(getActivity(), stocks);
//        recycleAdapter.setClickListener(getActivity());
//        recyclerView.setAdapter(recycleAdapter);

        // Swipe to refresh recycler data
        swipeRefresh.setOnRefreshListener(() -> {

            onRefresh();
            swipeRefresh.setNestedScrollingEnabled(false);
        });
        return mView;
    }

    // If stock is tapped, switch to that stock
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(View view, int position) {

        ticker.set(recycleAdapter.getItem(position));
//        MainActivity.viewPager.findViewWithTag(MainActivity.dashboardFragment);
//        requireActivity().getSupportFragmentManager().beginTransaction().remove(MainActivity.stockFragment).commit();
//        MainActivity.dashboardFragment = new DashboardFragment();
//        MainActivity.pagerAdapter.addFragments(MainActivity.dashboardFragment);
//        MainActivity.pagerAdapter.notifyDataSetChanged();
//        MainActivity.pagerAdapter.notifyAll();
//        MainActivity.viewPager.setAdapter(MainActivity.pagerAdapter);
//        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
//        transaction.remove(MainActivity.dashboardFragment);
//        transaction.add(MainActivity.dashboardFragment, ticker.get());
//        DashboardFragment temp = new DashboardFragment();
//        MainActivity.dashboardFragment = temp;
//        transaction.replace(R.id.containerFrag, MainActivity.dashboardFragment, MainActivity.dashboardFragment.getClass().getSimpleName());
//        transaction.addToBackStack(MainActivity.dashboardFragment.getClass().getSimpleName());
//        transaction.commit();
//        MainActivity.pagerAdapter.notifyDataSetChanged();
//        MainActivity.lastItem = viewPager.getCurrentItem();
//        viewPager.setCurrentItem(4, false);

//        FragmentManager manager = requireActivity().getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.show(MainActivity.dashboardFragment).commit();
//        transaction.add(R.id.containerFrag, MainActivity.dashboardFragment);
//        transaction.replace(R.id.containerFrag, MainActivity.dashboardFragment, MainActivity.dashboardFragment.getClass().getSimpleName());
//        transaction.addToBackStack(MainActivity.dashboardFragment.getClass().getSimpleName());
//        transaction.commit();

        Intent intentMain = new Intent(getActivity(), StockPageActivity.class);
        requireActivity().startActivity(intentMain, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
    }

    @Override
    public void switchColors(RecyclerViewAdapter.ViewHolder view, boolean pos) {

        TypedValue typedValue = new TypedValue();
        if (isAdded()) {

            requireActivity().getTheme().resolveAttribute(R.attr.color_positive, typedValue, true);
            int posColor = ContextCompat.getColor(requireActivity(), typedValue.resourceId);
            requireActivity().getTheme().resolveAttribute(R.attr.color_negative, typedValue, true);
            int negColor = ContextCompat.getColor(requireActivity(), typedValue.resourceId);
            requireActivity().getTheme().resolveAttribute(R.attr.color_positive_light, typedValue, true);
            int posColorLight = ContextCompat.getColor(requireActivity(), typedValue.resourceId);
            requireActivity().getTheme().resolveAttribute(R.attr.color_negative_light, typedValue, true);
            int negColorLight = ContextCompat.getColor(requireActivity(), typedValue.resourceId);

            if (pos) {
                view.percentChange.setTextColor(posColor);
                view.percentChange.setBackgroundTintList(ColorStateList.valueOf(posColorLight));

                Drawable downArrow = getActivity().getDrawable(R.drawable.arrow_top_right);
                downArrow.setTint(posColor);
                view.percentChange.setCompoundDrawablesWithIntrinsicBounds(downArrow, null, null, null);
            } else {
                view.percentChange.setTextColor(negColor);
                view.percentChange.setBackgroundTintList(ColorStateList.valueOf(negColorLight));

                Drawable downArrow = getActivity().getDrawable(R.drawable.arrow_bottom_right);
                downArrow.setTint(negColor);
                view.percentChange.setCompoundDrawablesWithIntrinsicBounds(downArrow, null, null, null);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onRefresh() {

        swipeRefresh.setRefreshing(true);

        Thread thread = new Thread(() -> {

            AlpacaAPI alpacaAPI = new AlpacaAPI();

            // Fetch current positions
            ArrayList<Position> positions = new ArrayList<>();
            try {
                positions = alpacaAPI.getOpenPositions();
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            ArrayList<String> stocks = new ArrayList<>();
            for (Position i : positions) {
                stocks.add(i.getSymbol());
            }

            // Set Recycle Adapter for positions
            requireActivity().runOnUiThread(() -> recyclerView.setAdapter(recycleAdapter));
        });
        thread.start();

        Thread thread2 = new Thread(() -> {

            AlpacaAPI alpacaAPI = new AlpacaAPI();

            // Fetch curent orders
            orders = new ArrayList<>();
            try {
                orders = alpacaAPI.getOrders(OrderStatus.CLOSED, 10, null, ZonedDateTime.now().plusDays(1), Direction.DESCENDING, false);
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            // Set Recycle Adapter for orders
            requireActivity().runOnUiThread(() -> recyclerOrders.setAdapter(recycleAdapterOrders));
            swipeRefresh.setRefreshing(false);
        });
        thread2.start();
    }

    @Override
    public void onClick(View v) {

        TypedValue outValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.themeName, outValue, true);
        if ("light".equals(outValue.string)) {
            new SharedPreferencesManager(getActivity()).storeInt("theme", THEME_DARK);
            Utils.changeToTheme(getActivity(), Utils.THEME_DARK);

        } else {
            new SharedPreferencesManager(getActivity()).storeInt("theme", THEME_LIGHT);
            Utils.changeToTheme(getActivity(), Utils.THEME_LIGHT);
        }

    }

    public void setDashboardColors(boolean pos, float profitLoss, float percentageChange) {

        TypedValue typedValue = new TypedValue();
        requireActivity().getTheme().resolveAttribute(R.attr.color_positive_light, typedValue, true);
        AtomicInteger posColorLight = new AtomicInteger(ContextCompat.getColor(requireActivity(), typedValue.resourceId));
        requireActivity().getTheme().resolveAttribute(R.attr.color_negative_light, typedValue, true);
        AtomicInteger negColorLight = new AtomicInteger(ContextCompat.getColor(requireActivity(), typedValue.resourceId));

        if (pos) {
            percentChange.setText(String.format("+$%.2f (%.2f%%)", profitLoss, percentageChange));

            requireActivity().getTheme().resolveAttribute(R.attr.color_positive, typedValue, true);
            int color = ContextCompat.getColor(requireActivity(), typedValue.resourceId);
            percentChange.setTextColor(color);
            percentChange.setBackgroundTintList(ColorStateList.valueOf(posColorLight.get()));
            Drawable upArrow = ContextCompat.getDrawable(requireActivity(), R.drawable.arrow_top_right);
            upArrow.setTint(color);
            percentChange.setCompoundDrawablesWithIntrinsicBounds(null, null, upArrow, null);
            sparkView.setLineColor(color);
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(posColorLight.get()));
            oneDay.setTextColor(color);
            oneWeek.setTextColor(color);
            oneMonth.setTextColor(color);
            threeMonth.setTextColor(color);
            oneYear.setTextColor(color);
            oneDay.setRippleColor(ColorStateList.valueOf(color));
            oneWeek.setRippleColor(ColorStateList.valueOf(color));
            oneMonth.setRippleColor(ColorStateList.valueOf(color));
            threeMonth.setRippleColor(ColorStateList.valueOf(color));
            oneYear.setRippleColor(ColorStateList.valueOf(color));

            requireActivity().getTheme().resolveAttribute(R.attr.color_positive_light, typedValue, true);
            posOrNegColorLight.set(ContextCompat.getColor(requireActivity(), typedValue.resourceId));

        } else {
            percentChange.setText(String.format("-$%.2f (%.2f%%)", Math.abs(profitLoss), percentageChange));

            requireActivity().getTheme().resolveAttribute(R.attr.color_negative, typedValue, true);
            int color = ContextCompat.getColor(requireActivity(), typedValue.resourceId);
            percentChange.setTextColor(color);
            percentChange.setBackgroundTintList(ColorStateList.valueOf(negColorLight.get()));
            Drawable downArrow = ContextCompat.getDrawable(requireActivity(), R.drawable.arrow_bottom_right);
            downArrow.setTint(color);
            percentChange.setCompoundDrawablesWithIntrinsicBounds(null, null, downArrow, null);
            sparkView.setLineColor(color);
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(negColorLight.get()));
            oneDay.setTextColor(color);
            oneWeek.setTextColor(color);
            oneMonth.setTextColor(color);
            threeMonth.setTextColor(color);
            oneYear.setTextColor(color);
            oneDay.setRippleColor(ColorStateList.valueOf(color));
            oneWeek.setRippleColor(ColorStateList.valueOf(color));
            oneMonth.setRippleColor(ColorStateList.valueOf(color));
            threeMonth.setRippleColor(ColorStateList.valueOf(color));
            oneYear.setRippleColor(ColorStateList.valueOf(color));

            requireActivity().getTheme().resolveAttribute(R.attr.color_negative_light, typedValue, true);
            posOrNegColorLight.set(ContextCompat.getColor(requireActivity(), typedValue.resourceId));
        }
    }

    public void initializeDashboardValues(int periodLength, PortfolioPeriodUnit periodUnit, PortfolioTimeFrame timeFrame, StockAdapter selectedAdapterInitial) {

        AlpacaAPI alpacaAPI = new AlpacaAPI();

        Thread t2 = new Thread(() -> {

            // Gather old portfolio data
            ArrayList<Double> history = new ArrayList<>();
            try {
                history = alpacaAPI.getPortfolioHistory(periodLength, periodUnit, timeFrame, LocalDate.now(), true).getEquity();

            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            // Add data to chart
            if (history.size() != 0) {
                for (Double i : history) {
                    if (i != null) {
                        selectedAdapterInitial.addVal(Float.parseFloat(String.valueOf(i)));
                    }
                }
            }

//            while (selectedAdapterInitial.getCount() >= 100) {
//                selectedAdapterInitial.smoothGraph();
//            }

            if (periodUnit == PortfolioPeriodUnit.YEAR) {
                selectedAdapterInitial.smoothGraph();
            }

        });
        t2.start();
    }

    public void setDashboardValues() {

        AlpacaAPI alpacaAPI = new AlpacaAPI();

        // Create thread for updating equity
        Thread t1 = new Thread(() -> {

            // Fetch portfolio value
            String portVal = null;
            try {
                portVal = alpacaAPI.getAccount().getPortfolioValue();
                cash = alpacaAPI.getAccount().getCash();
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            String finalPortVal = portVal;
            getActivity().runOnUiThread(() -> {

                // Set percent change and update colors
                if (finalPortVal != null && cash != null) {

                    float holdingVal = Float.parseFloat(finalPortVal) - Float.parseFloat(cash);

                    float oldVal = selectedAdapter.getValue(0) - holdingVal;
                    float newVal = selectedAdapter.getValue(selectedAdapter.getCount() - 1) - holdingVal;
                    float percentageChange = (newVal - oldVal) / oldVal * 100;
                    float profitLoss = selectedAdapter.getValue(selectedAdapter.getCount() - 1) - selectedAdapter.getValue(0);

                    // Set colors
                    if (percentageChange >= 0) {

                        setDashboardColors(true, profitLoss, percentageChange);

                    } else {

                        setDashboardColors(false, profitLoss, percentageChange);
                    }
                }

            });

        });
        t1.start();
    }

}