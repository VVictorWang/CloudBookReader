package com.victor.cloudbookreader.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet

/**
 * @author victor
 * @date 12/13/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

class MyLinearLayoutManger : LinearLayoutManager {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {}


    override fun canScrollVertically(): Boolean {
        return false
    }
}
