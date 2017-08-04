package com.micky.kotlinproject.common.utils

import android.util.Base64
import com.google.gson.GsonBuilder

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/30 16:41
 */
object StringUtils {

    fun encodeString(str: String): String {
        return String(Base64.encode(str.toByteArray(), Base64.DEFAULT))
    }

    fun decodeString(str: String): String {
        val bsByte = Base64.decode(str, Base64.DEFAULT)
        return String(bsByte)
    }

    fun objectToString(obj: Any): String {
        return StringUtils.encodeString(GsonBuilder().create().toJson(obj))
    }

    fun <T> stringToObject(str: String, clazz: Class<T>): T {
        return GsonBuilder().create().fromJson(decodeString(str), clazz)
    }

}