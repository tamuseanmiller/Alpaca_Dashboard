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

public class SplashActivity extends Activity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }
}
