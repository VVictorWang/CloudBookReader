package com.victor.cloudbookreader.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.Constants
import com.victor.cloudbookreader.bean.Recommend
import kotlinx.android.synthetic.main.book_list_item.view.*

/**
 * @author victor
 * @date 12/6/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class BookListAdapter(var context: Context, var item: Recommend) : RecyclerView.Adapter<BookListAdapter.BookListViewHolder>() {
    override fun getItemCount(): Int {
        return item.books.size
    }

    override fun onBindViewHolder(holder: BookListViewHolder?, position: Int) {
        if (item.ok) {
            holder?.bind(item.books[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BookListViewHolder {
        return BookListViewHolder(parent!!)
    }

    fun setData(item: Recommend) {
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
        }
    }
}