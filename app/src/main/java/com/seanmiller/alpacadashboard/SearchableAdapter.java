package com.seanmiller.alpacadashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import net.jacobpeterson.domain.polygon.tickers.ticker.Ticker;

import java.util.ArrayList;

public class SearchableAdapter extends RecyclerView.Adapter<SearchableAdapter.ViewHolder> {

    private final ArrayList<Ticker> mValues;
    private ItemClickListener mClickListener;

    public SearchableAdapter(ArrayList<Ticker> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        String name = mValues.get(position).getName();
//        String ticker = mValues.get(position);
//        holder.stockTicker.setText(ticker);
//        holder.stockFullName.setText("test");

//        String fullString = mValues.get(position);
        holder.stockTicker.setText(mValues.get(position).getTicker());
        holder.stockFullName.setText(mValues.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final View mView;
        final TextView stockFullName;
        final TextView stockTicker;
        final CardView searchCard;

        ViewHolder(View view) {
            super(view);
            mView = view;
            stockFullName = view.findViewById(R.id.stockFullNameSearch);
            stockTicker = view.findViewById(R.id.stockTickerSearch);
            searchCard = view.findViewById(R.id.searchCard);
            searchCard.setOnClickListener(this);
            view.setOnClickListener(this);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + stockFullName.getText();
        }


        @Override
        public void onClick(View v) {
            if (mClickListener != null) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    // convenience method for getting data at click position
    Ticker getItem(int id) {
        return mValues.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);

    }

}

