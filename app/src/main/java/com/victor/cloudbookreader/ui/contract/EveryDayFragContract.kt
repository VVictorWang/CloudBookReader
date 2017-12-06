package com.victor.cloudbookreader.ui.contract

import com.victor.cloudbookreader.bean.Recommend
import com.victor.cloudbookreader.ui.base.BasePresenter
import com.victor.cloudbookreader.ui.base.BaseView

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
interface EveryDayFragContract {

    interface View : BaseView<Presenter> {
        fun setEveryDayCount(count: Int)

        fun setImageUrls(imageUrls: MutableList<String>)

    }

    interface Presenter : BasePresenter {

    }
}