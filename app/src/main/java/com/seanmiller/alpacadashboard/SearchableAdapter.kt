package com.seanmiller.alpacadashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import net.jacobpeterson.domain.polygon.tickers.ticker.Ticker
import org.json.JSONObject
import java.util.*

class SearchableAdapter(private val mValues: ArrayList<Ticker>) : RecyclerView.Adapter<SearchableAdapter.ViewHolder>() {
    private var mClickListener: ItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = mValues[position].name
        val ticker = mValues[position].ticker
        holder.stockTicker.text = ticker
        holder.stockFullName.text = name

//        String fullString = mValues.get(position);
//        holder.stockTicker.text = mValues[position].ticker
//        holder.stockFullName.text = mValues[position].name
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView), View.OnClickListener {
        val stockFullName: TextView = mView.findViewById(R.id.stockFullNameSearch)
        val stockTicker: TextView = mView.findViewById(R.id.stockTickerSearch)
        val searchCard: CardView = mView.findViewById(R.id.searchCard)
        override fun toString(): String {
            return super.toString() + " '" + stockFullName.text
        }

        override fun onClick(v: View) {
            if (mClickListener != null) {
                mClickListener!!.onItemClick(v, adapterPosition)
            }
        }

        init {
            searchCard.setOnClickListener(this)
            mView.setOnClickListener(this)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): Ticker {
        return mValues[id]
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
}