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
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.WindowInsets
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.robinhood.ticker.TickerUtils
import com.robinhood.ticker.TickerView
import io.cabriole.decorator.GridMarginDecoration
import io.cabriole.decorator.LinearMarginDecoration
import net.jacobpeterson.abstracts.enums.SortDirection
import net.jacobpeterson.alpaca.AlpacaAPI
import net.jacobpeterson.alpaca.enums.api.DataAPIType
import net.jacobpeterson.alpaca.enums.api.EndpointAPIType
import net.jacobpeterson.alpaca.enums.order.OrderStatus
import net.jacobpeterson.alpaca.enums.portfolio.PortfolioPeriodUnit
import net.jacobpeterson.alpaca.enums.portfolio.PortfolioTimeFrame
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException
import net.jacobpeterson.domain.alpaca.calendar.Calendar
import net.jacobpeterson.domain.alpaca.order.Order
import net.jacobpeterson.domain.alpaca.position.Position
//import net.jacobpeterson.polygon.PolygonAPI
//import net.jacobpeterson.polygon.rest.exception.PolygonAPIRequestException
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import kotlin.math.abs
import io.cabriole.decorator.ColumnProvider as ColumnProvider1


class DashboardFragment : Fragment(), RecyclerViewAdapterPositions.ItemClickListener, RecyclerViewAdapterWatchlist.ItemClickListener, OnClickListener {
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
        sparkCard!!.minimumHeight = (height / 1.75).toInt()
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Utils.startTheme(requireActivity(), SharedPreferencesManager(requireActivity()).retrieveInt("theme", Utils.THEME_DEFAULT))
        val mView = inflater.inflate(R.layout.fragment_dashboard, null)
        prefs = SharedPreferencesManager(requireActivity())

        // Vary size of spark view by height of screen size
        val height = fetchHeight()
        sparkView = mView.findViewById(R.id.sparkview)
        sparkCard = mView.findViewById(R.id.sparkCard)
        with(sparkCard) {
            this?.setMinimumHeight((height / 1.75).toInt())
        }

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
        val posColorLight = AtomicInteger(ContextCompat.getColor(requireActivity(), typedValue.resourceId))
        requireActivity().theme.resolveAttribute(R.attr.color_negative_light, typedValue, true)
        val negColorLight = AtomicInteger(ContextCompat.getColor(requireActivity(), typedValue.resourceId))
        negColorLight.set(ContextCompat.getColor(requireActivity(), typedValue.resourceId))
//        val polygonAPI = PolygonAPI(prefs!!.retrieveString("polygon_id", "NULL"))

        val alpacaAPI = AlpacaAPI(null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)

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

        val t4 = Thread {

            // Initialize all graphs
            initializeDashboardValues(1, PortfolioPeriodUnit.DAY, PortfolioTimeFrame.FIVE_MINUTE, oneDayAdapter)
            initializeDashboardValues(1, PortfolioPeriodUnit.WEEK, PortfolioTimeFrame.ONE_HOUR, oneWeekAdapter)
            initializeDashboardValues(1, PortfolioPeriodUnit.MONTH, PortfolioTimeFrame.ONE_DAY, oneMonthAdapter)
            initializeDashboardValues(3, PortfolioPeriodUnit.MONTH, PortfolioTimeFrame.ONE_DAY, threeMonthAdapter)
            initializeDashboardValues(1, PortfolioPeriodUnit.YEAR, PortfolioTimeFrame.ONE_DAY, oneYearAdapter)
        }

            // Set colors on click, for toggle buttons
            requireActivity().theme.resolveAttribute(R.attr.color_positive_light, typedValue, true)
            posOrNegColorLight = AtomicInteger(ContextCompat.getColor(requireActivity(), typedValue.resourceId))
            requireActivity().theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
            val colorPrimary = ContextCompat.getColor(requireActivity(), typedValue.resourceId)

//            requireActivity().runOnUiThread {

                // One Day Multibutton
                oneDay?.setOnClickListener {
                    selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
                    selectedButton = oneDay
                    selectedButton!!.backgroundTintList = ColorStateList.valueOf(posOrNegColorLight!!.get())
                    selectedAdapter = oneDayAdapter
                    sparkView.let {
                        sparkView!!.adapter = selectedAdapter
                    }
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
                    with(sparkView) { this?.setAdapter(selectedAdapter) }
                    setDashboardValues()
                }


