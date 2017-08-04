package com.micky.kotlinproject.domain.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.micky.kotlinproject.domain.db.dao.CityDao
import com.micky.kotlinproject.domain.model.City

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/8/3 11:36
*/
@Database(entities = arrayOf(City::class), version = DBCore.DB_VERSION)
abstract class MKDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}