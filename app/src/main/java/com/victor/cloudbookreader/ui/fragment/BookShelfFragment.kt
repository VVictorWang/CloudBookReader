package com.victor.cloudbookreader.ui.fragment

import android.os.Bundle
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.BookDetail
import com.victor.cloudbookreader.bean.Constants
import com.victor.cloudbookreader.repository.BookReposity
import com.victor.cloudbookreader.repository.UserRepository
import com.victor.cloudbookreader.ui.adapter.BookShelfAdapter
import com.victor.cloudbookreader.ui.base.BaseFragment
import com.victor.cloudbookreader.utils.NetWorkBoundUtils
import com.victor.cloudbookreader.utils.PrefUtils
import kotlinx.android.synthetic.main.fragment_book_shelf.*
import rx.Observable
import rx.schedulers.Schedulers


class BookShelfFragment : BaseFragment() {

    private var bookId: String? = null
    //    private var books: List<BookDetail> = arrayListOf()
    private var bookshelfAdapter: BookShelfAdapter? = null

    companion object {
        private val BOOKID = "bookId"
        fun newInstance(bookId: String): BookShelfFragment {
            val fragment = BookShelfFragment()
            val args = Bundle()
            args.putString(BOOKID, bookId)
            fragment.arguments = args
            return fragment
        }

    }

    override fun getLayout(): Int {
        return R.layout.fragment_book_shelf
    }

    override fun initView() {
        val layoutMnager = CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true)
        book_list.layoutManager = layoutMnager
        book_list.setHasFixedSize(true)
        bookshelfAdapter = BookShelfAdapter(context!!, arrayListOf())
        book_list.adapter = bookshelfAdapter
        getData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            bookId = arguments!!.getString(BOOKID)
        }
    }

    fun getData() {
        Observable.just(1).subscribeOn(Schedulers.io())
                .doOnNext({
                    Observable.from(UserRepository.getInstanc().getAllBookId(PrefUtils.getValue(Constants.USER_ID)!!))
                            .subscribeOn(Schedulers.io())
                            .doOnNext({ bookId: String? ->
                                BookReposity.getInstanc().getBookDetail(bookId!!, object : NetWorkBoundUtils.CallBack<BookDetail> {
                                    override fun callFailure(errorMessage: String) {

                                    }

                                    override fun callSuccess(result: BookDetail) {
                                        bookshelfAdapter!!.addData(result)
                                    }
                                })

                            })
                            .subscribe()
                })
                .subscribe()


    }
}
