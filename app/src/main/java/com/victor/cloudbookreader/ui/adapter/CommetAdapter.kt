package com.victor.cloudbookreader.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.Constants
import com.victor.cloudbookreader.bean.HotComment
import com.victor.cloudbookreader.utils.formatAgoStyleForWeChat
import kotlinx.android.synthetic.main.comment_item.view.*

/**
 * @author victor
 * @date 12/10/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class CommetAdapter(var context: Context, var item: HotComment? = null) : RecyclerView.Adapter<CommetAdapter.CommentViewHolder>() {
    override fun getItemCount(): Int {
        if (item == null) {
            return 0
        }
        return item!!.reviews.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder?, position: Int) {
        holder!!.bind(item!!.reviews[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommentViewHolder {
        return CommentViewHolder(parent!!)
    }

    fun setData(item: HotComment) {
        this.item = item
        notifyDataSetChanged()
    }

    class CommentViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.comment_item, null)) {
        fun bind(item: HotComment.ReviewsBean) = with(itemView) {
            Glide.with(context).load(Constants.IMG_BASE_URL + item.author.avatar).into(avatar)
            name.text = item.author.nickname
            level.text = "lv." + item.author.lv
            tvTitle.text = item.title
            comment.text = item.content
            rating.setCountSelected(item.rating)
            tvHelpfulYes.text = item.helpful.total.toString()
            time.text = formatAgoStyleForWeChat(item.updated)

        }


    }
}