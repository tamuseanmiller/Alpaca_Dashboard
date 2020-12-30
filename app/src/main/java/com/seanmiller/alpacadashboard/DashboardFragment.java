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
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Insets;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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
import net.jacobpeterson.domain.alpaca.portfoliohistory.PortfolioHistory;
import net.jacobpeterson.domain.alpaca.position.Position;
import net.jacobpeterson.polygon.PolygonAPI;
import net.jacobpeterson.polygon.rest.exception.PolygonAPIRequestException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import io.cabriole.decorator.ColumnProvider;
import io.cabriole.decorator.GridMarginDecoration;
import io.cabriole.decorator.LinearMarginDecoration;

import static com.seanmiller.alpacadashboard.StockAdapter.getLastFilledIndex;
import static com.seanmiller.alpacadashboard.Utils.THEME_DARK;
import static com.seanmiller.alpacadashboard.Utils.THEME_LIGHT;

public class DashboardFragment extends Fragment implements RecyclerViewAdapterPositions.ItemClickListener, View.OnClickListener {

    private CustomSparkView sparkView;
    private StockAdapter selectedAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerOrders;
    public static TickerView tickerView;
    public static AtomicReference<String> ticker;
    private RecyclerViewAdapterPositions recycleAdapter;
    private RecyclerViewAdapterOrders recycleAdapterOrders;
    private Button percentChange;
    private SwipeRefreshLayout swipeRefresh;
    private ArrayList<Order> orders;
    private MaterialButton oneDay;
    private MaterialButton oneWeek;
    private MaterialButton oneMonth;
    private MaterialButton threeMonth;
    private MaterialButton oneYear;
    private MaterialButton selectedButton;
    private AtomicInteger posOrNegColorLight;
    public static StockAdapter oneDayAdapter;
    private StockAdapter oneWeekAdapter;
    private StockAdapter oneMonthAdapter;
    private StockAdapter threeMonthAdapter;
    private StockAdapter oneYearAdapter;
    public static ArrayList<String> stocks;
    private SharedPreferencesManager prefs;


