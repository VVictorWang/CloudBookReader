package com.victor.cloudbookreader.repository

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

interface RepositoryCallBack<T> {
    fun callSuccess(data: T)
    fun callFailure(message: String)
}