package com.victor.cloudbookreader.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.victor.cloudbookreader.ReaderApplication
import com.victor.cloudbookreader.utils.screenWidth
import java.util.*

/**
 * @author victor
 * @date 12/13/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

class MyBookListView : FrameLayout {

    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private val mScraptViews = LinkedList<View>()
    private var mAdapter: MyBookListAdapter? = null
    private var mOnTouchEffectListener: onTouchEffectListener? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight
    }

    fun setAdapter(adapter: MyBookListAdapter) {
        mAdapter = adapter
        val itemCount = adapter.count
        val loadCount = if (itemCount > 2) 2 else itemCount
        for (i in 0 until loadCount) {
            makeAndAddView(0)
        }
    }

    fun removeViewWithAnimate(view: View, isLeft: Boolean): View {
        view.animate().alpha(0f).translationX((if (isLeft) ReaderApplication.readerApplication.screenWidth else -ReaderApplication.readerApplication.screenWidth).toFloat())
                .setDuration(400).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                removeView(view)
                resetItem(view)
                mScraptViews.add(view)


            }
        })
        return view
    }

    private fun resetItem(item: View) {
        item.translationX = 0f
        item.alpha = 1f
    }

    /**
     * 获得一个view并且添加到布局的指定位置
     *
     * @param pos 要添加到布局的下标
     */
    private fun makeAndAddView(pos: Int) {
        if (mAdapter!!.currentIndex == mAdapter!!.count) {
            return //没有更多数据
        }
        val item = obtainView(mAdapter!!.currentIndex)
        addView(item, pos)
        //增加数据集的下标
        mAdapter!!.currentIndex = mAdapter!!.currentIndex + 1
    }

    /**
     * 得到一个item布局
     *
     * @return
     */
    private fun obtainView(pos: Int): View {
        //先尝试从废弃缓存中取出view
        val scrapView = if (mScraptViews.size > 0)
            mScraptViews.removeAt(mScraptViews.size - 1)
        else
            null
        val item = mAdapter!!.getView(pos, scrapView, this)
        if (item !== scrapView) {
            //代表view布局变化了，inflate了新的布局
            val lp = FrameLayout.LayoutParams(mWidth,
                    mHeight, Gravity.CENTER_HORIZONTAL)
            item.layoutParams = lp
            //初始化事件
            initEvent(item)
        }
        return item
    }

    private fun initEvent(item: View) {
        //        设置item的重心，主要是旋转的中心
        //        item.setPivotX(item.getLayoutParams().width / 2);
        //        item.setPivotY(item.getLayoutParams().height * 2);
        item.setOnTouchListener(object : View.OnTouchListener {
            internal var touchX: Float = 0.toFloat()
            internal var distanceX: Float = 0.toFloat()//手指按下时的坐标以及手指在屏幕移动的距离

            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> touchX = event.rawX
                    MotionEvent.ACTION_MOVE -> {
                        distanceX = event.rawX - touchX
                        if (mOnTouchEffectListener != null) {
                            mOnTouchEffectListener!!.onTouchEffect(item, event, distanceX)
                        }
                        item.translationX = distanceX
                        //alpha scale 1~0.1
                        //item的透明度为从1到0.1
                        item.alpha = 1 - Math.abs(distanceX / mWidth).toFloat()
                    }
                    MotionEvent.ACTION_UP -> {
                        if (mOnTouchEffectListener != null) {
                            mOnTouchEffectListener!!.onTouchEffect(item, event, distanceX)
                        }
                        if (Math.abs(distanceX) > mWidth) {
                            //移除view
                            removeViewWithAnimate(item, distanceX < 0)
                            makeAndAddView(0)
                        } else {
                            //复位
                            resetItem(item)
                        }
                    }
                }
                return true
            }
        })
    }

    fun setOnTouchEffectListener(listener: onTouchEffectListener) {
        this.mOnTouchEffectListener = listener
    }

    /**
     * 触摸item的回调事件
     */
    interface onTouchEffectListener {
        fun onTouchEffect(item: View, event: MotionEvent, distanceX: Float)
    }

}
