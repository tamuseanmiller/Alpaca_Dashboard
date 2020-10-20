package com.bedefined.alpaca_dashboard;

import android.content.ClipData;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.robinhood.spark.SparkAdapter;
import com.robinhood.spark.SparkView;
import com.robinhood.spark.animation.LineSparkAnimator;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.enums.Direction;
import net.jacobpeterson.alpaca.enums.OrderStatus;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.domain.alpaca.order.Order;
import net.jacobpeterson.domain.polygon.websocket.PolygonStreamMessage;
import net.jacobpeterson.domain.polygon.websocket.quote.QuoteMessage;
import net.jacobpeterson.polygon.PolygonAPI;
import net.jacobpeterson.polygon.rest.exception.PolygonAPIRequestException;
import net.jacobpeterson.polygon.websocket.listener.PolygonStreamListenerAdapter;
import net.jacobpeterson.polygon.websocket.message.PolygonStreamMessageType;

import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

import io.cabriole.decorator.ColumnProvider;
import io.cabriole.decorator.GridMarginDecoration;
import io.cabriole.decorator.LinearDividerDecoration;

@RequiresApi(api = Build.VERSION_CODES.O)
public class StockPage extends AppCompatActivity implements RecyclerViewAdapterStocks.ItemClickListener {

    private TickerView tickerView;
    private StockAdapter adapter;
    private SparkView sparkView;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterStocks recycleAdapter;
    private ImageView arrowUp;
    private ImageView arrowDown;
    Properties props = new Properties();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_Alpaca_Dashboard_Light);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_page);

        // Initializations
        PolygonAPI polygonAPI = new PolygonAPI();
        AlpacaAPI alpacaAPI = new AlpacaAPI();

        // Name of stock traded
        TextView nameOfStock = findViewById(R.id.stockTraded);
        nameOfStock.setText(MainActivity.ticker.get());

        // Set arrows
        arrowUp = findViewById(R.id.arrowUpStocks);
        arrowDown = findViewById(R.id.arrowDownStocks);

        // Ticker information
        tickerView = findViewById(R.id.tickerView);
        tickerView.setCharacterLists(TickerUtils.provideNumberList());

        // The sparkline graph itself
        sparkView = findViewById(R.id.sparkview);
        LineSparkAnimator lineSparkAnimator = new LineSparkAnimator();
        sparkView.setSparkAnimator(lineSparkAnimator);
        try {
            adapter = new StockAdapter(MainActivity.ticker);
        } catch (PolygonAPIRequestException | AlpacaAPIRequestException e) {
            e.printStackTrace();
        }
        try {
            adapter.initializeStock();
        } catch (AlpacaAPIRequestException e) {
            e.printStackTrace();
        }

        // Add last value
        float lastPrice = 0;
        if (adapter.getCount() > 1) {
            lastPrice = adapter.getValue(adapter.getCount() - 1);
        }
        double amount = Double.parseDouble(String.valueOf(lastPrice));
        DecimalFormat formatter = new DecimalFormat("#,###.00");

        tickerView.setText("$" + formatter.format(amount));
        sparkView.setAdapter(adapter);

        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.color_negative, typedValue, true);
        int negColor = ContextCompat.getColor(getApplicationContext(), typedValue.resourceId);
        getTheme().resolveAttribute(R.attr.color_positive, typedValue, true);
        int posColor = ContextCompat.getColor(getApplicationContext(), typedValue.resourceId);

        // Set Line and Ticker color
        if (adapter.baseline < amount) {
            tickerView.setTextColor(posColor);
            sparkView.setLineColor(posColor);
            arrowUp.setVisibility(View.INVISIBLE);
            arrowDown.setVisibility(View.VISIBLE);

        } else {
            tickerView.setTextColor(negColor);
            sparkView.setLineColor(negColor);
            arrowUp.setVisibility(View.VISIBLE);
            arrowDown.setVisibility(View.INVISIBLE);
        }

        // Scrub for chart
        sparkView.setSparkAnimator(null);
        sparkView.setBaseLineColor(getColor(R.color.colorAccentTransparent));

        sparkView.setScrubListener(value -> {
            if (value != null) {
                tickerView.setText("$" + getString(R.string.scrub_format, value));
            }
        });

        // Check if the market is open
        String marketStatus = null;
        try {
            marketStatus = polygonAPI.getMarketStatus().getMarket();
        } catch (PolygonAPIRequestException e) {
            e.printStackTrace();
        }

        if (marketStatus.equals("open")) {

            // Stream live data for a stock
            MainActivity vars = new MainActivity();
            vars.streamStockData(polygonAPI, MainActivity.ticker, adapter, sparkView, tickerView);
        }

        // Fetch orders of stock
        ArrayList<Order> alpacaOrders = new ArrayList<>();
        ArrayList<String> orders = new ArrayList<>();
        try {
            alpacaOrders = alpacaAPI.getOrders(OrderStatus.ALL, 20, null, ZonedDateTime.now(), Direction.ASCENDING, true);
        } catch (AlpacaAPIRequestException e) {
            e.printStackTrace();
        }

        for (Order i : alpacaOrders) {
            orders.add(i.getSide());
        }

        // Fetch the Recycler View
        recyclerView = findViewById(R.id.recyclerStockPage);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(LinearDividerDecoration.create(getColor(R.color.colorAccentTransparent), 10, 10, 10, 10, 10, LinearLayoutManager.VERTICAL, false, null));
        ColumnProvider col = () -> 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new GridMarginDecoration(0, col, GridLayoutManager.VERTICAL, false, null));

        // Set Recycle Adapter
        recycleAdapter = new RecyclerViewAdapterStocks(this, orders);
        recycleAdapter.setClickListener(this);
        recyclerView.setAdapter(recycleAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}