package com.micky.kotlinproject.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager


/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/26 21:58
 */
object NetUtils {

    /**网络是否连接*/
    fun isNetConnected(context: Context): Boolean {
        var connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected
    }

    /**是否是Wifi连接*/
    fun isWifi(context: Context): Boolean {
        var connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.type == ConnectivityManager.TYPE_WIFI
    }

    /**打开网络设置界面*/
    fun openWifiSetting(activity: Activity) {
        var intent = Intent(android.provider.Settings.ACTION_WIFI_SETTINGS)
        activity.startActivity(intent)
    }

    /**是否是手机网络*/
    fun isPhoneNetConnect(context: Context): Boolean {
        var connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected
    }

}