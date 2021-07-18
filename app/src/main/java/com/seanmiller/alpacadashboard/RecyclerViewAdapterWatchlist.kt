package com.seanmiller.alpacadashboard

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import net.jacobpeterson.alpaca.AlpacaAPI
import net.jacobpeterson.alpaca.model.endpoint.position.Position
import net.jacobpeterson.alpaca.model.properties.DataAPIType
import net.jacobpeterson.alpaca.model.properties.EndpointAPIType
import net.jacobpeterson.alpaca.rest.AlpacaClientException

//import net.jacobpeterson.alpaca.enums.api.DataAPIType
//import net.jacobpeterson.alpaca.enums.api.EndpointAPIType
//import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException
//import net.jacobpeterson.domain.alpaca.position.Position

class RecyclerViewAdapterWatchlist internal constructor(context: Context?, data: List<String>) : RecyclerView.Adapter<RecyclerViewAdapterWatchlist.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.recyclerview_position, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val mainActivity = MainActivity()
        val stockName = mData[position]
        holder.stock_name.text = stockName
        val prefs = SharedPreferencesManager(mInflater.context)
        val alpacaAPI = AlpacaAPI(null, null, null, prefs.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)
        val thread = Thread {

            // Get Amount of shares owned
            var shrOwned: Position? = null
            try {
                shrOwned = alpacaAPI.positions().getBySymbol(stockName)
            } catch (e: AlpacaClientException) {
                e.printStackTrace()
            }

            // Set shares owned
            val finalShrOwned = shrOwned
            mainActivity.runOnUiThread {
                when (finalShrOwned!!.qty) {
                    "1" -> {
                        holder.sharesOwned.text = String.format("%s share owned", finalShrOwned.qty)
                    }
                    null -> {
                        holder.sharesOwned.text = String.format("%s shares owned", 0)
                    }
                    else -> {
                        holder.sharesOwned.text = String.format("%s shares owned", finalShrOwned.qty)
                    }
                }
            }

//            // Fetch last open day's information
//            var calendar: ArrayList<Calendar>? = null
//            try {
//                calendar = alpacaAPI.getCalendar(LocalDate.now().minusWeeks(1), LocalDate.now())
//            } catch (e: AlpacaAPIRequestException) {
//                e.printStackTrace()
//            }
//            var lastOpenDate = LocalDate.parse(calendar!![calendar.size - 2].date)
//            val oldTime = LocalTime.of(calendar[calendar.size - 2].open.substring(0, 2).toInt(), calendar[calendar.size - 2].open.substring(3, 5).toInt())
//
//            // Switch given open datetime from US/Eastern to System Default
//            val zonedDateTime = ZonedDateTime.of(lastOpenDate, oldTime, ZoneId.of("US/Eastern"))
//            val standardDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault())
//
//            // Check if it is the morning of
//            if (standardDateTime.toLocalTime() > LocalTime.now()) {
//                lastOpenDate = LocalDate.parse(calendar[calendar.size - 3].date)
//            }

            // Get Last value
//            var close = 0f
//            var closeCurr = 0f
//            try {
//
//                // Fetch Daily Open Close endpoint
//                // https://api.polygon.io/v1/open-close/AAPL/2020-10-14?apiKey=
//                var nodeHttpResponse: JSONObject? = null
//                try {
//                    nodeHttpResponse = Unirest.get("https://api.polygon.io/v1/open-close/" + stockName +
//                            "/" + lastOpenDate + "?apiKey=" + prefs.retrieveString("polygon_id", "NULL"))
//                            .asJson().body.getObject()
//                } catch (e: UnirestException) {
//                    e.printStackTrace()
//                }
//                if (nodeHttpResponse != null) {
//                    close = nodeHttpResponse["close"].toString().toFloat()
//                }
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }

//            while (true) {

