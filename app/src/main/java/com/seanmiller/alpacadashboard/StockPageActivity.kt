package com.seanmiller.alpacadashboard

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.View.GONE
import android.view.WindowInsets
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.robinhood.ticker.TickerUtils
import com.robinhood.ticker.TickerView
import io.cabriole.decorator.LinearMarginDecoration
import net.jacobpeterson.alpaca.AlpacaAPI
import net.jacobpeterson.alpaca.model.endpoint.common.enums.SortDirection
import net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.bar.BarsResponse
import net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.bar.enums.BarTimePeriod
import net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.snapshot.Snapshot
import net.jacobpeterson.alpaca.model.endpoint.marketdata.realtime.MarketDataMessage
import net.jacobpeterson.alpaca.model.endpoint.marketdata.realtime.enums.MarketDataMessageType
import net.jacobpeterson.alpaca.model.endpoint.order.Order
import net.jacobpeterson.alpaca.model.endpoint.order.enums.CurrentOrderStatus
import net.jacobpeterson.alpaca.rest.AlpacaClientException
import net.jacobpeterson.alpaca.websocket.marketdata.MarketDataListener
import net.jacobpeterson.alpaca.model.endpoint.calendar.Calendar
import net.jacobpeterson.alpaca.model.endpoint.marketdata.historical.bar.enums.BarAdjustment
import net.jacobpeterson.alpaca.model.properties.DataAPIType
import net.jacobpeterson.alpaca.model.properties.EndpointAPIType
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import kotlin.collections.ArrayList

class StockPageActivity : AppCompatActivity(), RecyclerViewAdapterStocks.ItemClickListener {
    private var sparkViewStock: CustomSparkView? = null
    private var selectedAdapterStock: StockAdapter? = null
    private var recyclerOrdersStock: RecyclerView? = null
    private var percentChangeStock: Button? = null
    private var ordersStock: ArrayList<Order>? = null
    private var order: ArrayList<Order>? = null
    private var recycleAdapterOrdersStock: RecyclerViewAdapterStocks? = null
    private var swipeRefreshStock: SwipeRefreshLayout? = null
    private var fab: FloatingActionButton? = null
    private var numPos: TextView? = null
    private var oneDayStock: MaterialButton? = null
    private var oneWeekStock: MaterialButton? = null
    private var oneMonthStock: MaterialButton? = null
    private var threeMonthStock: MaterialButton? = null
    private var oneYearStock: MaterialButton? = null
    private var selectedButton: MaterialButton? = null
    private var posOrNegColorLight: AtomicInteger? = null
    private var oneWeekStockAdapter: StockAdapter? = null
    private var oneMonthStockAdapter: StockAdapter? = null
    private var threeMonthStockAdapter: StockAdapter? = null
    private var oneYearStockAdapter: StockAdapter? = null
    private var prefs: SharedPreferencesManager? = null
    private var ordersStockText: TextView? = null
    private var marketDataListener: MarketDataListener? = null

    public override fun onResume() {
        super.onResume()
        isInFront = true
    }

    override fun onPause() {
        super.onPause()
        isInFront = false
    }

