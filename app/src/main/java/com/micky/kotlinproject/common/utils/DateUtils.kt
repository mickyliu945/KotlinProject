package com.micky.kotlinproject.common.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/30 16:37
 */
object DateUtils {
    
    val ymdhmsFormat: ThreadLocal<SimpleDateFormat> = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"))
            return sdf
        }
    }

    val ymdhmsFormat2: ThreadLocal<SimpleDateFormat> = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            val sdf = SimpleDateFormat("yyyyMMdd HH:mm:ss")
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"))
            return sdf
        }
    }
    val ymdhmsFormat3: ThreadLocal<SimpleDateFormat> = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            val sdf = SimpleDateFormat("yyyyMMddHHmmss")
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"))
            return sdf
        }
    }

    val ymdhmsmFormat: ThreadLocal<SimpleDateFormat> = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"))
            return sdf
        }
    }

    val sYMD_HMSFormat: ThreadLocal<SimpleDateFormat> = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"))
            return sdf
        }
    }

    val sYMDHMFormat: ThreadLocal<SimpleDateFormat> = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"))
            return sdf
        }
    }

    val sYMDFormat: ThreadLocal<SimpleDateFormat> = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"))
            return sdf
        }
    }

    val sHMFormat: ThreadLocal<SimpleDateFormat> = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            val sdf = SimpleDateFormat("HH:mm")
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"))
            return sdf
        }
    }

    val sEFormat: ThreadLocal<SimpleDateFormat> = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            val sdf = SimpleDateFormat("EEEE")
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"))
            return sdf
        }
    }

    val sEHMFormat: ThreadLocal<SimpleDateFormat> = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            val sdf = SimpleDateFormat("EEEE HH:mm")
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"))
            return sdf
        }
    }

    val sUTCFormat: ThreadLocal<SimpleDateFormat> = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"))
            return sdf
        }
    }
}