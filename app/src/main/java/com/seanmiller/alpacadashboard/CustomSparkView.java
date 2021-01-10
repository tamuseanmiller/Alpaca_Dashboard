package com.seanmiller.alpacadashboard;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.robinhood.spark.SparkView;

import java.text.DecimalFormat;

public class CustomSparkView extends SparkView {
    private Context context;

    public CustomSparkView(Context context) {
        super(context);
        this.context = context;
    }

    public CustomSparkView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSparkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomSparkView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onScrubEnded() {
        super.onScrubEnded();

        if (getAdapter().getCount() > 0) {
            double amount = Double.parseDouble(String.valueOf(getAdapter().getItem(getAdapter().getCount() - 1)));
            DecimalFormat formatter = new DecimalFormat("#,###.00");
            if (!StockPageActivity.isInFront)
                DashboardFragment.tickerView.setText("$" + formatter.format(amount));
            else {
                StockPageActivity.tickerViewStock.setText("$" + formatter.format(amount));
            }
        }

    }
}
