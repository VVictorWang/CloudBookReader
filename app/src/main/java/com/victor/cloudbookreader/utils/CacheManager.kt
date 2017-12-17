package com.victor.cloudbookreader.utils

import com.victor.cloudbookreader.bean.ChapterDetail
import com.victor.cloudbookreader.bean.Constants
import java.io.File

/**
 * @author victor
 * @date 12/8/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

class CacheManager {
    companion object {
        var instance: CacheManager? = null
        fun getInstanc(): CacheManager {
            if (instance == null) {
                instance = CacheManager()
            }
            return instance!!
        }
    }

    fun saveChapterFile(bookId: String, chapter: Int, data: ChapterDetail.ChapterBean) {
        val file = FileUtils.getChapterFile(bookId, chapter)
        FileUtils.writeFile(file.absolutePath, Utils.formatContent(data.body), false)
    }

    fun getChapterFile(bookId: String, chapter: Int): File? {
        val file = FileUtils.getChapterFile(bookId, chapter)
        return if (file != null && file.length() > 50) file else null
    }

    @Synchronized
    fun putCurrentRead(bookId: String, cuurentChaper: Int, startPos: Int, endPos: Int) {
        PrefUtils.putIntValue(bookId + Constants.CURRENT_CHAPETR, cuurentChaper)
        PrefUtils.putIntValue(bookId + Constants.START_POS, startPos)
        PrefUtils.putIntValue(bookId + Constants.END_POS, endPos)
    }

    fun getCurrentReadPos(bookId: String): IntArray
            = intArrayOf(PrefUtils.getIntValue(bookId + Constants.CURRENT_CHAPETR, 1), PrefUtils.getIntValue(bookId + Constants.START_POS, 0),
            PrefUtils.getIntValue(bookId + Constants.END_POS, 0))
}
