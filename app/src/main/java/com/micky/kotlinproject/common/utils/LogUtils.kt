package com.micky.kotlinproject.common.utils

import com.micky.logger.*


/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/26 21:58
 */
object LogUtils {

    fun initLogger(globalTag: String) {
        Logger.clearLogAdapters();
        var fomatStrategy = PrettyFormatStrategy.newBuilder().tag(globalTag).build();
        Logger.addLogAdapter(object: AndroidLogAdapter(fomatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return Constants.DEBUG
            }
        })
    }

    fun initLogger(globalTag: String, logPath:String, logFile:String) {
        initLogger(globalTag)
        var cvsFormatStrategy = CsvFormatStrategy.newBuilder()
                .tag(globalTag)
                .logPath(logPath)
                .logFile(logFile)
                .build()
        Logger.addLogAdapter(object: DiskLogAdapter(cvsFormatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return return Constants.DEBUG || priority == Logger.ERROR
            }
        })
    }
}