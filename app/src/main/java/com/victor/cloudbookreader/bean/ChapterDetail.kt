package com.victor.cloudbookreader.bean

/**
 * @author victor
 * @date 12/8/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

data class ChapterDetail(var isOk: Boolean,
                         var chapter: ChapterBean) {
    /**
     * ok : true
     * chapter : {"title":"第一章 他叫白小纯",
     * "body":"帽儿山，位于东林山脉中，山下有一个村子，民风淳朴，以耕田为生，与世隔绝。\n
     * 清晨，村庄的大门前，整个村子里的乡亲，正为一个十五六岁少年送别，这少年瘦弱，但却白白净净，看起来很是乖巧，衣着尽管是寻常的青衫"}
     */

    data class ChapterBean(var title: String,
                           var body: String)
    /**
     * title : 第一章 他叫白小纯
     * body : 帽儿山，位于东林山脉中，山下有一个村子，民风淳朴，以耕田为生，与世隔绝。
     * 清晨，村庄的大门前，整个村子里的乡亲，正为一个十五六岁少年送别，这少年瘦弱，但却白白净净，看起来很是乖巧，衣着尽管是寻常的青衫
     */
}
