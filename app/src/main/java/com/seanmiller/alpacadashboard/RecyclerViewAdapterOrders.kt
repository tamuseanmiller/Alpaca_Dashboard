package com.seanmiller.alpacadashboard

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import net.jacobpeterson.alpaca.model.endpoint.order.Order
//import net.jacobpeterson.domain.alpaca.order.Order
import java.util.*
import java.util.concurrent.atomic.AtomicReference

class RecyclerViewAdapterOrders internal constructor(context: Context?, data: List<Order>) : RecyclerView.Adapter<RecyclerViewAdapterOrders.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.recyclerview_order, parent, false)
        return ViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.stockName.text = mData[position].symbol
        holder.setIsRecyclable(false)
        //        holder.shrQty.setText(mData.get(position).getQty());
//        holder.timeClosed.setText(mData.get(position).getFilledAt().getMonth().getValue() + "/" + mData.get(position).getFilledAt().getDayOfMonth() + "/" + mData.get(position).getFilledAt().getYear() /*+ "\n" + mData.get(position).getFilledAt().getHour() + ":" + mData.get(position).getFilledAt().getMinute()*/);
        val mainActivity = MainActivity()
        val thread = Thread{

            // Fetches 12hour hour:minute format including am/pm
            // Works by switching timezone to default then parsing
            if (mData[position].filledAt != null) {
                val zonedDateTime = mData[position].filledAt
                val zDT = zonedDateTime.withZoneSameInstant(TimeZone.getDefault().toZoneId())
                val hour = AtomicReference("")
                var hourTemp = zDT.toLocalTime().hour
                var minute = zDT.toLocalTime().minute.toString()
                if (minute.toInt() < 10) {
                    minute = "0$minute"
                }
                minute += "am"
                val minSub = minute.substring(0, minute.length - 2)
                if (hourTemp > 12) {
                    hourTemp -= 12
                    minute = minSub
                    minute += "pm"
                } else if (hourTemp == 12) {
                    minute = minSub
                    minute += "pm"
                }
                val finalHourTemp = hourTemp
                val finalMinute = minute
                mainActivity.runOnUiThread {
                    hour.set(finalHourTemp.toString())
                    holder.timeClosed.text = "$hour:$finalMinute"
                    holder.price.text = "$" + mData[position].filledAvgPrice
                    holder.pricePlaced.text = mData[position].side.toString() + " " + mData[position].qty
                }
            } else {
                val zonedDateTime = mData[position].canceledAt
                val zDT = zonedDateTime.withZoneSameInstant(TimeZone.getDefault().toZoneId())
                var hourTemp = zDT.toLocalTime().hour
                val hour = AtomicReference("")
                var minute = zDT.toLocalTime().minute.toString()
                if (minute.toInt() < 10) {
                    minute = "0$minute"
                }
                minute += "am"
                val minSub = minute.substring(0, minute.length - 2)
                if (hourTemp > 12) {
                    hourTemp -= 12
                    minute = minSub
                    minute += "pm"
                } else if (hourTemp == 12) {
                    minute = minSub
                    minute += "pm"
                }
                val finalHourTemp = hourTemp
                val finalMinute = minute
                mainActivity.runOnUiThread {
                    hour.set(finalHourTemp.toString())
                    holder.timeClosed.text = "$hour:$finalMinute"
                    holder.price.text = "Cancelled"
                    holder.pricePlaced.text = mData[position].side.toString() + " " + mData[position].qty
                }
            }
        }
        thread.start()
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        //        TextView shrQty;
        var pricePlaced: Button
        var stockName: TextView
        var price: TextView
        var timeClosed: TextView
        override fun onClick(view: View) {
            if (mClickListener != null) {
                mClickListener!!.onItemClick(view, adapterPosition)
            }
        }

        init {
            itemView.setOnClickListener(this)
            pricePlaced = itemView.findViewById(R.id.pricePlaced)
            pricePlaced.tag = this
            //            shrQty = itemView.findViewById(R.id.shrQty);
            stockName = itemView.findViewById(R.id.stockNameOrder)
            stockName.tag = this
            price = itemView.findViewById(R.id.price)
            price.tag = this
            timeClosed = itemView.findViewById(R.id.timeClosed)
            timeClosed.tag = this
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): Order {
        return mData[id]
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    companion object {
        private lateinit var mData: List<Order>
    }

    // data is passed into the constructor
    init {
        mData = data
    }
}