package com.victor.cloudbookreader.ui.base

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.transition.ArcMotion
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.utils.PerfectClickListener
import com.victor.cloudbookreader.utils.StatusBarUtil
import com.victor.cloudbookreader.utils.StatusBarUtils
import com.victor.cloudbookreader.utils.Utils
import com.victor.cloudbookreader.widget.CustomChangeBounds
import com.victor.cloudbookreader.widget.MyNestedScrollView
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.base_header_title_bar.*
import java.lang.Exception

/**
 * @author victor
 * @date 12/7/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
abstract class BaseHeaderActivity : AppCompatActivity() {

    // 标题
    protected var bindingTitleView: View? = null
    // 内容布局头部
    protected var bindingHeaderView: View? = null
    // 内容布局view
    protected var bindingContentView: View? = null
    private var llProgressBar: LinearLayout? = null
    private var refresh: View? = null
    // 滑动多少距离后标题不透明
    private var slidingDistance: Int = 1
    // 这个是高斯图背景的高度
    private var imageBgHeight: Int = 0
    private var mAnimationDrawable: AnimationDrawable? = null

    protected fun <T : View> getView(id: Int): T {
        return findViewById<View>(id) as T
    }


    override fun setContentView(@LayoutRes layoutResID: Int) {
        val ll = layoutInflater.inflate(R.layout.activity_header_base, null)

        // 内容
        bindingContentView = layoutInflater.inflate(layoutResID, null, false)
        // 头部
        bindingHeaderView = layoutInflater.inflate(setHeaderLayout(), null, false)
        // 标题
        bindingTitleView = layoutInflater.inflate(R.layout.base_header_title_bar, null, false)

        // title (如自定义很强可以拿出去)
        val titleParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        bindingTitleView!!.layoutParams = titleParams
        val mTitleContainer = ll.findViewById<View>(R.id.title_container) as RelativeLayout
        mTitleContainer.addView(bindingTitleView)
        window.setContentView(ll)

        // header
        val headerParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        bindingHeaderView!!.layoutParams = headerParams
        val mHeaderContainer = ll.findViewById<View>(R.id.header_container) as RelativeLayout
        mHeaderContainer.addView(bindingHeaderView)
        window.setContentView(ll)

        // content
        val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        bindingContentView!!.layoutParams = params
        val mContainer = ll.findViewById<View>(R.id.container) as RelativeLayout
        mContainer.addView(bindingContentView)
        window.setContentView(ll)

        llProgressBar = getView(R.id.ll_progress_bar)
        refresh = getView(R.id.ll_error_refresh)

        // 设置自定义元素共享切换动画
        setMotion(setHeaderPicView(), false)

//        // 初始化滑动渐变
//        with(bindingTitleView) {
        initSlideShapeTheme("", setHeaderImageView())
//        }

        // 设置toolbar
        setToolBar()

        val img = getView<ImageView>(R.id.img_progress)

        // 加载动画
        mAnimationDrawable = img.drawable as AnimationDrawable
        // 默认进入页面就开启动画
        if (!mAnimationDrawable!!.isRunning) {
            mAnimationDrawable!!.start()
        }
        // 点击加载失败布局
        refresh!!.setOnClickListener(object : PerfectClickListener() {
            override fun onNoDoubleClick(v: View) {
                showLoading()
                onRefresh()
            }
        })
        bindingContentView!!.visibility = View.GONE

    }


    /**
     * a. 布局高斯透明图 header布局
     */
    protected abstract fun setHeaderLayout(): Int



    //    /**
