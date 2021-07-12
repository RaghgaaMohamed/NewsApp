package com.example.newsapplication.repositry

import com.example.newsapplication.SourcesItem

interface SourcesRepositry {
suspend fun getSources():List<SourcesItem?>?
suspend fun cacheSources(data:List<SourcesItem?>?) // bta5od elsources mn api to database
}