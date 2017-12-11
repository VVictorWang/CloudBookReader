package com.victor.cloudbookreader.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.Constants
import com.victor.cloudbookreader.bean.RecommendList
import kotlinx.android.synthetic.main.recommend_list.view.*

/**
 * @author victor
 * @date 12/11/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class RecommendListAdapter(var recommend: RecommendList?) : RecyclerView.Adapter<RecommendListAdapter.RecommentListViewHolder>() {
    override fun getItemCount(): Int {
        if (recommend != null) {
            return recommend!!.booklists.size
        }
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecommentListViewHolder {
        return RecommentListViewHolder(parent!!)
    }

    override fun onBindViewHolder(holder: RecommentListViewHolder?, position: Int) {
        holder!!.bind(recommend!!.booklists[position])
    }

    fun setData(data: RecommendList) {
        recommend = data
        notifyDataSetChanged()
    }


    class RecommentListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.recommend_list, null)) {
        fun bind(item: RecommendList.RecommendBook) = with(itemView) {
            Glide.with(context).load(Constants.IMG_BASE_URL + item.cover).into(book_cover)
            title.text = item.title
            book_author.text = item.author
            book_desc.text = item.desc
        }
    }


}