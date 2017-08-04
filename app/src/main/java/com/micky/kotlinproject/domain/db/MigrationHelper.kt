package com.micky.kotlinproject.domain.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration



/**
 * @Description 数据库升级
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/8/3 11:36
 */
object MigrationHelper {

    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE City ADD COLUMN selected INTEGER DEFAULT 0")
        }
    }
}