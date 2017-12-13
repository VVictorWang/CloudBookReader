package com.victor.cloudbookreader.bean

import android.graphics.Color
import com.victor.cloudbookreader.ReaderApplication
import com.victor.cloudbookreader.utils.FileUtils

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

object Constants {

    val API_BASE_URL = "http://api.zhuishushenqi.com"

    val USER_ID = "user_id"
    val DEFAULT_USER = "default"

    val IMG_BASE_URL = "http://statics.zhuishushenqi.com"

    var PATH_DATA = FileUtils.createRootPath(ReaderApplication.readerApplication) + "/cache"
    var PATH_TXT = PATH_DATA + "/book/"
    var PATH_EPUB = PATH_DATA + "/epub"

    var PATH_CHM = PATH_DATA + "/chm"

    val SUFFIX_TXT = ".txt"
    val SUFFIX_PDF = ".pdf"
    val SUFFIX_EPUB = ".epub"
    val SUFFIX_ZIP = ".zip"
    val SUFFIX_CHM = ".chm"

    val CHAPTER = "http://chapter2.zhuishushenqi.com/chapter/"

    val tagColors = intArrayOf(Color.parseColor("#90C5F0"), Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"), Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"), Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E"))
}