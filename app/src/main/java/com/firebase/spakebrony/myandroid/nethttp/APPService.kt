package com.firebase.spakebrony.myandroid.nethttp

import com.google.gson.JsonObject
import retrofit2.http.GET
import rx.Observable
import java.util.*

interface APPService {


//    @GET("api/v1/versions/findByType")
//    fun text(@Body body: body?): Observable<String>

    @GET("api/v1/versions/findByType")
    fun getNum(): Observable<JsonObject>

}