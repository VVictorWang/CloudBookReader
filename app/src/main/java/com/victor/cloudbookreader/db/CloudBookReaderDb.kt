package com.victor.cloudbookreader.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.victor.cloudbookreader.bean.BookDetail
import com.victor.cloudbookreader.bean.UserInfo

/**
 * @author victor
 * @date 12/12/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

@Database(entities = arrayOf(BookDetail::class, UserInfo::class), version = 1)
abstract class CloudBookReaderDb : RoomDatabase() {

    abstract fun bookDao(): BookDao

    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var INSTANCE: CloudBookReaderDb? = null
        fun getInstance(context: Context): CloudBookReaderDb
                = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        CloudBookReaderDb::class.java, "coudreader.db").build()
    }
}