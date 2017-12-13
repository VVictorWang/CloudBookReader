package com.victor.cloudbookreader.db

import android.arch.persistence.room.TypeConverter
import com.victor.cloudbookreader.bean.BookDetail

/**
 * @author victor
 * @date 12/12/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class RattingBeanConverter {
    @TypeConverter
    fun RattingToString(rattingBean: BookDetail.RatingBean): String {
        return rattingBean.count.toString() + "," + rattingBean.score.toString() + "," + rattingBean.isIsEffect
    }

    @TypeConverter
    fun StringToRatting(rattingString: String): BookDetail.RatingBean {
        val temp = rattingString.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return BookDetail.RatingBean(temp[0].toInt(), temp[1].toDouble(), temp[2].toBoolean())
    }
}