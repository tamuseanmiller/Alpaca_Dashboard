package com.seanmiller.alpacadashboard;

import org.json.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lapism.search.internal.SearchLayout;
import com.lapism.search.util.SearchUtils;
import com.lapism.search.widget.MaterialSearchView;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import net.jacobpeterson.alpaca.AlpacaAPI;
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
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class SearchFragment extends Fragment implements SearchLayout.OnQueryTextListener, SearchableAdapter.ItemClickListener {

    private static final int SPEECH_REQUEST_CODE = 0;
    private MaterialSearchView materialSearch;
    private SearchableAdapter searchableAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.search_fragment, null);

        materialSearch = mView.findViewById(R.id.material_search_view);
        materialSearch.setAdapterLayoutManager(new LinearLayoutManager(getActivity()));

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
        materialSearch.setClearIconColorFilter(colorLight);
        materialSearch.setMicIconColorFilter(colorLight);

        // When microphone is clicked
        materialSearch.setOnMicClickListener(this::displaySpeechRecognizer);

        // When search is focused
        materialSearch.setOnFocusChangeListener(v -> {
            if (materialSearch.hasFocus()) {
                materialSearch.setNavigationIconImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.arrow_left));
            } else {
                materialSearch.setNavigationIconImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.magnify));
            }
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

        Thread thread = new Thread(() -> {

            // Calls yahoo finance api
            // http://d.yimg.com/aq/autoc?query=y&region=US&lang=en-US&callback=YAHOO.util.ScriptNodeDataSource.callbacks
            HttpResponse<String> nodeHttpResponse = null;
            try {
                nodeHttpResponse = Unirest.get("http://d.yimg.com/aq/autoc")
                        .queryString("query", charSequence.toString())
                        .queryString(ImmutableMap.of("region", "US", "lang", "en-US", "callback", "YAHOO.util.ScriptNodeDataSource.callbacks")).asString();
            } catch (UnirestException e) {
                e.printStackTrace();
            }

            // Formats the response into an array
            if (nodeHttpResponse != null) {
                String innerJson = nodeHttpResponse.getBody().substring(42, nodeHttpResponse.getBody().length() - 2);
                JsonParser parse = new JsonParser();
                JsonObject jsonObject = (JsonObject) parse.parse(innerJson);
                JsonArray stocks = jsonObject.get("ResultSet").getAsJsonObject().get("Result").getAsJsonArray();
                ArrayList<Ticker> tickers = new ArrayList<>();

                for (JsonElement i : stocks) {
                    Ticker t = new Ticker();
                    t.setName(i.getAsJsonObject().get("name").getAsString());
                    t.setTicker(i.getAsJsonObject().get("symbol").getAsString());
                    tickers.add(t);
                }

                // Sets adapter
                searchableAdapter = new SearchableAdapter(tickers);
                searchableAdapter.setClickListener(this);
                requireActivity().runOnUiThread(() -> materialSearch.setAdapter(searchableAdapter));
            }
        });
        thread.start();

        return false;
    }

    @Override
    public boolean onQueryTextSubmit(CharSequence charSequence) {

        Thread thread = new Thread(() -> {

            // Calls yahoo finance api
            // http://d.yimg.com/aq/autoc?query=y&region=US&lang=en-US&callback=YAHOO.util.ScriptNodeDataSource.callbacks
            HttpResponse<String> nodeHttpResponse = null;
            try {
                nodeHttpResponse = Unirest.get("http://d.yimg.com/aq/autoc")
                        .queryString("query", charSequence.toString())
                        .queryString(ImmutableMap.of("region", "US", "lang", "en-US", "callback", "YAHOO.util.ScriptNodeDataSource.callbacks")).asString();
            } catch (UnirestException e) {
                e.printStackTrace();
            }

            // Formats the response into an array
            if (nodeHttpResponse != null) {
                String innerJson = nodeHttpResponse.getBody().substring(42, nodeHttpResponse.getBody().length() - 2);
                JsonParser parse = new JsonParser();
                JsonObject jsonObject = (JsonObject) parse.parse(innerJson);
                JsonArray stocks = jsonObject.get("ResultSet").getAsJsonObject().get("Result").getAsJsonArray();
                ArrayList<Ticker> tickers = new ArrayList<>();

                for (JsonElement i : stocks) {
                    Ticker t = new Ticker();
                    t.setName(i.getAsJsonObject().get("name").getAsString());
                    t.setTicker(i.getAsJsonObject().get("symbol").getAsString());
                    tickers.add(t);
                }

                // Sets adapter
                searchableAdapter = new SearchableAdapter(tickers);
                searchableAdapter.setClickListener(this);
                requireActivity().runOnUiThread(() -> materialSearch.setAdapter(searchableAdapter));
            }
        });
        thread.start();

        return true;
    }

    // When a search item is chose, go to stock page
    @Override
    public void onItemClick(View view, int position) {
        DashboardFragment.ticker.set(searchableAdapter.getItem(position).getTicker());
        Intent intentMain = new Intent(getActivity(), StockPageActivity.class);
        requireActivity().startActivity(intentMain, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
    }

    // Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        // Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    // To set the text query for microphone
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            // Do something with spokenText
            materialSearch.setTextQuery(spokenText, false);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}