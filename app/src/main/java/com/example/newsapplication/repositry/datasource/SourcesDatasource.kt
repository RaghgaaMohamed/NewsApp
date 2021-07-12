package com.example.newsapplication.repositry.datasource

import com.example.newsapplication.SourcesItem

interface OnlinrSourcesdatasource{
suspend fun getSources():List<SourcesItem?>?
}
interface OfflineSourcesdatasource{
    suspend fun getSources():List<SourcesItem?>?
    suspend fun cacheSources(data:List<SourcesItem?>?)
}