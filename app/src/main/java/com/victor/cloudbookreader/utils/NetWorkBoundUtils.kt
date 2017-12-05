package com.victor.cloudbookreader.utils

import android.support.annotation.MainThread
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.io.IOException

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
abstract class NetWorkBoundUtils<ResultType, RequestType>(callBack: CallBack<ResultType>) {

    //    private Observable<ResultType> result = null;

    interface CallBack<ResultType> {
        fun callSuccess(result: Flowable<ResultType>)

        fun callFailure(errorMessage: String)
    }

    init {

        Flowable.just(1).subscribeOn(Schedulers.io())
                .subscribe {
                    loadFromDb().subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                            .subscribe { resultType ->
                                if (shouldFetch(resultType)) {
                                    fetchFromNetwork(callBack)
                                } else {
                                    callBack.callSuccess(loadFromDb())
                                }
                            }
                }
    }


    private fun fetchFromNetwork(callBack: CallBack<ResultType>) {
        val apiResponse = createCall()
        apiResponse.subscribeOn(Schedulers.io())
                .doOnNext { requestTypeApiResponse ->
                    if (requestTypeApiResponse.isSuccessful) {
                        saveCallResult(processResponse(requestTypeApiResponse)!!)
                        callBack.callSuccess(loadFromDb())
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { requestTypeApiResponse ->
                    if (!requestTypeApiResponse.isSuccessful) {
                        onFetchFailed()
                        try {
                            callBack.callFailure(requestTypeApiResponse.errorBody()!!.string())
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                    }
                }
    }


    protected fun onFetchFailed() {}


    protected fun processResponse(response: Response<RequestType>): RequestType? {
        return response.body()
    }

    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDb(): Flowable<ResultType>

    protected abstract fun createCall(): Flowable<Response<RequestType>>
}

