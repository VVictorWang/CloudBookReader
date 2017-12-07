package com.victor.cloudbookreader.widget

import android.graphics.Color
import com.victor.cloudbookreader.bean.Constants
import java.util.*

/**
 * @author victor
 * @date 12/6/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

class TagColor {

    var borderColor = Color.parseColor("#49C120")
    var backgroundColor = Color.parseColor("#49C120")
    var textColor = Color.WHITE

    companion object {

        fun getRandomColors(size: Int): List<TagColor> {

            val list = ArrayList<TagColor>()
            for (i in 0 until size) {
                val color = TagColor()
                color.backgroundColor = Constants.tagColors[i % Constants
                        .tagColors.size]
                color.borderColor = color.backgroundColor
                list.add(color)
            }
            return list
        }
    }
}
