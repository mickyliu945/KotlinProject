package com.micky.kotlinproject.presenter

import com.micky.kotlinproject.domain.model.City

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/30 23:11
 */
interface CityPresenter : BasePresenter {
    fun loadData()

    open interface CityView {
        fun onLoadData(cityList:MutableList<City>?)
    }
}