package com.victor.cloudbookreader.bean

/**
 * @author victor
 * @date 12/6/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

data class SearchResult(var ok: Boolean, var books: List<SearchBooksBean>) {
    data class SearchBooksBean(var _id: String, var isHasCp: Boolean, var title: String,
                               var cat: String, var author: String, var site: String,
                               var cover: String, var shortIntro: String, var lastChapter: String,
                               var retentionRatio: Double, var banned: Int, var latelyFollower: Int,
                               var wordCount: Int, var contentType: String, var superscript: String,
                               var sizetype: Int, var highlight: HighlightBean) {
        data class HighlightBean(var title: List<String>)
    }
}
