package com.bedefined.alpaca_dashboard;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

public class SearchableAdapter extends ArrayAdapter<String> implements Filterable {
    private List<String> mOrigionalValues;
    private List<String> mObjects;
    private Filter mFilter;

    public SearchableAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        mOrigionalValues = new ArrayList<>();
        mObjects = new ArrayList<>();
    }

    public void add(String object) {
        mOrigionalValues.add(object);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public String getItem(int position) {
        return mObjects.get(position);
    }

    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new CustomFilter();
        }
        return mFilter;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    private class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if(constraint == null || constraint.length() == 0) {
                ArrayList<String> list = new ArrayList<>(mOrigionalValues);
                results.values = list;
                results.count = list.size();
            } else {
                ArrayList<String> newValues = new ArrayList<>();
                for(int i = 0; i < mOrigionalValues.size(); i++) {
                    String item = mOrigionalValues.get(i);
                    if(item.contains(constraint)) {
                        newValues.add(item);
                    }
                }
                results.values = newValues;
                results.count = newValues.size();
            }

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mObjects = (List<String>) results.values;
            Log.d("CustomArrayAdapter", String.valueOf(results.values));
            Log.d("CustomArrayAdapter", String.valueOf(results.count));
            notifyDataSetChanged();
        }

    }

}