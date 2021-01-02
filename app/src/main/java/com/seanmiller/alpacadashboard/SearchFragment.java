package com.seanmiller.alpacadashboard;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
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

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.domain.alpaca.asset.Asset;
import net.jacobpeterson.domain.alpaca.position.Position;
import net.jacobpeterson.domain.polygon.tickers.ticker.Ticker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.cabriole.decorator.LinearMarginDecoration;

import static android.app.Activity.RESULT_OK;

public class SearchFragment extends Fragment implements SearchLayout.OnQueryTextListener, SearchableAdapter.ItemClickListener/*, BillingProcessor.IBillingHandler*/ {

    private static final int SPEECH_REQUEST_CODE = 0;
    private MaterialSearchView materialSearch;
    private SearchableAdapter searchableAdapter;
    private static RecyclerView newsRecycler;
    private static RecyclerViewAdapterNews newsAdapter;
    private SharedPreferencesManager prefs;
    private BillingProcessor bp;
    public static MaterialCardView searchCard;
    private static SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.search_fragment, null);
        prefs = new SharedPreferencesManager(getActivity());
        bp = new BillingProcessor(requireActivity(), Properties.getPlayLicenseKey(), (BillingProcessor.IBillingHandler) requireActivity());
        bp.initialize();

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
        materialSearch.setTextColor(colorLight);
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
                getActivity().getCurrentFocus().clearFocus();

            } else {
                materialSearch.requestFocus();
            }
        });

        // News Recycler
        refreshLayout = mView.findViewById(R.id.refreshSearch);
        newsRecycler = mView.findViewById(R.id.newsRecycler);
        searchCard = mView.findViewById(R.id.searchCard);
        if (prefs.retrieveBoolean("premium", false)) {
            startNews(prefs, getActivity());

        } else {
            searchCard.setVisibility(View.VISIBLE);
            MaterialButton buy_premium = mView.findViewById(R.id.buy_premium_search);
            buy_premium.setOnClickListener(v -> {
                bp.subscribe(requireActivity(), "premium_sub");
            });
        }

        refreshLayout.setProgressViewOffset(true, 0, 260);
        refreshLayout.setOnRefreshListener(() -> {
            if (prefs.retrieveBoolean("premium", false)) {
                startNews(prefs, getActivity());
            } else {
                refreshLayout.setRefreshing(false);
            }
        });

        return mView;
    }

    public static void startNews(SharedPreferencesManager prefs, Activity activity) {

        Thread thread = new Thread(() -> {

            // Initialize Alpaca's API
            AlpacaAPI alpacaAPI = new AlpacaAPI(prefs.retrieveString("auth_token", "NULL"));
            activity.runOnUiThread(() -> refreshLayout.setRefreshing(true));
            ArrayList<Position> positions = null;
            try {
                positions = alpacaAPI.getOpenPositions();

            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }
            ArrayList<JSONObject> news = new ArrayList<>();

            // If no positions, show news for AAPL and AMD
            if (positions == null || positions.isEmpty()) {
                ArrayList<String> stocks = new ArrayList<>();
                stocks.add("AAPL");
                stocks.add("GOOGL");
                for (String stock : stocks) {
                    news.addAll(getNews(stock));
                }
                news.sort((o1, o2) -> {
                    try {
                        return o1.get("datetime").toString().compareTo(o2.get("datetime").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return 0;
                });
                Collections.reverse(news);

            // If positions exist show news for your positions
            } else {

                for (Position stock : positions) {
                    news.addAll(getNews(stock.getSymbol()));
                }
                news.sort((o1, o2) -> {
                    try {
                        return o1.get("datetime").toString().compareTo(o2.get("datetime").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return 0;
                });
                Collections.reverse(news);
            }

            // Make sure that there is enough news returned, grab first 21 articles
            if (news.size() >= 21) {
                try {
                    news = removeDuplicates(news);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Set<JSONObject> set = new HashSet<>(news.subList(0, 20));
                news.clear();
                news.addAll(set);
                set.clear();
            }

            // Initialize news recyclerview
            ArrayList<JSONObject> finalNews = news;
            activity.runOnUiThread(() -> {

                newsRecycler.setLayoutManager(new LinearLayoutManager(activity));
                newsRecycler.addItemDecoration(new LinearMarginDecoration());
                newsAdapter = new RecyclerViewAdapterNews(activity, finalNews);
                newsRecycler.setAdapter(newsAdapter);
                refreshLayout.setRefreshing(false);

            });

        });
        thread.start();
    }

    public static ArrayList<JSONObject> getNews(String ticker) {

        // Call get news endpoint from IEX Cloud
        JSONArray nodeHttpResponse = null;
        try {
            nodeHttpResponse = Unirest.get("https://cloud-sse.iexapis.com/stable/stock/" + ticker + "/news/last/10?token=" + Properties.getIexApiKey()).asJson().getBody().getArray();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        // Add to ArrayList
        ArrayList<JSONObject> articles = new ArrayList<>();
        try {
            for (int i = 0; i < nodeHttpResponse.length(); i++) {
                if (nodeHttpResponse.getJSONObject(i).get("lang").toString().equals("en")) {

                    articles.add(nodeHttpResponse.getJSONObject(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return articles;
    }

    // Remove duplicate articles
    public static ArrayList<JSONObject> removeDuplicates(ArrayList<JSONObject> news) throws JSONException {
        for (int i = 0; i < news.size(); i++) {
            for (int j = i; j < news.size(); j++) {
                if (i != j && news.get(i).get("headline").toString().equals(news.get(j).get("headline").toString())) {
                    news.remove(i);
                }
            }
        }
        return news;
    }

    @Override
    public boolean onQueryTextChange(@NonNull CharSequence charSequence) {

        Thread thread = new Thread(() -> {

            AlpacaAPI alpacaAPI = new AlpacaAPI(prefs.retrieveString("auth_token", "NULL"));

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
//                    try {
//                        Asset asset = alpacaAPI.getAssetBySymbol(i.getAsJsonObject().get("symbol").getAsString());
//                        if (asset.getStatus().equals("active")) {
                            Ticker t = new Ticker();
                            t.setName(i.getAsJsonObject().get("name").getAsString());
                            t.setTicker(i.getAsJsonObject().get("symbol").getAsString());
                            tickers.add(t);
//                        }
//                    } catch (AlpacaAPIRequestException e) {
//                        e.printStackTrace();
//                    }
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

            AlpacaAPI alpacaAPI = new AlpacaAPI(prefs.retrieveString("auth_token", "NULL"));

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
//                    try {
//                        Asset asset = alpacaAPI.getAssetBySymbol(i.getAsJsonObject().get("symbol").getAsString());
//                        if (asset.getStatus().equals("active")) {
                            Ticker t = new Ticker();
                            t.setName(i.getAsJsonObject().get("name").getAsString());
                            t.setTicker(i.getAsJsonObject().get("symbol").getAsString());
                            tickers.add(t);
//                        }
//                    } catch (AlpacaAPIRequestException e) {
//                        e.printStackTrace();
//                    }
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

    // When a search item is chosen, go to stock page
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
    }

    // IBillingHandler implementation

    /*@Override
    public void onBillingInitialized() {
        *//*
         * Called when BillingProcessor was initialized and it's ready to purchase
         *//*

        // Check to see if premium has been purchased
        prefs.storeBoolean("premium", bp.isPurchased("premium_sub"));
    }

    @Override
    public void onProductPurchased(@NonNull String productId, TransactionDetails details) {
        *//*
         * Called when requested PRODUCT ID was successfully purchased
         *//*
        prefs.storeBoolean("premium", true);
        startNews();
        searchCard.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
        *//*
         * Called when some error occurred. See Constants class for more details
         *
         * Note - this includes handling the case where the user canceled the buy dialog:
         * errorCode = Constants.BILLING_RESPONSE_RESULT_USER_CANCELED
         *//*
        prefs.storeBoolean("premium", false);
        Log.v("Billing Error", String.valueOf(errorCode), error);
    }

    @Override
    public void onPurchaseHistoryRestored() {
        *//*
         * Called when purchase history was restored and the list of all owned PRODUCT ID's
         * was loaded from Google Play
         *//*
        prefs.storeBoolean("premium", bp.isPurchased("premium_sub"));
    }*/

    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }
}