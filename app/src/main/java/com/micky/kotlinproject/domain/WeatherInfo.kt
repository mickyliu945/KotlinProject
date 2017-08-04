package com.micky.kotlinproject.domain

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/28 0:05
 */
data class WeatherInfo(val city: String,
                       val cityid: String,
                       val temp1: String,
                       val temp2: String,
                       val img1: String,
                       val img2: String,
                       val ptime: String)
