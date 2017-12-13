package com.victor.cloudbookreader.utils

import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/**
 * @author victor
 * @date 12/11/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

class RecyclerItemClickListenner(recyclerView: RecyclerView, private val mOnItemClickListener: OnItemClickListener?) : RecyclerView.SimpleOnItemTouchListener() {
    private val mGestureDetectorCompat: GestureDetectorCompat

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    init {
        mGestureDetectorCompat = GestureDetectorCompat(recyclerView.context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                val childView = recyclerView.findChildViewUnder(e.x, e.y)
                if (childView != null && mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(childView, recyclerView
                            .getChildAdapterPosition(childView))
                    return true
                }
                return super.onSingleTapUp(e)
            }

        })
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        return mGestureDetectorCompat.onTouchEvent(e)
    }
}

