package com.victor.cloudbookreader.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.BookDetail
import com.victor.cloudbookreader.bean.Constants

/**
 * @author victor
 * @date 12/13/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

class MyBookListAdapter(private val mContext: Context, private val mData: List<BookDetail>) {
    var currentIndex: Int = 0

    val count: Int
        get() = mData.size

    fun getView(postion: Int, container: View?, parent: ViewGroup): View {
        var container = container
        val viewHolder: MyViewHolder
        if (container == null) {
            container = LayoutInflater.from(mContext).inflate(R.layout.bookshelf_item, null)
            viewHolder = MyViewHolder(container)
            container!!.tag = viewHolder
        } else {
            viewHolder = container.tag as MyViewHolder
        }
        Glide.with(mContext).load(Constants.IMG_BASE_URL + mData[postion].cover)
                .into(viewHolder.mImageView)
        return container
    }

    internal class MyViewHolder(itemView: View) {
        val mImageView: ImageView

        init {
            mImageView = itemView.findViewById(R.id.book_cover)
        }
    }

}
