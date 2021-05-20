package com.seanmiller.alpacadashboard

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapterStocks internal constructor(context: Context?, data: List<String>) : RecyclerView.Adapter<RecyclerViewAdapterStocks.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null
    var props = Properties
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.recyclerview_position, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stockName = mData[position]
        holder.myTextView.text = stockName

//        PolygonAPI polygonAPI = new PolygonAPI();
//        AlpacaAPI alpacaAPI = new AlpacaAPI();
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var myTextView: TextView
        var percentChange: TextView
        override fun onClick(view: View) {
            if (mClickListener != null) {
                mClickListener!!.onItemClick(view, adapterPosition)
            }
        }

        init {
            myTextView = itemView.findViewById(R.id.stockName)
            percentChange = itemView.findViewById(R.id.currentPrice)
            itemView.setOnClickListener(this)
            myTextView.setOnClickListener(this)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): String {
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
        private lateinit var mData: List<String>
    }

    // data is passed into the constructor
    init {
        mData = data
    }
}