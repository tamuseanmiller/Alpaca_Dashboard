package com.seanmiller.alpacadashboard

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.WindowInsets
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mashape.unirest.http.Unirest
import com.mashape.unirest.http.exceptions.UnirestException
import com.robinhood.ticker.TickerUtils
import com.robinhood.ticker.TickerView
import io.cabriole.decorator.LinearMarginDecoration
import net.jacobpeterson.abstracts.enums.SortDirection
import net.jacobpeterson.abstracts.websocket.exception.WebsocketException
import net.jacobpeterson.alpaca.AlpacaAPI
import net.jacobpeterson.alpaca.enums.*
import net.jacobpeterson.alpaca.enums.api.DataAPIType
import net.jacobpeterson.alpaca.enums.api.EndpointAPIType
import net.jacobpeterson.alpaca.enums.marketdata.BarsTimeFrame
import net.jacobpeterson.alpaca.enums.order.OrderStatus
import net.jacobpeterson.alpaca.enums.portfolio.PortfolioPeriodUnit
import net.jacobpeterson.alpaca.enums.portfolio.PortfolioTimeFrame
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException
import net.jacobpeterson.alpaca.websocket.marketdata.listener.MarketDataListener
import net.jacobpeterson.alpaca.websocket.marketdata.listener.MarketDataListenerAdapter
import net.jacobpeterson.alpaca.websocket.marketdata.message.MarketDataMessageType
import net.jacobpeterson.alpaca.websocket.marketdata.message.MarketDataMessageType.*
import net.jacobpeterson.domain.alpaca.calendar.Calendar
import net.jacobpeterson.domain.alpaca.marketdata.historical.bar.Bar
import net.jacobpeterson.domain.alpaca.marketdata.historical.bar.BarsResponse
import net.jacobpeterson.domain.alpaca.marketdata.historical.quote.LatestQuoteResponse
import net.jacobpeterson.domain.alpaca.marketdata.historical.snapshot.Snapshot
import net.jacobpeterson.domain.alpaca.marketdata.realtime.MarketDataMessage
import net.jacobpeterson.domain.alpaca.marketdata.realtime.bar.BarMessage
import net.jacobpeterson.domain.alpaca.marketdata.realtime.quote.QuoteMessage
import net.jacobpeterson.domain.alpaca.marketdata.realtime.trade.TradeMessage
import net.jacobpeterson.domain.alpaca.order.Order
import org.json.JSONException
import org.json.JSONObject
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import kotlin.collections.ArrayList
import kotlin.concurrent.thread


@RequiresApi(api = Build.VERSION_CODES.O)
class StockPageActivity : AppCompatActivity(), RecyclerViewAdapterStocks.ItemClickListener {
    private var sparkViewStock: CustomSparkView? = null
    private var selectedAdapterStock: StockAdapter? = null
    private var recyclerOrdersStock: RecyclerView? = null
    private val recycleAdapterOrders: RecyclerViewAdapterOrders? = null
    private var percentChangeStock: Button? = null
    private var ordersStock: ArrayList<Order>? = null
    private var order: ArrayList<Order>? = null
    private var recycleAdapterOrdersStock: RecyclerViewAdapterOrders? = null
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
    public override fun onResume() {
        super.onResume()
        isInFront = true
    }

    override fun onPause() {
        super.onPause()
        isInFront = false
    }

