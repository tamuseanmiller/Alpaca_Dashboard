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

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.domain.polygon.websocket.PolygonStreamMessage;
import net.jacobpeterson.domain.polygon.websocket.quote.QuoteMessage;
import net.jacobpeterson.polygon.PolygonAPI;
import net.jacobpeterson.polygon.websocket.listener.PolygonStreamListenerAdapter;
import net.jacobpeterson.polygon.websocket.message.PolygonStreamMessageType;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ClientAuthentication;
import net.openid.appauth.TokenRequest;
import net.openid.appauth.TokenResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static AtomicReference<String> ticker;
    private DashboardFragment dashboardFragment;
    public static SearchFragment searchFragment;
    private ProfileFragment profileFragment;
    public static NoSwipePager viewPager;
    public static EmergencyFragment emergencyFragment;
    public static int lastItem = 0;
    public static BottomBarAdapter pagerAdapter;
    public static DatabaseReference myRef;
    public static AuthorizationResponse resp;
    private Thread t1;
    private SharedPreferencesManager prefs;

    // onCreate
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        prefs = new SharedPreferencesManager(this);

        Utils.startTheme(MainActivity.this, prefs.retrieveInt("theme", Utils.THEME_DEFAULT));

        ClassLoader classLoader = MainActivity.class.getClassLoader();
        assert classLoader != null;
        URL resource = classLoader.getResource("org/apache/http/message/BasicLineFormatter.class");
        System.out.println(resource);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean check = false;

        if (prefs.retrieveString("auth_token", "NULL").equals("NULL") || prefs.retrieveString("polygon_id", "NULL").equals("NULL")) {
            performAuthentication();
            check = true;
        }

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

//        runOnUiThread(() -> {

            // Create viewpager for bottombar fragments
            viewPager = findViewById(R.id.viewPager);
            viewPager.setPagingEnabled(false);
            pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());

            BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
            bottomNavigation.bringToFront();
            bottomNavigation.setOnNavigationItemSelectedListener(this);

//        });

        t1 = new Thread(() -> {

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

        if (!prefs.retrieveString("auth_token", "NULL").equals("NULL") &&
                !prefs.retrieveString("polygon_id", "NULL").equals("NULL") &&
                !check) {
            t1.start();
        }

//        t1.start();
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

    public void performAuthentication() {
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
            return;
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

        AuthorizationService authService = new AuthorizationService(this);

        authService.performTokenRequest(tokenRequest, (tokenResponse1, ex1) -> {

            if (tokenResponse1 != null) {

                // exchange succeeded
                System.out.println("Authentication Done");
                authState.updateAfterTokenResponse(tokenResponse1, ex1);
                prefs.storeString("auth_token", tokenResponse1.accessToken);
                System.out.println(tokenResponse1.accessToken);
                authenticationResponse.set(tokenResponse1.accessToken);

                // Fetch Polygon Id and add to SharedPreferences
                try {

                    // Create Client and request
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://api.alpaca.markets/oauth/token")
                            .addHeader("Authorization", "Bearer " + tokenResponse1.accessToken).build();

                    // Catch response after execution
                    Response response = client.newCall(request).execute();

                    // Convert response to json to find the field
                    JSONObject jsonObject = new JSONObject(Objects.requireNonNull(response.body()).string());
                    System.out.println(jsonObject.get("id"));
                    prefs.storeString("polygon_id", jsonObject.get("id").toString());

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                // Authenticate Polygon as well
//                HttpResponse<JsonNode> nodeHttpResponse = null;
//                try {
//                    nodeHttpResponse = Unirest.get("https://api.alpaca.markets/oauth/token")
//                            .header("Authorization", "Bearer " + tokenResponse1.accessToken).asJson();
//                } catch (UnirestException e) {
//                    e.printStackTrace();
//                }

                // Add Polygon ID to SharedPreferences
//                try {
//                    prefs.storeString("polygon_id", nodeHttpResponse.getBody().getObject().get("id").toString());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

            } else {
                // authorization failed, check ex for more details
                System.out.println(ex1);
            }

            if (prefs.retrieveString("auth_token", "NULL").equals("NULL") || prefs.retrieveString("polygon_id", "NULL").equals("NULL")) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            t1.start();

        });


    }

}