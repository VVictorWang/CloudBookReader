package com.victor.cloudbookreader.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.victor.cloudbookreader.bean.BookDetail

/**
 * @author victor
 * @date 12/15/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class CommunityAdapter(val context: Context, var item: BookDetail) : RecyclerView.Adapter<CommetAdapter.CommentViewHolder>() {
    override fun getItemCount(): Int {
        return item.chaptersCount
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommetAdapter.CommentViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: CommetAdapter.CommentViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class CommunityViewHolder()
}