    // Streams ticker data from polygon
    private fun streamStockData(
        alpacaAPI: AlpacaAPI,
        ticker: AtomicReference<String>?,
        tickerV: TickerView?
    ) {

        val thread = Thread {

            // Add a 'MarketDataListener' that simply prints market data information
            marketDataListener =
                MarketDataListener { messageType: MarketDataMessageType, message: MarketDataMessage? ->

                    System.out.printf("%s: %s\n", messageType.name, message)
                    /*
                    val askingPrice = message.askPrice
                    val amount = askingPrice.toString().toDouble()
                    val formatter = DecimalFormat("#,###.00")

                    // Render tickerView
                    runOnUiThread { tickerV!!.text = "$" + formatter.format(amount)
                     }
                     */
                }
            //alpacaAPI.marketDataStreaming().subscribe() .addListener(marketDataListener)

            // Listen to quotes of ticker
            alpacaAPI.marketDataStreaming().subscribe(
                null,
                listOf(DashboardFragment.ticker!!.get()),
                null
            )

            // Wait for 5 seconds
            Thread.sleep(4000)

            /*
            try {
                // Listen to TSLA quotes, trades, and minute bars and print their messages out
                val listenerTSLA: Any = object : MarketDataListenerAdapter(
                        ticker?.get(),
                        TRADE,
                        QUOTE,
                        BAR) {
                    override fun onStreamUpdate(streamMessageType: MarketDataMessageType, streamMessage: MarketDataMessage) {
                        when (streamMessageType) {

                            QUOTE -> {
                                val quoteMessage = streamMessage as QuoteMessage
                                val askingPrice = quoteMessage.askPrice
                                val amount = askingPrice.toString().toDouble()
                                val formatter = DecimalFormat("#,###.00")

                                // Render tickerView
                                runOnUiThread { tickerV!!.text = "$" + formatter.format(amount) }

                            }
                            SUCCESS -> TODO()
                            ERROR -> TODO()
                            SUBSCRIPTION -> TODO()
                            TRADE -> TODO()
                            BAR -> TODO()
                        }
                    }
                }

                // Add the 'MarketDataListener'
                // Note that when the first 'MarketDataListener' is added, the Websocket
                // connection is created.
                alpacaAPI.addMarketDataStreamListener(listenerTSLA)

                // Wait for 5 seconds
                Thread.sleep(0)

                // Remove the 'MarketDataListener'
                // Note that when the last 'MarketDataListener' is removed, the Websocket
                // connection is closed.
                alpacaAPI.removeMarketDataStreamListener(listenerTSLA)
            } catch (exception: WebsocketException) {
                exception.printStackTrace()
            }
            */

        }
        thread.start()

    }

    private fun fetchHeight(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.height() - insets.top - insets.bottom
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        Utils.startTheme(
            this,
            SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_DEFAULT)
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_page)
        prefs = SharedPreferencesManager(this)

        // Vary size of spark view by height of screen size
        sparkViewStock = findViewById(R.id.sparkviewStock)
        val height = fetchHeight()
        val sparkCardStock = findViewById<MaterialCardView>(R.id.sparkCardStock)
        sparkCardStock.layoutParams.height = (height / 1.75).toInt()

        // Initializations
        val alpacaAPI = AlpacaAPI(
            null,
            null,
            null,
            prefs!!.retrieveString("auth_token", "NULL"),
            EndpointAPIType.PAPER,
            DataAPIType.IEX
        )
        val alpacaData = AlpacaAPI(Properties.apiKey, Properties.secretKey);

        // Set title
        val totalEquity = findViewById<TextView>(R.id.stockTradedStock)
        totalEquity.text = DashboardFragment.ticker!!.get()

        // Set percent change
        percentChangeStock = findViewById(R.id.percentChangeStock)

        // Set fab
        fab = findViewById(R.id.placeOrder)
        fab?.setOnClickListener {
            val dialogFrag = PlaceOrderFragment()
            dialogFrag.setParentFab(fab)
            dialogFrag.show(supportFragmentManager, dialogFrag.tag)
        }

        // Watchlist, set below
        val addWatchlist = findViewById<ImageButton>(R.id.addWatchlist)

