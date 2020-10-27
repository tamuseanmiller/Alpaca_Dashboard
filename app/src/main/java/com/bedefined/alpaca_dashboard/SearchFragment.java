package com.bedefined.alpaca_dashboard;

import org.json.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.lapism.search.internal.SearchLayout;
import com.lapism.search.util.SearchUtils;
import com.lapism.search.widget.MaterialSearchView;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.domain.polygon.tickers.TickersResponse;
import net.jacobpeterson.domain.polygon.tickers.ticker.Ticker;
import net.jacobpeterson.polygon.PolygonAPI;
import net.jacobpeterson.polygon.enums.Market;
import net.jacobpeterson.polygon.enums.StockType;
import net.jacobpeterson.polygon.enums.TickerSort;
import net.jacobpeterson.polygon.rest.exception.PolygonAPIRequestException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchFragment extends Fragment implements SearchLayout.OnQueryTextListener, SearchableAdapter.ItemClickListener {

    private MaterialSearchView materialSearch;
    private SearchableAdapter searchableAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.search_fragment, null);

        materialSearch = mView.findViewById(R.id.material_search_view);
        materialSearch.setAdapterLayoutManager(new LinearLayoutManager(getActivity()));

//        MainActivity.myRef.child("alpaca/assets").orderByChild("symbol").startAt("E").endAt("E\uf8ff").addValueEventListener(new ValueEventListener() {
//            @Override
//            public
//            void onDataChange(@NonNull DataSnapshot snapshot) {
//                HashMap<String, String> map = (HashMap<String, String>)snapshot.getValue();
//                System.out.println(map.get("-MKWv-ueiyE7YwtnR8LN"));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        // Set themes
        TypedValue typedValue = new TypedValue();
        requireActivity().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        int color = ContextCompat.getColor(requireActivity(), typedValue.resourceId);
        requireActivity().getTheme().resolveAttribute(R.attr.colorAccentTransparent, typedValue, true);
        int strokeColor = ContextCompat.getColor(requireActivity(), typedValue.resourceId);
        requireActivity().getTheme().resolveAttribute(R.attr.colorPrimaryLight, typedValue, true);
        int colorLight = ContextCompat.getColor(requireActivity(), typedValue.resourceId);
        materialSearch.setBackgroundColor(color);
        materialSearch.setBackgroundStrokeColor(strokeColor);
        materialSearch.setBackgroundStrokeWidth(2);
        materialSearch.setBackgroundRadius(60);
        materialSearch.setTextHintColor(colorLight);
        materialSearch.setNavigationIconColorFilter(colorLight);
        materialSearch.setTextHint("Stock Ticker");
        materialSearch.setElevation(3);

        // When microphone is clicked
        materialSearch.setOnMicClickListener(() -> {

            if (SearchUtils.isVoiceSearchAvailable(getActivity())) {
                SearchUtils.setVoiceSearch(getActivity(), getString(R.string.speak));
            }
        });

        // When search is focused
        materialSearch.setOnFocusChangeListener(v -> {
            materialSearch.setNavigationIconImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.arrow_left));
        });

        materialSearch.setOnQueryTextListener(this);

        // If the navigation button is clicked
        materialSearch.setOnNavigationClickListener(v -> {
            if (materialSearch.hasFocus()) {
                requireActivity().findViewById(R.id.containerFragSearch).requestFocus();

            } else {
                materialSearch.requestFocus();
            }
        });
        return mView;
    }

    @Override
    public boolean onQueryTextChange(@NonNull CharSequence charSequence) {

        AlpacaAPI alpacaAPI = new AlpacaAPI();

        URL url = null;
        try {
            url = new URL("https://financialmodelingprep.com/api/v3/search?query=" + charSequence + "&limit=30&exchange=NASDAQ&apikey=" + Properties.getAPIKey());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray json = null;
        ArrayList<String> tickerResults = new ArrayList<>();
        try {
            json = new JSONArray(sb.toString());
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonobject = json.getJSONObject(i);
                String id = jsonobject.getString("symbol");
                String title = jsonobject.getString("name");
                tickerResults.add(id + " : " + title);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        searchableAdapter = new SearchableAdapter(tickerResults);
        searchableAdapter.setClickListener(this);
        materialSearch.setAdapter(searchableAdapter);

//        PolygonAPI polygonAPI = new PolygonAPI();
//        ArrayList<Ticker> tickers = null;
//        try {
//            tickers = polygonAPI.getTickers(TickerSort.TYPE_ASCENDING, StockType.COMMON_STOCKS, Market.STOCKS, "us", charSequence.toString(), 10, 1, true).getResults();
//        } catch (PolygonAPIRequestException e) {
//            e.printStackTrace();
//        }
//        if (tickers != null) {
//            searchableAdapter = new SearchableAdapter(tickers);
//            searchableAdapter.setClickListener(this);
//            materialSearch.setAdapter(searchableAdapter);
//        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(CharSequence charSequence) {

        AlpacaAPI alpacaAPI = new AlpacaAPI();

        URL url = null;
        try {
            url = new URL("https://financialmodelingprep.com/api/v3/search?query=" + charSequence + "&limit=30&exchange=NASDAQ&apikey=" + Properties.getAPIKey());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray json = null;
        ArrayList<String> tickerResults = new ArrayList<>();
        try {
            json = new JSONArray(sb.toString());
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonobject = json.getJSONObject(i);
                String id = jsonobject.getString("symbol");
                String title = jsonobject.getString("name");
                tickerResults.add(id + " : " + title);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        searchableAdapter = new SearchableAdapter(tickerResults);
        searchableAdapter.setClickListener(this);
        materialSearch.setAdapter(searchableAdapter);

        return true;
    }

    @Override
    public void onItemClick(View view, int position) {
        DashboardFragment.ticker.set(searchableAdapter.getItem(position).substring(0, searchableAdapter.getItem(position).indexOf(':') - 1));
        Intent intentMain = new Intent(getActivity(), StockPageActivity.class);
        requireActivity().startActivity(intentMain, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
    }
}