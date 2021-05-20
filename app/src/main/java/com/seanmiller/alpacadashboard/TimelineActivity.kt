package com.seanmiller.alpacadashboard

import android.app.Activity
import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class TimelineActivity : Activity() {
    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSwipeRefreshLayout = findViewById(R.id.refresh)
    }
}