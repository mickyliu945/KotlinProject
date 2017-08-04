package com.micky.kotlinproject.domain.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/30 23:15
 */
@Entity
data class City(
        @PrimaryKey
        val id: String,
        val name: String,
        val namePinyin: String?,
        val parentName: String?,
        val parentId: String?,
        var selected:Boolean = false)