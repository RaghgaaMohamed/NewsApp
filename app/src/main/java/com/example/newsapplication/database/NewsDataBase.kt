package com.example.newsapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapplication.SourcesItem
import com.example.newsapplication.database.dao.SourcesDao


@Database(entities = [SourcesItem::class],version = 1,exportSchema = false)
abstract class NewsDataBase :RoomDatabase(){
abstract fun sourcesDao():SourcesDao
companion object{
    var Newsdatabase :NewsDataBase?=null

    fun init(context:Context){
        Newsdatabase=Room.databaseBuilder(context, NewsDataBase::class.java,"NEWS_DB")
            .fallbackToDestructiveMigration()
            .build()
    }
    fun getInstance():NewsDataBase{
       return Newsdatabase!!
    }

}
}