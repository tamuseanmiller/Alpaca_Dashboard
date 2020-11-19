package com.seanmiller.alpacadashboard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

public class Utils {
    private static int sTheme;

    public final static int THEME_DEFAULT = 0;
    public final static int THEME_LIGHT = 1;
    public final static int THEME_DARK = 2;

    /**
     * Set the theme of the Activity, and restart it by creating a new Activity
     * of the same type.
     */
    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();

        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public static void startTheme(Activity activity, int theme) {
        switch (theme) {
            default:
            case THEME_DEFAULT:
                activity.setTheme(R.style.Theme_Alpaca_Dashboard_Light);
                break;
            case THEME_LIGHT:
                activity.setTheme(R.style.Theme_Alpaca_Dashboard_Light);
                break;
            case THEME_DARK:
                activity.setTheme(R.style.Theme_Alpaca_Dashboard_Dark);
                break;
        }
    }

    /**
     * Set the theme of the activity, according to the configuration.
     */
    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            default:
            case THEME_DEFAULT:
                activity.setTheme(R.style.Theme_Alpaca_Dashboard_Light);
                
                break;
            case THEME_LIGHT:
                activity.setTheme(R.style.Theme_Alpaca_Dashboard_Light);
                break;
            case THEME_DARK:
                activity.setTheme(R.style.Theme_Alpaca_Dashboard_Dark);
                break;
        }
    }
}
