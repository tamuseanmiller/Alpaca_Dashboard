package com.seanmiller.alpacadashboard

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.robinhood.spark.SparkAdapter
//import net.jacobpeterson.alpaca.enums.portfolio.PortfolioPeriodUnit
//import net.jacobpeterson.alpaca.enums.portfolio.PortfolioTimeFrame
import java.util.*
import java.util.concurrent.atomic.AtomicReference
import kotlin.collections.ArrayList

class StockAdapter @RequiresApi(api = Build.VERSION_CODES.O) constructor(context: Context?) : SparkAdapter() {
    private val yData: Vector<Float> = Vector()
    private val random: Random = Random()
    private var baseline: Float
    var percent = 0f
    var profit = 0f
    private val prefs: SharedPreferencesManager
    fun pushFront(`val`: Float) {
        yData.add(0, `val`)
    }

    fun setBaseline(baseline: Float) {
        this.baseline = baseline
    }

    fun smoothGraph() {
        val temp = Vector(yData)
        yData.clear()
        var i = 0
        while (i < temp.size - 3) {
            yData.add((temp[i] + temp[i + 1] + temp[i + 2] + temp[i + 3]) / 4)
            i += 4
        }
        notifyDataSetChanged()
    }

    // Solely for testing purposes
    fun clearData() {
        yData.clear()
        notifyDataSetChanged()
    }

    fun randomize() {
        yData.add(random.nextFloat())
        notifyDataSetChanged()
    }

    fun addVal(`val`: Float) {
        yData.add(`val`)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return yData.size
    }

    override fun getItem(index: Int): Any {
        return if (yData.size <= index) 0 else yData[index]
    }

    fun getValue(index: Int): Float {
        return if (count > 0)
            yData[index]
        else 0F

    }

    //        @Override
    //        public float getX(int index) {
    //            return index;
    //        }
    //        @Override
    //        public RectF getDataBounds() {
    //            final int count = getCount();
    //            final boolean hasBaseLine = hasBaseLine();
    //
    //            float minY = hasBaseLine ? getBaseLine() : Float.MAX_VALUE;
    //            float maxY = hasBaseLine ? minY : -Float.MAX_VALUE;
    //            float minX = Float.MAX_VALUE;
    //            float maxX = -Float.MAX_VALUE;
    //            for (int i = 0; i < count; i++) {
    //                final float x = getX(i);
    //                minX = Math.min(minX, x);
    //                maxX = Math.max(maxX, x + 10);
    //
    //                final float y = getY(i);
    //                minY = Math.min(minY, y);
    //                maxY = Math.max(maxY, y);
    //            }
    //
    //            maxX = 30;
    //
    //            // set values on the return object
    //            return createRectF(minX, minY, maxX, maxY);
    //        }
    override fun getY(index: Int): Float {
        return yData[index]
    }

    override fun hasBaseLine(): Boolean {
        return true
    }

    override fun getBaseLine(): Float {
        return yData[0]
    }

    companion object {
        // finds last non-null value in arraylist
        fun getLastFilledIndex(p: ArrayList<Double>): Double? {
            for (i in p.indices.reversed()) {
                return p[i]
            }
            return 0.0
        }
    }

    init {
        baseline = 0f
        prefs = SharedPreferencesManager(context!!)
    }
}