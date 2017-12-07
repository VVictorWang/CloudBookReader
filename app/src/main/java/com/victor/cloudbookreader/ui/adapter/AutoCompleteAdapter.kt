package com.victor.cloudbookreader.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.victor.cloudbookreader.R
import kotlinx.android.synthetic.main.search_auto_complete_item.view.*

/**
 * @author victor
 * @date 12/6/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class AutoCompleteAdapter(var completions: ArrayList<String>, var itemClickListenner: OnItemClickListenner) : RecyclerView.Adapter<AutoCompleteAdapter.AutoCompleteViewHolder>() {
    override fun getItemCount() = completions.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AutoCompleteViewHolder {
        return AutoCompleteViewHolder(parent!!)
    }

    override fun onBindViewHolder(holder: AutoCompleteViewHolder?, position: Int) {
        holder!!.bind(completions[position], itemClickListenner)
    }

    fun setData(data: List<String>) {
        completions.clear()
        completions.addAll(data)
        notifyDataSetChanged()
    }

    fun hide() {
        completions.clear()
        notifyDataSetChanged()
    }


    class AutoCompleteViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.search_auto_complete_item, null)) {
        fun bind(item: String, itemClickListenner: OnItemClickListenner) = with(itemView) {
            search_completion.text = item
            itemView.setOnClickListener { itemClickListenner.onClick(item) }
        }
    }

    interface OnItemClickListenner {
        fun onClick(text: String)
    }
}