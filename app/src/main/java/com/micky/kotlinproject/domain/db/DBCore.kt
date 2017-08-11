package com.micky.kotlinproject.domain.db

import android.arch.persistence.room.Room
import com.micky.kotlinproject.BaseApplication

/**
 * Created by Administrator on 2017/8/4.
 */
class DBCore private constructor() {

    val database: MKDatabase = Room.databaseBuilder(BaseApplication.instance!!, MKDatabase::class.java, "mk_db").addMigrations(MigrationHelper.MIGRATION_1_2).build()

    companion object {
        //数据库版本号
        const val DB_VERSION = 2

        fun getInstance() = DBCoreHolder.dbCore
    }

    private object DBCoreHolder {
        var dbCore = DBCore()
    }
}