    // Streams ticker data from polygon
    private fun streamStockData(alpacaAPI: AlpacaAPI, ticker: AtomicReference<String>?, tickerV: TickerView?) {

        val thread = Thread {
            try {
                // Listen to TSLA quotes, trades, and minute bars and print their messages out
                val listenerTSLA: MarketDataListener = object : MarketDataListenerAdapter(
                        "TSLA",
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
                Thread.sleep(5000)

                // Remove the 'MarketDataListener'
                // Note that when the last 'MarketDataListener' is removed, the Websocket
                // connection is closed.
                alpacaAPI.removeMarketDataStreamListener(listenerTSLA)
            } catch (exception: WebsocketException) {
                exception.printStackTrace()
            }
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
        Utils.startTheme(this, SharedPreferencesManager(this).retrieveInt("theme", Utils.THEME_DEFAULT))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_page)
        prefs = SharedPreferencesManager(this)

        // Vary size of spark view by height of screen size
        sparkViewStock = findViewById(R.id.sparkviewStock)
        val height = fetchHeight()
        val sparkCardStock = findViewById<MaterialCardView>(R.id.sparkCardStock)
        sparkCardStock.minimumHeight = (height / 1.75).toInt()

        // Initializations
        val alpacaAPI = AlpacaAPI(null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)

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

        val t3 = Thread {

            // Set number of stocks textview
            numPos = findViewById(R.id.numberOfStocks)
            val numPosition: String?
            try {
                numPosition = alpacaAPI.getOpenPositionBySymbol(DashboardFragment.ticker!!.get()).qty
                runOnUiThread { numPos?.text = String.format("%s shares owned", numPosition) }
            } catch (e: AlpacaAPIRequestException) {
                runOnUiThread { numPos?.text = R.string.zero_shares.toString() }
                e.printStackTrace()
            }
        }
        t3.start()

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

        val t2 = Thread {

            // Initalize all graphs
            try {
                initializeDashboardValues(ZonedDateTime.now(), 5, BarsTimeFrame.MINUTE, oneDayStockAdapter)
                initializeDashboardValues(ZonedDateTime.now().minusWeeks(1), 45, BarsTimeFrame.MINUTE, oneWeekStockAdapter)
                initializeDashboardValues(ZonedDateTime.now().minusMonths(1), 2, BarsTimeFrame.HOUR, oneMonthStockAdapter)
                initializeDashboardValues(ZonedDateTime.now().minusMonths(3), 1, BarsTimeFrame.DAY, threeMonthStockAdapter)
                initializeDashboardValues(ZonedDateTime.now().minusYears(1), 5, BarsTimeFrame.DAY, oneYearStockAdapter)
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }
        }
        t2.start()

        // Set colors on click, for toggle buttons
        val tV = TypedValue()
        theme.resolveAttribute(R.attr.color_positive_light, tV, true)
        posOrNegColorLight = AtomicInteger(ContextCompat.getColor(this, tV.resourceId))
        theme.resolveAttribute(R.attr.colorPrimary, tV, true)
        val colorPrimary = ContextCompat.getColor(this, tV.resourceId)
        oneDayStock?.setOnClickListener {
            selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
            selectedButton = oneDayStock
            selectedButton!!.backgroundTintList = ColorStateList.valueOf(posOrNegColorLight!!.get())
            selectedAdapterStock = oneDayStockAdapter
            sparkViewStock?.adapter = selectedAdapterStock
            setStockValues(oneDayStockAdapter)
        }
        oneWeekStock?.setOnClickListener {
            selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
            selectedButton = oneWeekStock
            selectedButton!!.backgroundTintList = ColorStateList.valueOf(posOrNegColorLight!!.get())
            selectedAdapterStock = oneWeekStockAdapter
            sparkViewStock?.adapter = selectedAdapterStock
            setStockValues(oneWeekStockAdapter)
        }
        oneMonthStock?.setOnClickListener {
            selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
            selectedButton = oneMonthStock
            selectedButton!!.backgroundTintList = ColorStateList.valueOf(posOrNegColorLight!!.get())
            selectedAdapterStock = oneMonthStockAdapter
            sparkViewStock?.adapter = selectedAdapterStock
            setStockValues(selectedAdapterStock)
        }
        threeMonthStock?.setOnClickListener {
            selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
            selectedButton = threeMonthStock
            selectedButton!!.backgroundTintList = ColorStateList.valueOf(posOrNegColorLight!!.get())
            selectedAdapterStock = threeMonthStockAdapter
            sparkViewStock?.adapter = selectedAdapterStock
            setStockValues(selectedAdapterStock)
        }
        oneYearStock?.setOnClickListener {
            selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
            selectedButton = oneYearStock
            selectedButton!!.backgroundTintList = ColorStateList.valueOf(posOrNegColorLight!!.get())
            selectedAdapterStock = oneYearStockAdapter
            sparkViewStock?.adapter = selectedAdapterStock
            setStockValues(selectedAdapterStock)
        }

        val amount = AtomicReference<Double>()
        val formatter = DecimalFormat("#,###.00")
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
                amount.set(value.toString().toDouble())
                tickerViewStock?.text = "$" + formatter.format(amount.get())
            }
        }

        // Get market status
        var marketStatus = true
        try {
            marketStatus = alpacaAPI.clock.isOpen
        } catch (e: AlpacaAPIRequestException) {
            e.printStackTrace()
        }

