package com.victor.cloudbookreader.utils

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * @author victor
 * @date 12/11/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    if (duration == 0 || duration == 1) {
        Toast.makeText(applicationContext, msg, duration).show()
    } else {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    activity!!.toast(msg, duration)
}

//像素单位转换
val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

val Context.screenHeight
    get() = resources.displayMetrics.heightPixels

fun Context.dp2px(value: Int) = value * resources.displayMetrics.density

fun Context.dp2px(value: Float) = value * resources.displayMetrics.density

fun Context.dp2pxInt(value: Int) = (dp2px(value) + 0.5f).toInt()

fun Context.dp2pxInt(value: Float) = (dp2px(value) + 0.5f).toInt()

fun Context.px2dp(px: Float) = px / (resources.displayMetrics.density)

fun Context.px2dp(px: Int) = px / (resources.displayMetrics.density)

fun Context.px2dpInt(px: Float) = (px2dp(px) + 0.5f).toInt()

