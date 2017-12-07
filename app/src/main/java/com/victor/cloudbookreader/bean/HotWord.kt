package com.victor.cloudbookreader.bean

/**
 * @author victor
 * @date 12/6/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

data class HotWord(var isOk: Boolean, var hotWords: List<String>, var newHotWords: List<NewHotWordsBean>) {
    /**
     * hotWords : ["枕上贪欢：兽性总裁请轻点","武道天尊","圣武星辰","王妃重生记","绝品小神医","快穿白月光：boss，捡起节操","近身兵王",
     * "BOSS太狂野：宝贝，小心撩","大仙木","神医嫡女","田园日常[重生]","太古神帝","重生之校园特种兵","穿成豪门太太","超级金钱帝国","王的彪悍宠妻",
     * "网游之神级炼妖师","撩爱上瘾：总裁万万睡！","神武至尊","极光之恋（精装）","御剑仙瑶","暗恋·橘生淮南（全2册）","极品侯爷","原来你还在这里（精装）",
     * "末世特种兵","末世之主宰虚空","瞬零","星之传说","将夜"]
     * newHotWords : [{"word":"枕上贪欢：兽性总裁请轻点"},{"word":"武道天尊"},{"word":"圣武星辰"},{"word":"王妃重生记"},
     * {"word":"绝品小神医"},{"word":"快穿白月光：boss，捡起节操"},{"word":"近身兵王"},{"word":"BOSS太狂野：宝贝，小心撩"},
     * {"word":"大仙木"},{"word":"神医嫡女"},{"word":"田园日常[重生]"},{"word":"太古神帝"},{"word":"重生之校园特种兵"},
     * {"word":"穿成豪门太太"},{"word":"超级金钱帝国"},{"word":"王的彪悍宠妻"},{"word":"网游之神级炼妖师"},
     * {"word":"撩爱上瘾：总裁万万睡！"},{"word":"神武至尊"},{"word":"极光之恋（精装）"},{"word":"御剑仙瑶"},
     * {"word":"暗恋·橘生淮南（全2册）"},{"word":"极品侯爷"},{"word":"原来你还在这里（精装）"},{"word":"末世特种兵"},
     * {"word":"末世之主宰虚空"},{"word":"瞬零"},{"word":"星之传说"},{"word":"将夜"}]
     * ok : true
     */
    data class NewHotWordsBean(var word: String)

    /**
     * word : 枕上贪欢：兽性总裁请轻点
     */
}
