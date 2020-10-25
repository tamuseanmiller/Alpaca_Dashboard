package com.bedefined.alpaca_dashboard;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.robinhood.spark.SparkView;
import com.robinhood.spark.animation.LineSparkAnimator;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.StrictMode;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.enums.Direction;
import net.jacobpeterson.alpaca.enums.OrderStatus;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.domain.alpaca.order.Order;
import net.jacobpeterson.polygon.PolygonAPI;
import net.jacobpeterson.polygon.rest.exception.PolygonAPIRequestException;

import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import io.cabriole.decorator.LinearMarginDecoration;

import static com.bedefined.alpaca_dashboard.MainActivity.lastItem;
import static com.bedefined.alpaca_dashboard.MainActivity.viewPager;

@RequiresApi(api = Build.VERSION_CODES.O)
public class StockPageActivity extends AppCompatActivity implements RecyclerViewAdapterStocks.ItemClickListener {

    private SparkView sparkViewStock;
    private StockAdapter adapterStock;
    private RecyclerView recyclerOrdersStock;
    public TickerView tickerViewStock;
    private RecyclerViewAdapterOrders recycleAdapterOrders;
    private Button percentChangeStock;
    private Properties propsStock = new Properties();
    private ImageButton themeChangeStock;
    private ArrayList<Order> ordersStock;
    private RecyclerViewAdapterOrders recycleAdapterOrdersStock;
    private SwipeRefreshLayout swipeRefreshStock;
    private FloatingActionButton fab;
    private TextView numPos;

    private static final int SWIPE_THRESHOLD = 100;
    private float _downX;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Utils.startTheme(this, new SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_DEFAULT));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_page);

        // Initializations
        PolygonAPI polygonAPI = new PolygonAPI();
        AlpacaAPI alpacaAPI = new AlpacaAPI();

        // Set title
        TextView totalEquity = findViewById(R.id.stockTradedStock);
        totalEquity.setText(DashboardFragment.ticker.get());

        // Set percent change
        percentChangeStock = findViewById(R.id.percentChangeStock);

        // Set fab
        fab = findViewById(R.id.placeOrder);
        fab.setOnClickListener(v -> {
            PlaceOrderFragment dialogFrag = new PlaceOrderFragment();
            dialogFrag.setParentFab(fab);
            dialogFrag.show(getSupportFragmentManager(), dialogFrag.getTag());
        });

        Thread t3 = new Thread(() -> {

            // Set number of stocks textview
            numPos = findViewById(R.id.numberOfStocks);
            String numPosition = null;
            try {
                numPosition = alpacaAPI.getOpenPositionBySymbol(DashboardFragment.ticker.get()).getQty();
                String finalNumPosition = numPosition;
                runOnUiThread(() -> numPos.setText(finalNumPosition + " shares owned"));
            } catch (AlpacaAPIRequestException e) {
                runOnUiThread(() -> numPos.setText(0 + " shares owned"));
                e.printStackTrace();
            }
        });
        t3.start();

        // Set swipeRefresh
        swipeRefreshStock = findViewById(R.id.refreshStock);
        swipeRefreshStock.setTranslationZ(100);
        swipeRefreshStock.bringToFront();

