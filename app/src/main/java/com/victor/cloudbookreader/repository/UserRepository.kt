package com.victor.cloudbookreader.repository

import com.victor.cloudbookreader.ReaderApplication
import com.victor.cloudbookreader.bean.BookDetail
import com.victor.cloudbookreader.bean.Constants
import com.victor.cloudbookreader.bean.UserInfo
import com.victor.cloudbookreader.db.CloudBookReaderDb
import com.victor.cloudbookreader.db.UserDao
import com.victor.cloudbookreader.utils.NetWorkBoundUtils
import com.victor.cloudbookreader.utils.PrefUtils
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers

/**
 * @author victor
 * @date 12/13/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class UserRepository {

    private var userDao: UserDao

    init {
        userDao = CloudBookReaderDb.getInstance(ReaderApplication.readerApplication)
                .userDao()
    }

    companion object {
        private var instance: UserRepository? = null
        fun getInstanc(): UserRepository {
            if (instance == null) {
                instance = UserRepository()
            }
            return instance as UserRepository
        }
    }

    fun getBooks(id: String, callBack: RepositoryCallBack<List<BookDetail>>) {
        Observable.just(1).subscribeOn(Schedulers.io())
                .doOnNext({ integer ->
                    val user = userDao.getUserById(id)
                    if (user != null) {
                        val books = arrayListOf<BookDetail>()
                        Observable.from(user.books).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(object : Observer<String?> {
                                    override fun onNext(t: String?) {
                                        BookReposity.getInstanc().getBookDetail(t!!, object : NetWorkBoundUtils.CallBack<BookDetail> {
                                            override fun callSuccess(result: BookDetail) {
                                                books.add(result)
                                            }

                                            override fun callFailure(errorMessage: String) {
                                                callBack.callFailure(errorMessage)
                                            }
                                        })
                                    }

                                    override fun onCompleted() {
                                        callBack.callSuccess(books)
                                    }

                                    override fun onError(e: Throwable?) {
                                        callBack.callFailure(e!!.message!!)
                                    }
                                })
                    }
                })
                .subscribe()

    }

    fun addUser(id: String, books: ArrayList<String> = arrayListOf<String>()) {
        Observable.just(1).subscribeOn(Schedulers.io())
                .subscribe({ t -> userDao.insert(UserInfo(id, books)) })
    }

    fun getAllBookId(id: String): List<String> {
        return userDao.getUserById(id)!!.books
    }

    fun changeBooks(books: ArrayList<String>) {
        Observable.just(1).subscribeOn(Schedulers.io())
                .doOnNext(Action1 {
                    books.addAll(getAllBookId(PrefUtils.getValue(Constants.USER_ID)!!))
                    userDao.insert(UserInfo(PrefUtils.getValue(Constants.USER_ID)!!, books))
                }).subscribe()

    }
}
