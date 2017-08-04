package com.micky.kotlinproject.domain.network

import com.micky.kotlinproject.domain.network.response.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/27 22:11
 */
interface TestService {

    @GET("data/cityinfo/{code}.html")
    fun getWeather(@Path("code") code: String): Observable<String>

    @GET("data/cityinfo/{code}.html")
    fun getWeather2(@Path("code") code: String): Observable<WeatherResponse>
}