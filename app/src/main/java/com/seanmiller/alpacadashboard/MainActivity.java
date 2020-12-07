package com.seanmiller.alpacadashboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.enums.AccountStatus;
import net.jacobpeterson.alpaca.enums.OrderTimeInForce;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.domain.alpaca.account.Account;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BillingProcessor.IBillingHandler {

    public static AtomicReference<String> ticker;
    private DashboardFragment dashboardFragment;
    public static SearchFragment searchFragment;
    private ProfileFragment profileFragment;
    public static NoSwipePager viewPager;
    public static EmergencyFragment emergencyFragment;
    public static int lastItem = 0;
    public static BottomBarAdapter pagerAdapter;
//    public static DatabaseReference myRef;
    public static AuthorizationResponse resp;
    private Thread t1;
    private SharedPreferencesManager prefs;
    private BillingProcessor bp;

    public static final int DASHBOARD_FRAGMENT = 0;
    public static final int SEARCH_FRAGMENT = 1;
    public static final int PROFILE_FRAGMENT = 2;
    public static final int EMERGENCY_FRAGMENT = 3;
    public static final int INFORMATION_FRAGMENT = 4;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        prefs = new SharedPreferencesManager(this);

        Utils.startTheme(MainActivity.this, prefs.retrieveInt("theme", Utils.THEME_DEFAULT));

        // Set up billing client
        bp = new BillingProcessor(this, Properties.getPlayLicenseKey(), this);
        bp.initialize();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean check = false;

        // Check to see if pending OAuth
        if (prefs.retrieveString("auth_token", "NULL").equals("NULL") ||
                prefs.retrieveString("polygon_id", "NULL").equals("NULL")) {

            finishAuthentication();
            check = true;
        }

        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        myRef = database.getReference();

        // Create viewpager for bottombar fragments
        viewPager = findViewById(R.id.viewPager);
        viewPager.setPagingEnabled(false);
        pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.bringToFront();
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        t1 = new Thread(() -> {

            // Initialize fragments and pagerAdapter
            dashboardFragment = new DashboardFragment();
            searchFragment = new SearchFragment();
            profileFragment = new ProfileFragment();
            emergencyFragment = new EmergencyFragment();
            pagerAdapter.addFragments(dashboardFragment);
            pagerAdapter.addFragments(searchFragment);
            pagerAdapter.addFragments(profileFragment);
            pagerAdapter.addFragments(emergencyFragment);

            InformationFragment informationFragment = new InformationFragment();
            pagerAdapter.addFragments(informationFragment);

            runOnUiThread(() -> {
                viewPager.setAdapter(pagerAdapter);
                viewPager.setCurrentItem(DASHBOARD_FRAGMENT);
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
                viewPager.setCurrentItem(DASHBOARD_FRAGMENT);
                return true;

            case R.id.search_page:
                lastItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(SEARCH_FRAGMENT);
                return true;

            case R.id.profile_page:
                lastItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(PROFILE_FRAGMENT);
                profileFragment.animateWhenCalled();
                return true;

            case R.id.emergency_page:
                lastItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(EMERGENCY_FRAGMENT);
                return true;
        }
        return false;
    }

    // Makes sure that swiping back will always go to the last focused item in the viewpager
    @Override
    public void onBackPressed() {

        // If at dashboard go to home, other wise go back to last fragment
        if (viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(lastItem, false);

        } else {
            finish();
        }

    }

    // Finish authentication method
    public void finishAuthentication() {

        // Reinitialize the service configuration for continued use
        AuthorizationServiceConfiguration serviceConfig =
                new AuthorizationServiceConfiguration(
                        Uri.parse("https://app.alpaca.markets/oauth/authorize"), // authorindeization endpoint
                        Uri.parse("https://api.alpaca.markets/oauth/token")); // token endpoint

        // Create Authstate for further reference after authorization
        AuthStateManager authState = new AuthStateManager(this);
        resp = AuthorizationResponse.fromIntent(getIntent());
        AuthorizationException ex = AuthorizationException.fromIntent(getIntent());

        // Check if the response received was null, and if so then go back to LoginActivity
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

        // Perform the request for the token
        AtomicReference<String> authenticationResponse = new AtomicReference<>();
        AuthorizationService authService = new AuthorizationService(this);
        authService.performTokenRequest(tokenRequest, (tokenResponse, ex1) -> {

            // Check to see if a token was returned
            if (tokenResponse != null) {

                // Exchange succeeded
                System.out.println("Authentication Done");
                authState.updateAfterTokenResponse(tokenResponse, ex1);
                prefs.storeString("auth_token", tokenResponse.accessToken);
                System.out.println(tokenResponse.accessToken);
                authenticationResponse.set(tokenResponse.accessToken);

                // Fetch account using alpaca-java
                Account account = null;
                AlpacaAPI alpacaAPI = new AlpacaAPI(tokenResponse.accessToken);
                try {
                    account = alpacaAPI.getAccount();
                } catch (AlpacaAPIRequestException e) {
                    e.printStackTrace();
                }

                // Check for if account is funded
                if (account != null && Float.parseFloat(account.getPortfolioValue()) > 0 && account.getStatus() == AccountStatus.ACTIVE) {

                    // Fetch Polygon Id and add to SharedPreferences
                    try {

                        // Create Client and request
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("https://api.alpaca.markets/oauth/token")
                                .addHeader("Authorization", "Bearer " + tokenResponse.accessToken).build();

                        // Catch response after execution
                        Response response = client.newCall(request).execute();

                        // Convert response to json to find the field
                        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(response.body()).string());
                        System.out.println(jsonObject.get("id"));
                        prefs.storeString("polygon_id", jsonObject.get("id").toString());

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    // If account is not funded, send back to Login Activity with dialog
                } else {

                    AtomicReference<MaterialAlertDialogBuilder> dialogBuilder = new AtomicReference<>(new MaterialAlertDialogBuilder(this, R.style.DialogThemePositive));
                    dialogBuilder.get().setTitle("Account is not Funded");
                    dialogBuilder.get().setMessage("Your Alpaca Account is not funded, and therefore cannot be used to authenticate.");
                    dialogBuilder.get().setNeutralButton("Cancel", (dialogInterface, i) -> {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        prefs.storeString("auth_token", "NULL");
                        dialogInterface.dismiss();
                    });
                    dialogBuilder.get().create().show();
                }

            } else {

                // authorization failed, check ex for more details
                System.out.println(ex1);
            }

            // Check if both are still the default values, then send back to LoginActivity
            // This helps for crashes
            if (prefs.retrieveString("auth_token", "NULL").equals("NULL") || prefs.retrieveString("polygon_id", "NULL").equals("NULL")) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            // Start main thread, after authorization is complete
            t1.start();

        });


    }

    // IBillingHandler implementation

    @Override
    public void onBillingInitialized() {
        /*
         * Called when BillingProcessor was initialized and it's ready to purchase
         */

        // Check to see if premium has been purchased
        prefs.storeBoolean("premium", bp.isPurchased("premium_sub"));
    }

    @Override
    public void onProductPurchased(@NonNull String productId, TransactionDetails details) {
        /*
         * Called when requested PRODUCT ID was successfully purchased
         */
        prefs.storeBoolean("premium", true);
        searchFragment = new SearchFragment();
    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
        /*
         * Called when some error occurred. See Constants class for more details
         *
         * Note - this includes handling the case where the user canceled the buy dialog:
         * errorCode = Constants.BILLING_RESPONSE_RESULT_USER_CANCELED
         */
        prefs.storeBoolean("premium", false);
        Log.v("Billing Error", String.valueOf(errorCode), error);
    }

    @Override
    public void onPurchaseHistoryRestored() {
        /*
         * Called when purchase history was restored and the list of all owned PRODUCT ID's
         * was loaded from Google Play
         */
        prefs.storeBoolean("premium", bp.isPurchased("premium_sub"));
    }

    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }
}