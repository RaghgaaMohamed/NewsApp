package com.example.newsapplication

import android.content.Context
import android.net.ConnectivityManager
import java.net.InetAddress

interface NetworkAwareHandler { // check feeh network wla la w hn3mloo imolentation bs msh hna kl3ada
    fun isOnline():Boolean
}

class NetworkAwareHandlerImp(val context:Context):NetworkAwareHandler{

    override fun isOnline(): Boolean {
       val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        return cm?.getActiveNetworkInfo() != null && cm.activeNetworkInfo!!.isConnected()
    }


    companion object{
        var myinstance:NetworkAwareHandler?=null
        fun init(context:Context){
          myinstance=NetworkAwareHandlerImp(context)
        }

        fun getInstance():NetworkAwareHandler{
    return myinstance!!
}
    }
}