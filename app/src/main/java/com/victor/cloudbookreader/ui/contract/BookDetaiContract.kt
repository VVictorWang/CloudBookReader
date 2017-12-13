package com.victor.cloudbookreader.ui.contract

import com.victor.cloudbookreader.bean.BookDetail
import com.victor.cloudbookreader.bean.HotComment
import com.victor.cloudbookreader.bean.RecommendList
import com.victor.cloudbookreader.ui.base.BasePresenter
import com.victor.cloudbookreader.ui.base.BaseView

/**
 * @author victor
 * @date 12/13/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
interface BookDetaiContract {
    interface View : BaseView<Presenter> {
        fun getBookId(): String
        fun showBookDetail(result: BookDetail)
        fun showCommentData(result: HotComment)
        fun showRecommendList(result: RecommendList)
    }

    interface Presenter : BasePresenter {
        fun addBook(bookId: String)
    }
}