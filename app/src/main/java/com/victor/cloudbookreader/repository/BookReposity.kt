package com.victor.cloudbookreader.repository

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.victor.cloudbookreader.ReaderApplication
import com.victor.cloudbookreader.api.BookApi
import com.victor.cloudbookreader.api.HeaderInterceptor
import com.victor.cloudbookreader.api.Logger
import com.victor.cloudbookreader.api.LoggingInterceptor
import com.victor.cloudbookreader.bean.*
import com.victor.cloudbookreader.db.BookDao
import com.victor.cloudbookreader.db.CloudBookReaderDb
import com.victor.cloudbookreader.utils.NetWorkBoundUtils
import okhttp3.OkHttpClient
import retrofit2.Response
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

class BookReposity {
    private val bookApi: BookApi
    private val bookDao: BookDao

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
        bookDao = CloudBookReaderDb.getInstance(ReaderApplication.readerApplication).bookDao()
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
        rxSchedulerHelper(bookApi.getRecommend(gender), callBack)
    }

    fun getHotWord(callBack: RepositoryCallBack<HotWord>) {
        rxSchedulerHelper(bookApi.hotWord, callBack)
    }

    fun searchBooks(keyWord: String, callBack: RepositoryCallBack<SearchResult>) {
        rxSchedulerHelper(bookApi.searchBook(keyWord), callBack)
    }

    fun autoComplete(keyWord: String, callBack: RepositoryCallBack<AutoComplete>) {
        rxSchedulerHelper(bookApi.autoComplete(keyWord), callBack)
    }

    fun getBookDetail(bookId: String, callBack: NetWorkBoundUtils.CallBack<BookDetail>) {
        object : NetWorkBoundUtils<BookDetail, BookDetail>(callBack) {
            override fun saveCallResult(item: BookDetail) {
                bookDao.insert(item)
            }

            override fun shouldFetch(data: BookDetail?) = data == null

            override fun loadFromDb(): BookDetail? {
                return bookDao.getBookByid(bookId)
            }

            override fun createCall(): Observable<Response<BookDetail>> {
                return bookApi.getBookDeatil(bookId)
            }
        }
    }

    fun getHotComment(bookId: String, callBack: RepositoryCallBack<HotComment>) {
        rxSchedulerHelper(bookApi.getHotCommet(bookId), callBack)
    }

    fun getBookChapter(bookId: String, callBack: RepositoryCallBack<BookChapter>) {
        rxSchedulerHelper(bookApi.getBookChapter(bookId), callBack)

    }

    fun getChapterContent(url: String, callBack: RepositoryCallBack<ChapterDetail>) {
        rxSchedulerHelper(bookApi.getChapterDetail(url), callBack)
    }

    fun getBookRecommend(bookId: String, limit: Int, callBack: RepositoryCallBack<RecommendList>) {
        rxSchedulerHelper(bookApi.getRecommendList(bookId, limit), callBack)
    }

    fun <T> rxSchedulerHelper(value: Observable<Response<T>>, callBack: RepositoryCallBack<T>) {    //compose简化线程
        value.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(RxObserverCallBack<T>(callBack))
    }
}