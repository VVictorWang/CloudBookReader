package com.victor.cloudbookreader.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.Constants
import com.victor.cloudbookreader.bean.Recommend
import com.victor.cloudbookreader.bean.SearchResult
import com.victor.cloudbookreader.ui.activity.BookDetailActivity
import kotlinx.android.synthetic.main.book_list_item.view.*

/**
 * @author victor
 * @date 12/6/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class BookListAdapter<T>(var context: Context, var item: T) : RecyclerView.Adapter<BookListAdapter.BookListViewHolder>() {
    override fun getItemCount(): Int {
        when (item) {
            is Recommend? -> return (item!! as Recommend).books.size
            is SearchResult? -> return (item!! as SearchResult).books.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: BookListViewHolder?, position: Int) {
        when (item) {
            is Recommend? -> {
                if ((item!! as Recommend).ok) {
                    holder?.bind((item as Recommend).books[position])
                }
            }
            is SearchResult? -> {
                if ((item!! as SearchResult).ok) {
                    holder?.bind((item as SearchResult).books[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BookListViewHolder {
        return BookListViewHolder(parent!!)
    }

    fun setData(item: T) {
        this.item = item
        notifyDataSetChanged()
    }

    class BookListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.book_list_item, null)) {
        fun bind(item: Recommend.RecommendBooksBean) = with(itemView) {
            tv_one_title.text = item.title
            book_author.text = item.author
            Glide.with(context).load(Constants.IMG_BASE_URL + item.cover).into(book_cover)
            short_desc.text = item.shortIntro
            setMyClickListener(item._id, context)
        }

        fun setMyClickListener(bookId: String, context: Context) {
            itemView.setOnClickListener {
                val intent = Intent(context, BookDetailActivity::class.java)
                intent.putExtra("id", bookId)
                context.startActivity(intent)
            }
        }

        fun bind(item: SearchResult.SearchBooksBean) = with(itemView) {
            tv_one_title.text = item.title
            book_author.text = item.author
            Glide.with(context).load(Constants.IMG_BASE_URL + item.cover).into(book_cover)
            short_desc.text = item.shortIntro
            setMyClickListener(item._id, context)
        }
    }
}