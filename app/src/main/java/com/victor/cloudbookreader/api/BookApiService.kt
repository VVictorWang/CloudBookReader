package com.victor.cloudbookreader.api

import com.victor.cloudbookreader.bean.AutoComplete
import com.victor.cloudbookreader.bean.HotWord
import com.victor.cloudbookreader.bean.Recommend
import com.victor.cloudbookreader.bean.SearchResult
import io.reactivex.Flowable
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

    @GET("book/hot-word")
    fun getHotWord(): Flowable<Response<HotWord>>

    @GET("book/fuzzy-search")
    fun searchBooks(@Query("query") keyWord: String): Flowable<Response<SearchResult>>

    @GET("book/auto-complete")
    fun autoComplete(@Query("query") keyWord: String): Flowable<Response<AutoComplete>>


}