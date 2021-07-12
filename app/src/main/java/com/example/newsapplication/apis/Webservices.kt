package com.example.newsapplication.apis

import android.view.textclassifier.TextLanguage
import com.example.newsapplication.EverythingResponse
import com.example.newsapplication.SoucesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Webservices {
    @GET("sources")
   suspend fun getNewsSources(@Query("apiKey") key :String ,
                              @Query("language")language: String) :SoucesResponse

    @GET("Everything ")
    fun getNewsEverything (@Query("apiKey") key :String ,
                       @Query("language")language: String,
                       @Query("q")searchKeyword:String,
                       @Query("sources")sources:String): Call<EverythingResponse>

}