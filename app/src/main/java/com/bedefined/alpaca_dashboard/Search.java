package com.bedefined.alpaca_dashboard;

import org.json.*;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lapism.search.internal.SearchLayout;
import com.lapism.search.util.SearchUtils;
import com.lapism.search.widget.MaterialSearchView;

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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class Search extends AppCompatActivity implements SearchLayout.OnQueryTextListener {

    private MaterialSearchView materialSearch;
    private BottomNavigationView bottomNavigationViewSearch;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Utils.startTheme(this, new SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_DEFAULT));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        bottomNavigationViewSearch = findViewById(R.id.bottom_navigation_search);
        bottomNavigationViewSearch.setSelectedItemId(R.id.search_page);
        bottomNavigationViewSearch.setOnNavigationItemSelectedListener( item -> {

            switch(item.getItemId()) {
                case R.id.dashboard_page:
                    Intent intentMain = new Intent(this, MainActivity.class);
                    startActivity(intentMain, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                    return true;
                case R.id.search_page:
                    return true;
                case R.id.profile_page:
                    Intent intentProfile = new Intent(this, Profile.class);
                    startActivity(intentProfile, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                    return true;
            }
            return false;
        });

        materialSearch = findViewById(R.id.material_search_view);

        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryLight, typedValue, true);
        int color = ContextCompat.getColor(getApplicationContext(), typedValue.resourceId);
        materialSearch.setNavigationIconColorFilter(color);

        materialSearch.setTextHint("Stock Ticker");
        materialSearch.setTextHintColor(color);

        materialSearch.setOnMicClickListener(() -> {

            if (SearchUtils.isVoiceSearchAvailable(this)) {
                SearchUtils.setVoiceSearch(this, getString(R.string.speak));
            }
        });
        materialSearch.setOnFocusChangeListener(v -> {
            materialSearch.setNavigationIconImageDrawable(ContextCompat.getDrawable(this, R.drawable.arrow_left));
        });
        ListView searchResults = findViewById(R.id.searchResults);

        materialSearch.setOnQueryTextListener(this);
        materialSearch.setElevation(3);
        materialSearch.setOnNavigationClickListener(v -> {
            if (materialSearch.hasFocus()) {
                finishAfterTransition();

            } else {
                materialSearch.requestFocus();
            }
        });

        URL url = null;
        try {
            url = new URL("https://financialmodelingprep.com/api/v3/search?query=" + "AA" + "&limit=10&exchange=NASDAQ&apikey=75a24f9231ff66df465791c318049e2a");
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
            for(int i=0; i < json.length(); i++) {
                JSONObject jsonobject = json.getJSONObject(i);
                String id       = jsonobject.getString("symbol");
                String title    = jsonobject.getString("name");
                tickerResults.add(id + " : " + title);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onQueryTextChange(@NonNull CharSequence charSequence) {
        URL url = null;
        try {
            url = new URL("https://financialmodelingprep.com/api/v3/search?query=" + charSequence + "&limit=10&exchange=NASDAQ&apikey=75a24f9231ff66df465791c318049e2a");
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
            for(int i=0; i < json.length(); i++) {
                JSONObject jsonobject = json.getJSONObject(i);
                String id       = jsonobject.getString("symbol");
                String title    = jsonobject.getString("name");
                tickerResults.add(id + " : " + title);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(CharSequence charSequence) {
        return false;
    }
}