package com.micky.kotlinproject

import android.app.Application
import android.arch.persistence.room.Room
import com.micky.kotlinproject.domain.db.MKDatabase

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/25 22:28
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: BaseApplication? = null
    }
}