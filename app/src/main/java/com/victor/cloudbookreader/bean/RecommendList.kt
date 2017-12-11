package com.victor.cloudbookreader.bean

/**
 * @author victor
 * @date 12/11/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

data class RecommendList(var booklists: List<RecommendBook>,
                         private val ok: Boolean) {


    /**
     * id : 5617c5f3e8a2065627e4cb85
     * title : 此单在手，书荒不再有！
     * author : 选择
     * desc : 应有尽有！注：随时有可能添加新书！
     * bookCount : 498
     * cover : /agent/http://image.cmfu.com/books/3582111/3582111.jpg
     * collectorCount : 3925
     */


    data class RecommendBook(var id: String,
                             var title: String,
                             var author: String,
                             var desc: String,
                             var bookCount: Int,
                             var cover: String,
                             var collectorCount: Int)
}
