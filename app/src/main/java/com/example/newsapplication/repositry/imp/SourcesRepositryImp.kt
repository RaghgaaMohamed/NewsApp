package com.example.newsapplication.repositry.imp

import com.example.newsapplication.NetworkAwareHandler
import com.example.newsapplication.SourcesItem
import com.example.newsapplication.repositry.SourcesRepositry
import com.example.newsapplication.repositry.datasource.OfflineSourcesdatasource
import com.example.newsapplication.repositry.datasource.OnlinrSourcesdatasource

class SourcesRepositryImp(val onlinrSourcesdatasource: OnlinrSourcesdatasource,
val offlineSourcesdatasource: OfflineSourcesdatasource,
val NetworkAwareHandler:NetworkAwareHandler):SourcesRepositry {


    override suspend fun getSources(): List<SourcesItem?>? {
        // if network avaliable
        //then get data from api
        //cache data
        if (NetworkAwareHandler.isOnline()) {
            val sources = onlinrSourcesdatasource.getSources()
            cacheSources(sources)
            return sources
        }
        return offlineSourcesdatasource.getSources()
    }
    override suspend fun cacheSources(data: List<SourcesItem?>?) {
      offlineSourcesdatasource.cacheSources(data)
    }
    }


