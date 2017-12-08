package com.victor.cloudbookreader.bean

/**
 * @author victor
 * @date 12/7/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

data class BookChapter(var _id: String,
                       var link: String,
                       var source: String,
                       var name: String,
                       var book: String,
                       var updated: String,
                       var host: String,
                       var chapters: List<ChaptersBean>) {
    /**
     * _id : 577b477dbd86a4bd3f8bf1b2
     * link : http://www.biquge.la/book/16431/
     * source : biquge
     * name : 笔趣阁
     * book : 57206c3539a913ad65d35c7b
     * chapters : [{"title":"第一章 他叫白小纯","link":"http://www.biquge.la/book/16431/6658470.html",
     * "totalpage":0,"partsize":0,"order":0,"currency":0,"unreadble":false,"isVip":false}]
     * updated : 2017-12-04T03:57:57.344Z
     * host : biquge.la
     */


    data class ChaptersBean(var title: String,
                            var link: String,
                            var totalpage: Int,
                            var partsize: Int,
                            var order: Int,
                            var currency: Int,
                            var isUnreadble: Boolean,
                            var isIsVip: Boolean)
    /**
     * title : 第一章 他叫白小纯
     * link : http://www.biquge.la/book/16431/6658470.html
     * totalpage : 0
     * partsize : 0
     * order : 0
     * currency : 0
     * unreadble : false
     * isVip : false
     */

}
