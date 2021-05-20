package com.seanmiller.alpacadashboard

import android.content.Context
import android.util.AttributeSet
import com.robinhood.spark.SparkView
import java.text.DecimalFormat

class CustomSparkView : SparkView {
    private var contextt: Context? = null

    constructor(contextt: Context?) : super(contextt) {
        this.contextt = contextt
    }

    constructor(contextt: Context?, attrs: AttributeSet?) : super(contextt, attrs)
    constructor(contextt: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(contextt, attrs, defStyleAttr)
    constructor(contextt: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(contextt, attrs, defStyleAttr, defStyleRes)

    override fun onScrubEnded() {
        super.onScrubEnded()
        if (adapter.count > 0) {
            val amount = adapter.getItem(adapter.count - 1).toString().toDouble()
            val formatter = DecimalFormat("#,###.00")
            if (!StockPageActivity.isInFront) DashboardFragment.tickerView!!.text = "$" + formatter.format(amount) else {
                StockPageActivity.tickerViewStock!!.text = "$" + formatter.format(amount)
            }
        }
    }
}