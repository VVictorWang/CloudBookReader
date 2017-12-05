package com.victor.cloudbookreader.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * @author victor
 * @date 12/4/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initView()
        initEvent()
    }

    protected abstract fun getLayout(): Int

    protected abstract fun initView()
    protected abstract fun initEvent()

}