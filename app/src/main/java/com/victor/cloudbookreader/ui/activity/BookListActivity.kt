package com.victor.cloudbookreader.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.Recommend
import com.victor.cloudbookreader.repository.BookReposity
import com.victor.cloudbookreader.repository.RepositoryCallBack
import com.victor.cloudbookreader.ui.adapter.BookListAdapter
import com.victor.cloudbookreader.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_book_list.*

class BookListActivity : BaseActivity() {
    private var bookAdapter: BookListAdapter? = null

    override fun getLayout(): Int {
        return R.layout.activity_book_list
    }

    override fun initView() {
        var layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        list.layoutManager = layoutmanager
        list.setPullRefreshEnabled(true)
        list.setLoadingMoreEnabled(false)
        list.isNestedScrollingEnabled = false
        list.setHasFixedSize(false)
        var headerView = layoutInflater.inflate(R.layout.header_simple, null)
        list.addHeaderView(headerView)
        bookAdapter = BookListAdapter(this, Recommend(listOf(), false))
        list.adapter = bookAdapter
        getData()
//        (XRecyclerView)list
    }

    override fun initEvent() {
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