                // One Year Multibutton
                oneYear?.setOnClickListener {
                    selectedButton!!.backgroundTintList = ColorStateList.valueOf(colorPrimary)
                    selectedButton = oneYear
                    selectedButton!!.backgroundTintList = ColorStateList.valueOf(posOrNegColorLight!!.get())
                    selectedAdapter = oneYearAdapter
                    with(sparkView) { this?.setAdapter(selectedAdapter) }
                    setDashboardValues()
                }
//            }

//        }
        t4.start()


        // Set percent change
        percentChange = mView.findViewById(R.id.percentChange)

        // Ticker information
        tickerView = mView.findViewById(R.id.tickerView)
        tickerView?.setCharacterLists(TickerUtils.provideNumberList())

        // Set swipe refresh
        swipeRefresh = mView.findViewById(R.id.refresh)
        swipeRefresh?.translationZ = 100f
        swipeRefresh?.bringToFront()

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

        val t3 = Thread {
            val formatter = DecimalFormat("#,###.00")

            // Run forever to get the new equities
            while (true) {
                try {
                    Thread.sleep(60000 * 5.toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                var currentValue: String? = null
                try {
                    currentValue = alpacaAPI.account.portfolioValue
                } catch (e: AlpacaAPIRequestException) {
                    e.printStackTrace()
                }

                // Format amount
                val amount = currentValue!!.toDouble()
                requireActivity().runOnUiThread { tickerView?.text = "$" + formatter.format(amount) }
                oneDayAdapter!!.addVal(currentValue.toFloat())
                oneDayAdapter!!.notifyDataSetChanged()
                requireActivity().runOnUiThread { setDashboardValues() }
            }
        }
        t3.start()
        recyclerViewPositions = mView.findViewById(R.id.recyclerStocks)
        recyclerOrders = mView.findViewById(R.id.orders)
        recyclerViewWatchlist = mView.findViewById(R.id.recyclerWatchlist)

        // Threads for getting recycler data
        val thread = Thread {

            // Fetch current positions
            var positions = ArrayList<Position>()
            try {
                positions = alpacaAPI.openPositions
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }
            stocks = ArrayList()
            for (i in positions) {
                stocks!!.add(i.symbol)
            }

            // Fetch the Recycler View
            recycleAdapterPositions = RecyclerViewAdapterPositions(requireActivity(), stocks!!)
            recycleAdapterPositions!!.setClickListener(this)
            requireActivity().runOnUiThread { recyclerViewPositions!!.adapter = recycleAdapterPositions }
        }
        thread.start()

        val thread3 = Thread {

            // Fetch watchlist
            watchlist = ArrayList()
            val tempList: ArrayList<String> = ArrayList()

            try {
                for (i in alpacaAPI.watchlists) {
                    for (j in alpacaAPI.getWatchlist(i.id).assets) {
                        if (!tempList.contains(j.symbol))
                            tempList.add(j.symbol)
                    }
                }
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }

            if (tempList.isEmpty()) {
                alpacaAPI.addWatchlistAsset("b0747289-92e4-404b-9558-7b682cb36de1", "TSLA")
                alpacaAPI.createWatchlist("alpaca_dashboard", "AAPL")

            } else {
                watchlist?.clear()
                watchlist?.addAll(tempList)
                recyclerViewAdapterWatchlist = RecyclerViewAdapterWatchlist(requireActivity(), watchlist!!)
                recyclerViewAdapterWatchlist?.setClickListener(this)
                requireActivity().runOnUiThread {
                    recyclerViewWatchlist?.adapter = recyclerViewAdapterWatchlist
                }
            }
        }
        thread3.start()

        val thread2 = Thread {

            // Fetch curent orders
            orders = ArrayList()
            var tempOrders: ArrayList<Order> = ArrayList()
            try {
                tempOrders = (alpacaAPI.getOrders(OrderStatus.CLOSED, 10, null, ZonedDateTime.now().plusDays(1), SortDirection.DESCENDING, false, null) as ArrayList<Order>?)!!
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }
            orders?.addAll(tempOrders)
            recycleAdapterOrders = RecyclerViewAdapterOrders(requireActivity(), orders!!)
            requireActivity().runOnUiThread { recyclerOrders?.adapter = recycleAdapterOrders }
        }
        thread2.start()
        val numColumns = calculateNoOfColumns(requireContext(), 137f)
        val col = object : ColumnProvider1 {
            override fun getNumberOfColumns(): Int = numColumns
        }
        val col2 = object : ColumnProvider1 {
            override fun getNumberOfColumns(): Int = numColumns
        }

        recyclerViewPositions?.layoutManager = GridLayoutManager(requireActivity(), numColumns)
        recyclerViewPositions?.addItemDecoration(GridMarginDecoration(0, col, GridLayoutManager.VERTICAL, false, null))

        recyclerOrders?.layoutManager = LinearLayoutManager(requireActivity())
        recyclerOrders?.addItemDecoration(LinearMarginDecoration.create(0, LinearLayoutManager.VERTICAL, false, null))

        recyclerViewWatchlist?.layoutManager = GridLayoutManager(requireActivity(), numColumns)
        recyclerViewWatchlist?.addItemDecoration(GridMarginDecoration(0, col2, GridLayoutManager.VERTICAL, false, null))

        // Swipe to refresh recycler data
        swipeRefresh?.setOnRefreshListener {
            onRefresh()
        }

        return mView
    }

    // If stock is tapped, switch to that stock
    override fun onItemClick(view: View?, position: Int) {
        ticker!!.set(recycleAdapterPositions!!.getItem(position))
        val intentMain = Intent(requireActivity(), StockPageActivity::class.java)
        requireActivity().startActivity(intentMain, ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle())

    }

    override fun onItemClickWatch(view: View?, position: Int) {
        ticker!!.set(recyclerViewAdapterWatchlist!!.getItem(position))
        val intentMain = Intent(requireActivity(), StockPageActivity::class.java)
        requireActivity().startActivity(intentMain, ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle())
    }

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

    private fun onRefresh() {
        swipeRefresh!!.isRefreshing = true
        val thread = Thread {
            val alpacaAPI = AlpacaAPI(null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)

            // Fetch current positions
            var positions = ArrayList<Position>()
            try {
                positions = alpacaAPI.openPositions
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }
            val temp = ArrayList<String>()
            for (i in positions) {
                temp.add(i.symbol)
            }
            stocks!!.clear()
            stocks!!.addAll(temp)
            temp.clear()

            // Set Recycle Adapter for positions
            requireActivity().runOnUiThread { recycleAdapterPositions?.notifyDataSetChanged() }
        }
        thread.start()

        val thread2 = Thread {
            val alpacaAPI = AlpacaAPI(null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)

            // Fetch curent orders
            orders!!.clear()
            var temp: ArrayList<Order> = ArrayList()
            try {
                temp = alpacaAPI.getOrders(OrderStatus.CLOSED, 10, null, ZonedDateTime.now().plusDays(1), SortDirection.DESCENDING, false, null) as ArrayList<Order>
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }
            orders!!.addAll(temp)
            temp.clear()

            // Set Recycle Adapter for orders
            recycleAdapterOrders = RecyclerViewAdapterOrders(requireActivity(), orders!!)
            requireActivity().runOnUiThread {
//                recycleAdapterOrders?.notifyDataSetChanged()
                recyclerOrders?.adapter = recycleAdapterOrders
            }
        }
        thread2.start()

        val thread3 = Thread {
            val alpacaAPI = AlpacaAPI(null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)

            // Fetch watchlist
            watchlist?.clear()
            val tempList: ArrayList<String> = ArrayList()

            try {
                for (i in alpacaAPI.watchlists) {
                    for (j in alpacaAPI.getWatchlist(i.id).assets) {
                        if (!tempList.contains(j.symbol))
                            tempList.add(j.symbol)
                    }
                }
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }

            // Set Recycle Adapter for positions
//            recyclerViewAdapterWatchlist = RecyclerViewAdapterWatchlist(requireActivity(), watchlist!!)
            watchlist?.addAll(tempList)
            tempList.clear()
            requireActivity().runOnUiThread { recyclerViewAdapterWatchlist?.notifyDataSetChanged() }
            swipeRefresh!!.isRefreshing = false
        }
        thread3.start()
    }

    // Theme change on click
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

    private fun initializeDashboardValues(periodLength: Int, periodUnit: PortfolioPeriodUnit, timeFrame: PortfolioTimeFrame, selectedAdapterInitial: StockAdapter?) {
        val alpacaAPI = AlpacaAPI(null, null, prefs!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)
//        val polygonAPI = PolygonAPI(prefs!!.retrieveString("polygon_id", "NULL"))
        val t2 = Thread {
            val historyInitial = AtomicReference(ArrayList<Double>())

            // Fetch last open day's information
            var calendar: ArrayList<Calendar>? = null
            try {
                calendar = alpacaAPI.getCalendar(LocalDate.now().minusWeeks(1), LocalDate.now()) as ArrayList<Calendar>?
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }
            var lastOpenDate2 = LocalDate.parse(calendar!![calendar.size - 2].date)
            var oldTime = LocalTime.of(calendar[calendar.size - 2].open.substring(0, 2).toInt(), calendar[calendar.size - 2].open.substring(3, 5).toInt())

            // Switch given open datetime from US/Eastern to System Default
            var zonedDateTime = ZonedDateTime.of(lastOpenDate2, oldTime, ZoneId.of("US/Eastern"))
            var standardDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault())

            // Check if it is the morning of
            if (standardDateTime.toLocalTime() > LocalTime.now()) {
                lastOpenDate2 = LocalDate.parse(calendar[calendar.size - 3].date)
            }

            // Set baseline values
            // Get market status
            var marketStatus = true
            try {
                marketStatus = alpacaAPI.clock.isOpen
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }

            var history: ArrayList<Double?>? = null
            if (PortfolioTimeFrame.FIVE_MINUTE == timeFrame) {

                // Gather old portfolio data
                historyInitial.set(ArrayList())
                try {
                    historyInitial.set(alpacaAPI.getPortfolioHistory(periodLength, periodUnit, timeFrame, lastOpenDate2, false).equity)
                } catch (e: AlpacaAPIRequestException) {
                    e.printStackTrace()
                }
                val temp = historyInitial.get()[historyInitial.get().size - 3]
                selectedAdapterInitial!!.pushFront(temp.toFloat())
                selectedAdapterInitial.setBaseline(selectedAdapterInitial.getValue(0))
                oneDayAdapter!!.notifyDataSetChanged()
            }

            if (periodUnit == PortfolioPeriodUnit.DAY) {

                // Assign last open datetime and check for if it is the morning of
                var lastOpenDate = LocalDate.parse(calendar[calendar.size - 1].date)
                oldTime = LocalTime.of(calendar[calendar.size - 1].open.substring(0, 2).toInt(), calendar[calendar.size - 1].open.substring(3, 5).toInt())

                // Switch given open datetime from US/Eastern to System Default
                zonedDateTime = ZonedDateTime.of(lastOpenDate2, oldTime, ZoneId.of("US/Eastern"))
                standardDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault())
                if (standardDateTime.toLocalTime() > LocalTime.now()) {
                    lastOpenDate = LocalDate.parse(calendar[calendar.size - 2].date)
                }

                // Gather old portfolio data
                try {
                    val portVal = alpacaAPI.getPortfolioHistory(periodLength,
                            periodUnit, timeFrame, lastOpenDate, true)
                    history = portVal.equity
                } catch (e: AlpacaAPIRequestException) {
                    e.printStackTrace()
                }
            } else {

                // Gather old portfolio data
                history = ArrayList()
                try {
                    val portVal = alpacaAPI.getPortfolioHistory(periodLength,
                            periodUnit, timeFrame, LocalDate.now(), true)
                    history = portVal.equity
                } catch (e: AlpacaAPIRequestException) {
                    e.printStackTrace()
                }
            }

            // Fixes weird bug with repeating data on week period
            if (periodUnit == PortfolioPeriodUnit.WEEK) {
                // Add data to chart
                if (history!!.size != 0) {
                    for (i in 10 until history.size) {
                        if (history[i] != null) {
                            selectedAdapterInitial!!.addVal(history[i].toString().toFloat())
                        }
                    }
                }
            } else {
                // Add data to chart
                if (history!!.size != 0) {
                    for (i in history.indices) {
                        if (history[i] != null) {
                            selectedAdapterInitial!!.addVal(history[i].toString().toFloat())
                        }
                    }
                }
            }

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
            var currentValue: String? = null
            try {
                currentValue = alpacaAPI.account.portfolioValue
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }

            // Format amount
            val amount = currentValue!!.toDouble()
            val formatter = DecimalFormat("#,###.00")
            requireActivity().runOnUiThread { tickerView!!.text = "$" + formatter.format(amount) }
            selectedAdapterInitial.addVal(currentValue.toFloat())
            selectedAdapterInitial.notifyDataSetChanged()
            setDashboardValues() // Set here to allow ample time for instantiation
        }
        t2.start()
    }

    private fun setDashboardValues() {

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

    companion object {
        @JvmField
        var tickerView: TickerView? = null

        @JvmField
        var ticker: AtomicReference<String>? = null
        var oneDayAdapter: StockAdapter? = null
        var stocks: ArrayList<String>? = null

        // To determine number of columns necessary for GridLayoutManager
        fun calculateNoOfColumns(context: Context?, columnWidthDp: Float): Int {
            val displayMetrics = context!!.resources.displayMetrics
            val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
            val noOfColumns = (screenWidthDp / columnWidthDp + 0.5).toInt() // +0.5 for correct rounding to int.
            return noOfColumns - 1
        }
    }

}
