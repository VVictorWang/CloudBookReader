package com.victor.cloudbookreader.ui.fragment

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.Constants
import com.victor.cloudbookreader.bean.Recommend
import com.victor.cloudbookreader.ui.activity.BookListActivity
import com.victor.cloudbookreader.ui.base.BaseFragment
import com.victor.cloudbookreader.ui.contract.EveryDayFragContract
import com.victor.cloudbookreader.ui.presenter.EveryDayFragPresenter
import com.victor.cloudbookreader.widget.GlideImageLoader
import kotlinx.android.synthetic.main.fragment_everyday.*

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class EveryDayFragment : BaseFragment(), EveryDayFragContract.View {
    override fun setRecommendData(recommend: Recommend) {
        var count = recommend.books.size
        if (count >= 3) {
            Glide.with(this).load(Constants.IMG_BASE_URL + recommend.books[0].cover).into(book_cover1)
            Glide.with(this).load(Constants.IMG_BASE_URL + recommend.books[1].cover).into(book_cover2)
            Glide.with(this).load(Constants.IMG_BASE_URL + recommend.books[2].cover).into(book_cover3)
            book_desc1.text = recommend.books[0].shortIntro
            book_desc2.text = recommend.books[1].shortIntro
            book_desc3.text = recommend.books[2].shortIntro
        }
    }

    override fun setImageUrls(imageUrls: MutableList<String>) {
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
        daily_btn.setOnClickListener {
            val intent = Intent(activity, BookListActivity::class.java)
            activity!!.startActivity(intent)
        }


    }

}