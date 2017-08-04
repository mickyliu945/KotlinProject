package com.micky.kotlinproject.common.utils

import android.os.Environment
import java.io.File

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/26 21:58
 */
object FileUtils {

    val BASE_FILE_PATH = getExternalStorePath() + File.separator + Constants.BASE_FILE_PATH
    val LOG_PATH = BASE_FILE_PATH + File.separator + "log"
    val LOG_FILE = Constants.BASE_FILE_PATH

    fun initBaseFile() {
        createFile(LOG_PATH, LOG_FILE)
    }

    fun isExistExternalStore(): Boolean {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
    }

    fun getExternalStorePath(): String {
        if (isExistExternalStore()) {
            return Environment.getExternalStorageDirectory().absolutePath
        }
        return ""
    }

    fun createFile(folderPath:String, fileName:String) :File {
        var destDir = File(folderPath)
        if (!destDir.exists()) {
            destDir.mkdirs()
        }
        var file = File(folderPath, fileName)
        if (file.exists()) {
            file.createNewFile();
        }
        return file
    }

}