package com.victor.cloudbookreader.utils

import com.victor.cloudbookreader.bean.ChapterDetail

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
}
