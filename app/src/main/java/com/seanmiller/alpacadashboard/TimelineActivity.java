package com.seanmiller.alpacadashboard;

import android.app.Activity;
import android.os.Bundle;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class TimelineActivity extends Activity {
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSwipeRefreshLayout = findViewById(R.id.refresh);
    }

}
