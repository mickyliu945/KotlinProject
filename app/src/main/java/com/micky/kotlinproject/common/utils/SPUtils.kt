package com.micky.kotlinproject.common.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/26 23:27
 */
object SPUtils {

    fun putOjbect(context: Context, key: String, obj: Any) {
        var sp: SharedPreferences = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sp.edit();
        when (obj) {
            is String -> editor.putString(key, obj.toString())
            is Int -> editor.putInt(key, obj.toInt())
            is Boolean -> editor.putBoolean(key, obj)
            is Float -> editor.putFloat(key, obj.toFloat())
            is Long -> editor.putLong(key, obj.toLong())
        }
        editor.commit()
    }

    fun <T> getObject(context: Context, key: String, defaultObj: T): T {
        var result: Any? = null
        var sp: SharedPreferences = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE)
        when (defaultObj) {
            is String -> result = sp.getString(key, defaultObj)
            is Int -> result = sp.getInt(key, defaultObj)
            is Boolean -> result = sp.getBoolean(key, defaultObj)
            is Float -> result = sp.getFloat(key, defaultObj)
            is Long -> result = sp.getLong(key, defaultObj)
        }
        return result as T
    }

    fun remove(context: Context, key: String) {
        var sp: SharedPreferences = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE)
        sp.edit().remove(key).commit()
    }

    fun clear(context: Context) {
        var sp: SharedPreferences = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE)
        sp.edit().clear().commit()
    }
}