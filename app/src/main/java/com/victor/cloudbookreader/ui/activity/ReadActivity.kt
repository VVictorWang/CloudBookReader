package com.victor.cloudbookreader.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.BookChapter
import com.victor.cloudbookreader.bean.BookDetail
import com.victor.cloudbookreader.bean.BookDetailTemp
import com.victor.cloudbookreader.bean.ChapterDetail
import com.victor.cloudbookreader.repository.BookReposity
import com.victor.cloudbookreader.repository.RepositoryCallBack
import com.victor.cloudbookreader.ui.base.BaseActivity
import com.victor.cloudbookreader.utils.CacheManager
import com.victor.cloudbookreader.widget.readview.BaseReadView
import com.victor.cloudbookreader.widget.readview.OnReadStateChangeListener
import com.victor.cloudbookreader.widget.readview.PageWidget
import kotlinx.android.synthetic.main.activity_read.*

class ReadActivity : BaseActivity() {
    private var bookId: String? = null

    private var mPageView: BaseReadView? = null
    private var mCharpterList: ArrayList<BookDetailTemp.MixTocBean.ChaptersBean> = arrayListOf()


    override fun getLayout() = R.layout.activity_read

    override fun initView() {
        mPageView = PageWidget(this, bookId!!, mCharpterList, object : OnReadStateChangeListener {
            override fun onPageChanged(chapter: Int, page: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFlip() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChapterChanged(chapter: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onLoadChapterFailure(chapter: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCenterClick() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        flReadWidget.removeAllViews()
        flReadWidget.addView(mPageView)
        getData()
    }

    override fun initEvent() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        bookId = intent?.getStringExtra("bookId")
        super.onCreate(savedInstanceState)
    }

    fun getData() {
        BookReposity.getInstanc().getBookChapter(bookId!!,object: RepositoryCallBack<BookDetailTemp> {
            override fun callSuccess(data: BookDetailTemp) {

                mCharpterList.clear()
                mCharpterList.addAll(data.mixToc.chapters)
                BookReposity.getInstanc().getChapterContent(mCharpterList.get(0).link,object: RepositoryCallBack<ChapterDetail> {
                    override fun callSuccess(data: ChapterDetail) {
                        CacheManager.getInstanc().saveChapterFile(bookId!!, 0, data.chapter)
                        mPageView!!.jumpToChapter(0)
                    }

                    override fun callFailure(message: String) {
                        Toast.makeText(this@ReadActivity,message,Toast.LENGTH_SHORT).show()
                    }
                })
            }

            override fun callFailure(message: String) {
                Toast.makeText(this@ReadActivity,message,Toast.LENGTH_SHORT).show()
            }
        })
    }


}
