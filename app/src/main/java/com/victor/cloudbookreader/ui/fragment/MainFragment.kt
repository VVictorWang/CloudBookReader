package com.victor.cloudbookreader.ui.fragment

import android.support.v4.app.Fragment
import com.victor.cloudbookreader.R
import com.victor.cloudbookreader.ui.adapter.MyFragmnetPageAdapter
import com.victor.cloudbookreader.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
class MainFragment : BaseFragment() {
    override fun initView() {
        val fragments = ArrayList<Fragment>()
        fragments.add(EveryDayFragment())
        val myAdapter = MyFragmnetPageAdapter(childFragmentManager, fragments)
        vp_main.adapter = myAdapter
    }

    override fun getLayout(): Int {
        return R.layout.fragment_main
    }


}