//        swipeRefreshStock.setOnTouchListener(new onTouchSwipeListener(StockPageFragment.) {
//
//            @Override
//            public void onSwipeLeft() {
//                Toast.makeText(StockPageFragment., "left", Toast.LENGTH_SHORT).show();
//            }
//        });

        // Ticker information
        tickerViewStock = findViewById(R.id.tickerViewStock);
        tickerViewStock.setCharacterLists(TickerUtils.provideNumberList());

        Thread t1 = new Thread(() -> {

            // The sparkline graph itself
            sparkViewStock = findViewById(R.id.sparkviewStock);
            LineSparkAnimator lineSparkAnimator = new LineSparkAnimator();
            sparkViewStock.setSparkAnimator(lineSparkAnimator);
            try {
                adapterStock = new StockAdapter(DashboardFragment.ticker);
            } catch (PolygonAPIRequestException | AlpacaAPIRequestException e) {
                e.printStackTrace();
            }
            try {
                adapterStock.initializeStock();
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            // Add last value
            float lastPrice = 0;
            if (adapterStock.getCount() > 1) {
                lastPrice = adapterStock.getValue(adapterStock.getCount() - 1);
            }
            AtomicReference<Double> amount = new AtomicReference<>(Double.parseDouble(String.valueOf(lastPrice)));
            DecimalFormat formatter = new DecimalFormat("#,###.00");

            runOnUiThread(() -> {

                tickerViewStock.setText("$" + formatter.format(amount.get()));
                sparkViewStock.setAdapter(adapterStock);

                // Set Line and Ticker color
                float oldVal = adapterStock.getValue(0);
                float newVal = adapterStock.getValue(adapterStock.getCount() - 1);
                float percentageChange = (newVal - oldVal) / oldVal * 100;
                float profitLoss = adapterStock.getValue(adapterStock.getCount() - 1) - adapterStock.getValue(0);

                TypedValue typedValue = new TypedValue();
                getTheme().resolveAttribute(R.attr.color_negative, typedValue, true);
                int negColor = ContextCompat.getColor(this, typedValue.resourceId);
                getTheme().resolveAttribute(R.attr.color_positive, typedValue, true);
                int posColor = ContextCompat.getColor(this, typedValue.resourceId);

                getTheme().resolveAttribute(R.attr.color_positive_light, typedValue, true);
                int posColorLight = ContextCompat.getColor(this, typedValue.resourceId);
                getTheme().resolveAttribute(R.attr.color_negative_light, typedValue, true);
                int negColorLight = ContextCompat.getColor(this, typedValue.resourceId);

                if (adapterStock.getCount() != 0) {

                    if (adapterStock.getValue(adapterStock.getCount() - 1) >= adapterStock.baseline) {

                        percentChangeStock.setText(String.format("+$%.2f (%.2f%%)", profitLoss, percentageChange));

                        percentChangeStock.setTextColor(posColor);
                        percentChangeStock.setBackgroundTintList(ColorStateList.valueOf(posColorLight));
                        Drawable upArrow = percentChangeStock.getContext().getResources().getDrawable(R.drawable.arrow_top_right);
                        upArrow.setTint(posColor);
                        percentChangeStock.setCompoundDrawablesWithIntrinsicBounds(null, null, upArrow, null);
                        sparkViewStock.setLineColor(posColor);
                    } else {
                        percentChangeStock.setText(String.format("-$%.2f (%.2f%%)", Math.abs(profitLoss), percentageChange));

                        percentChangeStock.setTextColor(negColor);
                        percentChangeStock.setBackgroundTintList(ColorStateList.valueOf(negColorLight));
                        Drawable downArrow = percentChangeStock.getContext().getResources().getDrawable(R.drawable.arrow_bottom_right);
                        downArrow.setTint(negColor);
                        percentChangeStock.setCompoundDrawablesWithIntrinsicBounds(null, null, downArrow, null);
                        sparkViewStock.setLineColor(negColor);
                    }
                }

                // Scrub for chart
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

            });

        });
        t1.start();

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
            MainActivity vars = new MainActivity();
            vars.streamStockData(polygonAPI, DashboardFragment.ticker, tickerViewStock);

            Thread t2 = new Thread(() -> {

                while (true) {
                    float askingPrice = 0;
                    try {
                        askingPrice = polygonAPI.getLastQuote(DashboardFragment.ticker.get()).getLast().getAskprice().floatValue();

                    } catch (PolygonAPIRequestException e) {
                        e.printStackTrace();
                    }

                    adapterStock.addVal(askingPrice);

                    // Fetch colors
                    TypedValue typedValue = new TypedValue();
                    getTheme().resolveAttribute(R.attr.color_negative, typedValue, true);
                    int negColor = ContextCompat.getColor(this, typedValue.resourceId);
                    getTheme().resolveAttribute(R.attr.color_positive, typedValue, true);
                    int posColor = ContextCompat.getColor(this, typedValue.resourceId);
                    getTheme().resolveAttribute(R.attr.color_positive_light, typedValue, true);
                    int posColorLight = ContextCompat.getColor(this, typedValue.resourceId);
                    getTheme().resolveAttribute(R.attr.color_negative_light, typedValue, true);
                    int negColorLight = ContextCompat.getColor(this, typedValue.resourceId);

                    float oldVal = adapterStock.getValue(0);
                    float newVal = adapterStock.getValue(adapterStock.getCount() - 1);
                    float percentageChange = (newVal - oldVal) / oldVal * 100;
                    float profitLoss = adapterStock.getValue(adapterStock.getCount() - 1) - adapterStock.getValue(0);

                    runOnUiThread(() -> {

                        // Set colors
                        if (newVal >= oldVal) {
                            percentChangeStock.setText(String.format("+$%.2f (%.2f%%)", profitLoss, percentageChange));

                            percentChangeStock.setTextColor(posColor);
                            percentChangeStock.setBackgroundTintList(ColorStateList.valueOf(posColorLight));
                            Drawable upArrow = percentChangeStock.getContext().getResources().getDrawable(R.drawable.arrow_top_right);
                            upArrow.setTint(posColor);
                            percentChangeStock.setCompoundDrawablesWithIntrinsicBounds(null, null, upArrow, null);
                            sparkViewStock.setLineColor(posColor);

                        } else {
                            percentChangeStock.setText(String.format("-$%.2f (%.2f%%)", Math.abs(profitLoss), percentageChange));

                            percentChangeStock.setTextColor(negColor);
                            percentChangeStock.setBackgroundTintList(ColorStateList.valueOf(negColorLight));
                            Drawable downArrow = percentChangeStock.getContext().getResources().getDrawable(R.drawable.arrow_bottom_right);
                            downArrow.setTint(negColor);
                            percentChangeStock.setCompoundDrawablesWithIntrinsicBounds(null, null, downArrow, null);
                            sparkViewStock.setLineColor(negColor);
                        }

                    });


                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();
        }

        Thread thread = new Thread(() -> {

            // Fetch curent orders
            ordersStock = new ArrayList<>();
            ArrayList<Order> order = new ArrayList();
            try {
                ordersStock = alpacaAPI.getOrders(OrderStatus.CLOSED, 100, null, ZonedDateTime.now().plusDays(1), Direction.DESCENDING, false);
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < ordersStock.size(); i++) {
                if (ordersStock.get(i).getSymbol().equals(DashboardFragment.ticker.get())) {
                    order.add(ordersStock.get(i));
                }
            }

            recyclerOrdersStock = findViewById(R.id.ordersStock);
            recycleAdapterOrdersStock = new RecyclerViewAdapterOrders(this, order);

            runOnUiThread(() -> {
                recyclerOrdersStock.setLayoutManager(new LinearLayoutManager(this));
                recyclerOrdersStock.addItemDecoration(LinearMarginDecoration.create(0, LinearLayoutManager.VERTICAL, false, null));
                recyclerOrdersStock.setAdapter(recycleAdapterOrdersStock);
            });


        });
        thread.start();

        swipeRefreshStock.setOnRefreshListener(() -> {
            onRefresh();
            swipeRefreshStock.setNestedScrollingEnabled(false);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onRefresh() {

        swipeRefreshStock.setRefreshing(true);

        Thread thread2 = new Thread(() -> {

            AlpacaAPI alpacaAPI = new AlpacaAPI();

            // Fetch curent orders
            ordersStock = new ArrayList<>();
            try {
                ordersStock = alpacaAPI.getOrders(OrderStatus.CLOSED, 10, null, ZonedDateTime.now().plusDays(1), Direction.DESCENDING, false);
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            // Set number of stocks textview
            String numPosition = null;
            try {
                numPosition = alpacaAPI.getOpenPositionBySymbol(DashboardFragment.ticker.get()).getQty();
                String finalNumPosition = numPosition;
                runOnUiThread(() -> numPos.setText(finalNumPosition + " shares owned"));
            } catch (AlpacaAPIRequestException e) {
                runOnUiThread(() -> numPos.setText(0 + " shares owned"));
                e.printStackTrace();
            }

            runOnUiThread(() -> recycleAdapterOrdersStock.notifyDataSetChanged());
            swipeRefreshStock.setRefreshing(false);
        });
        thread2.start();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}