        val watchlistThread = Thread {

            // Check for watchlist
            var inWatchlist = false
            val watchListId = prefs?.retrieveString("primary_watchlist", null)

            // Loop through all assets in watchlist
            for (j in alpacaAPI.watchlist().get(watchListId).assets) {

                // Check to see if the watchlist contains the current stock
                if (j.symbol == DashboardFragment.ticker!!.get()) {

                    // Modify icon
                    val offList = ContextCompat.getDrawable(this, R.drawable.eye_minus)
                    if (addWatchlist.drawable != offList)
                        runOnUiThread { addWatchlist.setImageDrawable(offList) }
                    inWatchlist = true  // Mark to change icon to plus afterwards
                    break
                }
            }

            // Follow previous comm
            if (!inWatchlist) {
                val onList = ContextCompat.getDrawable(this, R.drawable.eye_plus)
                runOnUiThread { addWatchlist.setImageDrawable(onList) }
            }

            // If watchlist is clicked, add/remove asset from the watchlist and change icon
            addWatchlist.setOnClickListener {

                val onClickWatchlistThread = Thread {
                    if (inWatchlist) {
                        val offList = ContextCompat.getDrawable(this, R.drawable.eye_plus)
                        runOnUiThread { addWatchlist.setImageDrawable(offList) }
                        for (i in alpacaAPI.watchlist().get()) {
                            alpacaAPI.watchlist()
                                .removeSymbol(i.id, DashboardFragment.ticker!!.get())
                        }

                    } else {
                        val onList = ContextCompat.getDrawable(this, R.drawable.eye_minus)
                        runOnUiThread { addWatchlist.setImageDrawable(onList) }
                        alpacaAPI.watchlist()
                            .addAsset(watchListId, DashboardFragment.ticker!!.get())
                    }
                    inWatchlist = !inWatchlist
                }
                onClickWatchlistThread.start()
            }
        }
        watchlistThread.start()

        val numPos = Thread {

            // Set number of stocks textview
            numPos = findViewById(R.id.numberOfStocks)
            var numPosition: String? = null
            try {
                numPosition =
                    alpacaAPI.positions().getBySymbol(DashboardFragment.ticker!!.get()).qty
            } catch (e: AlpacaClientException) {
                e.printStackTrace()
            }

            // Set number of shares owned
            runOnUiThread {
                when (numPosition) {
                    "1" -> {
                        numPos?.text = String.format("%s share owned", numPosition)
                    }
                    null -> {
                        numPos?.text = String.format("%s shares owned", 0)
                    }
                    else -> {
                        numPos?.text = String.format("%s shares owned", numPosition)
                    }
                }
            }
        }
        numPos.start()

        // Set swipeRefresh
        swipeRefreshStock = findViewById(R.id.refreshStock)
        swipeRefreshStock?.translationZ = 100f
        swipeRefreshStock?.bringToFront()

        // Ticker information
        tickerViewStock = findViewById(R.id.tickerViewStock)
        tickerViewStock?.setCharacterLists(TickerUtils.provideNumberList())

        // The sparkline graph itself
        // Set button group for timeframe
        oneDayStock = findViewById(R.id.oneDayStock)
        oneWeekStock = findViewById(R.id.oneWeekStock)
        oneMonthStock = findViewById(R.id.oneMonthStock)
        threeMonthStock = findViewById(R.id.threeMonthsStock)
        oneYearStock = findViewById(R.id.oneYearStock)
        oneYearStock = findViewById(R.id.oneYearStock)
        selectedButton = oneDayStock

        // The sparkline graph data
        oneDayStockAdapter = StockAdapter(this)
        oneWeekStockAdapter = StockAdapter(this)
        oneMonthStockAdapter = StockAdapter(this)
        threeMonthStockAdapter = StockAdapter(this)
        oneYearStockAdapter = StockAdapter(this)
        selectedAdapterStock = oneDayStockAdapter
        sparkViewStock?.adapter = selectedAdapterStock

