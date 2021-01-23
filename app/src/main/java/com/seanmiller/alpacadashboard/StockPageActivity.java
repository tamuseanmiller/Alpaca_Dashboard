package com.seanmiller.alpacadashboard;

import android.content.res.ColorStateList;
import android.graphics.Insets;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import net.jacobpeterson.abstracts.websocket.exception.WebsocketException;
import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.enums.BarsTimeFrame;
import net.jacobpeterson.alpaca.enums.Direction;
import net.jacobpeterson.alpaca.enums.OrderStatus;
import net.jacobpeterson.alpaca.enums.PortfolioPeriodUnit;
import net.jacobpeterson.alpaca.enums.PortfolioTimeFrame;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.domain.alpaca.calendar.Calendar;
import net.jacobpeterson.domain.alpaca.marketdata.Bar;
import net.jacobpeterson.domain.alpaca.order.Order;
import net.jacobpeterson.domain.polygon.aggregates.Aggregate;
import net.jacobpeterson.domain.polygon.lastquote.LastQuote;
import net.jacobpeterson.domain.polygon.lastquote.LastQuoteResponse;
import net.jacobpeterson.domain.polygon.stockfinancials.StockFinancials;
import net.jacobpeterson.domain.polygon.stockfinancials.StockFinancialsResponse;
import net.jacobpeterson.domain.polygon.websocket.PolygonStreamMessage;
import net.jacobpeterson.domain.polygon.websocket.quote.QuoteMessage;
import net.jacobpeterson.polygon.PolygonAPI;
import net.jacobpeterson.polygon.enums.FinancialReportType;
import net.jacobpeterson.polygon.enums.FinancialSort;
import net.jacobpeterson.polygon.enums.Timespan;
import net.jacobpeterson.polygon.rest.exception.PolygonAPIRequestException;
import net.jacobpeterson.polygon.websocket.listener.PolygonStreamListenerAdapter;
import net.jacobpeterson.polygon.websocket.message.PolygonStreamMessageType;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import io.cabriole.decorator.LinearMarginDecoration;

import static com.seanmiller.alpacadashboard.DashboardFragment.ticker;

@RequiresApi(api = Build.VERSION_CODES.O)
public class StockPageActivity extends AppCompatActivity implements RecyclerViewAdapterStocks.ItemClickListener {

    private CustomSparkView sparkViewStock;
    private StockAdapter selectedAdapterStock;
    private RecyclerView recyclerOrdersStock;
    public static TickerView tickerViewStock;
    private RecyclerViewAdapterOrders recycleAdapterOrders;
    private Button percentChangeStock;
    private ArrayList<Order> ordersStock;
    private ArrayList<Order> order;
    private RecyclerViewAdapterOrders recycleAdapterOrdersStock;
    private SwipeRefreshLayout swipeRefreshStock;
    private FloatingActionButton fab;
    private TextView numPos;
    private MaterialButton oneDayStock;
    private MaterialButton oneWeekStock;
    private MaterialButton oneMonthStock;
    private MaterialButton threeMonthStock;
    private MaterialButton oneYearStock;
    private MaterialButton selectedButton;
    private AtomicInteger posOrNegColorLight;
    public static StockAdapter oneDayStockAdapter;
    private StockAdapter oneWeekStockAdapter;
    private StockAdapter oneMonthStockAdapter;
    private StockAdapter threeMonthStockAdapter;
    private StockAdapter oneYearStockAdapter;
    private SharedPreferencesManager prefs;

    public static boolean isInFront = false;

    @Override
    public void onResume() {
        super.onResume();
        isInFront = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isInFront = false;
    }

