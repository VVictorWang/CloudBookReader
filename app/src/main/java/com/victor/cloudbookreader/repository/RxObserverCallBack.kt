package com.victor.cloudbookreader.repository

import retrofit2.Response
import rx.Observer

/**
 * @author victor
 * @date 12/13/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class RxObserverCallBack<T>(var callBack: RepositoryCallBack<T>) : Observer<Response<T>?> {
    override fun onError(e: Throwable?) {
        callBack.callFailure(e!!.message!!)
    }

    override fun onCompleted() {

    }

    override fun onNext(result: Response<T>?) {
        if (result!!.isSuccessful) {
            callBack.callSuccess(result.body()!!)
        } else {
            callBack.callFailure(result.errorBody().toString())
        }
    }

}