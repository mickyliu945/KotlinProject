package com.micky.kotlinproject.domain.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.micky.kotlinproject.domain.model.City
import io.reactivex.Flowable

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/8/3 11:15
 */
@Dao
interface CityDao {

    @Insert
    fun insert(city:City)

    @Insert
    fun insert(cityList:MutableList<City>)

    @Update
    fun update(city:City)

    @Update
    fun updateAsync(city:City):Flowable<City>

    @Query("SELECT * FROM city where id = :id")
    fun query(id:String):City

    @Query("SELECT * FROM city")
    fun queryAll():MutableList<City>
}