package com.victor.cloudbookreader.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.telephony.TelephonyManager
import com.victor.cloudbookreader.ReaderApplication

/**
 * @author victor
 * @date 12/7/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

object Utils {

    val resoure: Resources
        get() = ReaderApplication.readerApplication.resources

    fun getDimens(resId: Int): Float {
        return resoure.getDimension(resId)
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    fun getScreenWidth(): Int {
        return resoure.getDisplayMetrics().widthPixels
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    fun getScreenHeight(): Int {
        return resoure.getDisplayMetrics().heightPixels
    }

    /**
     * 将dp转换成px
     *
     * @param dp
     * @return
     */
    fun dpToPx(dp: Float): Float {
        return dp * resoure.getDisplayMetrics().density
    }

    fun dpToPxInt(dp: Float): Int {
        return (dpToPx(dp) + 0.5f).toInt()
    }

    /**
     * 将px转换成dp
     *
     * @param px
     * @return
     */
    fun pxToDp(px: Float): Float {
        return px / resoure.getDisplayMetrics().density
    }

    fun pxToDpInt(px: Float): Int {
        return (pxToDp(px) + 0.5f).toInt()
    }

    fun formatContent(str: String): String {
        var str = str
        str = str.replace("[ ]*".toRegex(), "")//替换来自服务器上的，特殊空格
        str = str.replace("[ ]*".toRegex(), "")//
        str = str.replace("\n\n", "\n")
        str = str.replace("\n", "\n" + getTwoSpaces())
        str = getTwoSpaces() + str
        //        str = convertToSBC(str);
        return str
    }

    /**
     * Return a String that only has two spaces.
     *
     * @return
     */
    fun getTwoSpaces(): String {
        return "\u3000\u3000"
    }

    @SuppressLint("MissingPermission", "HardwareIds")
    fun getIMEI(context: Context): String {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val IMEI = telephonyManager.getDeviceId()
        return IMEI;
    }

}
