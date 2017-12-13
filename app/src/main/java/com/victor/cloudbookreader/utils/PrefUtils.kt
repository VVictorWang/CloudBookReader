package com.victor.cloudbookreader.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.victor.cloudbookreader.ReaderApplication

/**
 * @author victor
 * @date 12/13/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
object PrefUtils {
    /**
     * 移除SharedPreference
     */
    fun RemoveValue(context: Context = ReaderApplication.readerApplication, key: String) {
        val editor = getSharedPreference(context).edit()
        editor.remove(key)
        val result = editor.commit()
        if (!result) {
            Log.e("移除Shared", "save $key failed")
        }
    }

    private fun getSharedPreference(context: Context = ReaderApplication.readerApplication): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    /**
     * 获取SharedPreference 值
     */
    fun getValue(key: String, defaultValu: String? = null, context: Context = ReaderApplication.readerApplication): String? {
        return getSharedPreference(context).getString(key, defaultValu)
    }

    fun getBooleanValue(context: Context = ReaderApplication.readerApplication, key: String, defaultValue: Boolean): Boolean {
        return getSharedPreference(context).getBoolean(key, defaultValue)
    }

    fun putBooleanValue(context: Context = ReaderApplication.readerApplication, key: String,
                        bl: Boolean) {
        val edit = getSharedPreference(context).edit()
        edit.putBoolean(key, bl)
        edit.commit()
    }

    fun getIntValue(context: Context = ReaderApplication.readerApplication, key: String, defaultValue: Int): Int {
        return getSharedPreference(context).getInt(key, defaultValue)
    }

    fun getLongValue(context: Context = ReaderApplication.readerApplication, key: String,
                     default_data: Long): Long {
        return getSharedPreference(context).getLong(key, default_data)
    }

    fun putLongValue(context: Context = ReaderApplication.readerApplication, key: String,
                     value: Long?): Boolean {
        val editor = getSharedPreference(context).edit()
        editor.putLong(key, value!!)
        return editor.commit()
    }

    fun hasValue(context: Context = ReaderApplication.readerApplication, key: String): Boolean {
        return getSharedPreference(context).contains(key)
    }

    /**
     * 设置SharedPreference 值
     */
    fun putValue(key: String,
                 value: String?, context: Context = ReaderApplication.readerApplication): Boolean {
        var value = value
        value = if (value == null) "" else value
        val editor = getSharedPreference(context).edit()
        editor.putString(key, value)
        val result = editor.commit()
        return result
    }

    /**
     * 设置SharedPreference 值
     */
    fun putIntValue(context: Context = ReaderApplication.readerApplication, key: String,
                    value: Int): Boolean {
        val editor = getSharedPreference(context).edit()
        editor.putInt(key, value)
        val result = editor.commit()
        return result
    }

}