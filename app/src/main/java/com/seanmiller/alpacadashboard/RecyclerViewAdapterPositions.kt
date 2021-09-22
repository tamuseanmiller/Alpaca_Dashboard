package com.seanmiller.alpacadashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
import java.text.DecimalFormat

class RecyclerViewAdapterPositions internal constructor(
    context: Context?,
    data: List<Position>,
    positionV: PositionView
) : RecyclerView.Adapter<RecyclerViewAdapterPositions.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.recyclerview_position, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Set variables for each of the areas
        val mainActivity = MainActivity()
        val stockName = mData[position].symbol
        holder.stock_name.text = stockName
        val prefs = SharedPreferencesManager(mInflater.context)
        val alpacaAPI = AlpacaAPI(
            null,
            null,
            null,
            prefs!!.retrieveString("auth_token", "NULL"),
            EndpointAPIType.PAPER,
            DataAPIType.IEX
        )
        val alpacaData = AlpacaAPI(
            null,
            Properties.apiKey,
            Properties.secretKey,
            null,
            EndpointAPIType.PAPER,
            DataAPIType.IEX
        )

        holder.priceOfStock.text = String.format("$%.2f", mData[position].currentPrice.toFloat())

        val initialThread = Thread {

            // Initialize variables
            val percentChange = mData[position].changeToday
            val totalPercentChange = mData[position].unrealizedPlpc
            val totalReturn = mData[position].unrealizedPl
            if (percentChange != "-1" || positionView != PositionView.PERCENT_CHANGE) {
                // Update the "percent change" parameter in holder
                mainActivity.runOnUiThread {
                    updateValues(
                        holder,
                        percentChange,
                        totalPercentChange,
                        totalReturn
                    )
                }

            } else {
                // Set values
                try {
                    val snapshot = alpacaData.marketData().getSnapshot(stockName)
                    val finalClose = snapshot.prevDailyBar.c.toFloat()
//                      val finalCurr = snapshot.latestQuote.ap.toFloat()
                    val finalCurr = snapshot.dailyBar.c.toFloat()

                    mainActivity.runOnUiThread {
                        if (finalCurr != 0f && finalClose != 0f) {
                            holder.priceOfStock.text = String.format("$%.2f", finalCurr)
                            val temp = (finalCurr - finalClose) / finalClose

                            updateValues(holder, temp.toString(), totalPercentChange, totalReturn)
                        }
                    }

                } catch (e: AlpacaClientException) {
                    e.printStackTrace()
                }

            }
        }
        initialThread.start()

        val updateThread = Thread {

            while (true) {

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
                    if (finalShrOwned == null) {
                        holder.sharesOwned.text = String.format("%s shares owned", 0)
                        return@runOnUiThread;
                    }
                    when (finalShrOwned.qty) {
                        "1" -> holder.sharesOwned.text =
                            String.format("%s share owned", finalShrOwned.qty)

                        null -> holder.sharesOwned.text = String.format("%s shares owned", 0)

                        else -> holder.sharesOwned.text =
                            String.format("%s shares owned", finalShrOwned.qty)

                    }
                }

                // Sleep for 1 minute
                try {
                    Thread.sleep(60000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                // Set values
                try {
                    val pos: Position = alpacaAPI.positions().getBySymbol(stockName)
                    val percentChange = pos.changeToday
                    val totalPercentChange = pos.unrealizedPlpc
                    val totalReturn = pos.unrealizedPl
                    if (mData[position].changeToday != "-1") { // If market day
                        mainActivity.runOnUiThread {
                            updateValues(
                                holder,
                                percentChange,
                                totalPercentChange,
                                totalReturn
                            )
                        }

                    } else {
                        val snapshot = alpacaData.marketData().getSnapshot(stockName)
                        val finalClose = snapshot.prevDailyBar.c.toFloat()
//                      val finalCurr = snapshot.latestQuote.ap.toFloat()
                        val finalCurr = snapshot.dailyBar.c.toFloat()

                        mainActivity.runOnUiThread {
                            if (finalCurr != 0f && finalClose != 0f) {
                                holder.priceOfStock.text = String.format("$%.2f", finalCurr)
                                val temp = (finalCurr - finalClose) / finalClose

                                updateValues(
                                    holder,
                                    temp.toString(),
                                    totalPercentChange,
                                    totalReturn
                                )
                            }
                        }
                    }

                } catch (e: AlpacaClientException) {
                    e.printStackTrace()
                }

            }
        }
        updateThread.start()
    }

    private fun updateValues(
        holder: ViewHolder,
        percentChange: String,
        totalPercentChange: String,
        totalReturn: String
    ) {

        // Sets value based on what positionView is
        if (positionView == PositionView.PERCENT_CHANGE) {
            // Sets the precision and adds to view, then changes color
            if (percentChange.toFloat() >= 0) {
                holder.percentChange.text = String.format("+%.2f%%", percentChange.toFloat() * 100)
                mClickListener!!.switchColors(holder, true)
            } else {
                holder.percentChange.text = String.format("%.2f%%", percentChange.toFloat() * 100)
                mClickListener!!.switchColors(holder, false)
            }

        } else if (positionView == PositionView.TOTAL_PERCENT_CHANGE) {
            // Sets the precision and adds to view, then changes color
            if (totalPercentChange.toFloat() >= 0) {
                holder.percentChange.text =
                    String.format("+%.2f%%", totalPercentChange.toFloat() * 100)
                mClickListener!!.switchColors(holder, true)
            } else {
                holder.percentChange.text =
                    String.format("%.2f%%", totalPercentChange.toFloat() * 100)
                mClickListener!!.switchColors(holder, false)
            }

        } else if (positionView == PositionView.TOTAL_RETURN) {
            // Sets the precision and adds to view, then changes color
            val formatter = DecimalFormat("#,###")
            val amount =
                "$" + formatter.format(kotlin.math.abs(totalReturn.toFloat() * 100)).toString()
            if (totalReturn.toFloat() >= 0) {
                holder.percentChange.text = String.format("+%s", amount)
                mClickListener!!.switchColors(holder, true)
            } else {
                holder.percentChange.text = String.format("-%s", amount)
                mClickListener!!.switchColors(holder, false)
            }

        }
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
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var stock_name: TextView = itemView.findViewById(R.id.stockName)
        var percentChange: MaterialButton = itemView.findViewById(R.id.currentPrice)
        var sharesOwned: TextView = itemView.findViewById(R.id.sharesOwned)
        var priceOfStock: TextView = itemView.findViewById(R.id.priceOfTicker)
        var stockCard: MaterialCardView = itemView.findViewById(R.id.positionCard)
        override fun onClick(view: View) {
            if (mClickListener != null) {
                mClickListener!!.onItemClick(view, adapterPosition)
            }
        }

        init {
            stockCard.setOnClickListener(this)
            itemView.setOnClickListener(this)
            percentChange.setOnClickListener(this)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): Position {
        return mData[id]
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    fun notifyItemChanged(positionV: PositionView?) {
        positionView = positionV!!
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
        fun switchColors(view: ViewHolder?, pos: Boolean)
    }

    companion object {
        private lateinit var mData: List<Position>
        private lateinit var positionView: PositionView
    }

    // data is passed into the constructor
    init {
        mData = data
        positionView = positionV
    }
}