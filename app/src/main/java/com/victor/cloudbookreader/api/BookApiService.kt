package com.victor.cloudbookreader.api

import com.victor.cloudbookreader.bean.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
interface BookApiService {

    @GET("book/recommend")
    fun getRecommend(@Query("gender") gender: String): Observable<Response<Recommend>>

    @GET("book/hot-word")
    fun getHotWord(): Observable<Response<HotWord>>

    @GET("book/fuzzy-search")
    fun searchBooks(@Query("query") keyWord: String): Observable<Response<SearchResult>>

    @GET("book/auto-complete")
    fun autoComplete(@Query("query") keyWord: String): Observable<Response<AutoComplete>>

    @GET("book/{bookId}")
    fun getBookDetail(@Path("bookId") bookId: String): Observable<Response<BookDetail>>

    @GET("/mix-atoc/{bookId}")
    fun getBookChapter(@Path("bookId") bookId: String, @Query("view") chapters: String): Observable<Response<BookChapter>>

    @GET("http://chapter2.zhuishushenqi.com/chapter/{url}")
    fun getChapterContent(@Path("url") url: String): Observable<Response<ChapterDetail>>

    @GET("/post/review/best-by-book")
    fun getHotComment(@Query("book") bookId: String): Observable<Response<HotComment>>


    @GET("/book-list/{bookId}/recommend")
    fun getRecommendBookList(@Path("bookId") bookId: String, @Query("limit") limit: String): Observable<Response<RecommendList>>

}