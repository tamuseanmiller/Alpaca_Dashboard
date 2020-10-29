package com.seanmiller.alpacadashboard;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import com.google.android.material.button.MaterialButton;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.RedirectUriReceiverActivity;
import net.openid.appauth.ResponseTypeValues;
import net.openid.appauth.TokenRequest;
import net.openid.appauth.TokenResponse;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    public static AuthorizationService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (!new SharedPreferencesManager(this).retrieveString("auth", "NULL").equals("NULL")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        Utils.startTheme(this, new SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_DEFAULT));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MaterialButton button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(v -> {
            authenticate();
        });
    }

    public void authenticate() {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        Intent intent2 = new Intent(LoginActivity.this, LoginActivity.class);
        PendingIntent ifSuccess = PendingIntent.getActivity(LoginActivity.this, 0, intent, 0);
        PendingIntent ifFail = PendingIntent.getActivity(LoginActivity.this, 0, intent2, 0);

        AuthorizationServiceConfiguration serviceConfig =
                new AuthorizationServiceConfiguration(
                        Uri.parse("https://app.alpaca.markets/oauth/authorize"), // authorindeization endpoint
                        Uri.parse("https://api.alpaca.markets/oauth/token")); // token endpoint

        AuthorizationRequest.Builder authRequestBuilder =
                new AuthorizationRequest.Builder(
                        serviceConfig, // the authorization service configuration
                        Properties.getOAuthID(), // the client ID, typically pre-registered and static
                        ResponseTypeValues.CODE, // the response_type value: we want a code
                        Uri.parse(Properties.getRedirectURI())); // the redirect URI to which the auth response is sent

        AuthorizationRequest authRequest = authRequestBuilder
                .setScope("account:write trading data")
                .build();

        authService = new AuthorizationService(this);


        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(getColor(R.color.yellow));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        authService.performAuthorizationRequest(authRequest, ifSuccess, ifFail, customTabsIntent);

    }
}