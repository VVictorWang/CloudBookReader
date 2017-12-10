package com.victor.cloudbookreader.api

import android.util.Log

/**
 * @author victor
 * @date 12/9/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

class Logger : LoggingInterceptor.Logger {

    override fun log(message: String) {
        Log.i("@victor", " http :" + message)
    }
}
