package com.seanmiller.alpacadashboard;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.robinhood.spark.SparkAdapter;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.enums.BarsTimeFrame;
import net.jacobpeterson.alpaca.enums.PortfolioPeriodUnit;
import net.jacobpeterson.alpaca.enums.PortfolioTimeFrame;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.domain.alpaca.bar.Bar;
import net.jacobpeterson.domain.alpaca.calendar.Calendar;
import net.jacobpeterson.polygon.PolygonAPI;
import net.jacobpeterson.polygon.rest.exception.PolygonAPIRequestException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Map;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public StockAdapter(AtomicReference<String> ticker, int periodLength, PortfolioPeriodUnit periodUnit, PortfolioTimeFrame timeFrame) throws PolygonAPIRequestException, AlpacaAPIRequestException {
        random = new Random();
        yData = new Vector<>();
        this.ticker = ticker;
        baseline = 0;

        PolygonAPI polygonAPI = new PolygonAPI();
        AlpacaAPI alpacaAPI = new AlpacaAPI();
        AtomicReference<Float> lastClose = new AtomicReference<>((float) 0);
        AtomicReference<ArrayList<Double>> history = new AtomicReference<>(new ArrayList<>());

        // Set baseline values
        Thread thread = new Thread(() -> {

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

            // If you are rendering a stockpage
            if (!ticker.get().equals("NOTICKER")) {

                if (periodUnit == PortfolioPeriodUnit.DAY) {
                    try {
                        lastClose.set(polygonAPI.getPreviousClose(String.valueOf(ticker), false).getResults().get(0).getC().floatValue());
                    } catch (PolygonAPIRequestException e) {
                        e.printStackTrace();
                    }

                    baseline = lastClose.get();
                    yData.add(lastClose.get());

                } /*else {
                    try {
                        lastClose.set(Objects.requireNonNull(alpacaAPI.getBars(BarsTimeFrame.ONE_DAY, ticker.get(), 2, ZonedDateTime.of(lastOpenDate, lastOpenTime, ZoneId.of("UTC-6")), null, null, null).get(ticker.get())).get(0).getC().floatValue());
                    } catch (AlpacaAPIRequestException e) {
                        e.printStackTrace();
                    }
                    baseline = lastClose.get();
                    yData.add(lastClose.get());
                }*/

            // Else you're on equity stock
            } else if (periodUnit == PortfolioPeriodUnit.DAY) {

                // Gather old portfolio data
                history.set(new ArrayList<>());
                try {

                    // Checks to see if market is closed for today, edge case
                    if (lastOpenDate.equals(LocalDate.now())) {
                        history.set(alpacaAPI.getPortfolioHistory(periodLength, periodUnit, timeFrame, LocalDate.parse(calendar.get(calendar.size() - 2).getDate()), false).getEquity());
                    } else {
                        history.set(alpacaAPI.getPortfolioHistory(periodLength, periodUnit, timeFrame, lastOpenDate, false).getEquity());
                    }

                } catch (AlpacaAPIRequestException e) {
                    e.printStackTrace();
                }

                double temp = history.get().get(history.get().size() - 1);
                yData.add((float) temp);
                baseline = yData.get(0);

            }

        });
        thread.start();

//        Thread thread = new Thread(() -> {
//            AlpacaAPI alpacaAPI = new AlpacaAPI();
//            Map<String, ArrayList<Bar>> bars = null;
//            try {
//                bars = alpacaAPI.getBars(BarsTimeFrame.FIFTEEN_MINUTE, ticker.get(), 30, null, null, null, ZonedDateTime.now());
//            } catch (AlpacaAPIRequestException e) {
//                e.printStackTrace();
//            }
//            for (Bar bar : bars.get(ticker.get())) {
//                yData.add(bar.getC().floatValue());
//            }
//        });
//        thread.start();

        // Get The days data and add it to yData
//        ArrayList<Aggregate> aggregates = new ArrayList<>();
//        PolygonAPI polygonAPI = new PolygonAPI();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            aggregates = polygonAPI.getAggregates(String.valueOf(ticker), 1, Timespan.HOUR, LocalDate.now(), LocalDate.now(), false).getResults();
//        }
//        for (Aggregate i : aggregates) {
//            yData.add(i.getC().floatValue());
//        }
//        smoothGraph();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initializeStock() throws AlpacaAPIRequestException {

        // Requests bars and adds to graph
        AlpacaAPI alpacaAPI = new AlpacaAPI();
        PolygonAPI polygonAPI = new PolygonAPI();
        Map<String, ArrayList<Bar>> bars = null;

        // Check if market is open
        String marketStatus = null;
        try {
            marketStatus = polygonAPI.getMarketStatus().getMarket();
        } catch (PolygonAPIRequestException e) {
            e.printStackTrace();
        }

        if (marketStatus.equals("open")) {

            // Fetch todays bars
            try {
                bars = alpacaAPI.getBars(BarsTimeFrame.FIVE_MINUTE, ticker.get(), 1000, null, null,
                        ZonedDateTime.of(LocalDateTime.of(LocalDate.now(), LocalTime.of(5, 30)), ZoneId.of("UTC-6")), null);

            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

        } else {

            // Fetch last open day's information
            ArrayList<Calendar> calendar = alpacaAPI.getCalendar(LocalDate.now().minusWeeks(1), LocalDate.now());
            LocalDate lastOpenDate = LocalDate.parse(calendar.get(calendar.size() - 1).getDate());
            LocalTime lastOpenTime = LocalTime.parse(calendar.get(calendar.size() - 1).getClose());
            LocalTime lastOpenTimeStart = LocalTime.parse(calendar.get(calendar.size() - 1).getOpen());

            try {
                bars = alpacaAPI.getBars(BarsTimeFrame.FIVE_MINUTE, ticker.get(), 1000, null, null,
                        ZonedDateTime.of(lastOpenDate, lastOpenTimeStart, ZoneId.of("UTC-4")),
                        ZonedDateTime.of(lastOpenDate, lastOpenTime, ZoneId.of("UTC-4")));

            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }
        }
        for (Bar bar : Objects.requireNonNull(bars.get(ticker.get()))) {
            yData.add(bar.getC().floatValue());
        }
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


