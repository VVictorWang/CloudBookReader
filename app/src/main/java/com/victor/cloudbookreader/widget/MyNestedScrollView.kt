package com.victor.cloudbookreader.widget

import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet

/**
 * @author victor
 * @date 12/7/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

class MyNestedScrollView : NestedScrollView {

    private var scrollInterface: ScrollInterface? = null

    /**
     * 定义滑动接口
     */
    interface ScrollInterface {
        fun onScrollChange(scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int)
    }

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        if (scrollInterface != null) {
            scrollInterface!!.onScrollChange(l, t, oldl, oldt)
        }
        super.onScrollChanged(l, t, oldl, oldt)
    }

    fun setScrollChangeListener(t: ScrollInterface) {
        this.scrollInterface = t
    }
}