        val initializeGraphsThread = Thread {

            // Initalize all graphs
            try {
                initializeDashboardValues(
                    ZonedDateTime.now(),
                    5,
                    BarTimePeriod.MINUTE,
                    oneDayStockAdapter
                )
                initializeDashboardValues(
                    ZonedDateTime.now().minusWeeks(1),
                    45,
                    BarTimePeriod.MINUTE,
                    oneWeekStockAdapter
                )
                initializeDashboardValues(
                    ZonedDateTime.now().minusMonths(1),
                    2,
                    BarTimePeriod.HOUR,
                    oneMonthStockAdapter
                )
                initializeDashboardValues(
                    ZonedDateTime.now().minusMonths(3),
                    1,
                    BarTimePeriod.DAY,
                    threeMonthStockAdapter
                )
                initializeDashboardValues(
                    ZonedDateTime.now().minusYears(1),
                    5,
                    BarTimePeriod.DAY,
                    oneYearStockAdapter
                )
            } catch (e: AlpacaClientException) {
                e.printStackTrace()
            }

            // Set colors on click, for toggle buttons
            val tV = TypedValue()
            theme.resolveAttribute(R.attr.color_positive_light, tV, true)
            posOrNegColorLight = AtomicInteger(ContextCompat.getColor(this, tV.resourceId))
            theme.resolveAttribute(R.attr.colorPrimary, tV, true)
            val colorPrimary = ContextCompat.getColor(this, tV.resourceId)

            // One Day Button Listener
            oneDayStock?.setOnClickListener {
                selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
                selectedButton = oneDayStock
                selectedButton!!.backgroundTintList =
                    ColorStateList.valueOf(posOrNegColorLight!!.get())
                selectedAdapterStock = oneDayStockAdapter
                sparkViewStock?.adapter = selectedAdapterStock
                setStockValues(oneDayStockAdapter)
            }

            // One Week Button Listener
            oneWeekStock?.setOnClickListener {
                selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
                selectedButton = oneWeekStock
                selectedButton!!.backgroundTintList =
                    ColorStateList.valueOf(posOrNegColorLight!!.get())
                selectedAdapterStock = oneWeekStockAdapter
                sparkViewStock?.adapter = selectedAdapterStock
                setStockValues(oneWeekStockAdapter)
            }

            // One Month Button Listener
            oneMonthStock?.setOnClickListener {
                selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
                selectedButton = oneMonthStock
                selectedButton!!.backgroundTintList =
                    ColorStateList.valueOf(posOrNegColorLight!!.get())
                selectedAdapterStock = oneMonthStockAdapter
                sparkViewStock?.adapter = selectedAdapterStock
                setStockValues(selectedAdapterStock)
            }

            // Three Months Button Listener
            threeMonthStock?.setOnClickListener {
                selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
                selectedButton = threeMonthStock
                selectedButton!!.backgroundTintList =
                    ColorStateList.valueOf(posOrNegColorLight!!.get())
                selectedAdapterStock = threeMonthStockAdapter
                sparkViewStock?.adapter = selectedAdapterStock
                setStockValues(selectedAdapterStock)
            }

            // One Year Button Listener
            oneYearStock?.setOnClickListener {
                selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
                selectedButton = oneYearStock
                selectedButton!!.backgroundTintList =
                    ColorStateList.valueOf(posOrNegColorLight!!.get())
                selectedAdapterStock = oneYearStockAdapter
                sparkViewStock?.adapter = selectedAdapterStock
                setStockValues(selectedAdapterStock)
            }

            // Set adapter
            sparkViewStock?.adapter = selectedAdapterStock

            // Scrub for chart
            theme.resolveAttribute(R.attr.colorPrimaryLight, tV, true)
            val color = AtomicInteger(ContextCompat.getColor(this, tV.resourceId))

            // Scrub for chart
            sparkViewStock?.sparkAnimator = null
            sparkViewStock?.baseLineColor = color.get()
            sparkViewStock?.setScrubListener { value: Any? ->

                // Format to add commas
                if (value != null) {
                    val amount = AtomicReference<Double>()
                    val formatter = DecimalFormat("#,###.00")
                    amount.set(value.toString().toDouble())
                    tickerViewStock?.text = "$" + formatter.format(amount.get())
                }
            }
        }
        initializeGraphsThread.start()

