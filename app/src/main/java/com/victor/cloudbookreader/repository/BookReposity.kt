package com.victor.cloudbookreader.repository

import com.victor.cloudbookreader.api.BookApi
import com.victor.cloudbookreader.bean.Recommend
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Response

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

class BookReposity {
    private val bookApi: BookApi

    init {
        bookApi = BookApi.getInstance(OkHttpClient())
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

    public fun getRecommend(gender: String, callBack: RepositoryCallBack<Recommend>) {
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

}