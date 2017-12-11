package com.victor.cloudbookreader.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.BookChapter
import com.victor.cloudbookreader.bean.ChapterDetail
import com.victor.cloudbookreader.repository.BookReposity
import com.victor.cloudbookreader.repository.RepositoryCallBack
import com.victor.cloudbookreader.ui.adapter.ChapterListAdapter
import com.victor.cloudbookreader.ui.base.BaseActivity
import com.victor.cloudbookreader.utils.CacheManager
import com.victor.cloudbookreader.widget.RecyclerItemClickListenner
import com.victor.cloudbookreader.widget.readview.BaseReadView
import com.victor.cloudbookreader.widget.readview.OnReadStateChangeListener
import com.victor.cloudbookreader.widget.readview.PageWidget
import kotlinx.android.synthetic.main.activity_read.*

class ReadActivity : BaseActivity() {
    private var bookId: String? = null

    private var mPageView: BaseReadView? = null
    private var mCharpterList: ArrayList<BookChapter.MixTocBean.ChaptersBean> = arrayListOf()

    private var chapterListAdapter: ChapterListAdapter? = null

    private var currentChapter: Int = 1


    override fun getLayout() = R.layout.activity_read

    override fun initView() {
        mPageView = PageWidget(this, bookId!!, mCharpterList, object : OnReadStateChangeListener {
            override fun onPageChanged(chapter: Int, page: Int) {
            }

            override fun onFlip() {
                hideReadBar()
            }

            override fun onChapterChanged(chapter: Int) {
            }

            override fun onLoadChapterFailure(chapter: Int) {
            }

            override fun onCenterClick() {
                toggleReadBar()
            }
        })
        flReadWidget.removeAllViews()
        flReadWidget.addView(mPageView)
        chapterListAdapter = ChapterListAdapter(this, mCharpterList, bookId!!, currentChapter)
        charater_list.layoutManager = LinearLayoutManager(this)
        charater_list.adapter = chapterListAdapter

        getData()
    }

    override fun initEvent() {
        tvBookReadToc.setOnClickListener {
            visible(charater_list_layout)
        }
        charater_list.addOnItemTouchListener(RecyclerItemClickListenner(charater_list, object : RecyclerItemClickListenner.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                gone(charater_list_layout)
                currentChapter = position + 1
                chapterListAdapter!!.setcurrentChapter(currentChapter)
                readCurrentChapter()
                hideReadBar()
            }
        }))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        bookId = intent?.getStringExtra("bookId")
        super.onCreate(savedInstanceState)
    }

    @Synchronized private fun hideReadBar() {
        gone(tvDownloadProgress, llBookReadBottom, llBookReadTop)
    }

    @Synchronized private fun showReadBar() {
        visible(llBookReadBottom, llBookReadTop)
    }

    @Synchronized private fun toggleReadBar() { // 切换工具栏 隐藏/显示 状态
        if (isVisible(llBookReadTop)) {
            hideReadBar()
        } else {
            showReadBar()
        }
    }


    /**
     * 获取当前章节。章节文件存在则直接阅读，不存在则请求
     */
    fun readCurrentChapter() {
        if (CacheManager.getInstanc().getChapterFile(bookId!!, currentChapter) != null) {
            showChapterRead(null, currentChapter)
        } else {
            BookReposity.getInstanc().getChapterContent(mCharpterList.get(currentChapter - 1).link, object : RepositoryCallBack<ChapterDetail> {
                override fun callSuccess(data: ChapterDetail) {
                    showChapterRead(data.chapter, currentChapter)
                }

                override fun callFailure(message: String) {
                    Toast.makeText(this@ReadActivity, message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    @Synchronized
    fun showChapterRead(data: ChapterDetail.ChapterBean?, chapter: Int) { // 加载章节内容
        if (data != null) {
            CacheManager.getInstanc().saveChapterFile(bookId!!, chapter, data)
        }
        currentChapter = chapter
        mPageView!!.jumpToChapter(currentChapter)
    }

    fun getData() {
        Log.d("@vic", bookId!!)
        BookReposity.getInstanc().getBookChapter(bookId!!, object : RepositoryCallBack<BookChapter> {
            override fun callSuccess(data: BookChapter) {
                mCharpterList.clear()
                mCharpterList.addAll(data.mixToc.chapters)
                chapterListAdapter!!.notifyDataSetChanged()
                BookReposity.getInstanc().getChapterContent(mCharpterList.get(currentChapter - 1).link, object : RepositoryCallBack<ChapterDetail> {
                    override fun callSuccess(data: ChapterDetail) {
                        CacheManager.getInstanc().saveChapterFile(bookId!!, currentChapter, data.chapter)

                        mPageView!!.jumpToChapter(currentChapter)
                    }

                    override fun callFailure(message: String) {
                        Toast.makeText(this@ReadActivity, message, Toast.LENGTH_SHORT).show()
                    }
                })
            }

            override fun callFailure(message: String) {
                Toast.makeText(this@ReadActivity, message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> return if (isVisible(charater_list_layout)) {
                gone(charater_list_layout)
                true
            } else {
                onBackPressed()
                true
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                mPageView!!.nextPage()
                return true
            }
            KeyEvent.KEYCODE_VOLUME_UP -> {
                mPageView!!.prePage()
                return true
            }

        }
        return super.onKeyDown(keyCode, event)
    }


}
