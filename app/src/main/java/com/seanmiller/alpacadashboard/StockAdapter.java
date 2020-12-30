package com.seanmiller.alpacadashboard;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.robinhood.spark.SparkAdapter;

import net.jacobpeterson.alpaca.enums.PortfolioPeriodUnit;
import net.jacobpeterson.alpaca.enums.PortfolioTimeFrame;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.polygon.rest.exception.PolygonAPIRequestException;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

public class StockAdapter extends SparkAdapter {

    private final Vector<Float> yData;
    private final Random random;
    private final AtomicReference<String> ticker;
    public float baseline;
    private float percent;
    private float profit;
    private SharedPreferencesManager prefs;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public StockAdapter(AtomicReference<String> ticker, int periodLength, PortfolioPeriodUnit periodUnit, PortfolioTimeFrame timeFrame, Context context) throws PolygonAPIRequestException, AlpacaAPIRequestException {
        random = new Random();
        yData = new Vector<>();
        this.ticker = ticker;
        baseline = 0;
        prefs = new SharedPreferencesManager(context);
    }

    public void push_front(float val) {
        yData.add(0, val);
    }

    public void setBaseline(float baseline) {
        this.baseline = baseline;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public void smoothGraph() {
        Vector<Float> temp = new Vector<>(yData);
        yData.clear();
        for (int i = 0; i < temp.size() - 2; i += 2) {
            yData.add((temp.get(i) + temp.get(i + 1)) / 2);
        }
        notifyDataSetChanged();
    }

    public void clearData() {
        yData.clear();
        notifyDataSetChanged();
    }

    public void randomize() {
        yData.add(random.nextFloat());
        notifyDataSetChanged();
    }

    public void addVal(float val) {
        yData.add(val);
        notifyDataSetChanged();
    }

    // finds last non-null value in arraylist
    public static Double getLastFilledIndex(ArrayList<Double> p) {
        for (int i = p.size() - 1; i >= 0; i--) {
            if (p.get(i) != null) {
                return p.get(i);
            }
        }
        return 0.0;
    }

    @Override
    public int getCount() {
        return yData.size();
    }

    @Override
    public Object getItem(int index) {
        return yData.get(index);
    }

    public float getValue(int index) {
        if (getCount() > 0) {
            return yData.get(index);
        } else {
            return 0;
        }
    }

//        @Override
//        public float getX(int index) {
//            return index;
//        }

//        @Override
//        public RectF getDataBounds() {
//            final int count = getCount();
//            final boolean hasBaseLine = hasBaseLine();
//
//            float minY = hasBaseLine ? getBaseLine() : Float.MAX_VALUE;
//            float maxY = hasBaseLine ? minY : -Float.MAX_VALUE;
//            float minX = Float.MAX_VALUE;
//            float maxX = -Float.MAX_VALUE;
//            for (int i = 0; i < count; i++) {
//                final float x = getX(i);
//                minX = Math.min(minX, x);
//                maxX = Math.max(maxX, x + 10);
//
//                final float y = getY(i);
//                minY = Math.min(minY, y);
//                maxY = Math.max(maxY, y);
//            }
//
//            maxX = 30;
//
//            // set values on the return object
//            return createRectF(minX, minY, maxX, maxY);
//        }

    @Override
    public float getY(int index) {
        return yData.get(index);
    }

    @Override
    public boolean hasBaseLine() {
        return true;
    }

    @Override
    public float getBaseLine() {
        return yData.get(0);
    }
}


