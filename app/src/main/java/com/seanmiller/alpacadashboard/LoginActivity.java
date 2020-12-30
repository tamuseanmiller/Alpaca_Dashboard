package com.seanmiller.alpacadashboard;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import com.google.android.material.button.MaterialButton;

import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ResponseTypeValues;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // If already authenticated
        if (!new SharedPreferencesManager(this).retrieveString("auth_token", "NULL").equals("NULL") ||
            !new SharedPreferencesManager(this).retrieveString("polygon_id", "NULL").equals("NULL")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Utils.startTheme(this, new SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_DEFAULT));
        setContentView(R.layout.activity_login);

        // On authenticate click
        MaterialButton button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(v -> {
            authenticate();
        });
    }

    public void authenticate() {

        // Create success and fail PendingIntents to next Activity
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        Intent intent2 = new Intent(LoginActivity.this, LoginActivity.class);
        PendingIntent ifSuccess = PendingIntent.getActivity(LoginActivity.this, 0, intent, 0);
        PendingIntent ifFail = PendingIntent.getActivity(LoginActivity.this, 0, intent2, 0);

        // Initialze ServiceConfig for the different OAuth Endpoints
        AuthorizationServiceConfiguration serviceConfig =
                new AuthorizationServiceConfiguration(
                        Uri.parse("https://app.alpaca.markets/oauth/authorize"), // authorization endpoint
                        Uri.parse("https://api.alpaca.markets/oauth/token")); // token endpoint

        // Start building request to obtain initial key
        AuthorizationRequest.Builder authRequestBuilder =
                new AuthorizationRequest.Builder(
                        serviceConfig, // the authorization service configuration
                        Properties.getOAuthID(), // the client ID, typically pre-registered and static
                        ResponseTypeValues.CODE, // the response_type value: we want a code
                        Uri.parse(Properties.getRedirectURI())); // the redirect URI to which the auth response is sent

        // Build the request in custom tabs and switch activities with PendingIntent
        AuthorizationRequest authRequest = authRequestBuilder
                .setScope("account:write trading data")
                .build();

        AuthorizationService authService = new AuthorizationService(this);


        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(getColor(R.color.yellow));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        authService.performAuthorizationRequest(authRequest, ifSuccess, ifFail, customTabsIntent);

    }
}