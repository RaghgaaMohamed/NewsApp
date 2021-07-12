package com.example.newsapplication.repositry.imp

import androidx.room.Database
import com.example.newsapplication.Constance
import com.example.newsapplication.SourcesItem
import com.example.newsapplication.apis.Webservices
import com.example.newsapplication.database.NewsDataBase
import com.example.newsapplication.repositry.datasource.OfflineSourcesdatasource
import com.example.newsapplication.repositry.datasource.OnlinrSourcesdatasource

class OnlineDataSourcesImp(val webServices:Webservices):OnlinrSourcesdatasource{

    override suspend fun getSources(): List<SourcesItem?>? {
        val sourceResponse= webServices.getNewsSources(Constance.apikey,"en")
        return sourceResponse.sources
    }
}
class OfflineDataSourcesImp(val database:NewsDataBase):OfflineSourcesdatasource{
    override suspend fun getSources(): List<SourcesItem?>? {
     val sources= database.sourcesDao()
    .getNewsSources()
        return sources
    }

    override suspend fun cacheSources(data: List<SourcesItem?>?) {
        database.sourcesDao().cacheSources(data)
    }
}