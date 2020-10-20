package com.bedefined.alpaca_dashboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.enums.Direction;
import net.jacobpeterson.alpaca.enums.OrderStatus;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.domain.alpaca.order.Order;
import net.jacobpeterson.domain.alpaca.position.Position;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import io.cabriole.decorator.ColumnProvider;
import io.cabriole.decorator.GridMarginDecoration;
import io.cabriole.decorator.LinearMarginDecoration;

/**
 * Created by AbhiAndroid
 */

public class SplashActivity extends Activity {

    Handler handler;
    private RecyclerView recyclerOrders;
    private RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utils.onActivityCreateSetTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

//        recyclerView = findViewById(R.id.recyclerStocksSplash);
//
//        ColumnProvider col = () -> 3;
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        recyclerView.addItemDecoration(new GridMarginDecoration(0, col, GridLayoutManager.VERTICAL, false, null));
//        onRefresh();

        handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 100);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onRefresh() {

        ArrayList<String> stocks = new ArrayList<>();
        stocks.add(" ");
        stocks.add(" ");
        stocks.add(" ");
        stocks.add(" ");
        stocks.add(" ");
        stocks.add(" ");
        stocks.add(" ");

        // Set Recycle Adapter for positions
        RecyclerViewAdapter recycleAdapter = new RecyclerViewAdapter(this, stocks);
        recyclerView.setAdapter(recycleAdapter);
    }
}
