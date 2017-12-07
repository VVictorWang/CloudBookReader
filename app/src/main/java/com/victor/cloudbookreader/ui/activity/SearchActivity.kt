package com.victor.cloudbookreader.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.AutoComplete
import com.victor.cloudbookreader.bean.HotWord
import com.victor.cloudbookreader.bean.SearchResult
import com.victor.cloudbookreader.repository.BookReposity
import com.victor.cloudbookreader.repository.RepositoryCallBack
import com.victor.cloudbookreader.ui.adapter.AutoCompleteAdapter
import com.victor.cloudbookreader.ui.adapter.BookListAdapter
import com.victor.cloudbookreader.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {
    private var bookAdapter: BookListAdapter<SearchResult>? = null
    //    private var mlistPopWindow: ListPopupWindow? = null
    private var completeAdapter: AutoCompleteAdapter? = null

    override fun getLayout(): Int = R.layout.activity_search


    override fun initView() {
        getHotWord()
        val layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        search_result_list.layoutManager = layoutmanager
        search_result_list.setPullRefreshEnabled(true)
        search_result_list.setLoadingMoreEnabled(false)
        search_result_list.isNestedScrollingEnabled = false
        search_result_list.setHasFixedSize(false)
        bookAdapter = BookListAdapter(this, SearchResult(false, listOf()))
        search_result_list.adapter = bookAdapter

        completeAdapter = AutoCompleteAdapter(arrayListOf(), object : AutoCompleteAdapter.OnItemClickListenner {
            override fun onClick(text: String) {
                search(text)
                completeAdapter!!.hide()
            }
        })
        auto_complete_list.layoutManager = LinearLayoutManager(this)
        auto_complete_list.adapter = completeAdapter
        auto_complete_list.bringToFront()
    }

    override fun initEvent() {
        search_btn.setOnClickListener {
            if (search_word.text == null || "".equals(search_word.text.toString())) {
                search_word.error = getString(R.string.please_input_search_content)
            } else {
                search(search_word.text.toString())
            }
        }


        hot_words.setOnTagClickListener { tag -> search(tag) }

        search_word.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (newText == null || "".equals(newText.toString())) {
                    completeAdapter!!.setData(listOf())
                } else {
                    BookReposity.getInstanc().autoComplete(newText.toString(), object : RepositoryCallBack<AutoComplete> {
                        override fun callSuccess(data: AutoComplete) {
                            showAutoComplte(data.keywords)
                        }

                        override fun callFailure(message: String) {
                        }
                    })
                }
            }
        })

    }

    fun showAutoComplte(list: List<String>) {
        completeAdapter!!.setData(list)
    }

    fun search(keyWord: String) {
        search_word.setText(keyWord)
        BookReposity.getInstanc().searchBooks(keyWord, object : RepositoryCallBack<SearchResult> {
            override fun callFailure(message: String) {
                Toast.makeText(this@SearchActivity, "失败" + message, Toast.LENGTH_SHORT).show()
            }

            override fun callSuccess(data: SearchResult) {
                bookAdapter!!.setData(data)
                hot_search.visibility = View.GONE
                completeAdapter!!.setData(listOf())
            }
        })
    }

    fun getHotWord() {
        BookReposity.getInstanc().getHotWord(object : RepositoryCallBack<HotWord> {
            override fun callFailure(message: String) {

            }

            override fun callSuccess(data: HotWord) {
                hot_words.setTags(data.hotWords.subList(0, 8))
            }
        })
    }


}

