package com.seanmiller.alpacadashboard

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.google.android.material.button.MaterialButton
import com.seanmiller.alpacadashboard.LoginActivity
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // If already authenticated
        if (SharedPreferencesManager(this).retrieveString("auth_token", "NULL") != "NULL" ||
                SharedPreferencesManager(this).retrieveString("polygon_id", "NULL") != "NULL") {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }
        Utils.startTheme(this, SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_DEFAULT))
        setContentView(R.layout.activity_login)

        // On authenticate click
        val button_login = findViewById<MaterialButton>(R.id.button_login)
        button_login.setOnClickListener { authenticate() }
    }

    fun authenticate() {

        // Create success and fail PendingIntents to next Activity
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        val intent2 = Intent(this@LoginActivity, LoginActivity::class.java)
        val ifSuccess = PendingIntent.getActivity(this@LoginActivity, 0, intent, 0)
        val ifFail = PendingIntent.getActivity(this@LoginActivity, 0, intent2, 0)

        // Initialze ServiceConfig for the different OAuth Endpoints
        val serviceConfig = AuthorizationServiceConfiguration(
                Uri.parse("https://app.alpaca.markets/oauth/authorize"),  // authorization endpoint
                Uri.parse("https://api.alpaca.markets/oauth/token")) // token endpoint

        // Start building request to obtain initial key
        val authRequestBuilder = AuthorizationRequest.Builder(
                serviceConfig,  // the authorization service configuration
                Properties.oAuthID,  // the client ID, typically pre-registered and static
                ResponseTypeValues.CODE,  // the response_type value: we want a code
                Uri.parse(Properties.redirectURI)) // the redirect URI to which the auth response is sent

        // Build the request in custom tabs and switch activities with PendingIntent
        val authRequest = authRequestBuilder
                .setScope("account:write trading data")
                .build()
        val authService = AuthorizationService(this)
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(getColor(R.color.yellow))
        val customTabsIntent = builder.build()
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        authService.performAuthorizationRequest(authRequest, ifSuccess, ifFail, customTabsIntent)
    }
}