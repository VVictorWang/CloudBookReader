package com.victor.cloudbookreader.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.BookDetail
import com.victor.cloudbookreader.bean.Constants
import kotlinx.android.synthetic.main.bookshelf_item.view.*

/**
 * @author victor
 * @date 12/13/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class BookShelfAdapter(var context: Context, var item: ArrayList<BookDetail>) : RecyclerView.Adapter<BookShelfAdapter.BookShelfViewHolder>() {
    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: BookShelfViewHolder?, position: Int) {
        holder!!.bind(item[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BookShelfViewHolder {
        return BookShelfViewHolder(parent!!)
    }

    fun setData(item: List<BookDetail>) {
        this.item.clear()
        this.item.addAll(item)
        notifyDataSetChanged()
    }

    fun addData(data: BookDetail) {
        item.add(data)
        notifyDataSetChanged()
    }

    class BookShelfViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.bookshelf_item, null)) {
        fun bind(item: BookDetail) = with(itemView) {
            Glide.with(context).load(Constants.IMG_BASE_URL + item.cover).into(book_cover)
        }


    }
}