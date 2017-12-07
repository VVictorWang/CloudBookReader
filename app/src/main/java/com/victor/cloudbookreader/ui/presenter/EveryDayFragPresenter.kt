package com.victor.cloudbookreader.ui.presenter

import com.victor.cloudbookreader.bean.Constants
import com.victor.cloudbookreader.bean.Recommend
import com.victor.cloudbookreader.repository.BookReposity
import com.victor.cloudbookreader.repository.RepositoryCallBack
import com.victor.cloudbookreader.ui.contract.EveryDayFragContract

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

class EveryDayFragPresenter(val mView: EveryDayFragContract.View) : EveryDayFragContract.Presenter {

    init {
        mView.setPresenter(this)
    }

    override fun start() {
        BookReposity.getInstanc().getRecommend("male", object : RepositoryCallBack<Recommend> {
            override fun callFailure(message: String) {

            }

            override fun callSuccess(data: Recommend) {
                mView.setEveryDayCount(data.books.size)
                val imageUrls: MutableList<String> = mutableListOf()
                for (book: Recommend.RecommendBooksBean in data.books) {
                    imageUrls.add(Constants.IMG_BASE_URL + book.cover)
                }
                mView.setImageUrls(imageUrls)
                mView.setRecommendData(data)
            }
        })
    }

}