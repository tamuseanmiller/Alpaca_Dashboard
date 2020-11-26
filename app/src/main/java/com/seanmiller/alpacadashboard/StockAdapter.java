package com.seanmiller.alpacadashboard;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.robinhood.spark.SparkAdapter;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.enums.BarsTimeFrame;
import net.jacobpeterson.alpaca.enums.PortfolioPeriodUnit;
import net.jacobpeterson.alpaca.enums.PortfolioTimeFrame;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.domain.alpaca.calendar.Calendar;
import net.jacobpeterson.polygon.PolygonAPI;
import net.jacobpeterson.polygon.rest.exception.PolygonAPIRequestException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Objects;
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

        PolygonAPI polygonAPI = new PolygonAPI(prefs.retrieveString("polygon_id", "NULL"));
        AlpacaAPI alpacaAPI = new AlpacaAPI(prefs.retrieveString("auth_token", "NULL"));
        AtomicReference<Float> lastClose = new AtomicReference<>((float) 0);
        AtomicReference<ArrayList<Double>> history = new AtomicReference<>(new ArrayList<>());

        // Fetch last open day's information
        ArrayList<Calendar> calendar = null;
        try {
            calendar = alpacaAPI.getCalendar(LocalDate.now().minusWeeks(1), LocalDate.now());
        } catch (AlpacaAPIRequestException e) {
            e.printStackTrace();
        }
        assert calendar != null;
        LocalDate lastOpenDate = LocalDate.parse(calendar.get(calendar.size() - 1).getDate());
        LocalTime lastOpenTime = LocalTime.parse(calendar.get(calendar.size() - 1).getOpen());

        // Set baseline values
        ArrayList<Calendar> finalCalendar = calendar;
        Thread thread = new Thread(() -> {

            // Get market status
            String marketStatus = null;
            try {
                marketStatus = polygonAPI.getMarketStatus().getMarket();

            } catch (PolygonAPIRequestException e) {
                e.printStackTrace();
            }

            // Check if market is open
            if (marketStatus.equals("open")) {

                // If you are rendering a stockpage
                if (!ticker.get().equals("NOTICKER")) {

                    // If you're looking at a single day
                    if (periodUnit == PortfolioPeriodUnit.DAY) {
                        try {
                            lastClose.set(polygonAPI.getPreviousClose(String.valueOf(ticker), false).getResults().get(0).getC().floatValue());
                        } catch (PolygonAPIRequestException e) {
                            e.printStackTrace();
                        }

                        baseline = lastClose.get();
                        yData.add(0, lastClose.get());

                    } else {
//                        try {
//                            lastClose.set(Objects.requireNonNull(alpacaAPI.getBars(BarsTimeFrame.ONE_DAY, ticker.get(), 2, ZonedDateTime.of(lastOpenDate, lastOpenTime, ZoneId.of("UTC-6")), null, null, null).get(ticker.get())).get(0).getC().floatValue());
//                        } catch (AlpacaAPIRequestException e) {
//                            e.printStackTrace();
//                        }
//                        baseline = lastClose.get();
//                        yData.add(lastClose.get());
                    }

                    // Else you're on equity stock
                } else if (periodUnit == PortfolioPeriodUnit.DAY) {

                    // Gather old portfolio data
                    history.set(new ArrayList<>());
                    try {
                        history.set(alpacaAPI.getPortfolioHistory(periodLength, periodUnit, timeFrame, LocalDate.parse(finalCalendar.get(finalCalendar.size() - 2).getDate()), false).getEquity());

                    } catch (AlpacaAPIRequestException e) {
                        e.printStackTrace();
                    }

                    double temp = history.get().get(history.get().size() - 1);
                    yData.add(0, (float) temp);
                    baseline = yData.get(0);

                }

            } else {

                // If you are rendering a stockpage
                if (!ticker.get().equals("NOTICKER")) {

                    if (periodUnit == PortfolioPeriodUnit.DAY) {

                        float temp = 0;

                        // Fetch Daily Open Close endpoint
//                      https://api.polygon.io/v1/open-close/AAPL/2020-10-14?apiKey=
                        JSONObject nodeHttpResponse = null;
                        try {
                            nodeHttpResponse = Unirest.get("https://api.polygon.io/v1/open-close/" + ticker + "/" + finalCalendar.get(finalCalendar.size() - 2).getDate() + "?apiKey=" + prefs.retrieveString("polygon_id", "NULL")).asJson().getBody().getObject();
                        } catch (UnirestException e) {
                            e.printStackTrace();
                        }

                        // Add the close value to temp
                        if (nodeHttpResponse != null) {
                            try {
                                temp = Float.parseFloat(nodeHttpResponse.get("close").toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        yData.add(0, temp);
                        baseline = yData.get(0);
                    }

                    // If it is a one day stock adapter for equity
                } else if (periodUnit == PortfolioPeriodUnit.DAY) {

                    history.set(new ArrayList<>());

                    try {
                        history.set(alpacaAPI.getPortfolioHistory(periodLength, periodUnit, timeFrame, LocalDate.parse(finalCalendar.get(finalCalendar.size() - 2).getDate()), false).getEquity());

                    } catch (AlpacaAPIRequestException e) {
                        e.printStackTrace();
                    }

                    double temp = getLastFilledIndex(history.get());
                    yData.add(0, (float) temp);
                    baseline = yData.get(0);

                }
            }

        });
        thread.start();
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
    private static Double getLastFilledIndex(ArrayList<Double> p) {
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public float getBaseLine() {
        return yData.get(0);
    }
}


