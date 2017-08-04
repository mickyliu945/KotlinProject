package com.micky.kotlinproject.common.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.inputmethod.InputMethodManager

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/26 22:41
 */
object ViewUtils {

    /**切换键盘*/
    fun switchKeyboard(activity: Activity) {
        var imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    /**获取屏幕信息*/
    fun getScreenInfo(context: Context): DisplayMetrics {
        return context.resources.displayMetrics;
    }

    /**获取状态栏高度*/
    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        try {
            val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = context.resources.getDimensionPixelSize(resourceId)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    /**将px值转换为dip或dp值*/
    fun px2dip(context: Context, pxValue: Float): Int {
        var scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5F).toInt()
    }

    /**将px值转换为dip或dp值*/
    fun dip2px(context: Context, dipValue: Float): Int {
        var scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5F).toInt()
    }

    /**将px值转换为sp值*/
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**将sp值转换为px值*/
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**防止连续点击 */
    private var lastClickTime: Long = 0

    /**是否是联系点击*/
    fun isValidClick(): Boolean {
        val time = System.currentTimeMillis()
        val timeD = time - lastClickTime
        if (timeD in 0..500) {
            return false
        }
        lastClickTime = time
        return true
    }

}