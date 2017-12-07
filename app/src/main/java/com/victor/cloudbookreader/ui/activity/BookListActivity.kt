package com.victor.cloudbookreader.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.Recommend
import com.victor.cloudbookreader.repository.BookReposity
import com.victor.cloudbookreader.repository.RepositoryCallBack
import com.victor.cloudbookreader.ui.adapter.BookListAdapter
import com.victor.cloudbookreader.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_book_list.*
import kotlinx.android.synthetic.main.header_simple.view.*
import kotlinx.android.synthetic.main.header_toolbar.view.*

class BookListActivity : BaseActivity() {
    private var bookAdapter: BookListAdapter<Recommend>? = null

    override fun getLayout(): Int {
        return R.layout.activity_book_list
    }

    override fun initView() {
        toolbar.title_text.text = getString(R.string.daily_recommend)
        val layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        list.layoutManager = layoutmanager
        list.setPullRefreshEnabled(true)
        list.setLoadingMoreEnabled(false)
        list.isNestedScrollingEnabled = false
        list.setHasFixedSize(false)
        val headerView = layoutInflater.inflate(R.layout.header_simple, null)
        headerView.text_header.text = getString(R.string.recommend)
        list.addHeaderView(headerView)
        bookAdapter = BookListAdapter(this, Recommend(listOf(), false))
        list.adapter = bookAdapter
        getData()
    }

    override fun initEvent() {
        toolbar.back_image.setOnClickListener { finish() }
    }

    fun getData() {
        val bookRepo = BookReposity.getInstanc()
        bookRepo.getRecommend("male", object : RepositoryCallBack<Recommend> {
            override fun callSuccess(data: Recommend) {
                bookAdapter!!.setData(data)
            }

            override fun callFailure(message: String) {
            }
        })
    }

}
