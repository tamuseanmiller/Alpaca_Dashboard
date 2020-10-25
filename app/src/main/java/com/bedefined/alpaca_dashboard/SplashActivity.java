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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Utils.startTheme(this, new SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_DEFAULT));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 1000);
    }
}
