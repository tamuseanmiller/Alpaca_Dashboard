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

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.domain.alpaca.position.Position;
import net.jacobpeterson.domain.polygon.lastquote.LastQuoteResponse;
import net.jacobpeterson.polygon.PolygonAPI;
import net.jacobpeterson.polygon.rest.exception.PolygonAPIRequestException;

import java.util.List;

public class RecyclerViewAdapterPositions extends RecyclerView.Adapter<RecyclerViewAdapterPositions.ViewHolder> {

    private static List<String> mData;
    private LayoutInflater mInflater;
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

            // Get Last value
            float close = 0;
            LastQuoteResponse curr = null;
            try {
                close = polygonAPI.getPreviousClose(mData.get(position), false).getResults().get(0).getC().floatValue();
            } catch (PolygonAPIRequestException e) {
                e.printStackTrace();
            }

            while (true) {

                // Get Amount of shares owned
                Position shrOwned = null;
                try {
                    shrOwned = alpacaAPI.getOpenPositionBySymbol(mData.get(position));
                } catch (AlpacaAPIRequestException e) {
                    e.printStackTrace();
                }

                Position finalShrOwned = shrOwned;
                mainActivity.runOnUiThread(() -> {
                    if (finalShrOwned.getQty() != null) {

                        if (finalShrOwned.getQty().equals("1")) {
                            holder.sharesOwned.setText(finalShrOwned.getQty() + " share owned");
                        } else {
                            holder.sharesOwned.setText(finalShrOwned.getQty() + " shares owned");
                        }
                    }
                });

                // Get the last quote
                try {
                    curr = polygonAPI.getLastQuote(mData.get(position));

                } catch (PolygonAPIRequestException e) {
                    e.printStackTrace();
                }

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
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView stock_name;
        Button percentChange;
        TextView sharesOwned;
        TextView priceOfStock;
        CardView stockCard;

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

