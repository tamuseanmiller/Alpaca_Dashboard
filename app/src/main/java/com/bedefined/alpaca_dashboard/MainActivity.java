package com.bedefined.alpaca_dashboard;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.robinhood.spark.SparkView;
import com.robinhood.spark.animation.LineSparkAnimator;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.enums.ActivityType;
import net.jacobpeterson.alpaca.enums.Direction;
import net.jacobpeterson.alpaca.enums.OrderStatus;
import net.jacobpeterson.alpaca.enums.PortfolioPeriodUnit;
import net.jacobpeterson.alpaca.enums.PortfolioTimeFrame;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.alpaca.websocket.listener.AlpacaStreamListenerAdapter;
import net.jacobpeterson.alpaca.websocket.message.AlpacaStreamMessageType;
import net.jacobpeterson.domain.alpaca.calendar.Calendar;
import net.jacobpeterson.domain.alpaca.order.Order;
import net.jacobpeterson.domain.alpaca.position.Position;
import net.jacobpeterson.domain.alpaca.websocket.AlpacaStreamMessage;
import net.jacobpeterson.domain.alpaca.websocket.account.AccountUpdateMessage;
import net.jacobpeterson.domain.alpaca.websocket.trade.TradeUpdateMessage;
import net.jacobpeterson.domain.polygon.websocket.PolygonStreamMessage;
import net.jacobpeterson.domain.polygon.websocket.quote.QuoteMessage;
import net.jacobpeterson.polygon.PolygonAPI;
import net.jacobpeterson.polygon.rest.exception.PolygonAPIRequestException;
import net.jacobpeterson.polygon.websocket.listener.PolygonStreamListener;
import net.jacobpeterson.polygon.websocket.listener.PolygonStreamListenerAdapter;
import net.jacobpeterson.polygon.websocket.message.PolygonStreamMessageType;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import io.cabriole.decorator.ColumnProvider;
import io.cabriole.decorator.GridMarginDecoration;
import io.cabriole.decorator.LinearMarginDecoration;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener, View.OnClickListener {

    private SparkView sparkView;
    private StockAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerOrders;
    public TickerView tickerView;
    public static AtomicReference<String> ticker;
    private static PolygonStreamListener polygonStream;
    private RecyclerViewAdapter recycleAdapter;
    private RecyclerViewAdapterOrders recycleAdapterOrders;
    private Button percentChange;
    private String cash = null;
    private Properties props = new Properties();
    private SwipeRefreshLayout swipeRefresh;
    private FloatingActionButton fab;
    private ImageButton themeChange;
    private ArrayList<Order> orders;
    private static Resources.Theme theme;
    private static Context context;
    private BottomNavigationView bottomNavigationView;

    // Streams ticker data from polygon
    public void streamStockData(PolygonAPI polygonAPI, AtomicReference<String> ticker, TickerView tickerV) {

        props.setProperties();

        polygonAPI.addPolygonStreamListener(polygonStream = new PolygonStreamListenerAdapter(String.valueOf(ticker), PolygonStreamMessageType.QUOTE) {

            float askingPrice = 0;

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onStreamUpdate(PolygonStreamMessageType streamMessageType, PolygonStreamMessage streamMessage) {
                askingPrice = ((QuoteMessage) streamMessage).getAp().floatValue();

                Thread thread = new Thread(() -> {

                    double amount = Double.parseDouble(String.valueOf(askingPrice));
                    DecimalFormat formatter = new DecimalFormat("#,###.00");

                    // Render tickerView
                    runOnUiThread(() -> {
                        tickerV.setText("$" + formatter.format(amount));
                    });

                });
                thread.start();
            }
        });
    }

    public void streamAccountData(AlpacaAPI alpacaAPI) {

        props.setProperties();

        // Register explicitly for ACCOUNT_UPDATES and ORDER_UPDATES Messages via stream listener
        alpacaAPI.addAlpacaStreamListener(new AlpacaStreamListenerAdapter(
                AlpacaStreamMessageType.ACCOUNT_UPDATES,
                AlpacaStreamMessageType.TRADE_UPDATES) {

            public void onStreamUpdate(AlpacaStreamMessageType streamMessageType, AlpacaStreamMessage streamMessage) {
                switch (streamMessageType) {
                    case ACCOUNT_UPDATES:
                        AccountUpdateMessage accountUpdateMessage = (AccountUpdateMessage) streamMessage;
                        System.out.println("\nReceived Account Update: \n\t" +
                                accountUpdateMessage.toString().replace(",", ",\n\t"));
                        break;
                    case TRADE_UPDATES:
                        TradeUpdateMessage tradeUpdateMessage = (TradeUpdateMessage) streamMessage;
                        System.out.println("\nReceived Order Update: \n\t" +
                                tradeUpdateMessage.toString().replace(",", ",\n\t"));
                        break;
                }
            }
        });
    }

    // onCreate
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Utils.onActivityCreateSetTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        props.setProperties();

        // Set theme and icon
        themeChange = findViewById(R.id.themeChange);
        themeChange.setOnClickListener(this);
        TypedValue outValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.themeName, outValue, true);
        if ("light".equals(outValue.string)) {
            Drawable lightTheme = getDrawable(R.drawable.brightness_6);
            themeChange.setImageDrawable(lightTheme);
        } else {
            Drawable darkTheme = getDrawable(R.drawable.brightness_4);
            themeChange.setImageDrawable(darkTheme);
        }
        theme = getTheme();
        context = getApplicationContext();

        // Initializations
        ticker = new AtomicReference<>("NOTICKER");
        PolygonAPI polygonAPI = new PolygonAPI();
        AlpacaAPI alpacaAPI = new AlpacaAPI();

        // Set title
        TextView totalEquity = findViewById(R.id.stockTraded);
        totalEquity.setText("Total Equity");

        // Set percent change
        percentChange = findViewById(R.id.percentChange);

        // Ticker information
        tickerView = findViewById(R.id.tickerView);
        tickerView.setCharacterLists(TickerUtils.provideNumberList());

        // Set swipe refresh
        swipeRefresh = findViewById(R.id.refresh);
        swipeRefresh.setTranslationZ(100);
        swipeRefresh.bringToFront();

        // Set bottom navbar
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.dashboard_page);
        bottomNavigationView.setOnNavigationItemSelectedListener( item -> {

            switch(item.getItemId()) {
                case R.id.dashboard_page:
                    Intent intentMain = new Intent(MainActivity.this, StockPage.class);
                    MainActivity.this.startActivity(intentMain, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                    return true;
                case R.id.search_page:
                    Intent intentSearch = new Intent(MainActivity.this, Search.class);
                    MainActivity.this.startActivity(intentSearch, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                    return true;
                case R.id.profile_page:
                    Intent intentProfile = new Intent(MainActivity.this, Profile.class);
                    MainActivity.this.startActivity(intentProfile, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                    return true;
            }
            return false;
        });

        // Set fab
//        fab = findViewById(R.id.placeOrder);
//        fab.setOnClickListener(v -> {
//            PlaceOrderFragment dialogFrag = new PlaceOrderFragment();
//            dialogFrag.setParentFab(fab);
//            dialogFrag.show(getSupportFragmentManager(), dialogFrag.getTag());
//        });

        // The sparkline graph itself
        sparkView = findViewById(R.id.sparkview);
        try {
            adapter = new StockAdapter(ticker);
        } catch (PolygonAPIRequestException | AlpacaAPIRequestException e) {
            e.printStackTrace();
        }
        sparkView.setAdapter(adapter);

        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryLight, typedValue, true);
        AtomicInteger color = new AtomicInteger(ContextCompat.getColor(this, typedValue.resourceId));

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
//                float temp = (floatNumEquity - adapter.baseline);
//                System.out.println(temp / adapter.baseline);
//                float percentageChange = (floatNumEquity - adapter.baseline) / adapter.baseline;
//
//                if (floatNumEquity > adapter.baseline) {
//                    arrow.setColorFilter(getColor(R.color.color_positive));
//                    arrow.setBackgroundDrawable(getResources().getDrawable(R.drawable.arrow_top_right));
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

            Thread t2 = new Thread(() -> {

                // Gather old portfolio data
                ArrayList<Double> history = new ArrayList<>();
                try {
                    history = alpacaAPI.getPortfolioHistory(1, PortfolioPeriodUnit.DAY, PortfolioTimeFrame.FIVE_MINUTE, LocalDate.now(), true).getEquity();

                } catch (AlpacaAPIRequestException e) {
                    e.printStackTrace();
                }

                // Add data to chart
                if (history.get(0) != null) {
                    for (Double i : history) {
                        if (i != null) {
                            adapter.addVal(Float.parseFloat(String.valueOf(i)));
                        }
                    }
                }

            });
            t2.start();

            // Create thread for updating equity
            Thread t1 = new Thread(() -> {

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
                        runOnUiThread(() -> {
                            tickerView.setText("$" + formatter.format(amount));
                            adapter.addVal(Float.parseFloat(finalCurrentValue));

                            // Fetch portfolio value
                            String portVal = null;
                            try {
                                portVal = alpacaAPI.getAccount().getPortfolioValue();
                                cash = alpacaAPI.getAccount().getCash();
                            } catch (AlpacaAPIRequestException e) {
                                e.printStackTrace();
                            }

                            // Set percent change and update colors
                            if (portVal != null && cash != null) {

                                float holdingVal = Float.parseFloat(portVal) - Float.parseFloat(cash);

                                float oldVal = adapter.getValue(0) - holdingVal;
                                float newVal = adapter.getValue(adapter.getCount() - 1) - holdingVal;
                                float percentageChange = (newVal - oldVal) / oldVal * 100;
                                float profitLoss = adapter.getValue(adapter.getCount() - 1) - adapter.getValue(0);

                                getTheme().resolveAttribute(R.attr.color_positive_light, typedValue, true);
                                int posColorLight = ContextCompat.getColor(getApplicationContext(), typedValue.resourceId);
                                getTheme().resolveAttribute(R.attr.color_negative_light, typedValue, true);
                                int negColorLight = ContextCompat.getColor(getApplicationContext(), typedValue.resourceId);

                                // Set colors
                                if (percentageChange >= 0) {
                                    percentChange.setText(String.format("+$%.2f (%.2f%%)", profitLoss, percentageChange));

                                    getTheme().resolveAttribute(R.attr.color_positive, typedValue, true);
                                    color.set(ContextCompat.getColor(this, typedValue.resourceId));
                                    percentChange.setTextColor(color.get());
                                    percentChange.setBackgroundTintList(ColorStateList.valueOf(posColorLight));
                                    Drawable upArrow = percentChange.getContext().getResources().getDrawable(R.drawable.arrow_top_right);
                                    upArrow.setTint(color.get());
                                    percentChange.setCompoundDrawablesWithIntrinsicBounds(null, null, upArrow, null);
                                    sparkView.setLineColor(color.get());

                                } else {
                                    percentChange.setText(String.format("-$%.2f (%.2f%%)", Math.abs(profitLoss), percentageChange));

                                    getTheme().resolveAttribute(R.attr.color_negative, typedValue, true);
                                    color.set(ContextCompat.getColor(this, typedValue.resourceId));
                                    percentChange.setTextColor(color.get());
                                    percentChange.setBackgroundTintList(ColorStateList.valueOf(negColorLight));
                                    Drawable downArrow = percentChange.getContext().getResources().getDrawable(R.drawable.arrow_bottom_right);
                                    downArrow.setTint(color.get());
                                    percentChange.setCompoundDrawablesWithIntrinsicBounds(null, null, downArrow, null);
                                    sparkView.setLineColor(color.get());
                                }
                            }

                        });
                        Thread.sleep(60000 * 5);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.start();
        } else {

            // Fetch last open day's information
            ArrayList<Calendar> calendar = null;
            try {
                calendar = alpacaAPI.getCalendar(LocalDate.now().minusWeeks(1), LocalDate.now());
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }
            assert calendar != null;
            LocalDate lastOpenDate = LocalDate.parse(calendar.get(calendar.size() - 1).getDate());

            // Gather old portfolio data
            ArrayList<Double> history = new ArrayList<>();
            try {
                history = alpacaAPI.getPortfolioHistory(1, PortfolioPeriodUnit.DAY, PortfolioTimeFrame.FIVE_MINUTE, lastOpenDate, true).getEquity();

            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            // Fetch portfolio value
            String portVal = null;
            try {
                portVal = alpacaAPI.getAccount().getPortfolioValue();
                cash = alpacaAPI.getAccount().getCash();
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            // Set percent change and update colors
            if (portVal != null && cash != null) {

                // Add data to chart
                if (history.get(0) != null) {
                    for (Double i : history) {
                        if (i != null) {
                            adapter.addVal(Float.parseFloat(String.valueOf(i)));
                        }
                    }
                }

                float holdingVal = Float.parseFloat(portVal) - Float.parseFloat(cash);

                float oldVal = adapter.getValue(0) - holdingVal;
                float newVal = adapter.getValue(adapter.getCount() - 1) - holdingVal;
                float percentageChange = (newVal - oldVal) / oldVal * 100;
                float profitLoss = adapter.getValue(adapter.getCount() - 1) - adapter.getValue(0);

                getTheme().resolveAttribute(R.attr.color_positive_light, typedValue, true);
                int posColorLight = ContextCompat.getColor(getApplicationContext(), typedValue.resourceId);
                getTheme().resolveAttribute(R.attr.color_negative_light, typedValue, true);
                int negColorLight = ContextCompat.getColor(getApplicationContext(), typedValue.resourceId);

                if (adapter.getCount() != 0) {

                    double amount = Double.parseDouble(String.valueOf(adapter.getValue(adapter.getCount() - 1)));
                    DecimalFormat formatter = new DecimalFormat("#,###.00");
                    tickerView.setText("$" + formatter.format(amount));

                    if (adapter.getValue(adapter.getCount() - 1) >= adapter.baseline) {

                        percentChange.setText(String.format("+$%.2f (%.2f%%)", profitLoss, percentageChange));

                        getTheme().resolveAttribute(R.attr.color_positive, typedValue, true);
                        color.set(ContextCompat.getColor(this, typedValue.resourceId));
                        percentChange.setTextColor(color.get());
                        percentChange.setBackgroundTintList(ColorStateList.valueOf(posColorLight));
                        Drawable upArrow = percentChange.getContext().getResources().getDrawable(R.drawable.arrow_top_right);
                        upArrow.setTint(color.get());
                        percentChange.setCompoundDrawablesWithIntrinsicBounds(null, null, upArrow, null);
                        sparkView.setLineColor(color.get());
                        percentChange.setForegroundTintList(ColorStateList.valueOf(color.get()));
                    } else {
                        percentChange.setText(String.format("-$%.2f (%.2f%%)", Math.abs(profitLoss), percentageChange));

                        getTheme().resolveAttribute(R.attr.color_negative, typedValue, true);
                        color.set(ContextCompat.getColor(this, typedValue.resourceId));
                        percentChange.setTextColor(color.get());
                        percentChange.setBackgroundTintList(ColorStateList.valueOf(negColorLight));
                        Drawable downArrow = percentChange.getContext().getResources().getDrawable(R.drawable.arrow_bottom_right);
                        downArrow.setTint(color.get());
                        percentChange.setCompoundDrawablesWithIntrinsicBounds(null, null, downArrow, null);
                        sparkView.setLineColor(color.get());
                    }
                }
            }
        }

        // Fetch the Recycler View
        recyclerView = findViewById(R.id.recyclerStocks);
        recyclerOrders = findViewById(R.id.orders);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(LinearDividerDecoration.create(color, 10, 10, 10, 10, 10, LinearLayoutManager.VERTICAL, false, null));

        ColumnProvider col = () -> 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new GridMarginDecoration(0, col, GridLayoutManager.VERTICAL, false, null));

        recyclerOrders.setLayoutManager(new LinearLayoutManager(this));
        recyclerOrders.addItemDecoration(LinearMarginDecoration.create(0, LinearLayoutManager.VERTICAL, false, null));
        onRefresh();


//        // Set Recycle Adapter
//        recycleAdapter = new RecyclerViewAdapter(this, stocks);
//        recycleAdapter.setClickListener(this);
//        recyclerView.setAdapter(recycleAdapter);

        // Swipe to refresh recycler data
        swipeRefresh.setOnRefreshListener(() -> {

            onRefresh();
            swipeRefresh.setNestedScrollingEnabled(false);
        });

    }

    // If stock is tapped, switch to that stock
    @Override
    public void onItemClick(View view, int position) {

        ticker.set(recycleAdapter.getItem(position));
        Intent intentMain = new Intent(MainActivity.this, StockPage.class);
        MainActivity.this.startActivity(intentMain, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @Override
    public void switchColors(RecyclerViewAdapter.ViewHolder view, boolean pos) {

        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.color_positive, typedValue, true);
        int posColor = ContextCompat.getColor(getApplicationContext(), typedValue.resourceId);
        getTheme().resolveAttribute(R.attr.color_negative, typedValue, true);
        int negColor = ContextCompat.getColor(getApplicationContext(), typedValue.resourceId);
        getTheme().resolveAttribute(R.attr.color_positive_light, typedValue, true);
        int posColorLight = ContextCompat.getColor(getApplicationContext(), typedValue.resourceId);
        getTheme().resolveAttribute(R.attr.color_negative_light, typedValue, true);
        int negColorLight = ContextCompat.getColor(getApplicationContext(), typedValue.resourceId);

        if (pos) {
            view.percentChange.setTextColor(posColor);
            view.percentChange.setBackgroundTintList(ColorStateList.valueOf(posColorLight));

            Drawable downArrow = getDrawable(R.drawable.arrow_top_right);
            downArrow.setTint(posColor);
            view.percentChange.setCompoundDrawablesWithIntrinsicBounds(downArrow, null, null, null);
        } else {
            view.percentChange.setTextColor(negColor);
            view.percentChange.setBackgroundTintList(ColorStateList.valueOf(negColorLight));

            Drawable downArrow = getDrawable(R.drawable.arrow_bottom_right);
            downArrow.setTint(negColor);
            view.percentChange.setCompoundDrawablesWithIntrinsicBounds(downArrow, null, null, null);
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
            recycleAdapter = new RecyclerViewAdapter(this, stocks);
            recycleAdapter.setClickListener(this);
            runOnUiThread(() -> recyclerView.setAdapter(recycleAdapter));
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
            recycleAdapterOrders = new RecyclerViewAdapterOrders(this, orders);
            runOnUiThread(() -> recyclerOrders.setAdapter(recycleAdapterOrders));
            swipeRefresh.setRefreshing(false);
        });
        thread2.start();
    }

    @Override
    public void onClick(View v) {

        TypedValue outValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.themeName, outValue, true);
        if ("light".equals(outValue.string)) {
            Utils.changeToTheme(this, Utils.THEME_DARK);

        } else {
            Utils.changeToTheme(this, Utils.THEME_LIGHT);
        }

    }
}