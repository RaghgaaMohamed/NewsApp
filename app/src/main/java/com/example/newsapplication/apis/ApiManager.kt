package com.example.newsapplication.apis

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    companion object{
        private var retrofit : Retrofit?=null
        private fun getRetrofitInstance (): Retrofit{
            if(retrofit==null){
                val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{
                    override fun log(message: String) {
                        Log.e("api",message)
                    }
                })
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

                retrofit = Retrofit.Builder()
                   .baseUrl("https://newsapi.org/v2/")
                    .client(client)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build()


            }
                return retrofit!!
        }
        fun getApis():Webservices{
return getRetrofitInstance().create(Webservices::class.java)
        }
    }
}