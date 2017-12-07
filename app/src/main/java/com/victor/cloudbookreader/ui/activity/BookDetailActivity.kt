package com.victor.cloudbookreader.ui.activity

import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.bean.BookDetail
import com.victor.cloudbookreader.bean.Constants
import com.victor.cloudbookreader.repository.BookReposity
import com.victor.cloudbookreader.repository.RepositoryCallBack
import com.victor.cloudbookreader.ui.base.BaseHeaderActivity
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.base_header_title_bar.*
import kotlinx.android.synthetic.main.header_book_detail.*

class BookDetailActivity : BaseHeaderActivity() {
    override fun setHeaderLayout(): Int {
        return R.layout.header_book_detail
    }

    override fun setHeaderImgUrl(): String {
        return "as"
    }


    fun initView() {
//        setToolBar()
//        top_layout.bringToFront()
        getData()
    }


    //    private var slidingDistance: Int = 1
//    private var imageBgHeight: Int = 0
    private var bookId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        bookId = intent?.getStringExtra("id")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        initView()
    }

    fun getData() {
        BookReposity.getInstanc().getBookDetail(bookId!!, object : RepositoryCallBack<BookDetail> {
            override fun callFailure(message: String) {
                Log.d("@vic", "error " + message)
            }

            override fun callSuccess(data: BookDetail) {
                with(bindingHeaderView) {
                    initSlideShapeTheme(Constants.IMG_BASE_URL + data.cover, iv_base_titlebar_bg)
                }
                with(bindingHeaderView) {
                    Glide.with(this@BookDetailActivity).load(Constants.IMG_BASE_URL + data.cover)
                            .into(book_cover)
                }
                setTitle(data.title)
                setSubTitle(data.author)
//                tb_base_title.title = data.title
//                tb_base_title.subtitle = data.author

//                remainning_rate.text = data.retentionRatio + "%"
//                total_people.text = data.latelyFollower.toString()
                test.text = data.longIntro
                test1.text = data.longIntro
                test2.text = data.longIntro
                test3.text = data.longIntro
                test4.text = data.longIntro
                showContentView()
                Log.d("@vic", Constants.IMG_BASE_URL + data.cover)
            }
        })
    }

//    fun setImageHeaderBg(imgUrl: String) {
//        if (!"".equals(imgUrl)) {
//            Glide.with(this).load(imgUrl)
//                    .error(R.drawable.stackblur_default)
//                    .bitmapTransform(BlurTransformation(this, 23, 4))
//                    .listener(object : RequestListener<String?, GlideDrawable?> {
//                        override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable?>?, isFirstResource: Boolean): Boolean {
//                            return false
//                        }
//
//                        override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable?>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
//                            tb_base_title.setBackgroundColor(Color.TRANSPARENT)
//                            iv_base_titlebar_bg.imageAlpha = 0
//                            iv_base_titlebar_bg.visibility = View.VISIBLE
//                            return false
//                        }
//                    }).into(iv_base_titlebar_bg)
//
//        }
//    }
//
//    fun scrollChangeHeader(myscrollY: Int) {
//        var scrollY = myscrollY
//        if (scrollY < 0) {
//            scrollY = 0
//        }
//        val alpha = Math.abs(scrollY) * 1.0f / slidingDistance
//        val drawable = iv_base_titlebar_bg.drawable ?: return
//
//        if (scrollY <= slidingDistance) {
//            drawable.mutate().alpha = ((alpha * 255).toInt())
//        } else {
//            drawable.mutate().alpha = 255
//        }
//        iv_base_titlebar_bg.setImageDrawable(drawable)
//    }
//
//    /**
//     * *** 初始化滑动渐变 一定要实现 ******
//     *
//     * @param imgUrl    header头部的高斯背景imageUrl
//     * @param mHeaderBg header头部高斯背景ImageView控件
//     */
//    protected fun initSlideShapeTheme(imgUrl: String, mHeaderBg: ImageView?) {
//        setImageHeaderBg(imgUrl)
//
//        // toolbar 的高
//        val toolbarHeight = tb_base_title.layoutParams.height
//        val headerBgHeight = toolbarHeight + StatusBarUtil.getStatusBarHeight(this)
//
//        // 使背景图向上移动到图片的最低端，保留（titlebar+statusbar）的高度
//        val params = iv_base_titlebar_bg.layoutParams
//        val ivTitleHeadBgParams = iv_base_titlebar_bg.layoutParams as ViewGroup.MarginLayoutParams
//        val marginTop = params.height - headerBgHeight
//        ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0)
//
//        iv_base_titlebar_bg.imageAlpha = 0
//        StatusBarUtils.setTranslucentImageHeader(this, 0, tb_base_title)
//
//        // 上移背景图片，使空白状态栏消失(这样下方就空了状态栏的高度)
//        if (mHeaderBg != null) {
//            val layoutParams = mHeaderBg.layoutParams as ViewGroup.MarginLayoutParams
//            layoutParams.setMargins(0, -StatusBarUtil.getStatusBarHeight(this), 0, 0)
//
//            val imgItemBgparams = mHeaderBg.layoutParams
//            // 获得高斯图背景的高度
//            imageBgHeight = imgItemBgparams.height
//        }
//
//        // 变色
//        initScrollViewListener()
//        initNewSlidingParams()
//    }
//
//    private fun initScrollViewListener() {
//        // 为了兼容23以下
//        mns_base.setOnScrollChangeListener { scrollX, scrollY, oldScrollX, oldScrollY -> scrollChangeHeader(scrollY) }
//    }
//
//    private fun initNewSlidingParams() {
//        val titleBarAndStatusHeight = (Utils.getDimens(R.dimen.nav_bar_height) + StatusBarUtil.getStatusBarHeight(this)).toInt()
//        // 减掉后，没到顶部就不透明了
//        slidingDistance = imageBgHeight - titleBarAndStatusHeight - Utils.getDimens(R.dimen.base_header_activity_slide_more).toInt()
//    }
//
//    /**
//     * 设置toolbar
//     */
//    fun setToolBar() {
//        setSupportActionBar(tb_base_title)
//        val actionBar = supportActionBar
//        if (actionBar != null) {
//            //去除默认Title显示
//            actionBar.setDisplayShowTitleEnabled(false)
//            actionBar.setDisplayHomeAsUpEnabled(true)
//            actionBar.setHomeAsUpIndicator(R.drawable.icon_back)
//        }
//        // 手动设置才有效果
//        tb_base_title.setTitleTextAppearance(this, R.style.ToolBar_Title)
//        tb_base_title.setSubtitleTextAppearance(this, R.style.Toolbar_SubTitle)
////        tb_base_title.inflateMenu(R.menu.base_header_menu)
//        tb_base_title.overflowIcon = ContextCompat.getDrawable(this, R.drawable.actionbar_more)
//        tb_base_title.setNavigationOnClickListener { onBackPressed() }
//    }

}