    public int fetchHeight() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            WindowMetrics windowMetrics = requireActivity().getWindowManager().getCurrentWindowMetrics();
            Insets insets = windowMetrics.getWindowInsets()
                    .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
            return windowMetrics.getBounds().height() - insets.top - insets.bottom;

        } else {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.heightPixels;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Utils.startTheme(getActivity(), new SharedPreferencesManager(getActivity()).retrieveInt("theme", Utils.THEME_DEFAULT));
        View mView = inflater.inflate(R.layout.dashboard_fragment, null);
        prefs = new SharedPreferencesManager(getActivity());

        // Vary size of spark view by height of screen size
        int height = fetchHeight();
        sparkView = mView.findViewById(R.id.sparkview);
        MaterialCardView sparkCard = mView.findViewById(R.id.sparkCard);
        sparkCard.setMinimumHeight((int) (height / 1.75));

        // Set theme and icon
        ImageButton themeChange = mView.findViewById(R.id.themeChange);
        themeChange.setOnClickListener(this);
        TypedValue outValue = new TypedValue();
        requireActivity().getTheme().resolveAttribute(R.attr.themeName, outValue, true);
        if ("light".contentEquals(outValue.string)) {
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
        PolygonAPI polygonAPI = new PolygonAPI(prefs.retrieveString("polygon_id", "NULL"));
        AlpacaAPI alpacaAPI = new AlpacaAPI(prefs.retrieveString("auth_token", "NULL"));

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
        try {
            oneDayAdapter = new StockAdapter(ticker, 1, PortfolioPeriodUnit.DAY, PortfolioTimeFrame.ONE_MIN, getActivity());
            oneWeekAdapter = new StockAdapter(ticker, 1, PortfolioPeriodUnit.WEEK, PortfolioTimeFrame.ONE_HOUR, getActivity());
            oneMonthAdapter = new StockAdapter(ticker, 1, PortfolioPeriodUnit.MONTH, PortfolioTimeFrame.ONE_DAY, getActivity());
            threeMonthAdapter = new StockAdapter(ticker, 3, PortfolioPeriodUnit.MONTH, PortfolioTimeFrame.ONE_DAY, getActivity());
            oneYearAdapter = new StockAdapter(ticker, 1, PortfolioPeriodUnit.YEAR, PortfolioTimeFrame.ONE_DAY, getActivity());
            selectedAdapter = oneDayAdapter;

        } catch (PolygonAPIRequestException | AlpacaAPIRequestException e) {
            e.printStackTrace();
        }
        sparkView.setAdapter(selectedAdapter);

        // Initalize all graphs
        initializeDashboardValues(1, PortfolioPeriodUnit.DAY, PortfolioTimeFrame.FIVE_MINUTE, oneDayAdapter, getActivity());
        initializeDashboardValues(1, PortfolioPeriodUnit.WEEK, PortfolioTimeFrame.ONE_HOUR, oneWeekAdapter, getActivity());
        initializeDashboardValues(1, PortfolioPeriodUnit.MONTH, PortfolioTimeFrame.ONE_DAY, oneMonthAdapter, getActivity());
        initializeDashboardValues(3, PortfolioPeriodUnit.MONTH, PortfolioTimeFrame.ONE_DAY, threeMonthAdapter, getActivity());
        initializeDashboardValues(1, PortfolioPeriodUnit.YEAR, PortfolioTimeFrame.ONE_DAY, oneYearAdapter, getActivity());

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

        requireActivity().getTheme().resolveAttribute(R.attr.colorPrimaryLight, typedValue, true);
        AtomicInteger color = new AtomicInteger(ContextCompat.getColor(requireActivity(), typedValue.resourceId));

        // Scrub for chart
        sparkView.setSparkAnimator(null);
        sparkView.setBaseLineColor(color.get());

        sparkView.setScrubListener(value -> {

            // Format to add commas, scrub for ticker price
            if (value != null) {
                double amount = Double.parseDouble(String.valueOf(value));
                DecimalFormat formatter = new DecimalFormat("#,###.00");
                tickerView.setText("$" + formatter.format(amount));

            }
        });

        String marketStatus = null;
        try {
            marketStatus = polygonAPI.getMarketStatus().getMarket();

        } catch (PolygonAPIRequestException e) {
            e.printStackTrace();
        }

        oneDay.callOnClick();

        if (marketStatus.equals("open")) {

            Thread t3 = new Thread(() -> {
                DecimalFormat formatter = new DecimalFormat("#,###.00");

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

                        getActivity().runOnUiThread(() -> tickerView.setText("$" + formatter.format(amount)));
                        oneDayAdapter.addVal(Float.parseFloat(currentValue));
                        oneDayAdapter.notifyDataSetChanged();

                        Thread.sleep(60000 * 5);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t3.start();

//            setDashboardValues();

        } else {

            Thread thread = new Thread(() -> {

                String currentValue = null;
                try {
                    currentValue = alpacaAPI.getAccount().getPortfolioValue();
                } catch (AlpacaAPIRequestException e) {
                    e.printStackTrace();
                }

                // Format amount
                assert currentValue != null;
                double amount = Double.parseDouble(currentValue);
                DecimalFormat formatter = new DecimalFormat("#,###.00");

                requireActivity().runOnUiThread(() -> {
                    tickerView.setText("$" + formatter.format(amount));
                });

//                setDashboardValues();
            });
            thread.start();

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

            stocks = new ArrayList<>();
            for (Position i : positions) {
                stocks.add(i.getSymbol());
            }

            // Fetch the Recycler View
            recycleAdapter = new RecyclerViewAdapterPositions(getActivity(), stocks);
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

        // Swipe to refresh recycler data
        swipeRefresh.setOnRefreshListener(this::onRefresh);

        return mView;
    }

    // If stock is tapped, switch to that stock
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(View view, int position) {

        ticker.set(recycleAdapter.getItem(position));
        Intent intentMain = new Intent(getActivity(), StockPageActivity.class);
        requireActivity().startActivity(intentMain, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
    }

    @Override
    public void switchColors(RecyclerViewAdapterPositions.ViewHolder view, boolean pos) {

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

                Drawable downArrow = ContextCompat.getDrawable(requireActivity(), R.drawable.arrow_top_right);
                downArrow.setTint(posColor);
                view.percentChange.setCompoundDrawablesWithIntrinsicBounds(downArrow, null, null, null);
            } else {
                view.percentChange.setTextColor(negColor);
                view.percentChange.setBackgroundTintList(ColorStateList.valueOf(negColorLight));

                Drawable downArrow = ContextCompat.getDrawable(requireActivity(), R.drawable.arrow_bottom_right);
                downArrow.setTint(negColor);
                view.percentChange.setCompoundDrawablesWithIntrinsicBounds(downArrow, null, null, null);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onRefresh() {

        swipeRefresh.setRefreshing(true);

        Thread thread = new Thread(() -> {

            AlpacaAPI alpacaAPI = new AlpacaAPI(prefs.retrieveString("auth_token", "NULL"));

            // Fetch current positions
            ArrayList<Position> positions = new ArrayList<>();
            try {
                positions = alpacaAPI.getOpenPositions();
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            ArrayList<String> temp = new ArrayList<>();
            for (Position i : positions) {
                temp.add(i.getSymbol());
            }
            stocks.clear();
            stocks.addAll(temp);
            temp.clear();
            temp = null;

            // Set Recycle Adapter for positions
            requireActivity().runOnUiThread(() -> recycleAdapter.notifyDataSetChanged());

        });
        thread.start();

        Thread thread2 = new Thread(() -> {

            AlpacaAPI alpacaAPI = new AlpacaAPI(prefs.retrieveString("auth_token", "NULL"));

            // Fetch curent orders
            ArrayList<Order> temp = new ArrayList<>();
            try {
                temp = alpacaAPI.getOrders(OrderStatus.CLOSED, 10, null, ZonedDateTime.now().plusDays(1), Direction.DESCENDING, false);

            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }
            orders.clear();
            orders.addAll(temp);

            // Set Recycle Adapter for orders
            recycleAdapterOrders = new RecyclerViewAdapterOrders(getActivity(), orders);
            requireActivity().runOnUiThread(() -> {
                recyclerOrders.setAdapter(recycleAdapterOrders);
            });

            swipeRefresh.setRefreshing(false);
        });
        thread2.start();
    }

    // Theme change on click
    @Override
    public void onClick(View v) {

        TypedValue outValue = new TypedValue();
        requireActivity().getTheme().resolveAttribute(R.attr.themeName, outValue, true);
        if ("light".contentEquals(outValue.string)) {
            new SharedPreferencesManager(getActivity()).storeInt("theme", THEME_DARK);
            Utils.changeToTheme(requireActivity(), Utils.THEME_DARK);

        } else {
            new SharedPreferencesManager(getActivity()).storeInt("theme", THEME_LIGHT);
            Utils.changeToTheme(requireActivity(), Utils.THEME_LIGHT);
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

    private void initializeDashboardValues(int periodLength, PortfolioPeriodUnit periodUnit, PortfolioTimeFrame timeFrame, StockAdapter selectedAdapterInitial, Context mContext) {

        AlpacaAPI alpacaAPI = new AlpacaAPI(prefs.retrieveString("auth_token", "NULL"));
        PolygonAPI polygonAPI = new PolygonAPI(prefs.retrieveString("polygon_id", "NULL"));

        Thread t2 = new Thread(() -> {

            ArrayList<Double> history = null;

            if (periodUnit == PortfolioPeriodUnit.DAY) {

                // Fetch last open day's information
                ArrayList<Calendar> calendar = null;
                try {
                    calendar = alpacaAPI.getCalendar(LocalDate.now().minusWeeks(1), LocalDate.now());

                } catch (AlpacaAPIRequestException e) {
                    e.printStackTrace();
                }

                // Assign last open datetime and check for if it is the morning of
                assert calendar != null;
                LocalDate lastOpenDate = LocalDate.parse(calendar.get(calendar.size() - 1).getDate());
                if (LocalTime.of(Integer.parseInt(calendar.get(calendar.size() - 1).getOpen().substring(0, 2)),
                        Integer.parseInt(calendar.get(calendar.size() - 1).getOpen().substring(3, 5))).compareTo(LocalTime.now()) > 0) {
                    lastOpenDate = LocalDate.parse(calendar.get(calendar.size() - 2).getDate());
                }

                // Gather old portfolio data
                history = new ArrayList<>();
                try {
                    PortfolioHistory portVal = alpacaAPI.getPortfolioHistory(periodLength, periodUnit, timeFrame, lastOpenDate, true);
                    history = portVal.getEquity();

                } catch (AlpacaAPIRequestException e) {
                    e.printStackTrace();
                }

            } else {

                // Gather old portfolio data
                history = new ArrayList<>();
                try {
                    PortfolioHistory portVal = alpacaAPI.getPortfolioHistory(periodLength, periodUnit, timeFrame, LocalDate.now(), true);
                    history = portVal.getEquity();

                } catch (AlpacaAPIRequestException e) {
                    e.printStackTrace();
                }
            }

            // Fixes weird bug with repeating data on week period
            if (periodUnit == PortfolioPeriodUnit.WEEK) {
                // Add data to chart
                if (history.size() != 0) {
                    for (int i = 10; i < history.size(); i++) {
                        if (history.get(i) != null) {
                            selectedAdapterInitial.addVal(Float.parseFloat(String.valueOf(history.get(i))));
                        }
                    }
                }

            } else {
                // Add data to chart
                if (history.size() != 0) {
                    for (int i = 0; i < history.size(); i++) {
                        if (history.get(i) != null) {
                            selectedAdapterInitial.addVal(Float.parseFloat(String.valueOf(history.get(i))));
                        }
                    }
                }
            }

            // Use data returned to get profit change
            float oldVal = selectedAdapterInitial.getValue(0);
            float newVal = selectedAdapterInitial.getValue(selectedAdapterInitial.getCount() - 1);
            float percentageChange = (newVal - oldVal) / oldVal * 100;
            float profitLoss = selectedAdapterInitial.getValue(selectedAdapterInitial.getCount() - 1) - selectedAdapterInitial.getValue(0);

            // Sets profit values
            selectedAdapterInitial.setPercent(percentageChange);
            selectedAdapterInitial.setProfit(profitLoss);

            // Smooth year graph
            if (periodUnit == PortfolioPeriodUnit.YEAR) {
                selectedAdapterInitial.smoothGraph();
            }


            AtomicReference<Float> lastClose = new AtomicReference<>((float) 0);
            AtomicReference<ArrayList<Double>> historyInitial = new AtomicReference<>(new ArrayList<>());

            // Fetch last open day's information
            ArrayList<Calendar> calendar = null;
            try {
                calendar = alpacaAPI.getCalendar(LocalDate.now().minusWeeks(1), LocalDate.now());
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }
            assert calendar != null;
            LocalDate lastOpenDate = LocalDate.parse(calendar.get(calendar.size() - 2).getDate());
            LocalTime lastOpenTime = LocalTime.parse(calendar.get(calendar.size() - 2).getOpen());

            // Set baseline values
            ArrayList<Calendar> finalCalendar = calendar;

            // Get market status
            String marketStatus = null;
            try {
                marketStatus = polygonAPI.getMarketStatus().getMarket();

            } catch (PolygonAPIRequestException e) {
                e.printStackTrace();
            }

            // Check if market is open
            if (marketStatus.equals("open")) {

                // Gather old portfolio data
                historyInitial.set(new ArrayList<>());
                try {
                    historyInitial.set(alpacaAPI.getPortfolioHistory(periodLength, periodUnit, timeFrame, lastOpenDate, false).getEquity());

                } catch (AlpacaAPIRequestException e) {
                    e.printStackTrace();
                }

                double temp = historyInitial.get().get(historyInitial.get().size() - 1);
                selectedAdapterInitial.push_front((float) temp);
                selectedAdapterInitial.setBaseline(selectedAdapterInitial.getValue(0));
                oneDayAdapter.notifyDataSetChanged();

            } else {

                historyInitial.set(new ArrayList<>());

                try {
                    historyInitial.set(alpacaAPI.getPortfolioHistory(periodLength, periodUnit, timeFrame, lastOpenDate, false).getEquity());

                } catch (AlpacaAPIRequestException e) {
                    e.printStackTrace();
                }

                double temp = getLastFilledIndex(historyInitial.get());
                selectedAdapterInitial.push_front((float) temp);
                selectedAdapterInitial.setBaseline(selectedAdapterInitial.getValue(0));
                oneDayAdapter.notifyDataSetChanged();

            }

            setDashboardValues(); // Set here to allow ample time for instantiation

        });
        t2.start();
    }

    public void setDashboardValues() {

        // Updating the values on history switch
        // Set colors
        requireActivity().runOnUiThread(() -> {
            if (selectedAdapter.getProfit() >= 0) {
                setDashboardColors(true, selectedAdapter.getProfit(), selectedAdapter.getPercent());

            } else {
                setDashboardColors(false, selectedAdapter.getProfit(), selectedAdapter.getPercent());

            }
        });
    }

}