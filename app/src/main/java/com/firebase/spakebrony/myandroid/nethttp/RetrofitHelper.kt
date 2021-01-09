package com.firebase.spakebrony.myandroid.nethttp

import android.util.Base64
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by hcc on 16/8/4 21:18
 * 100332338@qq.com
 *
 *
 * Retrofit帮助类
 */
object RetrofitHelper {

    private val baseUrl =  "http://tibeticenglish.mvtrail.cn/"


    private var mOkHttpClient: OkHttpClient? = null
    val appAPI: APPService
        get() = createApi(
            APPService::class.java,
            baseUrl
        )

    /**
     * 根据传入的baseUrl，和api创建retrofit
     */
    private fun <T> createApi(clazz: Class<T>, baseUrl: String): T {
        Log.d("baseUrl", baseUrl)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(mOkHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(clazz)
    }


    /**
     * 初始化OKHttpClient,设置缓存,设置超时时间,设置打印日志,设置UA拦截器
     */
    private fun initOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized(RetrofitHelper::class.java) {
                if (mOkHttpClient == null) {

                    mOkHttpClient = OkHttpClient.Builder()
                        .addInterceptor(HttpLogClass.httpLoggingInterceptor)
                        .retryOnConnectionFailure(true)
                        .readTimeout(100, TimeUnit.SECONDS)
                        .connectTimeout(100, TimeUnit.SECONDS)
                        .writeTimeout(100, TimeUnit.SECONDS)
                        .addInterceptor(UserAgentInterceptor())
                        .build()
                }
            }
        }
    }

    /**
     * 添加UA拦截器，B站请求API需要加上UA才能正常使用
     */
    private class UserAgentInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val requestWithUserAgent = originalRequest.newBuilder()
                .addHeader("Content-Type", "text/plain")
//                .addHeader("Authorization", BuildConfig.authorization)
                .addHeader("Accept", "application/json")
                .build()
            return chain.proceed(requestWithUserAgent)
        }
    }

    init {
        initOkHttpClient()
    }
}