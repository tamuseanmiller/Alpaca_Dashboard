package com.seanmiller.alpacadashboard

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.BillingProcessor.IBillingHandler
import com.anjlab.android.iab.v3.TransactionDetails
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import net.jacobpeterson.alpaca.AlpacaAPI
import net.jacobpeterson.alpaca.enums.account.AccountStatus
import net.jacobpeterson.alpaca.enums.api.DataAPIType
import net.jacobpeterson.alpaca.enums.api.EndpointAPIType
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException
import net.jacobpeterson.domain.alpaca.account.Account
import net.openid.appauth.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*
import java.util.concurrent.atomic.AtomicReference

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, IBillingHandler {
    private var dashboardFragment: DashboardFragment? = null
    private var profileFragment: ProfileFragment? = null
    private var t1: Thread? = null
    private var prefs: SharedPreferencesManager? = null
    private var bp: BillingProcessor? = null
    private var bottomNavigation: BottomNavigationView? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!bp!!.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
        for (fragment in supportFragmentManager.fragments) {
            fragment?.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onResume() {
        if (pagerAdapter != null) {
            pagerAdapter!!.notifyDataSetChanged()
        }
        super.onResume()
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        prefs = SharedPreferencesManager(this)
        Utils.startTheme(this@MainActivity, prefs!!.retrieveInt("theme", Utils.THEME_DEFAULT))

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check to see if pending OAuth
        if (prefs!!.retrieveString("auth_token", "NULL") == "NULL") {
            finishAuthentication()
        }

        // Set up billing client
        bp = BillingProcessor(this, Properties.playLicenseKey, this)
        bp!!.initialize()

        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        myRef = database.getReference();

        // Create viewpager for bottombar fragments
        viewPager = findViewById(R.id.viewPager)
        viewPager?.setPagingEnabled(false)
        pagerAdapter = BottomBarAdapter(supportFragmentManager)
        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation?.bringToFront()
        bottomNavigation?.setOnNavigationItemSelectedListener(this)
        t1 = Thread {

            // Initialize fragments and pagerAdapter
            dashboardFragment = DashboardFragment()
            searchFragment = SearchFragment()
            profileFragment = ProfileFragment()
            emergencyFragment = EmergencyFragment()
            pagerAdapter!!.addFragments(dashboardFragment!!)
            pagerAdapter!!.addFragments(searchFragment!!)
            pagerAdapter!!.addFragments(profileFragment!!)
            pagerAdapter!!.addFragments(emergencyFragment!!)
            val informationFragment = InformationFragment()
            pagerAdapter!!.addFragments(informationFragment)
            runOnUiThread {
                viewPager?.adapter = pagerAdapter
                viewPager?.currentItem = DASHBOARD_FRAGMENT
            }
        }
        if (prefs!!.retrieveString("auth_token", "NULL") != "NULL")
            t1!!.start()

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("NonConstantResourceId")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        // Switch for each of the fragments
        when (item.itemId) {
            R.id.dashboard_page -> {
                lastItem = viewPager!!.currentItem
                viewPager!!.currentItem = DASHBOARD_FRAGMENT
                return true
            }
            R.id.search_page -> {
                lastItem = viewPager!!.currentItem
                viewPager!!.currentItem = SEARCH_FRAGMENT
                return true
            }
            R.id.profile_page -> {
                lastItem = viewPager!!.currentItem
                viewPager!!.currentItem = PROFILE_FRAGMENT
                profileFragment!!.animateWhenCalled()
                return true
            }
            R.id.emergency_page -> {
                lastItem = viewPager!!.currentItem
                viewPager!!.currentItem = EMERGENCY_FRAGMENT
                return true
            }
        }
        return false
    }

    // Makes sure that swiping back will always go to the last focused item in the viewpager
    override fun onBackPressed() {

        // If at dashboard go to home, other wise go back to last fragment
        if (viewPager!!.currentItem != DASHBOARD_FRAGMENT) {
            viewPager!!.setCurrentItem(lastItem, false)
            bottomNavigation!!.menu.getItem(lastItem).isChecked = true
        } else {
            finish()
        }
    }

    // Finish authentication method
    private fun finishAuthentication() {

        // Reinitialize the service configuration for continued use
        val serviceConfig = AuthorizationServiceConfiguration(
                Uri.parse("https://app.alpaca.markets/oauth/authorize"),  // authorization endpoint
                Uri.parse("https://api.alpaca.markets/oauth/token")) // token endpoint

        // Create Authstate for further reference after authorization
        val authState = AuthStateManager(this)
        resp = AuthorizationResponse.fromIntent(intent)
        val ex = AuthorizationException.fromIntent(intent)

        // Check if the response received was null, and if so then go back to LoginActivity
        if (resp != null) {
            // authorization completed
            authState.updateAfterAuthorization(resp, ex)
        } else {
            Log.v("Exception", "Failed: ", ex)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
            // authorization failed, check ex for more details
        }

        // Build the authorization request
        val tokenRequestBuilder = TokenRequest.Builder(
                serviceConfig,
                Properties.oAuthID)
        val secret: MutableMap<String, String> = HashMap()
        secret["client_secret"] = Properties.secretID

        // Finish building the token request
        val tokenRequest = tokenRequestBuilder
                .setGrantType("authorization_code")
                .setAdditionalParameters(secret)
                .setAuthorizationCode(resp!!.authorizationCode)
                .setRedirectUri(Uri.parse(Properties.redirectURI))
                .build()

        // Perform the request for the token
        val authenticationResponse = AtomicReference<String?>()
        val authService = AuthorizationService(this)
        authService.performTokenRequest(tokenRequest) { tokenResponse: TokenResponse?, ex1: AuthorizationException? ->

            // Check to see if a token was returned
            if (tokenResponse != null) {

                // Exchange succeeded
                println("Authentication Done")
                authState.updateAfterTokenResponse(tokenResponse, ex1)
                println(tokenResponse.accessToken)
                authenticationResponse.set(tokenResponse.accessToken)

                // Fetch account using alpaca-java
                var account: Account? = null
                val alpacaAPI = AlpacaAPI(null, null, tokenResponse.accessToken, EndpointAPIType.PAPER, DataAPIType.IEX)
                try {
                    account = alpacaAPI.account
                } catch (e: AlpacaAPIRequestException) {
                    e.printStackTrace()
                }

                // Check for if account is funded
                if (account != null && account.portfolioValue.toFloat() > 0 && account.status.equals(AccountStatus.ACTIVE)) {
                    prefs!!.storeString("auth_token", tokenResponse.accessToken)

                    // If account is not funded, send back to Login Activity with dialog
                } else {
                    val dialogBuilder = AtomicReference(MaterialAlertDialogBuilder(this, R.style.DialogThemePositive))
                    dialogBuilder.get().setTitle("Account is not Funded")
                    dialogBuilder.get().setMessage("Your Alpaca Account is not funded, and therefore cannot be used to authenticate.")
                    dialogBuilder.get().setNeutralButton("Cancel") { dialogInterface: DialogInterface, _: Int ->
                        val intent = Intent(this@MainActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                        prefs!!.storeString("auth_token", "NULL")
                        dialogInterface.dismiss()
                    }
                    dialogBuilder.get().create().show()
                }
            } else {

                // authorization failed, check ex for more details
                println(ex1)
            }

            // Check if both are still the default values, then send back to LoginActivity
            // This helps for crashes
            if (prefs!!.retrieveString("auth_token", "NULL") == "NULL") {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

            // Start main thread, after authorization is complete
            t1!!.start()
        }
    }

    // IBillingHandler implementation
    override fun onBillingInitialized() {
        /*
         * Called when BillingProcessor was initialized and it's ready to purchase
         */

        // Check to see if premium has been purchased
        val purchaseResult = bp!!.loadOwnedPurchasesFromGoogle()
        if (purchaseResult) {
            val subscriptionTransactionDetails = bp!!.getSubscriptionTransactionDetails("premium_sub")
            if (subscriptionTransactionDetails != null) {
                //User is still subscribed
                prefs!!.storeBoolean("premium", true)
                Log.d("BILLING ", "Subscription is valid")
            } else {
                //Not subscribed
                prefs!!.storeBoolean("premium", false)
                Log.d("BILLING ", "Subscription is NOT valid")
            }
        }
    }

    override fun onProductPurchased(productId: String, details: TransactionDetails?) {
        /*
         * Called when requested PRODUCT ID was successfully purchased
         */
        prefs!!.storeBoolean("premium", true)
        SearchFragment.startNews(prefs!!, this)
        SearchFragment.searchCard?.visibility = View.INVISIBLE
    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {
        /*
         * Called when some error occurred. See Constants class for more details
         *
         * Note - this includes handling the case where the user canceled the buy dialog:
         * errorCode = Constants.BILLING_RESPONSE_RESULT_USER_CANCELED
         */
        prefs!!.storeBoolean("premium", false)
        Log.v("Billing Error", errorCode.toString(), error)
    }

    override fun onPurchaseHistoryRestored() {
        /*
         * Called when purchase history was restored and the list of all owned PRODUCT ID's
         * was loaded from Google Play
         */
        prefs!!.storeBoolean("premium", bp!!.isPurchased("premium_sub"))
    }

    public override fun onDestroy() {
        if (bp != null) {
            bp!!.release()
        }
        super.onDestroy()
    }

    companion object {
        var ticker: AtomicReference<String>? = null
        var searchFragment: SearchFragment? = null
        var viewPager: NoSwipePager? = null
        var emergencyFragment: EmergencyFragment? = null
        var lastItem = 0
        var pagerAdapter: BottomBarAdapter? = null

        //    public static DatabaseReference myRef;
        var resp: AuthorizationResponse? = null
        const val DASHBOARD_FRAGMENT = 0
        const val SEARCH_FRAGMENT = 1
        const val PROFILE_FRAGMENT = 2
        const val EMERGENCY_FRAGMENT = 3
        const val INFORMATION_FRAGMENT = 4
    }
}