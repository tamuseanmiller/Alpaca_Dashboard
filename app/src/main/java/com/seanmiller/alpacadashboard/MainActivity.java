package com.seanmiller.alpacadashboard;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.TokenRequest;
import net.openid.appauth.TokenResponse;

import org.json.JSONException;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.seanmiller.alpacadashboard.LoginActivity.authService;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

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
    public static AuthorizationResponse resp;

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

        if (new SharedPreferencesManager(this).retrieveString("auth", "NULL").equals("NULL")) {
            try {
                performAuthentication();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }

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
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

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

    public void performAuthentication() throws UnirestException {
        AuthorizationServiceConfiguration serviceConfig =
                new AuthorizationServiceConfiguration(
                        Uri.parse("https://app.alpaca.markets/oauth/authorize"), // authorindeization endpoint
                        Uri.parse("https://api.alpaca.markets/oauth/token")); // token endpoint

        // Create Authstate for further reference after authorization
        AuthStateManager authState = new AuthStateManager(this);
        resp = AuthorizationResponse.fromIntent(getIntent());
        AuthorizationException ex = AuthorizationException.fromIntent(getIntent());

        if (resp != null) {
            // authorization completed
            authState.updateAfterAuthorization(resp, ex);

        } else {
            System.out.println("Failed: " + ex);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            // authorization failed, check ex for more details
        }

        // Build the authorization request
        TokenRequest.Builder tokenRequestBuilder =
                new TokenRequest.Builder(
                        serviceConfig,
                        Properties.getOAuthID());
        Map<String, String> secret = new HashMap<>();
        secret.put("client_secret", Properties.getSecretID());

        // Finish building the token request
        TokenRequest tokenRequest = tokenRequestBuilder
                .setGrantType("authorization_code")
                .setAdditionalParameters(secret)
                .setAuthorizationCode(resp.authorizationCode)
                .setRedirectUri(Uri.parse(Properties.getRedirectURI()))
                .build();


        AtomicReference<String> authenticationResponse = new AtomicReference<>();

        authService.performTokenRequest(tokenRequest, (tokenResponse1, ex1) -> {

            if (tokenResponse1 != null) {

                // exchange succeeded
                System.out.println("Authentication Done");
                authState.updateAfterTokenResponse(tokenResponse1, ex1);
                new SharedPreferencesManager(this).storeString("auth", tokenResponse1.accessToken);
                authenticationResponse.set(tokenResponse1.accessToken);

                // Authenticate Polygon as well
                HttpResponse<JsonNode> nodeHttpResponse = null;
                try {
                    nodeHttpResponse = Unirest.get("https://api.alpaca.markets/oauth/token")
                            .header("Authorization", "Bearer " + authenticationResponse).asJson();
                } catch (UnirestException e) {
                    e.printStackTrace();
                }

                // Add to SharedPreferences
                try {
                    new SharedPreferencesManager(this).storeString("id", nodeHttpResponse.getBody().getObject().get("id").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                // authorization failed, check ex for more details
                System.out.println(ex1);
            }
        });


    }

}