//     * c. 设置头部header布局 高斯背景ImageView控件
//     */
    protected abstract fun setHeaderImageView(): ImageView

    /**
     * 设置头部header布局 左侧的图片(需要设置曲线路径切换动画时重写)
     */
    protected open fun setHeaderPicView(): ImageView {
        return ImageView(this)
    }

    /**
     * 1. 标题
     */
    override fun setTitle(text: CharSequence) {
        with(bindingTitleView) {
            tb_base_title.title = text
        }
    }

    /**
     * 2. 副标题
     */
    protected fun setSubTitle(text: CharSequence) {
        with(bindingTitleView) {
            tb_base_title.subtitle = text
        }
    }

    /**
     * 3. toolbar 单击"更多信息"
     */
    protected open fun setTitleClickMore() {}

    /**
     * 设置自定义 Shared Element切换动画
     * 默认不开启曲线路径切换动画，
     * 开启需要重写setHeaderPicView()，和调用此方法并将isShow值设为true
     *
     * @param imageView 共享的图片
     * @param isShow    是否显示曲线动画
     */
    protected fun setMotion(imageView: ImageView, isShow: Boolean) {
        if (!isShow) {
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //定义ArcMotion
            val arcMotion = ArcMotion()
            arcMotion.minimumHorizontalAngle = 50f
            arcMotion.minimumVerticalAngle = 50f
            //插值器，控制速度
            val interpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.fast_out_slow_in)

            //实例化自定义的ChangeBounds
            val changeBounds = CustomChangeBounds()
            changeBounds.pathMotion = arcMotion
            changeBounds.interpolator = interpolator
            changeBounds.addTarget(imageView)
            //将切换动画应用到当前的Activity的进入和返回
            window.sharedElementEnterTransition = changeBounds
            window.sharedElementReturnTransition = changeBounds
        }
    }

    /**
     * 设置toolbar
     */
    protected fun setToolBar() {
        with(bindingTitleView) {
            setSupportActionBar(tb_base_title)
            val actionBar = supportActionBar
            if (actionBar != null) {
                //去除默认Title显示
                actionBar.setDisplayShowTitleEnabled(false)
                actionBar.setDisplayHomeAsUpEnabled(true)
                actionBar.setHomeAsUpIndicator(R.drawable.icon_back)
            }
            tb_base_title.setTitleTextAppearance(this@BaseHeaderActivity, R.style.ToolBar_Title)
            tb_base_title.setSubtitleTextAppearance(this@BaseHeaderActivity, R.style.Toolbar_SubTitle)
//            tb_base_title.inflateMenu(R.menu.base_header_menu)
            tb_base_title.overflowIcon = ContextCompat.getDrawable(this@BaseHeaderActivity, R.drawable.actionbar_more)
            tb_base_title.setNavigationOnClickListener { onBackPressed() }
//            tb_base_title.setOnMenuItemClickListener { item ->
//                when (item.itemId) {
//                    R.id.actionbar_more// 更多信息
//                    -> setTitleClickMore()
//                }
//                false
//            }
        }

    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.base_header_menu, menu)
//        return true
//    }

    /**
    //     * 显示popu内的图片
    //     */
