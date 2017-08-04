package com.micky.kotlinproject.presenter

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/30 16:25
 */
interface BasePresenter {

    fun onCreate()
    fun onResume()
    fun onPause()
    fun onDestory()
}