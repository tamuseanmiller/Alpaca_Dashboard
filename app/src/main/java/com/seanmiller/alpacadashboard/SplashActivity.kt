package com.seanmiller.alpacadashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()
    }
}