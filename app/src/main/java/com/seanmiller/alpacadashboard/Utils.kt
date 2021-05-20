package com.seanmiller.alpacadashboard

import android.app.Activity
import android.content.Intent

object Utils {
    private var sTheme = 0
    const val THEME_DEFAULT = 0
    const val THEME_LIGHT = 1
    const val THEME_DARK = 2

    /**
     * Set the theme of the Activity, and restart it by creating a new Activity
     * of the same type.
     */
    fun changeToTheme(activity: Activity, theme: Int) {
        sTheme = theme
        activity.finish()
        activity.startActivity(Intent(activity, activity.javaClass))
    }

    fun startTheme(activity: Activity, theme: Int) {
        when (theme) {
            THEME_DEFAULT -> activity.setTheme(R.style.Theme_Alpaca_Dashboard_Light)
            THEME_LIGHT -> activity.setTheme(R.style.Theme_Alpaca_Dashboard_Light)
            THEME_DARK -> activity.setTheme(R.style.Theme_Alpaca_Dashboard_Dark)
            else -> activity.setTheme(R.style.Theme_Alpaca_Dashboard_Light)
        }
    }

    /**
     * Set the theme of the activity, according to the configuration.
     */
    fun onActivityCreateSetTheme(activity: Activity) {
        when (sTheme) {
            THEME_DEFAULT -> activity.setTheme(R.style.Theme_Alpaca_Dashboard_Light)
            THEME_LIGHT -> activity.setTheme(R.style.Theme_Alpaca_Dashboard_Light)
            THEME_DARK -> activity.setTheme(R.style.Theme_Alpaca_Dashboard_Dark)
            else -> activity.setTheme(R.style.Theme_Alpaca_Dashboard_Light)
        }
    }
}