package com.seanmiller.alpacadashboard

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.BillingProcessor.IBillingHandler
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.common.collect.ImmutableMap
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.lapism.search.internal.SearchLayout
import com.lapism.search.internal.SearchLayout.OnMicClickListener
import com.lapism.search.internal.SearchLayout.OnNavigationClickListener
import com.lapism.search.widget.MaterialSearchView
import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.Unirest
import com.mashape.unirest.http.exceptions.UnirestException
import io.cabriole.decorator.LinearMarginDecoration
import net.jacobpeterson.alpaca.AlpacaAPI
import net.jacobpeterson.alpaca.enums.api.DataAPIType
import net.jacobpeterson.alpaca.enums.api.EndpointAPIType
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException
import net.jacobpeterson.domain.alpaca.position.Position
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class SearchFragment : Fragment(), SearchLayout.OnQueryTextListener, SearchableAdapter.ItemClickListener /*, BillingProcessor.IBillingHandler*/ {
    private var materialSearch: MaterialSearchView? = null
    private var searchableAdapter: SearchableAdapter? = null
    private var prefs: SharedPreferencesManager? = null
    private var bp: BillingProcessor? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.search_fragment, null)
        prefs = SharedPreferencesManager(requireActivity())
        bp = BillingProcessor(requireActivity(), Properties.playLicenseKey, requireActivity() as IBillingHandler)
        bp!!.initialize()
        materialSearch = mView.findViewById(R.id.material_search_view)
        materialSearch?.setAdapterLayoutManager(LinearLayoutManager(activity))

        // Set themes
        val typedValue = TypedValue()
        requireActivity().theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
        val color = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
        requireActivity().theme.resolveAttribute(R.attr.colorAccentTransparent, typedValue, true)
        val strokeColor = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
        requireActivity().theme.resolveAttribute(R.attr.colorPrimaryLight, typedValue, true)
        val colorLight = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
        materialSearch?.setBackgroundColor(color)
        materialSearch?.setBackgroundStrokeColor(strokeColor)
        materialSearch?.setBackgroundStrokeWidth(2)
        materialSearch?.setTextColor(colorLight)
        materialSearch?.setBackgroundRadius(60f)
        materialSearch?.setTextHintColor(colorLight)
        materialSearch?.setNavigationIconColorFilter(colorLight)
        materialSearch?.setTextHint("Stock Ticker")
        materialSearch?.elevation = 3f
        materialSearch?.setClearIconColorFilter(colorLight)
        materialSearch?.setMicIconColorFilter(colorLight)

        // When microphone is clicked
        materialSearch?.setOnMicClickListener(object : OnMicClickListener {
            override fun onMicClick() {
                displaySpeechRecognizer()
            }
        })

        // When search is focused
        materialSearch?.setOnFocusChangeListener(object : SearchLayout.OnFocusChangeListener {

            override fun onFocusChange(hasFocus: Boolean) {
                if (materialSearch?.hasFocus()!!) {
                    materialSearch?.setNavigationIconImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.arrow_left))
                } else {
                    materialSearch?.setNavigationIconImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.magnify))
                }
            }
        })
        materialSearch?.setOnQueryTextListener(this)

        // If the navigation button is clicked
        materialSearch?.setOnNavigationClickListener(object : OnNavigationClickListener {
            override fun onNavigationClick(hasFocus: Boolean) {
                if (materialSearch?.hasFocus()!!) {
                    activity!!.currentFocus!!.clearFocus()
                } else {
                    materialSearch?.requestFocus()
                }
            }
        })

        // News Recycler
        refreshLayout = mView.findViewById(R.id.refreshSearch)
        newsRecycler = mView.findViewById(R.id.newsRecycler)
        searchCard = mView.findViewById(R.id.searchCard)
        if (prefs!!.retrieveBoolean("premium", false)) {
            startNews(prefs!!, activity)
        } else {
            searchCard?.visibility = View.VISIBLE
            val buy_premium: MaterialButton = mView.findViewById(R.id.buy_premium_search)
            buy_premium.setOnClickListener { bp!!.subscribe(requireActivity(), "premium_sub") }
        }
        refreshLayout?.setProgressViewOffset(true, 0, 260)
        refreshLayout?.setOnRefreshListener {
            if (prefs!!.retrieveBoolean("premium", false)) {
                startNews(prefs!!, activity)
            } else {
                refreshLayout?.isRefreshing = false
            }
        }
        return mView
    }

    override fun onQueryTextChange(newText: CharSequence): Boolean {
        val thread = Thread {
            val alpacaAPI = AlpacaAPI(null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)

            // Calls yahoo finance api
            // http://d.yimg.com/aq/autoc?query=y&region=US&lang=en-US&callback=YAHOO.util.ScriptNodeDataSource.callbacks
            var nodeHttpResponse: HttpResponse<String>? = null
            try {
                nodeHttpResponse = Unirest.get("http://d.yimg.com/aq/autoc")
                        .queryString("query", newText.toString())
                        .queryString(ImmutableMap.of<String, Any>("region", "US", "lang", "en-US", "callback", "YAHOO.util.ScriptNodeDataSource.callbacks")).asString()
            } catch (e: UnirestException) {
                e.printStackTrace()
            }

            // Formats the response into an array
            if (nodeHttpResponse != null) {
                val innerJson = nodeHttpResponse.body.substring(42, nodeHttpResponse.body.length - 2)
                val parse = JsonParser()
                val jsonObject = parse.parse(innerJson) as JsonObject
                val stocks = jsonObject["ResultSet"].asJsonObject["Result"].asJsonArray
                val tickers = ArrayList<String>()
                for (i in stocks) {
//                    try {
//                        Asset asset = alpacaAPI.getAssetBySymbol(i.getAsJsonObject().get("symbol").getAsString());
//                        if (asset.getStatus().equals("active")) {
//                    t.name = i.asJsonObject["name"].asString
//                    t.ticker = i.asJsonObject["symbol"].asString
                    tickers.add(i.asJsonObject["symbol"].asString)
                    //                        }
//                    } catch (AlpacaAPIRequestException e) {
//                        e.printStackTrace();
//                    }
                }

                // Sets adapter
                searchableAdapter = SearchableAdapter(tickers)
                searchableAdapter!!.setClickListener(this)
                requireActivity().runOnUiThread { materialSearch!!.setAdapter(searchableAdapter) }
            }
        }
        thread.start()
        return false
    }

    override fun onQueryTextSubmit(charSequence: CharSequence): Boolean {
        val thread = Thread {
            val alpacaAPI = AlpacaAPI(null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)

            // Calls yahoo finance api
            // http://d.yimg.com/aq/autoc?query=y&region=US&lang=en-US&callback=YAHOO.util.ScriptNodeDataSource.callbacks
            var nodeHttpResponse: HttpResponse<String>? = null
            try {
                nodeHttpResponse = Unirest.get("http://d.yimg.com/aq/autoc")
                        .queryString("query", charSequence.toString())
                        .queryString(ImmutableMap.of<String, Any>("region", "US", "lang", "en-US", "callback", "YAHOO.util.ScriptNodeDataSource.callbacks")).asString()
            } catch (e: UnirestException) {
                e.printStackTrace()
            }

            // Formats the response into an array
            if (nodeHttpResponse != null) {
                val innerJson = nodeHttpResponse.body.substring(42, nodeHttpResponse.body.length - 2)
                val parse = JsonParser()
                val jsonObject = parse.parse(innerJson) as JsonObject
                val stocks = jsonObject["ResultSet"].asJsonObject["Result"].asJsonArray
                val tickers = ArrayList<String>()
                for (i in stocks) {
//                    try {
//                        Asset asset = alpacaAPI.getAssetBySymbol(i.getAsJsonObject().get("symbol").getAsString());
//                        if (asset.getStatus().equals("active")) {
                    tickers.add(i.asJsonObject["symbol"].asString)
                    //                        }
//                    } catch (AlpacaAPIRequestException e) {
//                        e.printStackTrace();
//                    }
                }

                // Sets adapter
                searchableAdapter = SearchableAdapter(tickers)
                searchableAdapter!!.setClickListener(this)
                requireActivity().runOnUiThread { materialSearch!!.setAdapter(searchableAdapter) }
            }
        }
        thread.start()
        return true
    }

    // When a search item is chosen, go to stock page
    override fun onItemClick(view: View?, position: Int) {
        DashboardFragment.ticker!!.set(searchableAdapter!!.getItem(position))
        val intentMain = Intent(activity, StockPageActivity::class.java)
        requireActivity().startActivity(intentMain, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
    }

    // Create an intent that can start the Speech Recognizer activity
    private fun displaySpeechRecognizer() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

        // Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE)
    }

    // To set the text query for microphone
    override fun onActivityResult(
            requestCode: Int, resultCode: Int,
            data: Intent?,
    ) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val results: List<String>? = data!!.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS)
            val spokenText = results!![0]

            // Do something with spokenText
            materialSearch!!.setTextQuery(spokenText, false)
        }
    }

    // IBillingHandler implementation
    /*@Override
    public void onBillingInitialized() {
        */
    /*
         * Called when BillingProcessor was initialized and it's ready to purchase
         */
    /*

        // Check to see if premium has been purchased
        prefs.storeBoolean("premium", bp.isPurchased("premium_sub"));
    }

    @Override
    public void onProductPurchased(@NonNull String productId, TransactionDetails details) {
        */
    /*
         * Called when requested PRODUCT ID was successfully purchased
         */
    /*
        prefs.storeBoolean("premium", true);
        startNews();
        searchCard.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
        */
    /*
         * Called when some error occurred. See Constants class for more details
         *
         * Note - this includes handling the case where the user canceled the buy dialog:
         * errorCode = Constants.BILLING_RESPONSE_RESULT_USER_CANCELED
         */
    /*
        prefs.storeBoolean("premium", false);
        Log.v("Billing Error", String.valueOf(errorCode), error);
    }

    @Override
    public void onPurchaseHistoryRestored() {
        */
    /*
         * Called when purchase history was restored and the list of all owned PRODUCT ID's
         * was loaded from Google Play
         */
    /*
        prefs.storeBoolean("premium", bp.isPurchased("premium_sub"));
    }*/
    override fun onDestroy() {
        if (bp != null) {
            bp!!.release()
        }
        super.onDestroy()
    }

    companion object {
        private const val SPEECH_REQUEST_CODE = 0
        private var newsRecycler: RecyclerView? = null
        private var newsAdapter: RecyclerViewAdapterNews? = null
        var searchCard: MaterialCardView? = null
        private var refreshLayout: SwipeRefreshLayout? = null
        fun startNews(prefs: SharedPreferencesManager, activity: Activity?) {
            val thread = Thread {


                // Initialize Alpaca's API
                val alpacaAPI = AlpacaAPI(null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)
                activity!!.runOnUiThread { refreshLayout!!.isRefreshing = true }
                var positions: ArrayList<Position>? = null
                try {
                    positions = alpacaAPI.openPositions
                } catch (e: AlpacaAPIRequestException) {
                    e.printStackTrace()
                }
                var news = ArrayList<JSONObject?>()

                // If no positions, show news for AAPL and AMD
                if (positions == null || positions.isEmpty()) {
                    val stocks = ArrayList<String>()
                    stocks.add("AAPL")
                    stocks.add("GOOGL")
                    for (stock in stocks) {
                        news.addAll(getNews(stock))
                    }
                    news.sortBy { it!!["datetime"].toString() }
                    news.reverse()

                    // If positions exist show news for your positions
                } else {
                    for (stock in positions) {
                        news.addAll(getNews(stock.symbol))
                    }
                    news.sortBy { it!!["datetime"].toString() }
                    news.reverse()
                }

                // Make sure that there is enough news returned, grab first 21 articles
                if (news.size >= 21) {
                    try {
                        news = removeDuplicates(news)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    val set: MutableSet<JSONObject?> = HashSet(news.subList(0, 20))
                    news.clear()
                    news.addAll(set)
                    set.clear()
                }

                // Initialize news recyclerview
                val finalNews = news
                activity.runOnUiThread {
                    newsRecycler!!.layoutManager = LinearLayoutManager(activity)
                    newsRecycler!!.addItemDecoration(LinearMarginDecoration())
                    newsAdapter = RecyclerViewAdapterNews(activity, finalNews)
                    newsRecycler!!.adapter = newsAdapter
                    refreshLayout!!.isRefreshing = false
                }
            }
            thread.start()
        }

        private fun getNews(ticker: String): ArrayList<JSONObject?> {

            // Call get news endpoint from IEX Cloud
            val nodeHttpResponse: JSONArray?
            val okHttpClient = OkHttpClient()
            val request: Request = Request.Builder()
                    .url("https://cloud-sse.iexapis.com/stable/stock/" + ticker + "/news/last/10?token=" + Properties.iexApiKey)
                    .build()
            val response = okHttpClient.newCall(request).execute()
            nodeHttpResponse = JSONArray(response.body!!.string())

            // Add to ArrayList
            val articles = ArrayList<JSONObject?>()
            try {
                for (i in 0 until nodeHttpResponse.length()) {
                    if (nodeHttpResponse.getJSONObject(i)["lang"].toString() == "en") {
                        articles.add(nodeHttpResponse.getJSONObject(i))
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return articles
        }

        // Remove duplicate articles
        @Throws(JSONException::class)
        fun removeDuplicates(news: ArrayList<JSONObject?>): ArrayList<JSONObject?> {
            for (i in news.indices) {
                for (j in i until news.size - 1) {
                    if (i != j && news[i]!!["headline"].toString() == news[j]!!["headline"].toString()) {
                        news.removeAt(i)
                    }
                }
            }
            return news
        }
    }
}