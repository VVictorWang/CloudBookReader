package com.victor.cloudbookreader.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.victor.cloudbookreader.bean.BookDetail

/**
 * @author victor
 * @date 12/12/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookInfo: BookDetail)

    @Query("select * from books where id = :bookId")
    fun getBookByid(bookId: String): BookDetail?

}