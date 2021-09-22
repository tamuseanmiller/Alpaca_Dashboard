package com.seanmiller.alpacadashboard

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.annotation.MenuRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.robinhood.ticker.TickerUtils
import com.robinhood.ticker.TickerView
import io.cabriole.decorator.GridMarginDecoration
import io.cabriole.decorator.LinearMarginDecoration
//import net.jacobpeterson.abstracts.enums.SortDirection
import net.jacobpeterson.alpaca.AlpacaAPI
//import net.jacobpeterson.alpaca.enums.api.DataAPIType
//import net.jacobpeterson.alpaca.enums.api.EndpointAPIType
//import net.jacobpeterson.alpaca.enums.order.OrderStatus
//import net.jacobpeterson.alpaca.enums.portfolio.PortfolioPeriodUnit
//import net.jacobpeterson.alpaca.enums.portfolio.PortfolioTimeFrame
import net.jacobpeterson.alpaca.model.endpoint.calendar.Calendar
import net.jacobpeterson.alpaca.model.endpoint.common.enums.SortDirection
import net.jacobpeterson.alpaca.model.endpoint.order.Order
import net.jacobpeterson.alpaca.model.endpoint.order.enums.CurrentOrderStatus
import net.jacobpeterson.alpaca.model.endpoint.order.enums.OrderStatus
import net.jacobpeterson.alpaca.model.endpoint.portfoliohistory.PortfolioHistoryDataPoint
import net.jacobpeterson.alpaca.model.endpoint.portfoliohistory.enums.PortfolioPeriodUnit
import net.jacobpeterson.alpaca.model.endpoint.portfoliohistory.enums.PortfolioTimeFrame
import net.jacobpeterson.alpaca.model.endpoint.position.Position
import net.jacobpeterson.alpaca.model.properties.DataAPIType
import net.jacobpeterson.alpaca.model.properties.EndpointAPIType
import net.jacobpeterson.alpaca.rest.AlpacaClientException

import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import kotlin.math.abs
import io.cabriole.decorator.ColumnProvider as ColumnProvider1

class DashboardFragment : Fragment(), RecyclerViewAdapterPositions.ItemClickListener, RecyclerViewAdapterWatchlist.ItemClickListener, View.OnClickListener {
    private var sparkView: CustomSparkView? = null
    private var selectedAdapter: StockAdapter? = null
    private var recyclerViewPositions: RecyclerView? = null
    private var recyclerViewWatchlist: RecyclerView? = null
    private var recyclerViewAdapterWatchlist: RecyclerViewAdapterWatchlist? = null
    private var recyclerOrders: RecyclerView? = null
    private var recycleAdapterPositions: RecyclerViewAdapterPositions? = null
    private var recycleAdapterOrders: RecyclerViewAdapterOrders? = null
    private var percentChange: MaterialButton? = null
    private var swipeRefresh: SwipeRefreshLayout? = null
    private var orders: ArrayList<Order>? = null
    private var oneDay: MaterialButton? = null
    private var oneWeek: MaterialButton? = null
    private var oneMonth: MaterialButton? = null
    private var threeMonth: MaterialButton? = null
    private var oneYear: MaterialButton? = null
    private var selectedButton: MaterialButton? = null
    private var posOrNegColorLight: AtomicInteger? = null
    private var oneWeekAdapter: StockAdapter? = null
    private var oneMonthAdapter: StockAdapter? = null
    private var threeMonthAdapter: StockAdapter? = null
    private var oneYearAdapter: StockAdapter? = null
    private var prefs: SharedPreferencesManager? = null
    private var sparkCard: MaterialCardView? = null
    private var watchlist: ArrayList<String>? = null
    private var stocks: ArrayList<Position>? = null
    private var positionView: PositionView? = null
    private var watchlistText: TextView? = null
    private var positionsText: TextView? = null
    private var ordersText: TextView? = null