        // Stream to chart
        if (marketStatus) {

            // Stream live data for a stock
            streamStockData(alpacaAPI, DashboardFragment.ticker, tickerViewStock)
            val t4 = Thread {
                while (true) {
                    try {
                        Thread.sleep(60000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    var askingPrice: LatestQuoteResponse? = null
                    try {
                        askingPrice = alpacaAPI.getLatestQuote(DashboardFragment.ticker!!.get())
                    } catch (e: AlpacaAPIRequestException) {
                        e.printStackTrace()
                    }
                    var finalAskingPrice = 0f
                    if (askingPrice != null) {
                        finalAskingPrice = askingPrice.quote.ap.toFloat()
                    }
                    val finalAskingPrice1 = finalAskingPrice
                    runOnUiThread { selectedAdapterStock!!.addVal(finalAskingPrice1) }
                    runOnUiThread { setStockValues(selectedAdapterStock) }
                }
            }
            t4.start()

        } else {
            val t5 = Thread {
                var askingPrice = 0f
                try {
                    askingPrice = alpacaAPI.getLatestQuote(DashboardFragment.ticker!!.get()).quote.ap.toFloat()
                } catch (e: AlpacaAPIRequestException) {
                    e.printStackTrace()
                }
                val finalAskingPrice = askingPrice
                runOnUiThread { selectedAdapterStock!!.addVal(finalAskingPrice) }
            }
//            t5.start()
        }

        val thread = Thread {

            // Fetch current orders
            ordersStock = ArrayList()
            order = ArrayList()
            try {
                val symbols = ArrayList<String>()
                symbols.add(DashboardFragment.ticker!!.get())
                ordersStock = alpacaAPI.getOrders(OrderStatus.CLOSED, 10, null, ZonedDateTime.now().plusDays(1), SortDirection.DESCENDING, false, symbols) as ArrayList<Order>?
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }
            order?.clear()
            order?.addAll(ordersStock!!)
            ordersStock!!.clear()
            //            int i = 0;
//            while (i < ordersStock.size() && order.size() <= 10) {
//                if (ordersStock.get(i).getSymbol().equals(ticker.get())) {
//                    order.add(ordersStock.get(i));
//                }
//                i++;
//            }
            recyclerOrdersStock = findViewById(R.id.ordersStock)
            if (order?.isNotEmpty()!!) {
                recycleAdapterOrdersStock = RecyclerViewAdapterOrders(this, order!!)
            }
            runOnUiThread {
                recyclerOrdersStock?.layoutManager = LinearLayoutManager(this)
                recyclerOrdersStock?.addItemDecoration(LinearMarginDecoration.create(0, LinearLayoutManager.VERTICAL, false, null))
                recyclerOrdersStock?.adapter = recycleAdapterOrdersStock
            }
        }
        thread.start()

        // Refresh Listener
        swipeRefreshStock?.setOnRefreshListener {
            onRefresh()
            swipeRefreshStock?.isNestedScrollingEnabled = false
        }
        val t1 = Thread {

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun onRefresh() {
        swipeRefreshStock!!.isRefreshing = true
        val thread2 = Thread {
            val alpacaAPI = AlpacaAPI(null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)

            // Fetch current orders
            ordersStock = ArrayList()
            try {
                val symbols = ArrayList<String>()
                symbols.add(DashboardFragment.ticker!!.get())
                ordersStock = alpacaAPI.getOrders(OrderStatus.CLOSED, 10, null, ZonedDateTime.now().plusDays(1), SortDirection.DESCENDING, false, symbols) as ArrayList<Order>?
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }
            order?.clear()
            order?.addAll(ordersStock!!)
            ordersStock!!.clear()

            // Set number of stocks textview
            var numPosition: String? = null
            try {
                numPosition = alpacaAPI.getOpenPositionBySymbol(DashboardFragment.ticker!!.get()).qty
                val finalNumPosition = numPosition
                runOnUiThread { numPos!!.text = String.format("%s shares owned", finalNumPosition) }
            } catch (e: AlpacaAPIRequestException) {
                runOnUiThread { numPos!!.text = "0 shares owned" }
                e.printStackTrace()
            }
            runOnUiThread {
                recyclerOrdersStock!!.layoutManager = LinearLayoutManager(this)
                recyclerOrdersStock!!.addItemDecoration(LinearMarginDecoration.create(0, LinearLayoutManager.VERTICAL, false, null))
                recyclerOrdersStock!!.adapter = recycleAdapterOrdersStock
            }
            swipeRefreshStock!!.isRefreshing = false
        }
        thread2.start()
    }

    @Throws(AlpacaAPIRequestException::class)
    fun initializeDashboardValues(datetime: ZonedDateTime, multiplier: Int, timeFrame: BarsTimeFrame?, selectedAdapterInitial: StockAdapter?) {

        // Requests bars and adds to graph
        val alpacaAPI = AlpacaAPI(null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)
        val thread = Thread {

//            val snapshot = alpacaAPI.getSnapshot(DashboardFragment.ticker!!.get())
//            selectedAdapterInitial!!.setBaseline(snapshot.prevDailyBar.c.toFloat())
//            selectedAdapterInitial.pushFront(snapshot.prevDailyBar.c.toFloat())


            // Fetch last open day's information
            var calendarInitial: ArrayList<Calendar>? = null
            try {
                calendarInitial = alpacaAPI.getCalendar(LocalDate.now().minusWeeks(1), LocalDate.now()) as ArrayList<Calendar>?
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }
            var lastOpenDate = LocalDate.parse(calendarInitial!![calendarInitial.size - 2].date)
            val oldTime = LocalTime.of(calendarInitial[calendarInitial.size - 2].open.substring(0, 2).toInt(), calendarInitial[calendarInitial.size - 2].open.substring(3, 5).toInt())

            // Switch given open datetime from US/Eastern to System Default
            val zonedDateTime = ZonedDateTime.of(lastOpenDate, oldTime, ZoneId.of("US/Eastern"))
            val standardDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault())

            // Check if it is the morning of
            if (standardDateTime.toLocalTime() > LocalTime.now()) {
                lastOpenDate = LocalDate.parse(calendarInitial[calendarInitial.size - 3].date)
            }
            val lastClose = AtomicReference(0.toFloat())
            var bars: BarsResponse? = null
//            var aggs: ArrayList<Aggregate>? = null

            // https://cloud.iexapis.com/stable/stock/AAPL/intraday-prices?token=
            /*try {

                // Fetch Daily Open Close endpoint
                var nodeHttpResponse: JSONArray? = null
                try {
                    nodeHttpResponse = Unirest.get("https://cloud.iexapis.com/stable/stock/" + DashboardFragment.ticker!!.get() +
                            "/intraday-prices?token=" + Properties.iexApiKey).asJson().body.array
                    System.out.println("WE MADE IT")
                } catch (e: UnirestException) {
                    e.printStackTrace()
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }*/

            // Check if market is open
            // Get market status
            var marketStatus = true
            try {
                marketStatus = alpacaAPI.clock.isOpen
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }
//            if (marketStatus) {
            try {
                bars = alpacaAPI.getBars(DashboardFragment.ticker!!.get(), zonedDateTime, ZonedDateTime.now(), 1000, null, timeFrame)
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }
//            } else {
//
//                // If it's a day adapter and set baseline value
//                if (datetime.toLocalDate() == ZonedDateTime.now().toLocalDate()) {
//                    var temp = 0f
//
//                    // Fetch Daily Open Close endpoint
////                  https://api.polygon.io/v1/open-close/AAPL/2020-10-14?apiKey=
//                    var nodeHttpResponse: JSONObject? = null
//                    try {
//                        nodeHttpResponse = Unirest.get("https://api.polygon.io/v1/open-close/" +
//                                DashboardFragment.ticker + "/" + lastOpenDate + "?apiKey=" +
//                                prefs!!.retrieveString("polygon_id", "NULL"))
//                                .asJson().body.getObject()
//                    } catch (e: UnirestException) {
//                        e.printStackTrace()
//                    }
//
//                    // Add the close value to temp
//                    if (nodeHttpResponse != null) {
//                        try {
//                            temp = nodeHttpResponse["close"].toString().toFloat()
//                        } catch (e: JSONException) {
//                            e.printStackTrace()
//                        }
//                    }
//                    selectedAdapterInitial!!.pushFront(temp)
//                    selectedAdapterInitial.setBaseline(temp)
//                    oneDayStockAdapter!!.notifyDataSetChanged()
//                }
//                if (datetime.toLocalDate().toString() == ZonedDateTime.now().toLocalDate().toString()) {
//
//                    // Fetch last open day's information
//                    var calendar: ArrayList<Calendar>? = null
//                    try {
//                        calendar = alpacaAPI.getCalendar(LocalDate.now().minusWeeks(2), LocalDate.now()) as ArrayList<Calendar>?
//                    } catch (e: AlpacaAPIRequestException) {
//                        e.printStackTrace()
//                    }
//                    var lastOpenDate2 = LocalDate.parse(calendar!![calendar.size - 1].date)
//                    oldTime = LocalTime.of(calendar[calendar.size - 1].open.substring(0, 2).toInt(), calendar[calendar.size - 1].open.substring(3, 5).toInt())
//
//                    // Switch given open datetime from US/Eastern to System Default
//                    var time = calendar[calendar.size - 1].open
//                    var lastOpenTimeStart = LocalTime.of(time.substring(0, 2).toInt(), time.substring(3).toInt())
//                    if (standardDateTime.toLocalTime() > LocalTime.now()) {
//                        time = calendar[calendar.size - 2].open
//                        lastOpenTimeStart = LocalTime.of(time.substring(0, 2).toInt(), time.substring(3).toInt())
//                        lastOpenDate2 = LocalDate.parse(calendar[calendar.size - 2].date)
//                    }
//                    try {
//
//                        bars = alpacaAPI.getBars(DashboardFragment.ticker!!.get(), datetime, ZonedDateTime.now(), 1000, null, timeFrame)
//
////                        bars = alpacaAPI.getBars(BarsTimeFrame.FIVE_MINUTE, DashboardFragment.ticker!!.get(), 1000,
////                                ZonedDateTime.of(lastOpenDate2, lastOpenTimeStart, ZoneId.of("US/Eastern")), null, null, null)
//
////                        aggs = polygonAPI.getAggregates(ticker.get(), multiplier, timeFrame, datetime.toLocalDate().minusDays(1), LocalDate.now(), false).getResults();
//                    } catch (e: AlpacaAPIRequestException) {
//                        e.printStackTrace()
//                    }
//                } else {
//                    try {
//                        aggs = polygonAPI.getAggregates(DashboardFragment.ticker!!.get(),
//                                multiplier,
//                                timeFrame,
//                                datetime.toLocalDate(),
//                                LocalDate.now(),
//                                false).results
//                        //                        bars = alpacaAPI.getBars(timeFrame, ticker.get(), 1000, datetime, null, null, ZonedDateTime.now());
//                    } catch (e: PolygonAPIRequestException) {
//                        e.printStackTrace()
//                    }
//                }
//            }
            for (bar in bars!!.bars) {
                runOnUiThread {
                    bar.c.let { selectedAdapterInitial?.addVal(it.toFloat()) }
                }
            }

            if (datetime.year < ZonedDateTime.now().year) {
                runOnUiThread { selectedAdapterInitial?.smoothGraph() }
            }

            // Get Last value, check for weekend
            var askingPrice: Snapshot? = null
            try {
                askingPrice = alpacaAPI.getSnapshot(DashboardFragment.ticker!!.get())
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }
            val finalAskingPrice = askingPrice!!.latestQuote.ap.toFloat()
            runOnUiThread {
                selectedAdapterInitial?.addVal(finalAskingPrice)
                if (selectedAdapterInitial == oneDayStockAdapter) {
                    selectedAdapterInitial?.pushFront(askingPrice.prevDailyBar.c.toFloat())
                    selectedAdapterInitial?.setBaseline(askingPrice.prevDailyBar.c.toFloat())
                }
            }
        }
        thread.start()
        oneDayStock!!.callOnClick() // Set here to allow ample time for instantiation
    }

    private fun setDashboardColors(pos: Boolean, profitLoss: Float, percentageChange: Float) {
        val tV = TypedValue()
        theme.resolveAttribute(R.attr.color_positive_light, tV, true)
        val posColorLight = AtomicInteger(ContextCompat.getColor(this, tV.resourceId))
        theme.resolveAttribute(R.attr.color_negative_light, tV, true)
        val negColorLight = AtomicInteger(ContextCompat.getColor(this, tV.resourceId))
        if (pos) {
            percentChangeStock!!.text = String.format("+$%.2f (%.2f%%)", profitLoss, percentageChange)
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
            percentChangeStock!!.text = String.format("-$%.2f (%.2f%%)", Math.abs(profitLoss), percentageChange)
            theme.resolveAttribute(R.attr.color_negative, tV, true)
            val color = ContextCompat.getColor(this, tV.resourceId)
            percentChangeStock!!.setTextColor(color)
            percentChangeStock!!.backgroundTintList = ColorStateList.valueOf(negColorLight.get())
            val downArrow = ContextCompat.getDrawable(this, R.drawable.arrow_bottom_right)
            downArrow!!.setTint(color)
            percentChangeStock!!.setCompoundDrawablesWithIntrinsicBounds(null, null, downArrow, null)
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
        val profitLoss = selectedAdapter.getValue(selectedAdapter.count - 1) - selectedAdapter.getValue(0)
        if (selectedAdapterStock!!.count != 0) {

            // If positive
            if (profitLoss >= 0) {
                setDashboardColors(true, profitLoss, percentageChange)
            } else {
                setDashboardColors(false, profitLoss, percentageChange)
            }
        }
        val formatter = DecimalFormat("$#,###.00")
        val amount = newVal.toString().toDouble()
        tickerViewStock!!.text = formatter.format(amount)
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onItemClick(view: View?, position: Int) {}

    companion object {
        @JvmField
        var tickerViewStock: TickerView? = null
        var oneDayStockAdapter: StockAdapter? = null

        @JvmField
        var isInFront = false
    }
}
