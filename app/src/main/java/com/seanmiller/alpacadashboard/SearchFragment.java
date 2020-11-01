package com.seanmiller.alpacadashboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lapism.search.internal.SearchLayout;
import com.lapism.search.widget.MaterialSearchView;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ramotion.foldingcell.FoldingCell;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.domain.alpaca.position.Position;
import net.jacobpeterson.domain.polygon.tickernews.TickerNews;
import net.jacobpeterson.domain.polygon.tickers.ticker.Ticker;
import net.jacobpeterson.polygon.PolygonAPI;
import net.jacobpeterson.polygon.rest.exception.PolygonAPIRequestException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.cabriole.decorator.GridMarginDecoration;
import io.cabriole.decorator.LinearMarginDecoration;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static android.os.FileUtils.copy;

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
//                requireActivity().findViewById(R.id.containerFragSearch).requestFocus();
                getActivity().getCurrentFocus().clearFocus();

            } else {
                materialSearch.requestFocus();
            }
        });

        Thread thread = new Thread(() -> {

            // Fetch news for positions
            PolygonAPI polygonAPI = new PolygonAPI();
            AlpacaAPI alpacaAPI = new AlpacaAPI();
//            ArrayList<Position> stocks = null;
//            try {
//                stocks = alpacaAPI.getOpenPositions();
//            } catch (AlpacaAPIRequestException e) {
//                e.printStackTrace();
//            }

            ArrayList<String> stocks = new ArrayList<>();
            stocks.add("AAPL");
            stocks.add("AMD");
            ArrayList<TickerNews> news = null;
            if (stocks != null) {
                for (/*Position*/String stock : stocks) {
                    try {
                        news = polygonAPI.getTickerNews(stock/*.getSymbol()*/, 10, 1);
                    } catch (PolygonAPIRequestException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (news != null) {
                ArrayList<String> arr = new ArrayList<>();
                for (TickerNews i : news) {
                    arr.add(i.getTitle());
                }
//                arr.add(news.get(0).getTitle());
                RecyclerView newsRecycler = mView.findViewById(R.id.newsRecycler);

                ArrayList<TickerNews> finalNews = news;
                requireActivity().runOnUiThread(() -> {

//                    // Get rid of animation bug by rounding corners
//                    newsImage.setShapeAppearanceModel(new ShapeAppearanceModel().withCornerSize(bounds -> dpToPixel(15)));
//                    newsImage2.setShapeAppearanceModel(new ShapeAppearanceModel().withCornerSize(bounds -> dpToPixel(15)));

//                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//                    recyclerView.addItemDecoration(new GridMarginDecoration(0, col, GridLayoutManager.VERTICAL, false, null));

                    newsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                    newsRecycler.addItemDecoration(new LinearMarginDecoration());
                    RecyclerViewAdapterNews test = new RecyclerViewAdapterNews(getActivity(), finalNews);
                    newsRecycler.setAdapter(test);
                });
            }
        });
        thread.start();

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