    // Streams ticker data from polygon
    public void streamStockData(PolygonAPI polygonAPI, AtomicReference<String> ticker, TickerView tickerV) {

        Thread thread = new Thread(() -> {

            try {
                polygonAPI.addPolygonStreamListener(new PolygonStreamListenerAdapter(String.valueOf(ticker), PolygonStreamMessageType.QUOTE) {

                    float askingPrice = 0;

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onStreamUpdate(PolygonStreamMessageType streamMessageType, PolygonStreamMessage streamMessage) {
                        askingPrice = ((QuoteMessage) streamMessage).getAp().floatValue();

                        double amount = Double.parseDouble(String.valueOf(askingPrice));
                        DecimalFormat formatter = new DecimalFormat("#,###.00");

                        // Render tickerView
                        runOnUiThread(() -> {
                            tickerV.setText("$" + formatter.format(amount));
                        });
                    }

                });
            } catch (WebsocketException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public int fetchHeight() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            WindowMetrics windowMetrics = getWindowManager().getCurrentWindowMetrics();
            Insets insets = windowMetrics.getWindowInsets()
                    .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
            return windowMetrics.getBounds().height() - insets.top - insets.bottom;

        } else {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.heightPixels;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Utils.startTheme(this, new SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_DEFAULT));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_page);
        prefs = new SharedPreferencesManager(this);

        // Vary size of spark view by height of screen size
        sparkViewStock = findViewById(R.id.sparkviewStock);
        int height = fetchHeight();
        MaterialCardView sparkCardStock = findViewById(R.id.sparkCardStock);
        sparkCardStock.setMinimumHeight((int) (height / 1.75));

        // Initializations
        PolygonAPI polygonAPI = new PolygonAPI(prefs.retrieveString("polygon_id", "NULL"));
        AlpacaAPI alpacaAPI = new AlpacaAPI(prefs.retrieveString("auth_token", "NULL"));

        // Set title
        TextView totalEquity = findViewById(R.id.stockTradedStock);
        totalEquity.setText(ticker.get());

        // Set percent change
        percentChangeStock = findViewById(R.id.percentChangeStock);

        // Set fab
        fab = findViewById(R.id.placeOrder);
        fab.setOnClickListener(v -> {
            PlaceOrderFragment dialogFrag = new PlaceOrderFragment();
            dialogFrag.setParentFab(fab);
            dialogFrag.show(getSupportFragmentManager(), dialogFrag.getTag());

        });

        // Bottom navigation
        /*BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        // Create viewpager for bottombar fragments
        viewPagerStocks = findViewById(R.id.viewPager);
        viewPagerStocks.setPagingEnabled(false);
        pagerAdapterStocks = new BottomBarAdapter(getSupportFragmentManager());

        bottomNavigation.bringToFront();
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        Thread t1 = new Thread(() -> {

            pagerAdapterStocks.addFragments(MainActivity.dashboardFragment);
            pagerAdapterStocks.addFragments(MainActivity.searchFragment);
            pagerAdapterStocks.addFragments(MainActivity.profileFragment);
            pagerAdapterStocks.addFragments(MainActivity.emergencyFragment);

            runOnUiThread(() -> {
                viewPagerStocks.setAdapter(pagerAdapterStocks);
                viewPagerStocks.setCurrentItem(0);
            });
        });
        t1.start();*/

        Thread t3 = new Thread(() -> {

            // Set number of stocks textview
            numPos = findViewById(R.id.numberOfStocks);
            String numPosition = null;
            try {
                numPosition = alpacaAPI.getOpenPositionBySymbol(ticker.get()).getQty();
                String finalNumPosition = numPosition;
                runOnUiThread(() -> numPos.setText(String.format("%s shares owned", finalNumPosition)));
            } catch (AlpacaAPIRequestException e) {
                runOnUiThread(() -> numPos.setText(R.string.zero_shares));
                e.printStackTrace();
            }
        });
        t3.start();

        // Set swipeRefresh
        swipeRefreshStock = findViewById(R.id.refreshStock);
        swipeRefreshStock.setTranslationZ(100);
        swipeRefreshStock.bringToFront();

        // Ticker information
        tickerViewStock = findViewById(R.id.tickerViewStock);
        tickerViewStock.setCharacterLists(TickerUtils.provideNumberList());

        // The sparkline graph itself
        // Set button group for timeframe
        oneDayStock = findViewById(R.id.oneDayStock);
        oneWeekStock = findViewById(R.id.oneWeekStock);
        oneMonthStock = findViewById(R.id.oneMonthStock);
        threeMonthStock = findViewById(R.id.threeMonthsStock);
        oneYearStock = findViewById(R.id.oneYearStock);
        oneYearStock = findViewById(R.id.oneYearStock);
        selectedButton = oneDayStock;

        // The sparkline graph data
        try {
            oneDayStockAdapter = new StockAdapter(ticker, 1, PortfolioPeriodUnit.DAY, PortfolioTimeFrame.ONE_MIN, this);
            oneWeekStockAdapter = new StockAdapter(ticker, 1, PortfolioPeriodUnit.WEEK, PortfolioTimeFrame.ONE_HOUR, this);
            oneMonthStockAdapter = new StockAdapter(ticker, 1, PortfolioPeriodUnit.MONTH, PortfolioTimeFrame.ONE_DAY, this);
            threeMonthStockAdapter = new StockAdapter(ticker, 3, PortfolioPeriodUnit.MONTH, PortfolioTimeFrame.ONE_DAY, this);
            oneYearStockAdapter = new StockAdapter(ticker, 1, PortfolioPeriodUnit.YEAR, PortfolioTimeFrame.ONE_DAY, this);
            selectedAdapterStock = oneDayStockAdapter;

        } catch (PolygonAPIRequestException | AlpacaAPIRequestException e) {
            e.printStackTrace();
        }
        sparkViewStock.setAdapter(selectedAdapterStock);

        Thread t2 = new Thread(() -> {

            // Initalize all graphs
            try {
                initializeDashboardValues(ZonedDateTime.now(), 5, Timespan.MINUTE, oneDayStockAdapter);
                initializeDashboardValues(ZonedDateTime.now().minusWeeks(1), 45, Timespan.MINUTE, oneWeekStockAdapter);
                initializeDashboardValues(ZonedDateTime.now().minusMonths(1), 2, Timespan.HOUR, oneMonthStockAdapter);
                initializeDashboardValues(ZonedDateTime.now().minusMonths(3), 1, Timespan.DAY, threeMonthStockAdapter);
                initializeDashboardValues(ZonedDateTime.now().minusYears(1), 5, Timespan.DAY, oneYearStockAdapter);
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            // Set colors on click, for toggle buttons
            TypedValue typedValue = new TypedValue();
            getTheme().resolveAttribute(R.attr.color_positive_light, typedValue, true);
            posOrNegColorLight = new AtomicInteger(ContextCompat.getColor(this, typedValue.resourceId));
            getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            int colorPrimary = ContextCompat.getColor(this, typedValue.resourceId);
            oneDayStock.setOnClickListener(v -> {
                selectedButton.setBackgroundTintList(ColorStateList.valueOf(colorPrimary));
                selectedButton = oneDayStock;
                selectedButton.setBackgroundTintList(ColorStateList.valueOf(posOrNegColorLight.get()));
                selectedAdapterStock = oneDayStockAdapter;
                sparkViewStock.setAdapter(selectedAdapterStock);
                setStockValues(selectedAdapterStock);
            });
            oneWeekStock.setOnClickListener(v -> {
                selectedButton.setBackgroundTintList(ColorStateList.valueOf(colorPrimary));
                selectedButton = oneWeekStock;
                selectedButton.setBackgroundTintList(ColorStateList.valueOf(posOrNegColorLight.get()));
                selectedAdapterStock = oneWeekStockAdapter;
                sparkViewStock.setAdapter(selectedAdapterStock);
                setStockValues(selectedAdapterStock);
            });
            oneMonthStock.setOnClickListener(v -> {
                selectedButton.setBackgroundTintList(ColorStateList.valueOf(colorPrimary));
                selectedButton = oneMonthStock;
                selectedButton.setBackgroundTintList(ColorStateList.valueOf(posOrNegColorLight.get()));
                selectedAdapterStock = oneMonthStockAdapter;
                sparkViewStock.setAdapter(selectedAdapterStock);
                setStockValues(selectedAdapterStock);
            });
            threeMonthStock.setOnClickListener(v -> {
                selectedButton.setBackgroundTintList(ColorStateList.valueOf(colorPrimary));
                selectedButton = threeMonthStock;
                selectedButton.setBackgroundTintList(ColorStateList.valueOf(posOrNegColorLight.get()));
                selectedAdapterStock = threeMonthStockAdapter;
                sparkViewStock.setAdapter(selectedAdapterStock);
                setStockValues(selectedAdapterStock);
            });
            oneYearStock.setOnClickListener(v -> {
                selectedButton.setBackgroundTintList(ColorStateList.valueOf(colorPrimary));
                selectedButton = oneYearStock;
                selectedButton.setBackgroundTintList(ColorStateList.valueOf(posOrNegColorLight.get()));
                selectedAdapterStock = oneYearStockAdapter;
                sparkViewStock.setAdapter(selectedAdapterStock);
                setStockValues(selectedAdapterStock);
            });

        });
        t2.start();

//            try {
//                selectedAdapterStock = new StockAdapter(ticker, 0, null, null);
//            } catch (PolygonAPIRequestException | AlpacaAPIRequestException e) {
//                e.printStackTrace();
//            }
//            try {
//                selectedAdapterStock.initializeStock();
//            } catch (AlpacaAPIRequestException e) {
//                e.printStackTrace();
//            }

        AtomicReference<Double> amount = new AtomicReference<>();
        DecimalFormat formatter = new DecimalFormat("#,###.00");

        sparkViewStock.setAdapter(selectedAdapterStock);

        // Scrub for chart
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryLight, typedValue, true);
        AtomicInteger color = new AtomicInteger(ContextCompat.getColor(this, typedValue.resourceId));

        // Scrub for chart
        sparkViewStock.setSparkAnimator(null);
        sparkViewStock.setBaseLineColor(color.get());
        sparkViewStock.setScrubListener(value -> {

            // Format to add commas
            if (value != null) {
                amount.set(Double.parseDouble(String.valueOf(value)));

                tickerViewStock.setText("$" + formatter.format(amount.get()));

            }
        });

        // Check if the market is open, stream to ticker
        String marketStatus = null;
        try {
            marketStatus = polygonAPI.getMarketStatus().getMarket();
        } catch (PolygonAPIRequestException e) {
            e.printStackTrace();
        }

        // Stream to chart
        if (marketStatus.equals("open")) {

            // Stream live data for a stock
            streamStockData(polygonAPI, ticker, tickerViewStock);

            Thread t4 = new Thread(() -> {

                while (true) {

                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    LastQuoteResponse askingPrice = null;
                    try {
                        askingPrice = polygonAPI.getLastQuote(ticker.get());

                    } catch (PolygonAPIRequestException e) {
                        e.printStackTrace();
                    }

                    float finalAskingPrice = 0;
                    if (askingPrice != null) {
                        finalAskingPrice = askingPrice.getLast().getAskprice().floatValue();
                    }

                    float finalAskingPrice1 = finalAskingPrice;
                    runOnUiThread(() -> selectedAdapterStock.addVal(finalAskingPrice1));
                    runOnUiThread(() -> setStockValues(selectedAdapterStock));
                }
            });
            t4.start();

        } else {

            Thread t5 = new Thread(() -> {
                float askingPrice = 0;
                try {
                    askingPrice = polygonAPI.getLastQuote(ticker.get()).getLast().getAskprice().floatValue();

                } catch (PolygonAPIRequestException e) {
                    e.printStackTrace();
                }

                float finalAskingPrice = askingPrice;
                runOnUiThread(() -> selectedAdapterStock.addVal(finalAskingPrice));
//                runOnUiThread(this::setStockValues);
            });
//            t5.start();
        }

        Thread thread = new Thread(() -> {

            // Fetch current orders
            ordersStock = new ArrayList<>();
            order = new ArrayList();
            try {
                ArrayList<String> symbols = new ArrayList<>();
                symbols.add(ticker.get());
                ordersStock = alpacaAPI.getOrders(OrderStatus.CLOSED, 10, null, ZonedDateTime.now().plusDays(1), Direction.DESCENDING, false, symbols);
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }
            order.clear();
            order.addAll(ordersStock);
            ordersStock.clear();
//            int i = 0;
//            while (i < ordersStock.size() && order.size() <= 10) {
//                if (ordersStock.get(i).getSymbol().equals(ticker.get())) {
//                    order.add(ordersStock.get(i));
//                }
//                i++;
//            }

            recyclerOrdersStock = findViewById(R.id.ordersStock);
            if (!order.isEmpty()) {
                recycleAdapterOrdersStock = new RecyclerViewAdapterOrders(this, order);
            }

            runOnUiThread(() -> {
                recyclerOrdersStock.setLayoutManager(new LinearLayoutManager(this));
                recyclerOrdersStock.addItemDecoration(LinearMarginDecoration.create(0, LinearLayoutManager.VERTICAL, false, null));
                recyclerOrdersStock.setAdapter(recycleAdapterOrdersStock);
            });

        });
        thread.start();

        // Refresh Listener
        swipeRefreshStock.setOnRefreshListener(() -> {
            onRefresh();
            swipeRefreshStock.setNestedScrollingEnabled(false);
        });

        Thread t1 = new Thread(() -> {

            // Set Statistics
            TextView dividend_yield = findViewById(R.id.dividend_yield);
            TextView assets = findViewById(R.id.assets);
            TextView debt = findViewById(R.id.debt);
            TextView debt_equity_ratio = findViewById(R.id.debt_equity);
            TextView earnings_per_share = findViewById(R.id.earnings_per_share);
            TextView gross_margin = findViewById(R.id.gross_margin);
            TextView gross_profit = findViewById(R.id.gross_profit);
            TextView market_cap = findViewById(R.id.market_cap);
            TextView net_cash_flow = findViewById(R.id.net_cash_flow);
            TextView price_earnings_ratio = findViewById(R.id.price_earnings_ratio);
            TextView price_earnings = findViewById(R.id.price_earnings);
            TextView revenues = findViewById(R.id.revenues);
            TextView financial_date = findViewById(R.id.financial_date);

            StockFinancialsResponse financials = null;
            try {
                financials = polygonAPI.getStockFinancials(ticker.get(), 1, FinancialReportType.Q, FinancialSort.REPORT_PERIOD_DESCENDING);
            } catch (PolygonAPIRequestException e) {
                e.printStackTrace();
            }

            StockFinancialsResponse finalFinancials = financials;
            runOnUiThread(() -> {

                if (finalFinancials != null && !finalFinancials.getResults().isEmpty()) {
                    DecimalFormat format = new DecimalFormat("$#,###.##");
                    StockFinancials result = finalFinancials.getResults().get(0);
                    financial_date.setText(String.format("Last Updated: %s", result.getUpdated()));
                    dividend_yield.setText(result.getDividendYield().toString());
                    assets.setText(format.format(result.getAssets().floatValue()));
                    debt.setText(format.format(result.getDebt().floatValue()));
                    debt_equity_ratio.setText(result.getDebtToEquityRatio().toString());
                    earnings_per_share.setText(format.format(result.getEarningsPerBasicShareUSD().floatValue()));
                    gross_margin.setText(format.format(result.getGrossMargin().floatValue()));
                    gross_profit.setText(format.format(result.getGrossProfit().floatValue()));
                    market_cap.setText(format.format(result.getMarketCapitalization().floatValue()));
                    net_cash_flow.setText(format.format(result.getNetCashFlow().floatValue()));
                    price_earnings_ratio.setText(result.getPriceToEarningsRatio().toString());
                    price_earnings.setText(format.format(result.getPriceEarnings().floatValue()));
                    revenues.setText(format.format(result.getRevenuesUSD().floatValue()));
                }

            });

        });
        t1.start();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onRefresh() {

        swipeRefreshStock.setRefreshing(true);

        Thread thread2 = new Thread(() -> {

            AlpacaAPI alpacaAPI = new AlpacaAPI(prefs.retrieveString("auth_token", "NULL"));

            // Fetch current orders
            ordersStock = new ArrayList<>();
            try {
                ArrayList<String> symbols = new ArrayList<>();
                symbols.add(ticker.get());
                ordersStock = alpacaAPI.getOrders(OrderStatus.CLOSED, 10, null, ZonedDateTime.now().plusDays(1), Direction.DESCENDING, false, symbols);
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }
            order.clear();
            order.addAll(ordersStock);
            ordersStock.clear();

            // Set number of stocks textview
            String numPosition = null;
            try {
                numPosition = alpacaAPI.getOpenPositionBySymbol(ticker.get()).getQty();
                String finalNumPosition = numPosition;
                runOnUiThread(() -> numPos.setText(String.format("%s shares owned", finalNumPosition)));
            } catch (AlpacaAPIRequestException e) {
                runOnUiThread(() -> numPos.setText("0 shares owned"));
                e.printStackTrace();
            }

            runOnUiThread(() -> {
                recyclerOrdersStock.setLayoutManager(new LinearLayoutManager(this));
                recyclerOrdersStock.addItemDecoration(LinearMarginDecoration.create(0, LinearLayoutManager.VERTICAL, false, null));
                recyclerOrdersStock.setAdapter(recycleAdapterOrdersStock);
//                recycleAdapterOrdersStock.notifyDataSetChanged();
            });

            swipeRefreshStock.setRefreshing(false);
        });
        thread2.start();
    }

    public void initializeDashboardValues(ZonedDateTime datetime, int multiplier, Timespan timeFrame, StockAdapter selectedAdapterInitial) throws AlpacaAPIRequestException {

        // Requests bars and adds to graph
        PolygonAPI polygonAPI = new PolygonAPI(prefs.retrieveString("polygon_id", "NULL"));
        AlpacaAPI alpacaAPI = new AlpacaAPI(prefs.retrieveString("auth_token", "NULL"));

        Thread thread = new Thread(() -> {

            // Fetch last open day's information
            ArrayList<Calendar> calendarInitial = null;
            try {
                calendarInitial = alpacaAPI.getCalendar(LocalDate.now().minusWeeks(1), LocalDate.now());
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }
            // Assign last open datetime and check for if it is the morning of
            assert calendarInitial != null;
            LocalDate lastOpenDate = LocalDate.parse(calendarInitial.get(calendarInitial.size() - 2).getDate());
            LocalTime oldTime = LocalTime.of(Integer.parseInt(calendarInitial.get(calendarInitial.size() - 2).getOpen().substring(0, 2)),
                    Integer.parseInt(calendarInitial.get(calendarInitial.size() - 2).getOpen().substring(3, 5)));

            // Switch given open datetime from US/Eastern to System Default
            ZonedDateTime zonedDateTime = ZonedDateTime.of(lastOpenDate, oldTime, ZoneId.of("US/Eastern"));
            ZonedDateTime standardDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault());

            // Check if it is the morning of
            if (standardDateTime.toLocalTime().compareTo(LocalTime.now()) > 0) {
                lastOpenDate = LocalDate.parse(calendarInitial.get(calendarInitial.size() - 3).getDate());
            }

            AtomicReference<Float> lastClose = new AtomicReference<>((float) 0);
            Map<String, ArrayList<Bar>> bars = null;
            ArrayList<Aggregate> aggs = null;

            // Check if market is open
            String marketStatus = null;
            try {
                marketStatus = polygonAPI.getMarketStatus().getMarket();
            } catch (PolygonAPIRequestException e) {
                e.printStackTrace();
            }

            assert marketStatus != null;
            if (marketStatus.equals("open")) {

                // If you're looking at a single day, set baseline value
                if (datetime.toLocalDate().equals(ZonedDateTime.now().toLocalDate())) {
                    try {
                        lastClose.set(polygonAPI.getPreviousClose(String.valueOf(ticker),
                                false).getResults().get(0).getC().floatValue());

                    } catch (PolygonAPIRequestException e) {
                        e.printStackTrace();
                    }

                    selectedAdapterInitial.setBaseline(lastClose.get());
                    selectedAdapterInitial.push_front(lastClose.get());
//                    oneDayStockAdapter.notifyDataSetChanged();

                }

                if (datetime.toLocalDate().toString().equals(ZonedDateTime.now().toLocalDate().toString())) {

                    // Fetch todays bars
                    try {
                        aggs = polygonAPI.getAggregates(ticker.get(), multiplier, timeFrame, datetime.toLocalDate(), LocalDate.now(), false).getResults();

                    } catch (PolygonAPIRequestException e) {
                        e.printStackTrace();
                    }

                } else {

                    // Fetch todays bars
                    try {
                        aggs = polygonAPI.getAggregates(ticker.get(), multiplier, timeFrame, datetime.toLocalDate(), LocalDate.now(), false).getResults();

                    } catch (PolygonAPIRequestException e) {
                        e.printStackTrace();
                    }
                }


            } else {

                // If it's a day adapter and set baseline value
                if (datetime.toLocalDate().equals(ZonedDateTime.now().toLocalDate())) {

                    float temp = 0;

                    // Fetch Daily Open Close endpoint
//                  https://api.polygon.io/v1/open-close/AAPL/2020-10-14?apiKey=
                    JSONObject nodeHttpResponse = null;
                    try {
                        nodeHttpResponse = Unirest.get("https://api.polygon.io/v1/open-close/" +
                                ticker + "/" + lastOpenDate + "?apiKey=" +
                                prefs.retrieveString("polygon_id", "NULL"))
                                .asJson().getBody().getObject();

                    } catch (UnirestException e) {
                        e.printStackTrace();
                    }

                    // Add the close value to temp
                    if (nodeHttpResponse != null) {
                        try {
                            temp = Float.parseFloat(nodeHttpResponse.get("close").toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    selectedAdapterInitial.push_front(temp);
                    selectedAdapterInitial.setBaseline(temp);
                    oneDayStockAdapter.notifyDataSetChanged();

                }

                if (datetime.toLocalDate().toString().equals(ZonedDateTime.now().toLocalDate().toString())) {

                    // Fetch last open day's information
                    ArrayList<Calendar> calendar = null;
                    try {
                        calendar = alpacaAPI.getCalendar(LocalDate.now().minusWeeks(2), LocalDate.now());
                    } catch (AlpacaAPIRequestException e) {
                        e.printStackTrace();
                    }

                    // Assign last open datetime and check for if it is the morning of
                    assert calendar != null;
                    LocalDate lastOpenDate2 = LocalDate.parse(calendar.get(calendar.size() - 1).getDate());
                    oldTime = LocalTime.of(Integer.parseInt(calendar.get(calendar.size() - 1).getOpen().substring(0, 2)),
                            Integer.parseInt(calendar.get(calendar.size() - 1).getOpen().substring(3, 5)));

                    // Switch given open datetime from US/Eastern to System Default
                    String time = calendar.get(calendar.size() - 1).getOpen();
                    LocalTime lastOpenTimeStart = LocalTime.of(Integer.parseInt(time.substring(0, 2)), Integer.parseInt(time.substring(3)));
                    if (standardDateTime.toLocalTime().compareTo(LocalTime.now()) > 0) {
                        time = calendar.get(calendar.size() - 2).getOpen();
                        lastOpenTimeStart = LocalTime.of(Integer.parseInt(time.substring(0, 2)), Integer.parseInt(time.substring(3)));
                        lastOpenDate2 = LocalDate.parse(calendar.get(calendar.size() - 2).getDate());
                    }

                    try {
                        bars = alpacaAPI.getBars(BarsTimeFrame.FIVE_MINUTE, ticker.get(), 1000,
                                ZonedDateTime.of(lastOpenDate2, lastOpenTimeStart, ZoneId.of("US/Eastern")), null, null, null);

//                        aggs = polygonAPI.getAggregates(ticker.get(), multiplier, timeFrame, datetime.toLocalDate().minusDays(1), LocalDate.now(), false).getResults();

                    } catch (AlpacaAPIRequestException e) {
                        e.printStackTrace();
                    }

                } else {

                    try {
                        aggs = polygonAPI.getAggregates(ticker.get(),
                                multiplier,
                                timeFrame,
                                datetime.toLocalDate(),
                                LocalDate.now(),
                                false).getResults();
//                        bars = alpacaAPI.getBars(timeFrame, ticker.get(), 1000, datetime, null, null, ZonedDateTime.now());

                    } catch (PolygonAPIRequestException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (bars != null) {
                for (Bar bar : Objects.requireNonNull(bars.get(ticker.get()))) {
                    runOnUiThread(() -> selectedAdapterInitial.addVal(bar.getC().floatValue()));
                }
            }

            if (aggs != null) {
                for (Aggregate agg : aggs) {
                    runOnUiThread(() -> selectedAdapterInitial.addVal(agg.getC().floatValue()));
                }
            }

            if (datetime.getYear() < ZonedDateTime.now().getYear()) {
                runOnUiThread(selectedAdapterInitial::smoothGraph);
            }

            // Get Last value, check for weekend
            LastQuoteResponse askingPrice = null;
            float weekendLast = 0;
            try {
                if (calendarInitial.get(calendarInitial.size() - 1).getDate().equals(LocalDate.now().toString())) {
                    askingPrice = polygonAPI.getLastQuote(ticker.get());
                } else {
                    aggs = polygonAPI.getAggregates(ticker.get(), 1, Timespan.DAY, lastOpenDate, lastOpenDate.plusDays(1), false).getResults();
                    weekendLast = aggs.get(aggs.size() - 1).getC().floatValue();
                }

            } catch (PolygonAPIRequestException e) {
                e.printStackTrace();
            }

            float finalAskingPrice = 0;
            if (askingPrice != null) {
                finalAskingPrice = askingPrice.getLast().getAskprice().floatValue();
            } else if (weekendLast != 0) {
                finalAskingPrice = weekendLast;
            }

            float finalAskingPrice1 = finalAskingPrice;
            runOnUiThread(() -> selectedAdapterInitial.addVal(finalAskingPrice1));

            runOnUiThread(() -> oneDayStock.callOnClick()); // Set here to allow ample time for instantiation
        });
        thread.start();
    }

    public void setDashboardColors(boolean pos, float profitLoss, float percentageChange) {

        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.color_positive_light, typedValue, true);
        AtomicInteger posColorLight = new AtomicInteger(ContextCompat.getColor(this, typedValue.resourceId));
        getTheme().resolveAttribute(R.attr.color_negative_light, typedValue, true);
        AtomicInteger negColorLight = new AtomicInteger(ContextCompat.getColor(this, typedValue.resourceId));

        if (pos) {
            percentChangeStock.setText(String.format("+$%.2f (%.2f%%)", profitLoss, percentageChange));

            getTheme().resolveAttribute(R.attr.color_positive, typedValue, true);
            int color = ContextCompat.getColor(this, typedValue.resourceId);
            percentChangeStock.setTextColor(color);
            percentChangeStock.setBackgroundTintList(ColorStateList.valueOf(posColorLight.get()));
            Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.arrow_top_right);
            upArrow.setTint(color);
            percentChangeStock.setCompoundDrawablesWithIntrinsicBounds(null, null, upArrow, null);
            sparkViewStock.setLineColor(color);
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(posColorLight.get()));
            oneDayStock.setTextColor(color);
            oneWeekStock.setTextColor(color);
            oneMonthStock.setTextColor(color);
            threeMonthStock.setTextColor(color);
            oneYearStock.setTextColor(color);
            oneDayStock.setRippleColor(ColorStateList.valueOf(color));
            oneWeekStock.setRippleColor(ColorStateList.valueOf(color));
            oneMonthStock.setRippleColor(ColorStateList.valueOf(color));
            threeMonthStock.setRippleColor(ColorStateList.valueOf(color));
            oneYearStock.setRippleColor(ColorStateList.valueOf(color));

            getTheme().resolveAttribute(R.attr.color_positive_light, typedValue, true);

        } else {
            percentChangeStock.setText(String.format("-$%.2f (%.2f%%)", Math.abs(profitLoss), percentageChange));

            getTheme().resolveAttribute(R.attr.color_negative, typedValue, true);
            int color = ContextCompat.getColor(this, typedValue.resourceId);
            percentChangeStock.setTextColor(color);
            percentChangeStock.setBackgroundTintList(ColorStateList.valueOf(negColorLight.get()));
            Drawable downArrow = ContextCompat.getDrawable(this, R.drawable.arrow_bottom_right);
            downArrow.setTint(color);
            percentChangeStock.setCompoundDrawablesWithIntrinsicBounds(null, null, downArrow, null);
            sparkViewStock.setLineColor(color);
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(negColorLight.get()));
            oneDayStock.setTextColor(color);
            oneWeekStock.setTextColor(color);
            oneMonthStock.setTextColor(color);
            threeMonthStock.setTextColor(color);
            oneYearStock.setTextColor(color);
            oneDayStock.setRippleColor(ColorStateList.valueOf(color));
            oneWeekStock.setRippleColor(ColorStateList.valueOf(color));
            oneMonthStock.setRippleColor(ColorStateList.valueOf(color));
            threeMonthStock.setRippleColor(ColorStateList.valueOf(color));
            oneYearStock.setRippleColor(ColorStateList.valueOf(color));

            getTheme().resolveAttribute(R.attr.color_negative_light, typedValue, true);
        }
        posOrNegColorLight.set(ContextCompat.getColor(this, typedValue.resourceId));
    }

    public void setStockValues(StockAdapter selectedAdapter) {

        // Set Line and Ticker Info
        float oldVal = selectedAdapter.getValue(0);
        float newVal = selectedAdapter.getValue(selectedAdapter.getCount() - 1);
        float percentageChange = (newVal - oldVal) / oldVal * 100;
        float profitLoss = selectedAdapter.getValue(selectedAdapter.getCount() - 1) - selectedAdapter.getValue(0);

        if (selectedAdapterStock.getCount() != 0) {

            // If positive
            if (profitLoss >= 0) {
                setDashboardColors(true, profitLoss, percentageChange);

            } else {
                setDashboardColors(false, profitLoss, percentageChange);
            }
        }

        DecimalFormat formatter = new DecimalFormat("$#,###.00");
        double amount = Double.parseDouble(String.valueOf(newVal));
        tickerViewStock.setText(formatter.format(amount));
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}