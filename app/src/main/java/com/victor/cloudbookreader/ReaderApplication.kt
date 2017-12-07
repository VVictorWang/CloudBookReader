package com.victor.cloudbookreader

import android.app.Application

/**
 * @author victor
 * @date 12/7/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

class ReaderApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        readerApplication = this
    }

    companion object {
        lateinit var readerApplication: ReaderApplication
    }
}