    // Fetches the height of the screen being used in order to determine the size of the graph
    private fun fetchHeight(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = requireActivity().windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                    .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.height() - insets.top - insets.bottom
        } else {
            val displayMetrics = DisplayMetrics()
            requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }
    }

    // Changes number of columns and graph height based on orientation change
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Vary number of columns based on screen size
        val numColumns = calculateNoOfColumns(context, 137f)
        val col = object : ColumnProvider1 {
            override fun getNumberOfColumns(): Int = numColumns
        }
        recyclerViewPositions!!.layoutManager = GridLayoutManager(context, numColumns)
        recyclerViewPositions!!.addItemDecoration(GridMarginDecoration(0, col,
                GridLayoutManager.VERTICAL, false, null))
        recyclerViewPositions!!.adapter = recycleAdapterPositions

        // Vary size of spark view by height of screen size
        val height = fetchHeight()
        sparkCard!!.layoutParams.height = (height / 1.75).toInt()
    }

    // Main onCreate method
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Utils.startTheme(requireActivity(), SharedPreferencesManager(requireActivity()).retrieveInt("theme", Utils.THEME_DEFAULT))
        val mView = inflater.inflate(R.layout.fragment_dashboard, null)
        prefs = SharedPreferencesManager(requireActivity())

        // Vary size of spark view by height of screen size
        val height = fetchHeight()
        sparkView = mView.findViewById(R.id.sparkview)
        sparkCard = mView.findViewById(R.id.sparkCard)
        sparkCard!!.layoutParams.height = (height / 1.75).toInt()

        // Set theme and icon
        val themeChange = mView.findViewById<ImageButton>(R.id.themeChange)
        themeChange.setOnClickListener(this)
        val outValue = TypedValue()
        requireActivity().theme.resolveAttribute(R.attr.themeName, outValue, true)
        if ("light".contentEquals(outValue.string)) {
            val lightTheme = ContextCompat.getDrawable(requireActivity(), R.drawable.brightness_6)
            themeChange.setImageDrawable(lightTheme)
        } else {
            val darkTheme = ContextCompat.getDrawable(requireActivity(), R.drawable.brightness_4)
            themeChange.setImageDrawable(darkTheme)
        }

        // Initializations
        ticker = AtomicReference("NOTICKER")
        val typedValue = TypedValue()
        requireActivity().theme.resolveAttribute(R.attr.color_positive_light, typedValue, true)
        requireActivity().theme.resolveAttribute(R.attr.color_negative_light, typedValue, true)
        val negColorLight = AtomicInteger(ContextCompat.getColor(requireActivity(), typedValue.resourceId))
        negColorLight.set(ContextCompat.getColor(requireActivity(), typedValue.resourceId))

        val alpacaAPI = AlpacaAPI(prefs!!.retrieveString("auth_token", "NULL"))

        // Set title
        val totalEquity = mView.findViewById<TextView>(R.id.stockTraded)
        totalEquity.text = "Total Equity"

        // Set button group for timeframe
        oneDay = mView.findViewById(R.id.oneDay)
        oneWeek = mView.findViewById(R.id.oneWeek)
        oneMonth = mView.findViewById(R.id.oneMonth)
        threeMonth = mView.findViewById(R.id.threeMonths)
        oneYear = mView.findViewById(R.id.oneYear)
        selectedButton = oneDay

        // The sparkline graph data
        oneDayAdapter = StockAdapter(requireActivity())
        oneWeekAdapter = StockAdapter(requireActivity())
        oneMonthAdapter = StockAdapter(requireActivity())
        threeMonthAdapter = StockAdapter(requireActivity())
        oneYearAdapter = StockAdapter(requireActivity())
        selectedAdapter = oneDayAdapter
        sparkView!!.adapter = selectedAdapter

        val initializeValues = Thread {

            // Initialize all graphs
            initializeDashboardValues(1, PortfolioPeriodUnit.DAY, PortfolioTimeFrame.FIVE_MINUTE, oneDayAdapter)
            initializeDashboardValues(1, PortfolioPeriodUnit.WEEK, PortfolioTimeFrame.ONE_HOUR, oneWeekAdapter)
            initializeDashboardValues(1, PortfolioPeriodUnit.MONTH, PortfolioTimeFrame.ONE_DAY, oneMonthAdapter)
            initializeDashboardValues(3, PortfolioPeriodUnit.MONTH, PortfolioTimeFrame.ONE_DAY, threeMonthAdapter)
            initializeDashboardValues(1, PortfolioPeriodUnit.YEAR, PortfolioTimeFrame.ONE_DAY, oneYearAdapter)

            // Set colors on click, for toggle buttons
            requireActivity().theme.resolveAttribute(R.attr.color_positive_light, typedValue, true)
            posOrNegColorLight = AtomicInteger(ContextCompat.getColor(requireActivity(), typedValue.resourceId))
            requireActivity().theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
            val colorPrimary = ContextCompat.getColor(requireActivity(), typedValue.resourceId)

            // Position View Menu button initialization
            val positionViewButton = mView.findViewById<MaterialButton>(R.id.menu_button)
            when (prefs!!.retrieveInt("position_view", 1)) {
                1 -> positionViewButton.text = getString(R.string.percent_change)
                2 -> positionViewButton.text = getString(R.string.total_percent_change)
                3 -> positionViewButton.text = getString(R.string.total_return)
            }
            positionViewButton.setOnClickListener { v: View ->
                showMenu(v, R.menu.popup_menu)
            }

            // One Day Multibutton
            oneDay?.setOnClickListener {
                selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
                selectedButton = oneDay
                selectedButton!!.backgroundTintList = ColorStateList.valueOf(posOrNegColorLight!!.get())
                selectedAdapter = oneDayAdapter
                sparkView?.adapter = selectedAdapter
                setDashboardValues()
            }

            // One Week Multibutton
            oneWeek?.setOnClickListener {
                selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
                selectedButton = oneWeek
                selectedButton!!.backgroundTintList = ColorStateList.valueOf(posOrNegColorLight!!.get())
                selectedAdapter = oneWeekAdapter
                sparkView?.adapter = selectedAdapter
                setDashboardValues()
            }

            // One Month Multibutton
            oneMonth?.setOnClickListener {
                selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
                selectedButton = oneMonth
                selectedButton!!.backgroundTintList = ColorStateList.valueOf(posOrNegColorLight!!.get())
                selectedAdapter = oneMonthAdapter
                sparkView?.adapter = selectedAdapter
                setDashboardValues()
            }

            // Three Month Multibutton
            threeMonth?.setOnClickListener {
                selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
                selectedButton = threeMonth
                selectedButton!!.backgroundTintList = ColorStateList.valueOf(posOrNegColorLight!!.get())
                selectedAdapter = threeMonthAdapter
                sparkView?.adapter = selectedAdapter
                setDashboardValues()
            }

            // One Year Multibutton
            oneYear?.setOnClickListener {
                selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
                selectedButton = oneYear
                selectedButton!!.backgroundTintList = ColorStateList.valueOf(posOrNegColorLight!!.get())
                selectedAdapter = oneYearAdapter
                sparkView?.adapter = selectedAdapter
                setDashboardValues()
            }

            // Set percent change
            percentChange = mView.findViewById(R.id.percentChange)

            // Ticker information
            tickerView = mView.findViewById(R.id.tickerView)
            tickerView?.setCharacterLists(TickerUtils.provideNumberList())

            // Set swipe refresh
            swipeRefresh = mView.findViewById(R.id.refresh)
            swipeRefresh?.translationZ = 100f
            swipeRefresh?.bringToFront()

            // Swipe to refresh recycler data
            swipeRefresh?.setOnRefreshListener {
                onRefresh()
            }

            requireActivity().theme.resolveAttribute(R.attr.colorPrimaryLight, typedValue, true)
            val color = AtomicInteger(ContextCompat.getColor(requireActivity(), typedValue.resourceId))

            // Scrub for chart
            sparkView?.sparkAnimator = null
            sparkView?.baseLineColor = color.get()
            sparkView?.setScrubListener { value: Any? ->

                // Format to add commas, scrub for ticker price
                if (value != null) {
                    val amount = value.toString().toDouble()
                    val formatter = DecimalFormat("#,###.00")
                    tickerView?.text = "$" + formatter.format(amount)
                }
            }
        }
        initializeValues.start()

        val tickerCounterThread = Thread {
            val formatter = DecimalFormat("#,###.00")

            // Run forever to get the new equities
            while (true) {
                try {
                    Thread.sleep(60000 * 5.toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                var currentValue = alpacaAPI.account().get().portfolioValue

                // Format amount
                val amount = currentValue!!.toDouble()
                requireActivity().runOnUiThread { tickerView?.text = "$" + formatter.format(amount) }
                oneDayAdapter!!.addVal(currentValue.toFloat())
                oneDayAdapter!!.notifyDataSetChanged()
                requireActivity().runOnUiThread { setDashboardValues() }
            }
        }
        tickerCounterThread.start()

        // Initialize RecyclerViews for Positions, Orders, and Watchlists
        recyclerViewPositions = mView.findViewById(R.id.recyclerStocks)
        recyclerOrders = mView.findViewById(R.id.orders)
        recyclerViewWatchlist = mView.findViewById(R.id.recyclerWatchlist)

        // Threads for getting recycler data
        val positionsThread = Thread {

            // Fetch current positions♥
            stocks = ArrayList()
            stocks = alpacaAPI.positions().get() as ArrayList<Position>?
            when (prefs!!.retrieveInt("position_view", 1)) {
                1 -> positionView = PositionView.PERCENT_CHANGE
                2 -> positionView = PositionView.TOTAL_PERCENT_CHANGE
                3 -> positionView = PositionView.TOTAL_RETURN
            }

            // If there are no positions
            positionsText = mView.findViewById(R.id.positionText)
            if (stocks!!.isEmpty()) {
                requireActivity().runOnUiThread { positionsText?.visibility = GONE }

            } else {
                // Fetch the Recycler View
                recycleAdapterPositions = RecyclerViewAdapterPositions(requireActivity(), stocks!!, positionView!!)
                recycleAdapterPositions!!.setClickListener(this)
                requireActivity().runOnUiThread {
                    recyclerViewPositions!!.adapter = recycleAdapterPositions
                    positionsText?.visibility = VISIBLE
                }

                try {
                    alpacaAPI.calendar().get()
                    val calendar = alpacaAPI.calendar().get(LocalDate.now().minusWeeks(1), LocalDate.now()) as ArrayList<Calendar>?

                    // Update positions if not market day
                    if (!calendar!!.last().date.equals(LocalDate.now().toString())) {
                        val tempList: ArrayList<Position> = ArrayList()
                        for (i in 0 until stocks?.size!!) {
                            stocks?.get(i)?.changeToday = "-1"
                            tempList.add(stocks?.get(i)!!)
                        }
                        stocks!!.clear()
                        stocks!!.addAll(tempList)
                        tempList.clear()
                        requireActivity().runOnUiThread { recycleAdapterPositions?.notifyDataSetChanged() }
                    }

                } catch (e: AlpacaClientException) {
                    e.printStackTrace()
                }
            }
        }
        positionsThread.start()

        val watchlistThread = Thread {

            // Fetch watchlist
            watchlist = ArrayList()

            // Add every stock in all watchlists
            try {
                // Check for if primary watchlist's id is already cached
                val watchlistId = prefs?.retrieveString("primary_watchlist", null)
                if (!watchlistId.isNullOrBlank()) {

                    // Loop through all assets and add symbol to list
                    for (j in alpacaAPI.watchlist().get(watchlistId).assets) {
                        watchlist?.add(j.symbol)
                    }

                } else {  // If not cached, find and cache it

                    // Loop through all watchlists
                    for (i in alpacaAPI.watchlist().get()) {

                        // Check for if this watchlist is primary
                        if (i.name == "Primary Watchlist") {

                            // Loop through all assets in primary watchlist and add to list
                            for (j in alpacaAPI.watchlist().get(i.id).assets) {
                                watchlist?.add(j.symbol)
                            }
                            prefs?.storeString("primary_watchlist", i.id)  // Store id
                            break
                        }
                    }
                }

            } catch (e: AlpacaClientException) {
                e.printStackTrace()
            }

            // If no stock in watchlist
            watchlistText = mView.findViewById(R.id.watchlistText)
            if (watchlist!!.isEmpty()) {
                requireActivity().runOnUiThread { watchlistText?.visibility = GONE }
//                alpacaAPI.createWatchlist("alpaca_dashboard", "AAPL")

            } else {

                // Set the recycler adapter
                recyclerViewAdapterWatchlist = RecyclerViewAdapterWatchlist(requireActivity(), watchlist!!)
                recyclerViewAdapterWatchlist?.setClickListener(this)
                requireActivity().runOnUiThread {
                    recyclerViewWatchlist?.adapter = recyclerViewAdapterWatchlist
                    watchlistText?.visibility = VISIBLE
                }
            }
        }
        watchlistThread.start()

        val ordersThread = Thread {

            // Fetch curent orders
            orders = ArrayList()
            try {
                orders = alpacaAPI.orders().get(
                        CurrentOrderStatus.CLOSED,
                        10,
                        ZonedDateTime.of(2000, 12, 23, 0, 0, 0, 0, ZoneId.of("America/New_York")),
                        ZonedDateTime.now().plusDays(1),
                        SortDirection.ASCENDING,
                        false, null) as ArrayList<Order>?
            } catch (e: AlpacaClientException) {
                e.printStackTrace()
            }

            // If there are no orders
            ordersText = mView.findViewById(R.id.ordersText)
            if (orders!!.isEmpty()) {
                requireActivity().runOnUiThread { ordersText?.visibility = GONE }

            } else {
                // Set the recycler adapter
                recycleAdapterOrders = RecyclerViewAdapterOrders(requireActivity(), orders!!)
                requireActivity().runOnUiThread {
                    recyclerOrders?.adapter = recycleAdapterOrders
                    ordersText?.visibility = VISIBLE
                }
            }
        }
        ordersThread.start()

        // Fetch the number of columns based on screen width
        val numColumns = calculateNoOfColumns(requireContext(), 137f)
        val col = object : ColumnProvider1 {
            override fun getNumberOfColumns(): Int = numColumns
        }
        val col2 = object : ColumnProvider1 {
            override fun getNumberOfColumns(): Int = numColumns
        }

        // Create the Decoration for each recycler from imported library
        recyclerViewPositions?.layoutManager = GridLayoutManager(requireActivity(), numColumns)
        recyclerViewPositions?.addItemDecoration(GridMarginDecoration(0, col, GridLayoutManager.VERTICAL, false, null))

        recyclerOrders?.layoutManager = LinearLayoutManager(requireActivity())
        recyclerOrders?.addItemDecoration(LinearMarginDecoration.create(0, LinearLayoutManager.VERTICAL, false, null))

        recyclerViewWatchlist?.layoutManager = GridLayoutManager(requireActivity(), numColumns)
        recyclerViewWatchlist?.addItemDecoration(GridMarginDecoration(0, col2, GridLayoutManager.VERTICAL, false, null))

        return mView
    }

    // If stock is tapped, switch to that stock
    override fun onItemClick(view: View?, position: Int) {
        ticker!!.set(recycleAdapterPositions!!.getItem(position).symbol)
        val intentMain = Intent(requireActivity(), StockPageActivity::class.java)
        requireActivity().startActivity(intentMain, ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle())

    }

    // Same as above, but for watchlists
    override fun onItemClickWatch(view: View?, position: Int) {
        ticker!!.set(recyclerViewAdapterWatchlist!!.getItem(position))
        val intentMain = Intent(requireActivity(), StockPageActivity::class.java)
        requireActivity().startActivity(intentMain, ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle())
    }

    // Next two methods both switch the colors for the recyclerviews
    override fun switchColors(view: RecyclerViewAdapterWatchlist.ViewHolder?, pos: Boolean) {
        val typedValue = TypedValue()
        if (isAdded) {
            requireActivity().theme.resolveAttribute(R.attr.color_positive, typedValue, true)
            val posColor = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
            requireActivity().theme.resolveAttribute(R.attr.color_negative, typedValue, true)
            val negColor = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
            requireActivity().theme.resolveAttribute(R.attr.color_positive_light, typedValue, true)
            val posColorLight = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
            requireActivity().theme.resolveAttribute(R.attr.color_negative_light, typedValue, true)
            val negColorLight = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
            if (pos) {
                view?.percentChange?.setTextColor(posColor)
                view?.percentChange?.backgroundTintList = ColorStateList.valueOf(posColorLight)
                view?.percentChange?.rippleColor = ColorStateList.valueOf(posColor)
                view?.stockCard?.rippleColor = ColorStateList.valueOf(posColor)
                val downArrow = ContextCompat.getDrawable(requireActivity(), R.drawable.arrow_top_right)
                downArrow!!.setTint(posColor)
                view?.percentChange?.setCompoundDrawablesWithIntrinsicBounds(downArrow, null, null, null)
            } else {
                view?.percentChange?.setTextColor(negColor)
                view?.percentChange?.backgroundTintList = ColorStateList.valueOf(negColorLight)
                view?.percentChange?.rippleColor = ColorStateList.valueOf(negColor)
                view?.stockCard?.rippleColor = ColorStateList.valueOf(negColor)
                val downArrow = ContextCompat.getDrawable(requireActivity(), R.drawable.arrow_bottom_right)
                downArrow!!.setTint(negColor)
                view?.percentChange?.setCompoundDrawablesWithIntrinsicBounds(downArrow, null, null, null)
            }
        }
    }

    override fun switchColors(view: RecyclerViewAdapterPositions.ViewHolder?, pos: Boolean) {
        val typedValue = TypedValue()
        if (isAdded) {
            requireActivity().theme.resolveAttribute(R.attr.color_positive, typedValue, true)
            val posColor = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
            requireActivity().theme.resolveAttribute(R.attr.color_negative, typedValue, true)
            val negColor = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
            requireActivity().theme.resolveAttribute(R.attr.color_positive_light, typedValue, true)
            val posColorLight = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
            requireActivity().theme.resolveAttribute(R.attr.color_negative_light, typedValue, true)
            val negColorLight = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
            if (pos) {
                view?.percentChange?.setTextColor(posColor)
                view?.percentChange?.backgroundTintList = ColorStateList.valueOf(posColorLight)
                view?.percentChange?.rippleColor = ColorStateList.valueOf(posColor)
                view?.stockCard?.rippleColor = ColorStateList.valueOf(posColor)
                val downArrow = ContextCompat.getDrawable(requireActivity(), R.drawable.arrow_top_right)
                downArrow!!.setTint(posColor)
                view?.percentChange?.setCompoundDrawablesWithIntrinsicBounds(downArrow, null, null, null)
            } else {
                view?.percentChange?.setTextColor(negColor)
                view?.percentChange?.backgroundTintList = ColorStateList.valueOf(negColorLight)
                view?.percentChange?.rippleColor = ColorStateList.valueOf(negColor)
                view?.stockCard?.rippleColor = ColorStateList.valueOf(negColor)
                val downArrow = ContextCompat.getDrawable(requireActivity(), R.drawable.arrow_bottom_right)
                downArrow!!.setTint(negColor)
                view?.percentChange?.setCompoundDrawablesWithIntrinsicBounds(downArrow, null, null, null)
            }
        }
    }

    // onRefresh method is what is called when swipe refresh is enabled
    private fun onRefresh() {
        swipeRefresh!!.isRefreshing = true  // Set refreshing for the animation

        val positionsThread = Thread {
            val alpacaAPI = AlpacaAPI(null, null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)

            // Fetch current positions
            var positions = ArrayList<Position>()
            try {
                positions = alpacaAPI.positions().get() as ArrayList<Position>
            } catch (e: AlpacaClientException) {
                e.printStackTrace()
            }

            // If there are no positions
            if (stocks!!.isEmpty()) {
                requireActivity().runOnUiThread {
                    positionsText?.visibility = GONE
                    recycleAdapterPositions?.notifyDataSetChanged()
                }

            } else {
                stocks!!.clear()
                stocks!!.addAll(positions)
                positions.clear()

                // Set Recycle Adapter for positions
                // Fetch the Recycler View
                requireActivity().runOnUiThread {
                    positionsText?.visibility = VISIBLE
                    recycleAdapterPositions?.notifyDataSetChanged()
                }
            }
        }
        positionsThread.start()

        val ordersThread = Thread {
            val alpacaAPI = AlpacaAPI(null, null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)

            // Fetch curent orders
            orders!!.clear()
            var temp: ArrayList<Order> = ArrayList()
            try {
                temp = (alpacaAPI.orders().get(
                        CurrentOrderStatus.CLOSED,
                        10,
                        ZonedDateTime.of(2000, 12, 23, 0, 0, 0, 0, ZoneId.of("America/New_York")),
                        ZonedDateTime.now().plusDays(1),
                        SortDirection.ASCENDING,
                        false, null) as ArrayList<Order>?)!!
            } catch (e: AlpacaClientException) {
                e.printStackTrace()
            }

            // If there are no orders
            if (temp.isEmpty()) {
                requireActivity().runOnUiThread {
                    ordersText?.visibility = GONE
                    recycleAdapterOrders?.notifyDataSetChanged()
                }

            } else {
                orders!!.addAll(temp)
                temp.clear()

                // Set Recycle Adapter for orders
//                recycleAdapterOrders = RecyclerViewAdapterOrders(requireActivity(), orders!!)
                requireActivity().runOnUiThread {
                    ordersText?.visibility = VISIBLE
                    recycleAdapterOrders?.notifyDataSetChanged()
//                    recyclerOrders?.adapter = recycleAdapterOrders
                }
            }
        }
        ordersThread.start()

        val watchlistThread = Thread {
            val alpacaAPI = AlpacaAPI(null, null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)

            // Fetch watchlist
            watchlist?.clear()
            val tempList: ArrayList<String> = ArrayList()

            try {

                // Check for if primary watchlist's id is already cached
                val watchlistId = prefs?.retrieveString("primary_watchlist", null)
                if (!watchlistId.isNullOrBlank()) {

                    // Loop through all assets and add symbol to list
                    for (j in alpacaAPI.watchlist().get(watchlistId).assets) {
                        tempList.add(j.symbol)
                    }

                } else {  // If not cached, find and cache it

                    // Loop through all watchlists
                    for (i in alpacaAPI.watchlist().get()) {

                        // Check for if this watchlist is primary
                        if (i.name == "Primary Watchlist") {

                            // Loop through all assets in primary watchlist and add to list
                            for (j in alpacaAPI.watchlist().get(i.id).assets) {
                                tempList.add(j.symbol)
                            }
                            prefs?.storeString("primary_watchlist", i.id)  // Store id
                            break
                        }
                    }
                }

            } catch (e: AlpacaClientException) {
                e.printStackTrace()
                swipeRefresh!!.isRefreshing = false
            }

            // If no stock in watchlist
            if (tempList.isEmpty()) {
                requireActivity().runOnUiThread {
                    watchlistText?.visibility = GONE
                    recyclerViewAdapterWatchlist?.notifyDataSetChanged()
                }

            } else {

                // Set Recycle Adapter for positions
//              recyclerViewAdapterWatchlist = RecyclerViewAdapterWatchlist(requireActivity(), watchlist!!)
                watchlist?.addAll(tempList)
                tempList.clear()
                requireActivity().runOnUiThread {
                    watchlistText?.visibility = VISIBLE
                    recyclerViewAdapterWatchlist?.notifyDataSetChanged()
                }
            }
            swipeRefresh!!.isRefreshing = false
        }
        watchlistThread.start()
    }

    // Theme change on click between dark and light
    override fun onClick(v: View) {
        val outValue = TypedValue()
        requireActivity().theme.resolveAttribute(R.attr.themeName, outValue, true)
        if ("light".contentEquals(outValue.string)) {
            SharedPreferencesManager(requireActivity()).storeInt("theme", Utils.THEME_DARK)
            Utils.changeToTheme(requireActivity(), Utils.THEME_DARK)
        } else {
            SharedPreferencesManager(requireActivity()).storeInt("theme", Utils.THEME_LIGHT)
            Utils.changeToTheme(requireActivity(), Utils.THEME_LIGHT)
        }
    }

    // Sets all of the colors for the entire dashboard fragment based on if positive or negative for the day
    private fun setDashboardColors(pos: Boolean, profitLoss: Float, percentageChange: Float) {
        val typedValue = TypedValue()
        requireActivity().theme.resolveAttribute(R.attr.color_positive_light, typedValue, true)
        val posColorLight = AtomicInteger(ContextCompat.getColor(requireActivity(), typedValue.resourceId))
        requireActivity().theme.resolveAttribute(R.attr.color_negative_light, typedValue, true)
        val negColorLight = AtomicInteger(ContextCompat.getColor(requireActivity(), typedValue.resourceId))
        if (pos) {
            percentChange!!.text = String.format("+$%.2f (%.2f%%)", profitLoss, percentageChange)
            requireActivity().theme.resolveAttribute(R.attr.color_positive, typedValue, true)
            val color = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
            percentChange!!.setTextColor(color)
            percentChange!!.backgroundTintList = ColorStateList.valueOf(posColorLight.get())
            val upArrow = ContextCompat.getDrawable(requireActivity(), R.drawable.arrow_top_right)
            upArrow!!.setTint(color)
            percentChange!!.setCompoundDrawablesWithIntrinsicBounds(null, null, upArrow, null)
            sparkView!!.lineColor = color
            selectedButton!!.backgroundTintList = ColorStateList.valueOf(posColorLight.get())
            oneDay!!.setTextColor(color)
            oneWeek!!.setTextColor(color)
            oneMonth!!.setTextColor(color)
            threeMonth!!.setTextColor(color)
            oneYear?.setTextColor(color)
            oneDay?.rippleColor = ColorStateList.valueOf(color)
            oneWeek?.rippleColor = ColorStateList.valueOf(color)
            oneMonth?.rippleColor = ColorStateList.valueOf(color)
            threeMonth?.rippleColor = ColorStateList.valueOf(color)
            oneYear?.rippleColor = ColorStateList.valueOf(color)
            requireActivity().theme.resolveAttribute(R.attr.color_positive_light, typedValue, true)
        } else {
            percentChange!!.text = String.format("-$%.2f (%.2f%%)", abs(profitLoss), percentageChange)
            requireActivity().theme.resolveAttribute(R.attr.color_negative, typedValue, true)
            val color = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
            percentChange!!.setTextColor(color)
            percentChange!!.backgroundTintList = ColorStateList.valueOf(negColorLight.get())
            val downArrow = ContextCompat.getDrawable(requireActivity(), R.drawable.arrow_bottom_right)
            downArrow!!.setTint(color)
            percentChange!!.setCompoundDrawablesWithIntrinsicBounds(null, null, downArrow, null)
            sparkView!!.lineColor = color
            selectedButton!!.backgroundTintList = ColorStateList.valueOf(negColorLight.get())
            oneDay!!.setTextColor(color)
            oneWeek!!.setTextColor(color)
            oneMonth!!.setTextColor(color)
            threeMonth!!.setTextColor(color)
            oneYear!!.setTextColor(color)
            oneDay?.rippleColor = ColorStateList.valueOf(color)
            oneWeek?.rippleColor = ColorStateList.valueOf(color)
            oneMonth?.rippleColor = ColorStateList.valueOf(color)
            threeMonth?.rippleColor = ColorStateList.valueOf(color)
            oneYear?.rippleColor = ColorStateList.valueOf(color)
            requireActivity().theme.resolveAttribute(R.attr.color_negative_light, typedValue, true)
        }
        posOrNegColorLight!!.set(ContextCompat.getColor(requireActivity(), typedValue.resourceId))
    }

    // Initialization for the stock graphs
    private fun initializeDashboardValues(periodLength: Int, periodUnit: PortfolioPeriodUnit, timeFrame: PortfolioTimeFrame, selectedAdapterInitial: StockAdapter?) {
        val alpacaAPI = AlpacaAPI(null, null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)
        val alpacaData = AlpacaAPI(Properties.apiKey, Properties.secretKey);
        val fetchHistoryThread = Thread {
            val historyInitial = AtomicReference(ArrayList<PortfolioHistoryDataPoint>())

            // Fetch last open day's information
            val calendar: java.util.ArrayList<Calendar>?
            try {
                calendar = alpacaData.calendar().get(LocalDate.now().minusWeeks(1), LocalDate.now()) as ArrayList<Calendar>?
                var lastOpenDate2 = LocalDate.now().minusDays(1)
                var oldTime = LocalTime.now().minusHours(2)
                if (calendar!!.size >= 2) {
                    lastOpenDate2 = LocalDate.parse(calendar[calendar.size - 2].date.toString())
                    oldTime = LocalTime.of(calendar[calendar.size - 2].open.toString().substring(0, 2).toInt(), calendar[calendar.size - 2].open.toString().substring(3, 5).toInt())
                }

                // Switch given open datetime from US/Eastern to System Default
                var zonedDateTime = ZonedDateTime.of(lastOpenDate2, oldTime, ZoneId.of("US/Eastern"))
                var standardDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault())

                // Check if it is the morning of
                if (standardDateTime.toLocalTime() > LocalTime.now()) {
                    lastOpenDate2 = LocalDate.parse(calendar[calendar.size - 3].date.toString())
                }

                var history: ArrayList<PortfolioHistoryDataPoint?>? = null
                if (PortfolioTimeFrame.FIVE_MINUTE == timeFrame) {

                    // Gather old portfolio data
                    historyInitial.set(ArrayList())
                    try {
                        historyInitial.set(alpacaAPI.portfolioHistory().get(periodLength, periodUnit, timeFrame, lastOpenDate2, false).dataPoints)
                    } catch (e: AlpacaClientException) {
                        e.printStackTrace()
                    }

                    if (historyInitial.get().isNotEmpty()) {
                        val temp: Int = historyInitial.get().last().equity.toInt()
                        selectedAdapterInitial!!.pushFront(temp.toFloat())
                        selectedAdapterInitial.setBaseline(selectedAdapterInitial.getValue(0))
                        oneDayAdapter!!.notifyDataSetChanged()
                    }
                }

                if (periodUnit == PortfolioPeriodUnit.DAY) {

                    // Assign last open datetime and check for if it is the morning of
                    var lastOpenDate = LocalDate.parse(calendar.last().date.toString())
                    oldTime = LocalTime.of(calendar.last().open.toString().substring(0, 2).toInt(), calendar.last().open.toString().substring(3, 5).toInt())

                    // Switch given open datetime from US/Eastern to System Default
                    zonedDateTime = ZonedDateTime.of(lastOpenDate2, oldTime, ZoneId.of("US/Eastern"))
                    standardDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault())
                    if (standardDateTime.toLocalTime() > LocalTime.now()) {
                        lastOpenDate = LocalDate.parse(calendar[calendar.size - 2].date.toString())
                    }

                    // Gather old portfolio data
                    try {
                        val portVal = alpacaAPI.portfolioHistory().get(periodLength,
                                periodUnit, timeFrame, lastOpenDate, true)
                        history = portVal.dataPoints
                    } catch (e: AlpacaClientException) {
                        e.printStackTrace()
                    }
                } else {

                    // Gather old portfolio data
                    history = ArrayList()
                    try {
                        val portVal = alpacaAPI.portfolioHistory().get(periodLength,
                                periodUnit, timeFrame, LocalDate.now(), true)
                        history = portVal.dataPoints
                    } catch (e: AlpacaClientException) {
                        e.printStackTrace()
                    }
                }

                // Fixes weird bug with repeating data on week period
                if (periodUnit == PortfolioPeriodUnit.WEEK) {
                    // Add data to chart
                    if (history!!.size != 0) {
                        for (i in 10 until history.size) {
                            if (history[i] != null) {
                                selectedAdapterInitial!!.addVal(history[i]?.equity.toString().toFloat())
                            }
                        }
                    }

                } else {
                    // Add data to chart
                    if (history!!.size != 0) {
                        for (i in history.indices) {
                            if (history[i] != null) {
                                selectedAdapterInitial!!.addVal(history[i]?.equity.toString().toFloat())
                            }
                        }
                    }
                }

            } catch (e: AlpacaClientException) {
                e.printStackTrace()
            }

            // Set baseline values
            // Get market status
//            var marketStatus = true
//            try {
//                marketStatus = alpacaAPI.clock.isOpen
//            } catch (e: AlpacaAPIRequestException) {
//                e.printStackTrace()
//            }

            // Use data returned to get profit change
            val oldVal = selectedAdapterInitial!!.getValue(0)
            val newVal = selectedAdapterInitial.getValue(selectedAdapterInitial.count - 1)
            val percentageChange = (newVal - oldVal) / oldVal * 100
            val profitLoss = selectedAdapterInitial.getValue(selectedAdapterInitial.count - 1) - selectedAdapterInitial.getValue(0)

            // Sets profit values
            selectedAdapterInitial.percent = percentageChange
            selectedAdapterInitial.profit = profitLoss

            // Smooth year graph
            if (periodUnit == PortfolioPeriodUnit.YEAR) {
                selectedAdapterInitial.smoothGraph()
            }

            selectedAdapterInitial.notifyDataSetChanged()
            setDashboardValues() // Set here to allow ample time for instantiation
        }
        fetchHistoryThread.start()

        val setCurrentEquityThread = Thread {
            val currentValue: String?
            try {
                currentValue = alpacaAPI.account().get().portfolioValue
                val amount = currentValue!!.toDouble()

                // Format amount
                val formatter = DecimalFormat("#,###.00")
                requireActivity().runOnUiThread { tickerView!!.text = "$" + formatter.format(amount) }

            } catch (e: AlpacaClientException) {
                e.printStackTrace()
            }
        }
        setCurrentEquityThread.start()
    }

    // What is called to update the dashboard colors for each graph
    private fun setDashboardValues() {

        val setThread = Thread {

            // Updating the values on history switch
            // Set colors
            requireActivity().runOnUiThread {
                if (selectedAdapter!!.profit >= 0) {
                    setDashboardColors(true, selectedAdapter!!.profit, selectedAdapter!!.percent)
                } else {
                    setDashboardColors(false, selectedAdapter!!.profit, selectedAdapter!!.percent)
                }
            }
        }
        setThread.start()
    }

    companion object {
        @JvmField
        var tickerView: TickerView? = null

        @JvmField
        var ticker: AtomicReference<String>? = null
        var oneDayAdapter: StockAdapter? = null

        // To determine number of columns necessary for GridLayoutManager
        fun calculateNoOfColumns(context: Context?, columnWidthDp: Float): Int {
            val displayMetrics = context!!.resources.displayMetrics
            val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
            val noOfColumns = (screenWidthDp / columnWidthDp + 0.5).toInt() // +0.5 for correct rounding to int.
            return noOfColumns - 1
        }
    }

    // Popup menu for position view
    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val wrapper: Context = ContextThemeWrapper(requireActivity(), R.style.light_dark)
        val popup = PopupMenu(wrapper, v, Gravity.END)
        popup.menuInflater.inflate(menuRes, popup.menu)
        val button: MaterialButton = v.findViewById(R.id.menu_button)

        popup.setOnMenuItemClickListener { item ->

            // Set button text to the chosen menu item, then set position
            button.text = item.title
            when (item.title.toString()) {
                getString(R.string.total_return) -> {
                    positionView = PositionView.TOTAL_RETURN
                    prefs!!.storeInt("position_view", 3)
                }
                getString(R.string.total_percent_change) -> {
                    positionView = PositionView.TOTAL_PERCENT_CHANGE
                    prefs!!.storeInt("position_view", 2)
                }
                getString(R.string.percent_change) -> {
                    positionView = PositionView.PERCENT_CHANGE
                    prefs!!.storeInt("position_view", 1)
                }
            }

            // notifies the adapter that the values need to be changed
            recycleAdapterPositions?.notifyItemChanged(positionView)
            recycleAdapterPositions?.notifyDataSetChanged()

            false
        }
        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }
        // Show the popup menu.
        popup.show()
    }

}