//    @SuppressLint("RestrictedApi")
//    override fun onPrepareOptionsPanel(view: View, menu: Menu?): Boolean {
//        if (menu != null) {
//            if (menu.javaClass.getSimpleName() == "MenuBuilder") {
//                try {
//                    val m = menu.javaClass.getDeclaredMethod(
//                            "setOptionalIconsVisible", java.lang.Boolean.TYPE)
//                    m.setAccessible(true)
//                    m.invoke(menu, true)
//                } catch (e: Exception) {
//                    Log.e(javaClass.getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e)
//                }
//
//            }
//        }
//        return super.onPrepareOptionsPanel(view, menu)
//    }

    /**
     * *** 初始化滑动渐变 一定要实现 ******
     *
     * @param imgUrl    header头部的高斯背景imageUrl
     * @param mHeaderBg header头部高斯背景ImageView控件
     */
    protected fun initSlideShapeTheme(imgUrl: String, mHeaderBg: ImageView?) {
        setImgHeaderBg(imgUrl)

        // toolbar 的高
        with(bindingTitleView) {
            val toolbarHeight = tb_base_title.layoutParams.height
            val headerBgHeight = toolbarHeight + StatusBarUtil.getStatusBarHeight(this@BaseHeaderActivity)

            // 使背景图向上移动到图片的最低端，保留（titlebar+statusbar）的高度
            val params = iv_base_titlebar_bg.layoutParams
            val ivTitleHeadBgParams = iv_base_titlebar_bg.layoutParams as ViewGroup.MarginLayoutParams
            val marginTop = params.height - headerBgHeight
            ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0)

            iv_base_titlebar_bg.imageAlpha = 0
            StatusBarUtils.setTranslucentImageHeader(this@BaseHeaderActivity, 0, tb_base_title)
        }


        // 上移背景图片，使空白状态栏消失(这样下方就空了状态栏的高度)
        if (mHeaderBg != null) {
            val layoutParams = mHeaderBg.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.setMargins(0, -StatusBarUtil.getStatusBarHeight(this), 0, 0)

            val imgItemBgparams = mHeaderBg.layoutParams
            // 获得高斯图背景的高度
            imageBgHeight = imgItemBgparams.height
        }

        // 变色
        initScrollViewListener()
        initNewSlidingParams()
    }


    /**
     * 加载titlebar背景
     */
    private fun setImgHeaderBg(imgUrl: String) {
        if (!TextUtils.isEmpty(imgUrl)) {
            with(bindingTitleView) {
                Glide.with(this@BaseHeaderActivity).load(imgUrl)
                        .error(R.drawable.stackblur_default)
                        .bitmapTransform(BlurTransformation(this@BaseHeaderActivity, 23, 4))
                        .listener(object : RequestListener<String, GlideDrawable> {
                            override fun onException(e: Exception, model: String, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
                                return false
                            }

                            override fun onResourceReady(resource: GlideDrawable, model: String, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {

                                tb_base_title.setBackgroundColor(Color.TRANSPARENT)
                                iv_base_titlebar_bg.imageAlpha = 0
                                iv_base_titlebar_bg.visibility = View.VISIBLE

                                return false
                            }
                        }).into(iv_base_titlebar_bg)
            }
            // 高斯模糊背景 原来 参数：12,5  23,4

        }
    }


    private fun initScrollViewListener() {
        // 为了兼容23以下
        (findViewById<View>(R.id.mns_base) as MyNestedScrollView).setOnScrollChangeListener { scrollX, scrollY, oldScrollX, oldScrollY -> scrollChangeHeader(scrollY) }
    }

    private fun initNewSlidingParams() {
        val titleBarAndStatusHeight = (Utils.getDimens(R.dimen.nav_bar_height) + StatusBarUtil.getStatusBarHeight(this)).toInt()
        // 减掉后，没到顶部就不透明了
        slidingDistance = imageBgHeight - titleBarAndStatusHeight - Utils.getDimens(R.dimen.base_header_activity_slide_more).toInt()
    }

    /**
     * 根据页面滑动距离改变Header方法
     */
    private fun scrollChangeHeader(scrolledY: Int) {
        var scrollY = scrolledY
        if (scrollY < 0) {
            scrollY = 0
        }
        val alpha = Math.abs(scrollY) * 1.0f / slidingDistance
        with(bindingTitleView) {
            val drawable = iv_base_titlebar_bg.drawable ?: return

            if (scrollY <= slidingDistance) {
                drawable.mutate().alpha = ((alpha * 255).toInt())
            } else {
                drawable.mutate().alpha = 255
            }
            iv_base_titlebar_bg.setImageDrawable(drawable)
        }


    }

    protected fun showLoading() {
        if (llProgressBar!!.visibility != View.VISIBLE) {
            llProgressBar!!.visibility = View.VISIBLE
        }
        // 开始动画
        if (!mAnimationDrawable!!.isRunning) {
            mAnimationDrawable!!.start()
        }
        if (bindingContentView!!.visibility != View.GONE) {
            bindingContentView!!.visibility = View.GONE
        }
        if (refresh!!.visibility != View.GONE) {
            refresh!!.visibility = View.GONE
        }
    }

    protected fun showContentView() {
        if (llProgressBar!!.visibility != View.GONE) {
            llProgressBar!!.visibility = View.GONE
        }
        // 停止动画
        if (mAnimationDrawable!!.isRunning) {
            mAnimationDrawable!!.stop()
        }
        if (refresh!!.visibility != View.GONE) {
            refresh!!.visibility = View.GONE
        }
        if (bindingContentView!!.visibility != View.VISIBLE) {
            bindingContentView!!.visibility = View.VISIBLE
        }
    }

    protected fun showError() {
        if (llProgressBar!!.visibility != View.GONE) {
            llProgressBar!!.visibility = View.GONE
        }
        // 停止动画
        if (mAnimationDrawable!!.isRunning) {
            mAnimationDrawable!!.stop()
        }
        if (refresh!!.visibility != View.VISIBLE) {
            refresh!!.visibility = View.VISIBLE
        }
        if (bindingContentView!!.visibility != View.GONE) {
            bindingContentView!!.visibility = View.GONE
        }
    }

    /**
     * 失败后点击刷新
     */
    protected open fun onRefresh() {

    }


}
