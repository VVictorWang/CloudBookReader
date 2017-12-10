package com.victor.cloudbookreader.bean

/**
 * @author victor
 * @date 12/8/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

data class BookChapter(var mixToc: MixTocBean,
                       var ok: Boolean) {
    /**
     * mixToc : {"_id":"5736014103c072d93f37d8fa","book":"5721df50bd7cc02728a981b1",
     * "chaptersCount1":1701,"chaptersUpdated":"2017-12-08T00:11:16.992Z",
     * "chapters":[{"title":"第1章 盲眼少年！","link":"http://book.my716.com/getBooks
     * .aspx?method=content&bookId=1074192&chapterFile=U_1074192_201706301419513914_8023_1.txt",
     * "unreadble":false},{"title":"第2章 登门问罪！","link":"http://book.my716.com/getBooks
     * .aspx?method=content&bookId=1074192&chapterFile=U_1074192_201706280838039297_8503_2.txt",
     * "unreadble":false}],"updated":"2017-12-08T00:11:16.992Z"}
     * ok : true
     */


    data class MixTocBean(var _id: String,
                          var book: String,
                          var chaptersCount1: Int,
                          var chaptersUpdated: String,
                          var updated: String,
                          var chapters: List<ChaptersBean>) {
        /**
         * _id : 5736014103c072d93f37d8fa
         * book : 5721df50bd7cc02728a981b1
         * chaptersCount1 : 1701
         * chaptersUpdated : 2017-12-08T00:11:16.992Z
         * chapters : [{"title":"第1章 盲眼少年！","link":"http://book.my716.com/getBooks
         * .aspx?method=content&bookId=1074192&chapterFile
         * =U_1074192_201706301419513914_8023_1.txt","unreadble":false},{"title":"第2章 登门问罪！",
         * "link":"http://book.my716.com/getBooks
         * .aspx?method=content&bookId=1074192&chapterFile
         * =U_1074192_201706280838039297_8503_2.txt","unreadble":false}]
         * updated : 2017-12-08T00:11:16.992Z
         */


        data class ChaptersBean(var title: String,
                                var link: String,
                                var unreadable: Boolean)
        /**
         * title : 第1章 盲眼少年！
         * link : http://book.my716.com/getBooks.aspx?method=content&bookId=1074192&chapterFile=U_1074192_201706301419513914_8023_1.txt
         * unreadble : false
         */


    }
}
