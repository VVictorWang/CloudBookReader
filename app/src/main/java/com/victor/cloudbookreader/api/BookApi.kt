package com.victor.cloudbookreader.api

import com.victor.cloudbookreader.bean.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class BookApi(client: OkHttpClient) {

    private val service: BookApiService

    companion object {
        var instance: BookApi? = null
        fun getInstance(client: OkHttpClient): BookApi {
            if (instance == null) {
                instance = BookApi(client)
            }
            return instance as BookApi
        }
    }

    init {
        val retrofit = Retrofit.Builder().baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        service = retrofit.create(BookApiService::class.java)
    }

    fun getRecommend(gender: String) = service.getRecommend(gender)
}