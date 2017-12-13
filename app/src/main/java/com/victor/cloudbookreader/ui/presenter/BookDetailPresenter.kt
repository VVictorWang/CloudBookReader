package com.victor.cloudbookreader.ui.presenter

import com.victor.cloudbookreader.bean.BookDetail
import com.victor.cloudbookreader.bean.HotComment
import com.victor.cloudbookreader.bean.RecommendList
import com.victor.cloudbookreader.repository.BookReposity
import com.victor.cloudbookreader.repository.RepositoryCallBack
import com.victor.cloudbookreader.repository.UserRepository
import com.victor.cloudbookreader.ui.contract.BookDetaiContract
import com.victor.cloudbookreader.utils.NetWorkBoundUtils

/**
 * @author victor
 * @date 12/13/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

class BookDetailPresenter(val mView: BookDetaiContract.View) : BookDetaiContract.Presenter {

    override fun addBook(bookId: String) {
        UserRepository.getInstanc().changeBooks(arrayListOf(bookId))
    }

    private val bookRepo = BookReposity.getInstanc()

    init {
        mView.setPresenter(this)
    }

    override fun start() {
        bookRepo.getBookDetail(mView.getBookId(), object : NetWorkBoundUtils.CallBack<BookDetail> {
            override fun callSuccess(result: BookDetail) {
                mView.showBookDetail(result)
            }

            override fun callFailure(errorMessage: String) {
            }
        })
        bookRepo.getBookRecommend(mView.getBookId(), 5, object : RepositoryCallBack<RecommendList> {
            override fun callSuccess(data: RecommendList) {
                mView.showRecommendList(data)
            }

            override fun callFailure(message: String) {
            }
        })
        bookRepo.getHotComment(mView.getBookId(), object : RepositoryCallBack<HotComment> {
            override fun callFailure(message: String) {

            }

            override fun callSuccess(data: HotComment) {
                mView.showCommentData(data)
            }
        })
    }

}