//            // https://cloud.iexapis.com/stable/stock/AAPL/quote?token=
//            try {
//
//                // Fetch Daily Open Close endpoint
//                var nodeHttpResponse: JSONObject? = null
//                try {
//                    nodeHttpResponse = Unirest.get("https://cloud.iexapis.com/stable/stock/" + stockName +
//                            "/quote?token=" + Properties.iexApiKey).asJson().body.`object`
//                } catch (e: UnirestException) {
//                    e.printStackTrace()
//                }
//                mainActivity.runOnUiThread {
//                    val temp: Double = (nodeHttpResponse!!["changePercent"] as Double).toDouble() * 100
//                    if (temp >= 0) {
//                        holder.percentChange.text = String.format("+%.2f%%", temp)
//                        mClickListener!!.switchColors(holder, true)
//                    } else {
//                        holder.percentChange.text = String.format("%.2f%%", temp)
//                        mClickListener!!.switchColors(holder, false)
//                    }
//                }
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }

            while (true) {
//
//                // Get market status
//                var marketStatus: String? = null
//                try {
//                    marketStatus = polygonAPI.marketStatus.market
//                } catch (e: PolygonAPIRequestException) {
//                    e.printStackTrace()
//                }
//
//                // Check if market is open
//                if (marketStatus != null) {
//                    if (marketStatus == "open") {
//
//                        // Get the last quote
//                        var curr: LastQuoteResponse? = null
//                        try {
//                            curr = polygonAPI.getLastQuote(stockName)
//                        } catch (e: PolygonAPIRequestException) {
//                            e.printStackTrace()
//                        }
//                        try {
//                            close = polygonAPI.getPreviousClose(stockName, false).results[0].c.toFloat()
//                        } catch (e: PolygonAPIRequestException) {
//                            e.printStackTrace()
//                        }
//
//                        // Set values
//                        val finalCurr = curr
//                        val finalClose = close
//                        mainActivity.runOnUiThread {
//                            if (finalCurr != null && finalClose != 0f) {
//                                holder.priceOfStock.text = String.format("$%.2f", finalCurr.last.askprice.toFloat())
//                                val temp = (finalCurr.last.askprice.toFloat() - finalClose) / finalClose * 100
//
//                                // Sets the precision and adds to view, then changes color
//                                if (temp >= 0) {
//                                    holder.percentChange.text = String.format("+%.2f%%", temp)
//                                    mClickListener!!.switchColors(holder, true)
//                                } else {
//                                    holder.percentChange.text = String.format("%.2f%%", temp)
//                                    mClickListener!!.switchColors(holder, false)
//                                }
//                            }
//                        }
//
//                        // Market isn't open
//                    } else {
//
//                        // Fetch Daily Open Close endpoint for prev day
//                        // https://api.polygon.io/v1/open-close/AAPL/2020-10-14?apiKey=
//                        /*JSONObject nodeHttpResponse = null;
//                        try {
//                            nodeHttpResponse = Unirest.get("https://api.polygon.io/v1/open-close/" + mData.get(position) + "/" + calendar.get(calendar.size() - 2).getDate() + "?apiKey=" + prefs.retrieveString("polygon_id", "NULL")).asJson().getBody().getObject();
//
//                        } catch (UnirestException e) {
//                            e.printStackTrace();
//                        }*/
//
//                        // Get last open day's close
//                        //                    if (nodeHttpResponse != null) {
//                        try {
//                            closeCurr = if (calendar[calendar.size - 1].date == LocalDate.now().toString()) {
//                                polygonAPI.getLastQuote(stockName).last.askprice.toFloat()
//                            } else {
//                                val aggs = polygonAPI.getAggregates(stockName, 1, Timespan.DAY, lastOpenDate, lastOpenDate.plusDays(1), false).results
//                                aggs[aggs.size - 1].c.toFloat()
//                            }
//                        } catch (e: PolygonAPIRequestException) {
//                            e.printStackTrace()
//                        }
//                        //                    }
//
//                        // Set values
//                        val finalClose = close
//                        val finalCurr = closeCurr
//                        mainActivity.runOnUiThread {
//                            if (finalCurr != 0f && finalClose != 0f) {
//                                holder.priceOfStock.text = String.format("$%.2f", finalCurr)
//                                val temp = (finalCurr - finalClose) / finalClose * 100
//
//                                // Sets the precision and adds to view, then changes color
//                                if (temp >= 0) {
//                                    holder.percentChange.text = String.format("+%.2f%%", temp)
//                                    mClickListener!!.switchColors(holder, true)
//                                } else {
//                                    holder.percentChange.text = String.format("%.2f%%", temp)
//                                    mClickListener!!.switchColors(holder, false)
//                                }
//                            }
//                        }
//                    }
//                }
//

                // Set values
                try {
                    val snapshot = alpacaAPI.marketData().getSnapshot(stockName)
                    val finalClose = snapshot.prevDailyBar.c.toFloat()
//                  val finalCurr = snapshot.latestQuote.ap.toFloat()
                    val finalCurr = snapshot.dailyBar.c.toFloat()

                    mainActivity.runOnUiThread {
                        if (finalCurr != 0f && finalClose != 0f) {
                            holder.priceOfStock.text = String.format("$%.2f", finalCurr)
                            val temp = (finalCurr - finalClose) / finalClose * 100

                            // Sets the precision and adds to view, then changes color
                            if (temp >= 0) {
                                holder.percentChange.text = String.format("+%.2f%%", temp)
                                mClickListener!!.switchColors(holder, true)
                            } else {
                                holder.percentChange.text = String.format("%.2f%%", temp)
                                mClickListener!!.switchColors(holder, false)
                            }
                        }
                    }
                } catch (e: AlpacaClientException) {
                    e.printStackTrace()
                }

                // Sleep for 1 minute
                try {
                    Thread.sleep(10000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        thread.start()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var stock_name: TextView = itemView.findViewById(R.id.stockName)
        var percentChange: MaterialButton = itemView.findViewById(R.id.currentPrice)
        var sharesOwned: TextView = itemView.findViewById(R.id.sharesOwned)
        var priceOfStock: TextView = itemView.findViewById(R.id.priceOfTicker)
        var stockCard: MaterialCardView = itemView.findViewById(R.id.positionCard)
        override fun onClick(view: View) {
            if (mClickListener != null) {
                mClickListener!!.onItemClickWatch(view, adapterPosition)
            }
        }

        init {
            stockCard.setOnClickListener(this)
            itemView.setOnClickListener(this)
            percentChange.setOnClickListener(this)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): String {
        return mData[id]
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: DashboardFragment) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClickWatch(view: View?, position: Int)
        fun switchColors(view: ViewHolder?, pos: Boolean)
    }

    companion object {
        private lateinit var mData: List<String>
    }

    // data is passed into the constructor
    init {
        mData = data
    }
}