        val liveUpdateThread = Thread {
            // Get market status
            var marketStatus = true
            try {
                marketStatus = alpacaData.clock().get().isOpen
            } catch (e: AlpacaClientException) {
                e.printStackTrace()
            }

            // Stream to chart if market open
            if (marketStatus) {

                // Stream live data for a stock
//            streamStockData(alpacaAPI, DashboardFragment.ticker, tickerViewStock)
                while (true) {
                    try {
                        Thread.sleep(60000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    var askingPrice: Float? = null
                    try {
                        askingPrice = alpacaData.marketData()
                            .getSnapshot(DashboardFragment.ticker!!.get()).dailyBar.c.toFloat()
//                        askingPrice = alpacaAPI.getLatestQuote(DashboardFragment.ticker!!.get())
                    } catch (e: AlpacaClientException) {
                        e.printStackTrace()
                    }
                    var finalAskingPrice = 0f
                    if (askingPrice != null) {
                        finalAskingPrice = askingPrice
                    }
                    runOnUiThread { selectedAdapterStock!!.addVal(finalAskingPrice) }
                    runOnUiThread { setStockValues(selectedAdapterStock) }
                }

            }
        }
        liveUpdateThread.start()

        val ordersThread = Thread {

            // Fetch current orders
            order = ArrayList()
            try {
                val symbols = ArrayList<String>()
                symbols.add(DashboardFragment.ticker!!.get())
                order = alpacaAPI.orders().get(
                    CurrentOrderStatus.CLOSED, 10,
                    ZonedDateTime.of(2000, 12, 23, 0, 0, 0, 0, ZoneId.of("America/New_York")),
                    ZonedDateTime.now().plusDays(1), SortDirection.DESCENDING, false, symbols
                ) as ArrayList<Order>?
            } catch (e: AlpacaClientException) {
                e.printStackTrace()
            }

            // If there are no orders
            ordersStockText = findViewById(R.id.ordersStockText)
            if (order!!.isEmpty()) {
                runOnUiThread { ordersStockText?.visibility = GONE }

            } else {

                // Initialize recyclerview and adapter
                recyclerOrdersStock = findViewById(R.id.ordersStock)
                if (order?.isNotEmpty()!!) {
                    recycleAdapterOrdersStock = RecyclerViewAdapterStocks(this, order!!)
                }

                // Add Decorations and set adapter
                runOnUiThread {
                    ordersStockText?.visibility = View.VISIBLE
                    recyclerOrdersStock?.layoutManager = LinearLayoutManager(this)
                    recyclerOrdersStock?.addItemDecoration(
                        LinearMarginDecoration.create(
                            0,
                            LinearLayoutManager.VERTICAL,
                            false,
                            null
                        )
                    )
                    recyclerOrdersStock?.adapter = recycleAdapterOrdersStock
                }
            }
        }
        ordersThread.start()

        // Refresh Listener
        swipeRefreshStock?.setOnRefreshListener {
            onRefresh()
            swipeRefreshStock?.isNestedScrollingEnabled = false
        }

        val financialsThread = Thread {

            // Set Statistics
            val dividend_yield = findViewById<TextView>(R.id.dividend_yield)
            val assets = findViewById<TextView>(R.id.assets)
            val debt = findViewById<TextView>(R.id.debt)
            val debt_equity_ratio = findViewById<TextView>(R.id.debt_equity)
            val earnings_per_share = findViewById<TextView>(R.id.earnings_per_share)
            val gross_margin = findViewById<TextView>(R.id.gross_margin)
            val gross_profit = findViewById<TextView>(R.id.gross_profit)
            val market_cap = findViewById<TextView>(R.id.market_cap)
            val net_cash_flow = findViewById<TextView>(R.id.net_cash_flow)
            val price_earnings_ratio = findViewById<TextView>(R.id.price_earnings_ratio)
            val price_earnings = findViewById<TextView>(R.id.price_earnings)
            val revenues = findViewById<TextView>(R.id.revenues)
            val financial_date = findViewById<TextView>(R.id.financial_date)
//            var financials: StockFinancialsResponse? = null
//            try {
//                financials = polygonAPI.getStockFinancials(DashboardFragment.ticker!!.get(), 1, FinancialReportType.Q, FinancialSort.REPORT_PERIOD_DESCENDING)
//            } catch (e: PolygonAPIRequestException) {
//                e.printStackTrace()
//            }
//            val finalFinancials = financials
//            runOnUiThread {
//                if (finalFinancials != null && !finalFinancials.results.isEmpty()) {
//                    val format = DecimalFormat("$#,###.##")
//                    val result = finalFinancials.results[0]
//                    financial_date.text = String.format("Last Updated: %s", result.updated)
//                    dividend_yield.text = result.dividendYield.toString()
//                    assets.text = format.format(result.assets.toFloat().toDouble())
//                    debt.text = format.format(result.debt.toFloat().toDouble())
//                    debt_equity_ratio.text = result.debtToEquityRatio.toString()
//                    earnings_per_share.text = format.format(result.earningsPerBasicShareUSD.toFloat().toDouble())
//                    gross_margin.text = format.format(result.grossMargin.toFloat().toDouble())
//                    gross_profit.text = format.format(result.grossProfit.toFloat().toDouble())
//                    market_cap.text = format.format(result.marketCapitalization.toFloat().toDouble())
//                    net_cash_flow.text = format.format(result.netCashFlow.toFloat().toDouble())
//                    price_earnings_ratio.text = result.priceToEarningsRatio.toString()
//                    price_earnings.text = format.format(result.priceEarnings.toFloat().toDouble())
//                    revenues.text = format.format(result.revenuesUSD.toFloat().toDouble())
//                }
//            }
        }
//        t1.start()
    }

    // Method that is called when swipe refresh is enabled
    private fun onRefresh() {
        swipeRefreshStock!!.isRefreshing = true
        val ordersThread = Thread {
            val alpacaAPI = AlpacaAPI(
                null,
                null,
                null,
                prefs!!.retrieveString("auth_token", "NULL"),
                EndpointAPIType.PAPER,
                DataAPIType.IEX
            )

            // Fetch current orders
            ordersStock = ArrayList()
            try {
                val symbols = ArrayList<String>()
                symbols.add(DashboardFragment.ticker!!.get())
                ordersStock = alpacaAPI.orders().get(
                    CurrentOrderStatus.CLOSED,
                    10,
                    null,
                    ZonedDateTime.now().plusDays(1),
                    SortDirection.DESCENDING,
                    false,
                    symbols
                ) as ArrayList<Order>?
            } catch (e: AlpacaClientException) {
                e.printStackTrace()
            }

            // If there are no orders
            if (ordersStock!!.isEmpty()) {
                runOnUiThread {
                    ordersStockText?.visibility = GONE
                    recycleAdapterOrdersStock?.notifyDataSetChanged()
                }

            } else {

                order?.clear()
                order?.addAll(ordersStock!!)
                ordersStock!!.clear()

                recycleAdapterOrdersStock = RecyclerViewAdapterStocks(this, order!!)
                runOnUiThread {
                    ordersStockText?.visibility = View.VISIBLE
                    recyclerOrdersStock!!.adapter = recycleAdapterOrdersStock
                }
            }

            // Set number of stocks textview
            val numPosition: String?
            try {
                numPosition =
                    alpacaAPI.positions().getBySymbol(DashboardFragment.ticker!!.get()).qty
                runOnUiThread { numPos!!.text = String.format("%s shares owned", numPosition) }
            } catch (e: AlpacaClientException) {
                runOnUiThread { numPos!!.text = "0 shares owned" }
                e.printStackTrace()
            }

            swipeRefreshStock!!.isRefreshing = false
        }
        ordersThread.start()
    }

    private fun initializeDashboardValues(
        datetime: ZonedDateTime,
        multiplier: Int,
        timeFrame: BarTimePeriod?,
        selectedAdapterInitial: StockAdapter?
    ) {

        // Requests bars and adds to graph
        val alpacaAPI = AlpacaAPI(
            null,
            null,
            null,
            prefs!!.retrieveString("auth_token", "NULL"),
            EndpointAPIType.PAPER,
            DataAPIType.IEX
        )
        val alpacaData = AlpacaAPI(Properties.apiKey, Properties.secretKey);
        val initializationThread = Thread {

            // Fetch last open day's information
            var calendarInitial: java.util.ArrayList<net.jacobpeterson.alpaca.model.endpoint.calendar.Calendar>? =
                null
            try {
                calendarInitial = alpacaData.calendar()
                    .get(LocalDate.now().minusWeeks(1), LocalDate.now()) as ArrayList<Calendar>?
            } catch (e: AlpacaClientException) {
                e.printStackTrace()
            }
            var lastOpenDate =
                LocalDate.parse(calendarInitial!![calendarInitial.size - 1].date.toString())
            val oldTime = LocalTime.of(
                calendarInitial[calendarInitial.size - 2].open.toString().substring(0, 2).toInt(),
                calendarInitial[calendarInitial.size - 2].open.toString().substring(3, 5).toInt()
            )

            // Switch given open datetime from US/Eastern to System Default
            val zonedDateTime = ZonedDateTime.of(lastOpenDate, oldTime, ZoneId.of("US/Eastern"))
            val standardDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault())

            // Check if it is the morning of
            if (standardDateTime.toLocalTime() > LocalTime.now()) {
                lastOpenDate = LocalDate.parse(calendarInitial[calendarInitial.size - 3].toString())
            }
            val lastClose = AtomicReference(0.toFloat())
            var bars: BarsResponse? = null

            // Check if market is open
            // Get market status
//            var marketStatus = true
//            try {
//                marketStatus = alpacaAPI.clock.isOpen
//            } catch (e: AlpacaAPIRequestException) {
//                e.printStackTrace()
//            }
//            if (marketStatus) {

            // If one day, get bars from only today
            if (selectedAdapterInitial == oneDayStockAdapter) {
                try {
                    bars = alpacaData.marketData().getBars(
                        DashboardFragment.ticker!!.get(),
                        zonedDateTime,
                        ZonedDateTime.now(),
                        10000,
                        null,
                        1,
                        timeFrame,
                        BarAdjustment.SPLIT
                    )
                } catch (e: AlpacaClientException) {
                    e.printStackTrace()
                }

            } else {  // Otherwise get bars from given datetime to now
                try {
                    bars = alpacaData.marketData().getBars(
                        DashboardFragment.ticker!!.get(),
                        datetime,
                        ZonedDateTime.now(),
                        10000,
                        null,
                        1,
                        timeFrame,
                        BarAdjustment.SPLIT
                    )
                } catch (e: AlpacaClientException) {
                    e.printStackTrace()
                }
            }

            // Add all bar's close value to adapter
            for (bar in bars!!.bars) {
                runOnUiThread {
                    bar.c.let { selectedAdapterInitial?.addVal(it.toFloat()) }
                }
            }

            // Smooth if too many data points
            if (selectedAdapterStock?.count!! > 150) {
                runOnUiThread { selectedAdapterInitial?.smoothGraph() }
            }

            // Get snapshot and add dailybar close to end and prevdailybar close to beginning
            var askingPrice: Snapshot? = null
            try {
                askingPrice = alpacaData.marketData().getSnapshot(DashboardFragment.ticker!!.get())
            } catch (e: AlpacaClientException) {
                e.printStackTrace()
            }

            if (askingPrice != null) {
                val finalAskingPrice = askingPrice.dailyBar.c.toFloat()
                runOnUiThread {
                    selectedAdapterInitial?.addVal(finalAskingPrice)
                    if (selectedAdapterInitial == oneDayStockAdapter) {
                        selectedAdapterInitial?.pushFront(askingPrice.prevDailyBar.c.toFloat())
                        selectedAdapterInitial?.setBaseline(askingPrice.prevDailyBar.c.toFloat())
                    }
                    selectedButton!!.callOnClick() // Set here to allow ample time for instantiation
                }
            }

        }
        initializationThread.start()
    }

