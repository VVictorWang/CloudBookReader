package com.victor.cloudbookreader

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.victor.cloudbookreader.ui.adapter.MyFragmnetPageAdapter
import com.victor.cloudbookreader.ui.base.BaseActivity
import com.victor.cloudbookreader.ui.fragment.MainFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> {
                iv_title_main.isSelected = true
                iv_title_my.isSelected = false
                iv_title_square.isSelected = false
            }
            1 -> {
                iv_title_main.isSelected = false
                iv_title_my.isSelected = true
                iv_title_square.isSelected = false
            }
            2 -> {
                iv_title_main.isSelected = false
                iv_title_my.isSelected = false
                iv_title_square.isSelected = true
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        val fragments = ArrayList<Fragment>()
        fragments.add(MainFragment())
        fragments.add(MainFragment())
        fragments.add(MainFragment())
        vp_content.adapter = MyFragmnetPageAdapter(supportFragmentManager, fragments)
        vp_content.currentItem = 0
        iv_title_main.isSelected = true
        vp_content.addOnPageChangeListener(this)
    }

    override fun initEvent() {
        iv_title_main.setOnClickListener {
            if (vp_content.currentItem != 0) {
                iv_title_main.isSelected = true
                iv_title_my.isSelected = false
                iv_title_square.isSelected = false
                vp_content.currentItem = 0
            }
        }
        iv_title_my.setOnClickListener {
            if (vp_content.currentItem != 1) {
                iv_title_main.isSelected = false
                iv_title_my.isSelected = true
                iv_title_square.isSelected = false
                vp_content.currentItem = 1
            }
        }
        iv_title_square.setOnClickListener {
            if (vp_content.currentItem != 2) {
                iv_title_main.isSelected = false
                iv_title_my.isSelected = false
                iv_title_square.isSelected = true
                vp_content.currentItem = 2
            }
        }
    }


}
