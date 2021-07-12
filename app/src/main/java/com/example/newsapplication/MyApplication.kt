package com.example.newsapplication

import android.app.Application
import com.example.newsapplication.database.NewsDataBase

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
    NewsDataBase.init(this)
        NetworkAwareHandlerImp.init(this)

    }
}