    // Helper method for colors for setStockValues
    private fun setStockColors(pos: Boolean, profitLoss: Float, percentageChange: Float) {
        val tV = TypedValue()
        theme.resolveAttribute(R.attr.color_positive_light, tV, true)
        val posColorLight = AtomicInteger(ContextCompat.getColor(this, tV.resourceId))
        theme.resolveAttribute(R.attr.color_negative_light, tV, true)
        val negColorLight = AtomicInteger(ContextCompat.getColor(this, tV.resourceId))
        if (pos) {
            percentChangeStock!!.text =
                String.format("+$%.2f (%.2f%%)", profitLoss, percentageChange)
            theme.resolveAttribute(R.attr.color_positive, tV, true)
            val color = ContextCompat.getColor(this, tV.resourceId)
            percentChangeStock!!.setTextColor(color)
            percentChangeStock!!.backgroundTintList = ColorStateList.valueOf(posColorLight.get())
            val upArrow = ContextCompat.getDrawable(this, R.drawable.arrow_top_right)
            upArrow!!.setTint(color)
            percentChangeStock!!.setCompoundDrawablesWithIntrinsicBounds(null, null, upArrow, null)
            sparkViewStock!!.lineColor = color
            selectedButton!!.backgroundTintList = ColorStateList.valueOf(posColorLight.get())
            oneDayStock!!.setTextColor(color)
            oneWeekStock!!.setTextColor(color)
            oneMonthStock!!.setTextColor(color)
            threeMonthStock!!.setTextColor(color)
            oneYearStock!!.setTextColor(color)
            oneDayStock!!.rippleColor = ColorStateList.valueOf(color)
            oneWeekStock!!.rippleColor = ColorStateList.valueOf(color)
            oneMonthStock!!.rippleColor = ColorStateList.valueOf(color)
            threeMonthStock!!.rippleColor = ColorStateList.valueOf(color)
            oneYearStock!!.rippleColor = ColorStateList.valueOf(color)
            theme.resolveAttribute(R.attr.color_positive_light, tV, true)
        } else {
            percentChangeStock!!.text =
                String.format("-$%.2f (%.2f%%)", Math.abs(profitLoss), percentageChange)
            theme.resolveAttribute(R.attr.color_negative, tV, true)
            val color = ContextCompat.getColor(this, tV.resourceId)
            percentChangeStock!!.setTextColor(color)
            percentChangeStock!!.backgroundTintList = ColorStateList.valueOf(negColorLight.get())
            val downArrow = ContextCompat.getDrawable(this, R.drawable.arrow_bottom_right)
            downArrow!!.setTint(color)
            percentChangeStock!!.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                downArrow,
                null
            )
            sparkViewStock!!.lineColor = color
            selectedButton!!.backgroundTintList = ColorStateList.valueOf(negColorLight.get())
            oneDayStock!!.setTextColor(color)
            oneWeekStock!!.setTextColor(color)
            oneMonthStock!!.setTextColor(color)
            threeMonthStock!!.setTextColor(color)
            oneYearStock!!.setTextColor(color)
            oneDayStock!!.rippleColor = ColorStateList.valueOf(color)
            oneWeekStock!!.rippleColor = ColorStateList.valueOf(color)
            oneMonthStock!!.rippleColor = ColorStateList.valueOf(color)
            threeMonthStock!!.rippleColor = ColorStateList.valueOf(color)
            oneYearStock!!.rippleColor = ColorStateList.valueOf(color)
            theme.resolveAttribute(R.attr.color_negative_light, tV, true)
        }
        posOrNegColorLight!!.set(ContextCompat.getColor(this, tV.resourceId))
    }

    private fun setStockValues(selectedAdapter: StockAdapter?) {

        // Set Line and Ticker Info
        val oldVal = selectedAdapter!!.getValue(0)
        val newVal = selectedAdapter.getValue(selectedAdapter.count - 1)
        val percentageChange = (newVal - oldVal) / oldVal * 100
        val profitLoss =
            selectedAdapter.getValue(selectedAdapter.count - 1) - selectedAdapter.getValue(0)
        if (selectedAdapterStock!!.count != 0) {

            // If positive
            if (profitLoss >= 0) {
                setStockColors(true, profitLoss, percentageChange)
            } else {
                setStockColors(false, profitLoss, percentageChange)
            }
        }
        val formatter = DecimalFormat("$#,###.00")
        val amount = newVal.toString().toDouble()
        tickerViewStock!!.text = formatter.format(amount)
    }

    // When back is pressed, finishes entire activity
    override fun onBackPressed() {

        //AlpacaAPI(null, null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX).marketDataStreaming().removeListener(marketDataListener)
        finish()
    }

    // Nothing happens when order is clicked yet
    override fun onItemClick(view: View?, position: Int) {}

    companion object {
        @JvmField
        var tickerViewStock: TickerView? = null
        var oneDayStockAdapter: StockAdapter? = null

        @JvmField
        var isInFront = false
    }
}
