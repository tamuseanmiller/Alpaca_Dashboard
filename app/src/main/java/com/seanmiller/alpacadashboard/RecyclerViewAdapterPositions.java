package com.seanmiller.alpacadashboard;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.domain.alpaca.calendar.Calendar;
import net.jacobpeterson.domain.alpaca.position.Position;
import net.jacobpeterson.domain.polygon.aggregates.Aggregate;
import net.jacobpeterson.domain.polygon.lastquote.LastQuoteResponse;
import net.jacobpeterson.polygon.PolygonAPI;
import net.jacobpeterson.polygon.enums.Timespan;
import net.jacobpeterson.polygon.rest.exception.PolygonAPIRequestException;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterPositions extends RecyclerView.Adapter<RecyclerViewAdapterPositions.ViewHolder> {

    private static List<String> mData;
    private final LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    RecyclerViewAdapterPositions(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        mData = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_position, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterPositions.ViewHolder holder, int position) {

        MainActivity mainActivity = new MainActivity();

        String stockName = mData.get(position);
        holder.stock_name.setText(stockName);
        SharedPreferencesManager prefs = new SharedPreferencesManager(mInflater.getContext());

        Thread thread = new Thread(() -> {

            PolygonAPI polygonAPI = new PolygonAPI(prefs.retrieveString("polygon_id", "NULL"));
            AlpacaAPI alpacaAPI = new AlpacaAPI(prefs.retrieveString("auth_token", "NULL"));

            // Fetch last open day's information
            ArrayList<Calendar> calendar = null;
            try {
                calendar = alpacaAPI.getCalendar(LocalDate.now().minusWeeks(1), LocalDate.now());
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }
            assert calendar != null;
            LocalDate lastOpenDate = LocalDate.parse(calendar.get(calendar.size() - 2).getDate());
            if (LocalTime.of(Integer.parseInt(calendar.get(calendar.size() - 2).getOpen().substring(0, 2)),
                    Integer.parseInt(calendar.get(calendar.size() - 2).getOpen().substring(3, 5))).compareTo(LocalTime.now()) > 0) {
                lastOpenDate = LocalDate.parse(calendar.get(calendar.size() - 3).getDate());
            }

            // Get Last value
            float close = 0;
            float closeCurr = 0;
            try {

                // Fetch Daily Open Close endpoint
                // https://api.polygon.io/v1/open-close/AAPL/2020-10-14?apiKey=
                JSONObject nodeHttpResponse = null;
                try {
                    nodeHttpResponse = Unirest.get("https://api.polygon.io/v1/open-close/" + mData.get(position) + "/" + lastOpenDate + "?apiKey=" + prefs.retrieveString("polygon_id", "NULL")).asJson().getBody().getObject();

                } catch (UnirestException e) {
                    e.printStackTrace();
                }

                if (nodeHttpResponse != null) {
                    close = Float.parseFloat(nodeHttpResponse.get("close").toString());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

//            while (true) {

            // Get Amount of shares owned
            Position shrOwned = null;
            try {
                shrOwned = alpacaAPI.getOpenPositionBySymbol(mData.get(position));
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            // Set shares owned
            Position finalShrOwned = shrOwned;
            mainActivity.runOnUiThread(() -> {
                if (finalShrOwned.getQty() != null) {

                    if (finalShrOwned.getQty().equals("1")) {
                        holder.sharesOwned.setText(String.format("%s share owned", finalShrOwned.getQty()));
                    } else {
                        holder.sharesOwned.setText(String.format("%s shares owned", finalShrOwned.getQty()));
                    }
                }
            });

            while (true) {

                // Get market status
                String marketStatus = null;
                try {
                    marketStatus = polygonAPI.getMarketStatus().getMarket();

                } catch (PolygonAPIRequestException e) {
                    e.printStackTrace();
                }

                // Check if market is open
                if (marketStatus != null) {
                    if (marketStatus.equals("open")) {

                        // Get the last quote
                        LastQuoteResponse curr = null;
                        try {
                            curr = polygonAPI.getLastQuote(mData.get(position));

                        } catch (PolygonAPIRequestException e) {
                            e.printStackTrace();
                        }

                        try {
                            close = polygonAPI.getPreviousClose(mData.get(position), false).getResults().get(0).getC().floatValue();
                        } catch (PolygonAPIRequestException e) {
                            e.printStackTrace();
                        }

                        // Set values
                        LastQuoteResponse finalCurr = curr;
                        float finalClose = close;
                        mainActivity.runOnUiThread(() -> {

                            if (finalCurr != null && finalClose != 0) {
                                holder.priceOfStock.setText(String.format("$%.2f", finalCurr.getLast().getAskprice().floatValue()));
                                float temp = (finalCurr.getLast().getAskprice().floatValue() - finalClose) / finalClose * 100;

                                // Sets the precision and adds to view, then changes color
                                if (temp >= 0) {
                                    holder.percentChange.setText(String.format("+%.2f%%", temp));
                                    mClickListener.switchColors(holder, true);

                                } else {
                                    holder.percentChange.setText(String.format("%.2f%%", temp));
                                    mClickListener.switchColors(holder, false);
                                }

                            }
                        });

                    // Market isn't open
                    } else {

                        // Fetch Daily Open Close endpoint for prev day
                        // https://api.polygon.io/v1/open-close/AAPL/2020-10-14?apiKey=
                        /*JSONObject nodeHttpResponse = null;
                        try {
                            nodeHttpResponse = Unirest.get("https://api.polygon.io/v1/open-close/" + mData.get(position) + "/" + calendar.get(calendar.size() - 2).getDate() + "?apiKey=" + prefs.retrieveString("polygon_id", "NULL")).asJson().getBody().getObject();

                        } catch (UnirestException e) {
                            e.printStackTrace();
                        }*/

                        // Get last open day's close
    //                    if (nodeHttpResponse != null) {
                        try {
//                            closeCurr = polygonAPI.getLastQuote(mData.get(position)).getLast().getAskprice().floatValue();
                            ArrayList<Aggregate> aggs = polygonAPI.getAggregates(mData.get(position), 1, Timespan.DAY, lastOpenDate, lastOpenDate.plusDays(1), false).getResults();
                            closeCurr = aggs.get(aggs.size() - 1).getC().floatValue();
                        } catch (PolygonAPIRequestException e) {
                            e.printStackTrace();
                        }
    //                    }

                        // Set values
                        float finalClose = close;
                        float finalCurr = closeCurr;
                        mainActivity.runOnUiThread(() -> {

                            if (finalCurr != 0 && finalClose != 0) {
                                holder.priceOfStock.setText(String.format("$%.2f", finalCurr));
                                float temp = (finalCurr - finalClose) / finalClose * 100;

                                // Sets the precision and adds to view, then changes color
                                if (temp >= 0) {
                                    holder.percentChange.setText(String.format("+%.2f%%", temp));
                                    mClickListener.switchColors(holder, true);

                                } else {
                                    holder.percentChange.setText(String.format("%.2f%%", temp));
                                    mClickListener.switchColors(holder, false);
                                }

                            }
                        });

                    }
                }

                // Sleep for 1 minute
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView stock_name;
        MaterialButton percentChange;
        TextView sharesOwned;
        TextView priceOfStock;
        MaterialCardView stockCard;

        ViewHolder(View itemView) {
            super(itemView);
            stock_name = itemView.findViewById(R.id.stockName);
            percentChange = itemView.findViewById(R.id.currentPrice);
            sharesOwned = itemView.findViewById(R.id.sharesOwned);
            priceOfStock = itemView.findViewById(R.id.priceOfTicker);
            stockCard = itemView.findViewById(R.id.positionCard);
            stockCard.setOnClickListener(this);
            itemView.setOnClickListener(this);
            percentChange.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);

        void switchColors(ViewHolder view, boolean pos);
    }
}

