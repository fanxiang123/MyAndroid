package com.firebase.spakebrony.myandroid.nethttp

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.util.*

internal object HttpLogClass {
    private val TAG = HttpLogClass::class.java.simpleName

    val httpLoggingInterceptor: HttpLoggingInterceptor
         get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
                try {
                    val text = URLDecoder.decode(message, "utf-8")
                    Log.d(TAG, text)
                } catch (e: UnsupportedEncodingException) {
                    e.printStackTrace()
                    Log.e(TAG, e.message!!)
                }
            }
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }
}