package com.micky.kotlinproject.domain.network.response

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/25 23:37
 */

const val SUCCESS = 0

data class BaseResponse<T>(val code: String, val data: T, val message: String)