package com.victor.cloudbookreader.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.BookDetail
import com.victor.cloudbookreader.bean.Constants
import com.victor.cloudbookreader.bean.HotComment
import com.victor.cloudbookreader.bean.RecommendList
import com.victor.cloudbookreader.ui.adapter.CommetAdapter
import com.victor.cloudbookreader.ui.adapter.RecommendListAdapter
import com.victor.cloudbookreader.ui.base.BaseHeaderActivity
import com.victor.cloudbookreader.ui.contract.BookDetaiContract
import com.victor.cloudbookreader.ui.presenter.BookDetailPresenter
import com.victor.cloudbookreader.widget.MyLinearLayoutManger
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.activity_header_base.*
import kotlinx.android.synthetic.main.header_book_detail.*

class BookDetailActivity : BaseHeaderActivity(), BookDetaiContract.View {
    private lateinit var mPresenter: BookDetaiContract.Presenter

    override fun setPresenter(presenter: BookDetaiContract.Presenter) {
        mPresenter = presenter
    }

    private var commentAdapter: CommetAdapter? = null
    private var recommendAdapter: RecommendListAdapter? = null

    override fun setHeaderImageView() = img_item_bg

    override fun setHeaderLayout() = R.layout.header_book_detail

    override fun setHeaderPicView() = book_cover


    fun initView() {
        setMotion(setHeaderImageView(), true)
        commentAdapter = CommetAdapter(this)
        comment.layoutManager = MyLinearLayoutManger(this)
        comment.adapter = commentAdapter
        recommendAdapter = RecommendListAdapter(null)
        recommend_books.layoutManager = MyLinearLayoutManger(this)
        recommend_books.adapter = recommendAdapter
        mPresenter.start()
        playlist_collect_view.setOnClickListener {
            val intent = Intent(this, ReadActivity::class.java)
            intent.putExtra("bookId", bookId!!)
            startActivity(intent)
        }
        initEvent()
    }

    private fun initEvent() {
        with(footerView) {
            add_book.setOnClickListener {
                mPresenter.addBook(bookId!!)
            }
        }
    }


    private var bookId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        bookId = intent?.getStringExtra("id")
        mPresenter = BookDetailPresenter(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        initView()
    }

    override fun getBookId(): String {
        return bookId!!
    }

    override fun showBookDetail(result: BookDetail) {
        Glide.with(this@BookDetailActivity).load(Constants.IMG_BASE_URL + result.cover)
                .error(R.drawable.stackblur_default)
                .bitmapTransform(BlurTransformation(this@BookDetailActivity, 23, 4))
                .into(setHeaderImageView())
        initSlideShapeTheme(Constants.IMG_BASE_URL + result.cover, setHeaderImageView())
        with(bindingHeaderView) {
            Glide.with(this@BookDetailActivity).load(Constants.IMG_BASE_URL + result.cover)
                    .into(book_cover)
        }
        setTitle(result.title)
        setSubTitle(result.author)
        remainning_rate.text = result.retentionRatio + "%"
        total_follower.text = result.latelyFollower.toString()
        book_author.text = result.author
        brief_intro.text = result.longIntro
        category.text = result.cat
        charater_count.text = result.wordCount.toString() + "字"
        tag.setTags(result.tags)
        long_info.text = result.longIntro
        showContentView()
        Log.d("@vic", Constants.IMG_BASE_URL + result.cover)
    }

    override fun showCommentData(result: HotComment) {
        commentAdapter!!.setData(result)
    }


    override fun showRecommendList(result: RecommendList) {
        recommendAdapter!!.setData(result)
    }

}
