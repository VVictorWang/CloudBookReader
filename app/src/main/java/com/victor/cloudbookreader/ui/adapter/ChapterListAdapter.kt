package com.victor.cloudbookreader.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.BookChapter
import com.victor.cloudbookreader.utils.FileUtils
import kotlinx.android.synthetic.main.chapter_list_item.view.*

/**
 * @author victor
 * @date 12/11/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class ChapterListAdapter(var context: Context, var item: ArrayList<BookChapter.MixTocBean.ChaptersBean>,
                         val bookId: String, var currentChapter: Int) : RecyclerView.Adapter<ChapterListAdapter.ChapterListViewHolder>() {
    private var isEpub: Boolean = false
    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ChapterListViewHolder?, position: Int) {
        holder!!.bind(item[position], position, currentChapter, bookId)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChapterListViewHolder {
        return ChapterListViewHolder(parent!!)
    }

    fun setData(item: List<BookChapter.MixTocBean.ChaptersBean>) {
        this.item.clear()
        this.item.addAll(item)
        notifyDataSetChanged()
    }

    fun setcurrentChapter(chapter: Int) {
        currentChapter = chapter
        notifyDataSetChanged()
    }

    class ChapterListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.chapter_list_item, null)) {
        fun bind(item: BookChapter.MixTocBean.ChaptersBean, position: Int, currentChapter: Int, bookId: String) = with(itemView) {
            var drawble: Drawable? = null
            if (currentChapter == position + 1) {
                tvTocItem.setTextColor(ContextCompat.getColor(context, R.color.light_red))
                drawble = ContextCompat.getDrawable(context, R.drawable.ic_toc_item_activated)
            } else if (FileUtils.getChapterFile(bookId, position + 1).length() > 10) {
                tvTocItem.setTextColor(ContextCompat.getColor(context, R.color.light_black))
                drawble = ContextCompat.getDrawable(context, R.drawable.ic_toc_item_download)
            } else {
                tvTocItem.setTextColor(ContextCompat.getColor(context, R.color.light_black))
                drawble = ContextCompat.getDrawable(context, R.drawable.ic_toc_item_normal)
            }
            drawble!!.setBounds(0, 0, drawble.minimumWidth, drawble.minimumHeight)
            tvTocItem.setCompoundDrawables(drawble, null, null, null)
            tvTocItem.text = item.title
        }
    }


}
