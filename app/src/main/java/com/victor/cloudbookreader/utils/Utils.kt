package com.victor.cloudbookreader.utils

import android.content.res.Resources

import com.victor.cloudbookreader.ReaderApplication

/**
 * @author victor
 * @date 12/7/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

object Utils {

    val resoure: Resources
        get() = ReaderApplication.readerApplication.resources

    fun getDimens(resId: Int): Float {
        return resoure.getDimension(resId)
    }


}
