package com.victor.cloudbookreader.utils

import android.support.annotation.MainThread
import retrofit2.Response
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
abstract class NetWorkBoundUtils<ResultType, RequestType>(callBack: CallBack<ResultType>) {

    //    private Observable<ResultType> result = null;

    interface CallBack<ResultType> {
        fun callSuccess(result: ResultType)

        fun callFailure(errorMessage: String)
    }

    init {

        Observable.just(1).subscribeOn(Schedulers.io())
                .subscribe {
                    if (shouldFetch(loadFromDb())) {
                        fetchFromNetwork(callBack)
                    } else {
                        Observable.just(loadFromDb()).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ t -> callBack.callSuccess(t!!) })
                    }

                }
    }


    private fun fetchFromNetwork(callBack: CallBack<ResultType>) {
        val apiResponse = createCall()
        apiResponse.subscribeOn(Schedulers.io())
                .doOnNext { requestTypeApiResponse ->
                    if (requestTypeApiResponse.isSuccessful) {
                        saveCallResult(processResponse(requestTypeApiResponse)!!)
                        Observable.just(loadFromDb()).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ t -> callBack.callSuccess(t!!) })
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Response<RequestType>?> {
                    override fun onError(e: Throwable?) {
                        onFetchFailed()
                    }

                    override fun onNext(requestTypeApiResponse: Response<RequestType>?) {
                        if (!requestTypeApiResponse!!.isSuccessful) {
                            onFetchFailed()
                            try {
                                callBack.callFailure(requestTypeApiResponse.errorBody()!!.string())
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }

                        }
                    }

                    override fun onCompleted() {
                    }
                })

    }


    protected fun onFetchFailed() {}


    protected fun processResponse(response: Response<RequestType>): RequestType? {
        return response.body()
    }

    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDb(): ResultType?

    protected abstract fun createCall(): Observable<Response<RequestType>>
}

