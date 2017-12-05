package com.victor.cloudbookreader.ui.fragment

import android.os.Bundle
import android.util.Log
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.ui.base.BaseFragment
import com.victor.cloudbookreader.ui.contract.EveryDayFragContract
import com.victor.cloudbookreader.ui.presenter.EveryDayFragPresenter
import com.victor.cloudbookreader.widget.GlideImageLoader
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_everyday.*

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class EveryDayFragment : BaseFragment(), EveryDayFragContract.View {
    override fun setImageUrls(imageUrls: MutableList<String>) {
        for (url: String in imageUrls) {
            Log.d("@vic", url)
        }
        image_banner.setImages(imageUrls)
        image_banner.start()
    }

    override fun setEveryDayCount(count: Int) {
        tv_daily_text.text = count.toString()
    }

    private lateinit var mPresenter: EveryDayFragContract.Presenter
    override fun setPresenter(presenter: EveryDayFragContract.Presenter) {
        mPresenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = EveryDayFragPresenter(this)
        mPresenter.start()
    }

    override fun getLayout() = R.layout.fragment_everyday

    override fun initView() {
        image_banner.setImageLoader(GlideImageLoader())
//        image_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)

    }

}