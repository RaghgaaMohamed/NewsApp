package com.example.newsapplication.apis

import android.view.textclassifier.TextLanguage
import com.example.newsapplication.SoucesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Webservices {
   @GET("sources")
    fun getNewsSources(@Query("apiKey") key :String ,
   @Query("language")language: String): Call<SoucesResponse>
}