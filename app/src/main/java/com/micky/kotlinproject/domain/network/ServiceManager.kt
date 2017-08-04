package com.micky.kotlinproject.domain.network

import com.micky.kotlinproject.BaseApplication
import com.micky.kotlinproject.R
import com.micky.kotlinproject.common.utils.Constants
import com.micky.kotlinproject.domain.network.common.StringConverterFactory
import com.micky.logger.Logger
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2017/7/27 18:32
 */
object ServiceManager {

    var stringServiceMap: MutableMap<String, Any?> = mutableMapOf()
    var gsonServiceMap: MutableMap<String, Any?> = mutableMapOf()

    var okHttpClient: OkHttpClient

    init {
        var clientBuilder = OkHttpClient.Builder()

        //Token拦截器
//        clientBuilder.addInterceptor(TokenInteceptor())

        if (Constants.DEBUG) {
            //日志拦截器
            var loggingInterceptor = HttpLoggingInterceptor({ message ->
                if (message != null && message.length > 0) {
                    Logger.t("HttpMessage").i(message)
                }
            })
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(loggingInterceptor)
        }

        //缓存处理
        var baseDir = BaseApplication.instance?.cacheDir
        if (baseDir != null) {
            var cacheDir = File(baseDir, "HttpResponseCache")
            clientBuilder.cache(Cache(cacheDir, Constants.HTTP_RESPONSE_DISK_CACHE_MAX_SIZE))
        }
        clientBuilder.connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
        okHttpClient = clientBuilder.build()
    }

    fun <T> createGsonService(t: Class<T>): T {
        var service = stringServiceMap[t.name]
        if (service == null) {
            var retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(getEndPoint(t))
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            service = retrofit.create(t)
            stringServiceMap.put(t.name, service)
        }
        return service as T
    }

    fun <T> createStringService(t: Class<T>): T {
        var service = gsonServiceMap[t.name]
        if (service == null) {
            var retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(getEndPoint(t))
                    .addConverterFactory(StringConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            service = retrofit.create(t)
            gsonServiceMap.put(t.name, service)
        }
        return service as T
    }

    fun <T> getEndPoint(t: Class<T>): String {
        var endPoint = ""
        if (t.name == TestService::class.java.name) {
            endPoint = BaseApplication.instance!!.getString(R.string.end_point)
        }
        return endPoint;
    }


    /**
     * Token相关拦截器
     */
    class TokenInteceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request: Request? = chain.request()?.newBuilder()
                    ?.addHeader("token", "")?.build()
            Logger.t("HttpMessage").i(request?.url().toString())
            return chain.proceed(request)
        }
    }
}