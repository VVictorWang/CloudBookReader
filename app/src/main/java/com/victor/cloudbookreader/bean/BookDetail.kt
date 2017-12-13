package com.victor.cloudbookreader.bean

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.victor.cloudbookreader.db.RattingBeanConverter
import com.victor.cloudbookreader.db.StringListConverter

/**
 * @author victor
 * @date 12/7/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

@Entity(tableName = "books")
@TypeConverters(*arrayOf(StringListConverter::class, RattingBeanConverter::class))
data class BookDetail(@PrimaryKey
                      @ColumnInfo(name = "id")
                      var _id: String,
                      @ColumnInfo(name = "title") var title: String,
                      @ColumnInfo(name = "author") var author: String,
                      @ColumnInfo(name = "longIntro") var longIntro: String,
                      @ColumnInfo(name = "cover") var cover: String,
                      @ColumnInfo(name = "tags") var tags: List<String>,
                      @ColumnInfo(name = "rentention") var retentionRatio: String,
                      @ColumnInfo(name = "majorCate") var majorCate: String?,
                      @ColumnInfo(name = "minorCate") var minorCate: String?,
                      @ColumnInfo(name = "creater") var creater: String,
                      @ColumnInfo(name = "rating") var rating: RatingBean = RatingBean(),
                      @ColumnInfo(name = "isHasCopyRight") var isHasCopyright: Boolean,
                      @ColumnInfo(name = "buytype") var buytype: Int,
                      @ColumnInfo(name = "sizetype") var sizetype: Int,
                      @ColumnInfo(name = "wordCount") var wordCount: Int,
                      @ColumnInfo(name = "lately") var latelyFollower: Int,
                      @ColumnInfo(name = "supercript") var superscript: String?,
                      @ColumnInfo(name = "currency") var currency: Int,
                      @ColumnInfo(name = "contenttype") var contentType: String,
                      @ColumnInfo(name = "is_le") var is_le: Boolean?,
                      @ColumnInfo(name = "cat") var cat: String,
                      var isHasCp: Boolean, var postCount: Int, var followerCount: Int,
                      var serializeWordCount: Int,

                      var updated: String, var isIsSerial: Boolean, var chaptersCount: Int,
                      var lastChapter: String, var isAdvertRead: Boolean,
                      var isDonate: Boolean, var copyright: String?, var is_gg: Boolean?,
                      var gender: List<String>,
                      var isAllowMonthly: Boolean, var isAllowVoucher: Boolean, var isAllowBeanVoucher: Boolean
) {


    /**
     * _id : 5816b415b06d1d32157790b1
     * title : 圣墟
     * author : 辰东
     * longIntro : 在破败中崛起，在寂灭中复苏。沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角……
     * cover : /agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1228859
     * %2F_1228859_441552.jpg%2F
     * majorCate : 玄幻
     * minorCate : 东方玄幻
     * creater : iPhone 5s (UK+Europe+Asis+China)
     * rating : {"count":22375,"score":8.755,"isEffect":true}
     * hasCopyright : true
     * buytype : 2
     * sizetype : -1
     * superscript :
     * currency : 0
     * contentType : txt
     * _le : false
     * allowMonthly : false
     * allowVoucher : true
     * allowBeanVoucher : false
     * hasCp : true
     * postCount : 53000
     * latelyFollower : 296560
     * followerCount : 0
     * wordCount : 3081569
     * serializeWordCount : 7057
     * retentionRatio : 73.08
     * updated : 2017-12-06T15:57:20.131Z
     * isSerial : true
     * chaptersCount : 800
     * lastChapter : 第799章 负责
     * gender : ["male"]
     * tags : ["玄幻","东方玄幻"]
     * advertRead : true
     * cat : 东方玄幻
     * donate : false
     * copyright : 阅文集团正版授权
     * _gg : false
     * discount : null
     */


    data class RatingBean(var count: Int,
                          var score: Double,
                          var isIsEffect: Boolean) {
        constructor() : this(0, 0.0, false)
    }
    /**
     * count : 22375
     * score : 8.755
     * isEffect : true
     */
}
