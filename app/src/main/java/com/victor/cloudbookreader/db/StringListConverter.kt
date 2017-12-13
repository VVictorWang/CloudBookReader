package com.victor.cloudbookreader.db

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * @author victor
 * @date 12/12/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class StringListConverter {

    @TypeConverter
    fun stringToList(needs: String): List<String> {
        val result = needs.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return Arrays.asList(*result)
    }

    @TypeConverter
    fun listToString(needs: List<String>?): String {
        if (needs == null || needs.isEmpty()) {
            return " "
        }
        val result = StringBuilder()
        for (item in needs) {
            result.append(item)
            result.append(" ")
        }
        return result.toString()
    }
}