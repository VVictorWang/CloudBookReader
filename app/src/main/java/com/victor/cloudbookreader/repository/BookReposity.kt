package com.victor.cloudbookreader.repository

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.victor.cloudbookreader.api.BookApi
import com.victor.cloudbookreader.api.HeaderInterceptor
import com.victor.cloudbookreader.api.Logger
import com.victor.cloudbookreader.api.LoggingInterceptor
import com.victor.cloudbookreader.bean.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Response
import java.util.concurrent.TimeUnit

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

class BookReposity {
    private val bookApi: BookApi

    init {
        val logging = LoggingInterceptor(Logger())
        logging.setLevel(LoggingInterceptor.Level.BODY)
        val builder = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(HeaderInterceptor())
                .addInterceptor(logging)
                .addNetworkInterceptor(StethoInterceptor())
        bookApi = BookApi.getInstance(builder.build())
    }

    companion object {
        private var instance: BookReposity? = null
        fun getInstanc(): BookReposity {
            if (instance == null) {
                instance = BookReposity()
            }
            return instance as BookReposity
        }
    }

    fun getRecommend(gender: String, callBack: RepositoryCallBack<Recommend>) {
        bookApi.getRecommend(gender).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result: Response<Recommend>? ->
                    if (result!!.isSuccessful) {
                        callBack.callSuccess(result.body()!!)
                    } else {
                        callBack.callFailure(result.errorBody().toString())
                    }
                })
    }

    fun getHotWord(callBack: RepositoryCallBack<HotWord>) {
        bookApi.hotWord.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result: Response<HotWord>? ->
                    if (result!!.isSuccessful) {
                        callBack.callSuccess(result.body()!!)
                    } else {
                        callBack.callFailure(result.errorBody().toString())
                    }
                })
    }

    fun searchBooks(keyWord: String, callBack: RepositoryCallBack<SearchResult>) {
        bookApi.searchBook(keyWord).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result: Response<SearchResult>? ->
                    if (result!!.isSuccessful) {
                        callBack.callSuccess(result.body()!!)
                    } else {
                        callBack.callFailure(result.errorBody().toString())
                    }
                })
    }

    fun autoComplete(keyWord: String, callBack: RepositoryCallBack<AutoComplete>) {
        bookApi.autoComplete(keyWord).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result: Response<AutoComplete>? ->
                    if (result!!.isSuccessful) {
                        callBack.callSuccess(result.body()!!)
                    } else {
                        callBack.callFailure(result.errorBody().toString())
                    }
                })
    }

    fun getBookDetail(bookId: String, callBack: RepositoryCallBack<BookDetail>) {
        bookApi.getBookDeatil(bookId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result: Response<BookDetail>? ->
                    if (result!!.isSuccessful) {
                        callBack.callSuccess(result.body()!!)
                    } else {
                        callBack.callFailure(result.errorBody().toString())
                    }
                })
    }

    fun getHotComment(bookId: String, callBack: RepositoryCallBack<HotComment>) {
        bookApi.getHotCommet(bookId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result: Response<HotComment>? ->
                    if (result!!.isSuccessful) {
                        callBack.callSuccess(result.body()!!)
                    } else {
                        callBack.callFailure(result.errorBody().toString())
                    }
                })
    }

    fun getBookChapter(bookId: String, callBack: RepositoryCallBack<BookChapter>) {
        bookApi.getBookChapter(bookId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result: Response<BookChapter>? ->
                    if (result!!.isSuccessful) {
                        callBack.callSuccess(result.body()!!)
                    } else {
                        callBack.callFailure(result.errorBody().toString())
                    }
                })

    }

    fun getChapterContent(url: String, callBack: RepositoryCallBack<ChapterDetail>) {
        bookApi.getChapterDetail(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result: Response<ChapterDetail>? ->
                    if (result!!.isSuccessful) {
                        callBack.callSuccess(result.body()!!)
                    } else {
                        callBack.callFailure(result.errorBody().toString())
                    }
                })

    }

    fun getBookRecommend(bookId: String, limit:Int,callBack: RepositoryCallBack<RecommendList>) {
        bookApi.getRecommendList(bookId,limit).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result: Response<RecommendList>? ->
                    if (result!!.isSuccessful) {
                        callBack.callSuccess(result.body()!!)
                    } else {
                        callBack.callFailure(result.errorBody().toString())
                    }
                })

    }
}