package com.victor.cloudbookreader.bean

/**
 * @author victor
 * @date 12/10/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

data class HotComment(var total: Int,
                      var isOk: Boolean,
                      var reviews: List<ReviewsBean>) {
    /**
     * total : 178
     * reviews : [{"_id":"577f788fdfcfeef9715b9921","rating":5,
     * "author":{"_id":"571c6fc0a9f6f6b76c712ccc",
     * "avatar":"/avatar/0a/0e/0a0e954ee58905f48aa819a1edbdd38a","nickname":"两个人的藏地",
     * "activityAvatar":"","type":"normal","lv":8,"gender":"male"},"helpful":{"total":3087,
     * "yes":3364,"no":277},"likeCount":131,"state":"normal",
     * "updated":"2017-12-10T11:06:23.284Z","created":"2016-07-08T09:55:27.085Z",
     * "commentCount":967,"content":"写的确实非常好，但是嘛，我看到一个小bug让我改变了世界观=_=在猪脚进皇家宝库那一章，得到了一把武器，介绍说重20
     * 万斤!猪脚尽全力拿了起来，虽说重量有点夸张，但是我也可以接受，\u2026\u2026但是，后面主角在没到达天灵境之前都是骑马当脚力的，描写的时候\u201c
     * 骑着骏马背后背着一把漆黑的大剑\u2026\u2026\u201d第一次看到的时候我还没反应过来，直到主角下马杀敌，描写从背后抽出剑杀敌的时候\u2026\u2026
     * 我才刚反应过来，你背着个20万斤的剑骑着马!!!主角能拿动剑的时候都地灵镜了，才全身力气拿动→_→这马背着一个主角加剑，还飞奔?\u2026\u2026
     * 这骑得不是马吧，这骑得是龙吧→_→\u2026\u2026","title":"确实不错，但是\u2026\u2026"},
     * {"_id":"59de757e6a76879c0e83b2bc","rating":1,"author":{"_id":"5889a2a16a071ee213fe62b0",
     * "avatar":"/avatar/03/0b/030bd2fa692d2c8de03b5ae4bd1e792e","nickname":"君安ss",
     * "activityAvatar":"/activities/20170120/1.jpg","type":"normal","lv":7,"gender":"male"},
     * "helpful":{"total":180,"yes":211,"no":31},"likeCount":13,"state":"distillate",
     * "updated":"2017-12-10T04:43:54.793Z","created":"2017-10-11T19:48:14.974Z",
     * "commentCount":145,
     * "content":"书名为完美至尊，但是我看到第五章就看不下去了。\r\n书龄越高的人对书越挑剔，也就是对书的逻辑要求的更完美，对作者思想境界要求更高。\r\n
     * 第一，真正的大家闺秀天之娇女、天之骄子性格都很好，和陌生人接触也会很友善，轻易不会得罪人，更别说第一面就特意与人过不去。可是到了快餐流里，这些人就成了弱智。我看到主角怼的这些人气的动了杀心时，我忍了一下。\r\n第二，猪脚性格脆弱敏感。一个大男生总跟陌生人过不去，到哪都能发生矛盾，总觉得别人看不起他，尤其是天之娇女、骄子，这种人不就是典型的心胸狭小，气量不够，不能容人的人吗？而这种人在生活里只会被人厌恶，所有人都不喜欢的人在中国社会里还想出头？想都别想！\r\n第三，书里人物塑造的浮夸而浮躁。里面的所有人都是弱智吗？不论多大年纪的人，喜怒哀乐都表现在脸上，好人从好人眸子里刻出来，长得都一样，坏人也是如此，作者能稍微塑造人物是用点心吗？\r\n第四，不是天才就会成功，前面也论述过，脑残的人再天才也没人喜欢帮他、用他。这样不完美的人竟然是主角，而且还叫完美至尊，真是对此书莫大的讽刺！\r\n总结说来，快餐流得特点就是读者看的爽，尤其是书龄低的人，这类事就是为他们准备的，但是关掉网页你会发现作者什么都没写出来。然而可悲的是，这种书竟然占据了网络小说的主流，浮躁的心态、充满漏洞的逻辑、简单的文笔、粗枝烂造的情节\u2026何时写书的人能多做一些功课，多练一下自己的文笔，对完善一下自己的逻辑，提高一下自己的审美。任何题材其实我都不反感，我反感的是这些粗糙的东西严重影响了我读小说的快感！其实这是一些很早就想说的话，只是很恰巧翻到了这本书所以写到了这里。希望能和大家交流一下心得，希望能有更多的人注意到这些东西，这样大家的挑剔才能成为这些作者写东西的动力，才会提供更好的小说让我们看！","title":"【丙类+】不完美的完美至尊，写书的人何时不浮躁"}]
     * ok : true
     */


    data class ReviewsBean(var _id: String,
                           var rating: Int = 0,
                           var author: AuthorBean,
                           var helpful: HelpfulBean,
                           var likeCount: Int,
                           var state: String,
                           var updated: String,
                           var created: String,
                           var commentCount: Int,
                           var content: String,
                           var title: String) {
        /**
         * _id : 577f788fdfcfeef9715b9921
         * rating : 5
         * author : {"_id":"571c6fc0a9f6f6b76c712ccc",
         * "avatar":"/avatar/0a/0e/0a0e954ee58905f48aa819a1edbdd38a","nickname":"两个人的藏地",
         * "activityAvatar":"","type":"normal","lv":8,"gender":"male"}
         * helpful : {"total":3087,"yes":3364,"no":277}
         * likeCount : 131
         * state : normal
         * updated : 2017-12-10T11:06:23.284Z
         * created : 2016-07-08T09:55:27.085Z
         * commentCount : 967
         * content : 写的确实非常好，但是嘛，我看到一个小bug让我改变了世界观=_=在猪脚进皇家宝库那一章，得到了一把武器，介绍说重20
         * 万斤!猪脚尽全力拿了起来，虽说重量有点夸张，但是我也可以接受，……但是，后面主角在没到达天灵境之前都是骑马当脚力的，描写的时候“骑着骏马背后背着一把漆黑的大剑……”第一次看到的时候我还没反应过来，直到主角下马杀敌，描写从背后抽出剑杀敌的时候……我才刚反应过来，你背着个20万斤的剑骑着马!!!主角能拿动剑的时候都地灵镜了，才全身力气拿动→_→这马背着一个主角加剑，还飞奔?……这骑得不是马吧，这骑得是龙吧→_→……
         * title : 确实不错，但是……
         */
        data class AuthorBean(var _id: String,
                              var avatar: String,
                              var nickname: String,
                              var activityAvatar: String,
                              var type: String,
                              var lv: Int,
                              var gender: String) {
            /**
             * _id : 571c6fc0a9f6f6b76c712ccc
             * avatar : /avatar/0a/0e/0a0e954ee58905f48aa819a1edbdd38a
             * nickname : 两个人的藏地
             * activityAvatar :
             * type : normal
             * lv : 8
             * gender : male
             */
        }

        data class HelpfulBean(var total: Int,
                               var yes: Int,
                               var no: Int)
        /**
         * total : 3087
         * yes : 3364
         * no : 277
         */
    }
}
