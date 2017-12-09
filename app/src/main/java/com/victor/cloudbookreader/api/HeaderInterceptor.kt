package com.victor.cloudbookreader.api

import com.victor.cloudbookreader.ReaderApplication
import com.victor.cloudbookreader.utils.Utils

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * @author victor
 * @date 12/9/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

class HeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val url = original.url().toString()
        if (url.contains("book/") ||
                url.contains("book-list/") ||
                url.contains("toc/") ||
                url.contains("post/") ||
                url.contains("user/")) {
            val request = original.newBuilder()
                    .addHeader("User-Agent", "ZhuiShuShenQi/3.40[preload=false;locale=zh_CN;clientidbase=android-nvidia]") // 不能转UTF-8
                    .addHeader("X-User-Agent", "ZhuiShuShenQi/3.40[preload=false;locale=zh_CN;clientidbase=android-nvidia]")
                    .addHeader("X-Device-Id", Utils.getIMEI(ReaderApplication.readerApplication))
                    .addHeader("Host", "api.zhuishushenqi.com")
                    .addHeader("Connection", "Keep-Alive")
                    .addHeader("If-None-Match", "W/\"2a04-4nguJ+XAaA1yAeFHyxVImg\"")
                    .addHeader("If-Modified-Since", "Tue, 02 Aug 2016 03:20:06 UTC")
                    .build()
            return chain.proceed(request)
        }
        return chain.proceed(original)
    }
}
