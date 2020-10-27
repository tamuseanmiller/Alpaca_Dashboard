package com.bedefined.alpaca_dashboard;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.robinhood.ticker.TickerView;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.websocket.listener.AlpacaStreamListenerAdapter;
import net.jacobpeterson.alpaca.websocket.message.AlpacaStreamMessageType;
import net.jacobpeterson.domain.alpaca.websocket.AlpacaStreamMessage;
import net.jacobpeterson.domain.alpaca.websocket.account.AccountUpdateMessage;
import net.jacobpeterson.domain.alpaca.websocket.trade.TradeUpdateMessage;
import net.jacobpeterson.domain.polygon.websocket.PolygonStreamMessage;
import net.jacobpeterson.domain.polygon.websocket.quote.QuoteMessage;
import net.jacobpeterson.polygon.PolygonAPI;
import net.jacobpeterson.polygon.websocket.listener.PolygonStreamListenerAdapter;
import net.jacobpeterson.polygon.websocket.message.PolygonStreamMessageType;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener /*implements RecyclerViewAdapter.ItemClickListener, View.OnClickListener*/ {

    public static AtomicReference<String> ticker;
    private Properties props = new Properties();
    public static DashboardFragment dashboardFragment;
    public static SearchFragment searchFragment;
    public static ProfileFragment profileFragment;
    private BottomNavigationView bottomNavigation;
    public static NoSwipePager viewPager;
    public static EmergencyFragment emergencyFragment;
    public static int lastItem = 0;
    public static BottomBarAdapter pagerAdapter;
    public static DatabaseReference myRef;

    // Streams ticker data from polygon
    public void streamStockData(PolygonAPI polygonAPI, AtomicReference<String> ticker, TickerView tickerV) {

        props.setProperties();

        polygonAPI.addPolygonStreamListener(new PolygonStreamListenerAdapter(String.valueOf(ticker), PolygonStreamMessageType.QUOTE) {

            float askingPrice = 0;

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onStreamUpdate(PolygonStreamMessageType streamMessageType, PolygonStreamMessage streamMessage) {
                askingPrice = ((QuoteMessage) streamMessage).getAp().floatValue();

                Thread thread = new Thread(() -> {

                    double amount = Double.parseDouble(String.valueOf(askingPrice));
                    DecimalFormat formatter = new DecimalFormat("#,###.00");

                    // Render tickerView
                    runOnUiThread(() -> {
                        tickerV.setText("$" + formatter.format(amount));
                    });

                });
                thread.start();
            }
        });
    }

    public void streamAccountData(AlpacaAPI alpacaAPI) {

        props.setProperties();

        // Register explicitly for ACCOUNT_UPDATES and ORDER_UPDATES Messages via stream listener
        alpacaAPI.addAlpacaStreamListener(new AlpacaStreamListenerAdapter(
                AlpacaStreamMessageType.ACCOUNT_UPDATES,
                AlpacaStreamMessageType.TRADE_UPDATES) {

            public void onStreamUpdate(AlpacaStreamMessageType streamMessageType, AlpacaStreamMessage streamMessage) {
                switch (streamMessageType) {
                    case ACCOUNT_UPDATES:
                        AccountUpdateMessage accountUpdateMessage = (AccountUpdateMessage) streamMessage;
                        System.out.println("\nReceived Account Update: \n\t" +
                                accountUpdateMessage.toString().replace(",", ",\n\t"));
                        break;
                    case TRADE_UPDATES:
                        TradeUpdateMessage tradeUpdateMessage = (TradeUpdateMessage) streamMessage;
                        System.out.println("\nReceived Order Update: \n\t" +
                                tradeUpdateMessage.toString().replace(",", ",\n\t"));
                        break;
                }
            }
        });
    }

    // onCreate
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Utils.startTheme(MainActivity.this, new SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_DEFAULT));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        // Create viewpager for bottombar fragments
        viewPager = findViewById(R.id.viewPager);
        viewPager.setPagingEnabled(false);
        pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.bringToFront();
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        Thread t1 = new Thread(() -> {

            dashboardFragment = new DashboardFragment();
            searchFragment = new SearchFragment();
            profileFragment = new ProfileFragment();
            emergencyFragment = new EmergencyFragment();
            pagerAdapter.addFragments(dashboardFragment);
            pagerAdapter.addFragments(searchFragment);
            pagerAdapter.addFragments(profileFragment);
            pagerAdapter.addFragments(emergencyFragment);

            runOnUiThread(() -> {
                viewPager.setAdapter(pagerAdapter);
                viewPager.setCurrentItem(0);
            });
        });
        t1.start();

//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.containerFrag, searchFragment);
//        transaction.add(R.id.containerFrag, stockFragment);
//        transaction.add(R.id.containerFrag, dashboardFragment);
//        transaction.add(R.id.containerFrag, profileFragment);
//                    transaction.replace(R.id.containerFrag, fragment, fragment.getClass().getSimpleName());
//        transaction.addToBackStack(dashboardFragment.getClass().getSimpleName());
//        transaction.commit();

//        super.onCreate(savedInstanceState);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();

        // Switch for each of the fragments
        switch (item.getItemId()) {
            case R.id.dashboard_page:
                lastItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(0);
                return true;

            case R.id.search_page:
                lastItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(1);
                return true;

            case R.id.profile_page:
                lastItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(2);
                profileFragment.animateWhenCalled();
                return true;

            case R.id.emergency_page:
                lastItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(3);
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {

        // If at dashboard go to home, other wise go back to last fragment
        if (viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(lastItem, false);

        } else {
            finish();
        }

    }

}