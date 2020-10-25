package com.bedefined.alpaca_dashboard;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SearchableAdapter extends RecyclerView.Adapter<SearchableAdapter.ViewHolder> {

    private final List<String> mValues;
    private ItemClickListener mClickListener;

    public SearchableAdapter(List<String> items) {
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
        String fullString = mValues.get(position);
        holder.stockTicker.setText(fullString.substring(0, fullString.indexOf(':') - 1));
        holder.stockFullName.setText(fullString.substring(fullString.indexOf(':') + 2));

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
    String getItem(int id) {
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

