package com.victor.cloudbookreader.api

import com.victor.cloudbookreader.bean.Recommend
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
interface BookApiService {

    @GET("book/recommend")
    fun getRecommend(@Query("gender") gender: String): Flowable<Response<Recommend>>

}