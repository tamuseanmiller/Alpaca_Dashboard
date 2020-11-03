package com.seanmiller.alpacadashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.util.TimeUnit;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import androidx.annotation.RequiresApi;

/**
 * Created by AbhiAndroid
 */

public class SplashActivity extends Activity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        Utils.startTheme(this, new SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_DEFAULT));

        super.onCreate(savedInstanceState);
        new SharedPreferencesManager(this).storeString("auth",  "root");
        new SharedPreferencesManager(this).storeString("id", "root");
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();

        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Thread thread = new Thread(() -> {

//            MainActivity.dashboardFragment = new DashboardFragment();
//            MainActivity.searchFragment = new SearchFragment();
//            MainActivity.profileFragment = new ProfileFragment();
//            MainActivity.emergencyFragment = new EmergencyFragment();

//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Intent intent = new Intent(this, LoginActivity.class);
//            Intent intent = new Intent(this, MainActivity.class);
//            new SharedPreferencesManager(this).storeString("auth",  "root");
//            new SharedPreferencesManager(this).storeString("id", "root");
//            startActivity(intent);
//            finish();
        });thread.start();

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 0);*/
    }
}
