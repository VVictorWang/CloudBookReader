package com.victor.cloudbookreader.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.victor.cloudbookreader.R

/**
 * @author victor
 * @date 12/6/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class AutoCompleteAdapter(var completions: ArrayList<String>) : RecyclerView.Adapter<AutoCompleteAdapter.AutoCompleteViewHolder>() {
    override fun getItemCount() = completions.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AutoCompleteViewHolder {
        return AutoCompleteViewHolder(parent!!)
    }

    override fun onBindViewHolder(holder: AutoCompleteViewHolder?, position: Int) {
        holder!!.bind(completions[position])
    }

    fun setData(data: List<String>) {
        completions.clear()
        completions.addAll(data)
        notifyDataSetChanged()
    }


    class AutoCompleteViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.search_auto_complete_item, null)) {
        fun bind(item: String) = with(itemView) {

        }
    }
}