package com.example.newsapplication.ui.home

import android.content.DialogInterface
import android.content.Intent
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.*
import com.example.newsapplication.apis.ApiManager
import com.example.newsapplication.base.BaseViewModel
import com.example.newsapplication.database.NewsDataBase
import com.example.newsapplication.repositry.SourcesRepositry
import com.example.newsapplication.repositry.datasource.OfflineSourcesdatasource
import com.example.newsapplication.repositry.datasource.OnlinrSourcesdatasource
import com.example.newsapplication.repositry.imp.OfflineDataSourcesImp
import com.example.newsapplication.repositry.imp.OnlineDataSourcesImp
import com.example.newsapplication.repositry.imp.SourcesRepositryImp
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : BaseViewModel<Navigator>(){
    // hold data
    // handel logic

    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    val showprogressLiveData =MutableLiveData<Boolean>()
    val showprogressImageLiveData =MutableLiveData<Boolean>()
    val messageLiveData =MutableLiveData<String>()
    var showNewsLiveData =MutableLiveData<List<ArticlesItem?>?>()
    val onlinrSourcesdatasource:OnlinrSourcesdatasource=OnlineDataSourcesImp(ApiManager.getApis())
    val offlineSourcesdatasource:OfflineSourcesdatasource=OfflineDataSourcesImp(NewsDataBase.getInstance())
    val networkAwareHandler=NetworkAwareHandlerImp.getInstance()
    val sourcesRepositry:SourcesRepositry=SourcesRepositryImp(onlinrSourcesdatasource,offlineSourcesdatasource,networkAwareHandler)

     fun getSources() {
         viewModelScope.launch {
             showprogressLiveData.value=true

         try {
                 val sources = sourcesRepositry.getSources()
                 sourcesLiveData.value = sources
             showprogressLiveData.value=false

             }catch (e:Exception){
             showprogressLiveData.value=false
                 messageLiveData.value = e.localizedMessage
             }
         }
    }
     fun getEverythingNews(sourceId: String?) {
         showprogressLiveData.value=true
         showNewsLiveData.value=null
         ApiManager.getApis().getNewsEverything(Constance.apikey, "en", "", sourceId ?: "")
            .enqueue(object : Callback<EverythingResponse> {
                override fun onFailure(call: Call<EverythingResponse>, t: Throwable) {
                    showprogressLiveData.value=false
                    showprogressImageLiveData.value=false
                    messageLiveData.value=t.localizedMessage
                }


                override fun onResponse(call: Call<EverythingResponse>,
                                        response: Response<EverythingResponse>) {
                 showprogressLiveData.value=false
                    showprogressImageLiveData.value=false

                    if (response.isSuccessful) {
                        showNewsLiveData.value=response.body()?.articles

                    } else {
                        messageLiveData.value=response.body()?.message
                    }
                }

            })


    }

}