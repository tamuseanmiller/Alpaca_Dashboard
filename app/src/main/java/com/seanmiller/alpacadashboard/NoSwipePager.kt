package com.seanmiller.alpacadashboard

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class NoSwipePager(context: Context?, attrs: AttributeSet?) : ViewPager(context!!, attrs) {
    private var enabledd: Boolean = true
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (enabledd) {
            super.onTouchEvent(event)
        } else false
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (enabledd) {
            super.onInterceptTouchEvent(event)
        } else false
    }

    fun setPagingEnabled(enabled: Boolean) {
        this.enabledd = enabled
    }
}