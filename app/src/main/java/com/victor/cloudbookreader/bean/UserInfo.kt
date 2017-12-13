package com.victor.cloudbookreader.bean

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.victor.cloudbookreader.db.StringListConverter

/**
 * @author victor
 * @date 12/13/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

@Entity(tableName = "users")
@TypeConverters(*arrayOf(StringListConverter::class))
data class UserInfo(
        @PrimaryKey var id: String,
        var books: List<String>)