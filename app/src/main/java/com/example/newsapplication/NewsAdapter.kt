package com.example.newsapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.databinding.ItemsNewsBinding

class NewsAdapter(var newsList :List<ArticlesItem?>?):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     //   val view =LayoutInflater.from(parent.context).inflate(R.layout.items_news,parent,false)
     //   return ViewHolder(view)
        val newsItemBinding:ItemsNewsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.items_news,parent,false)
        return ViewHolder(newsItemBinding)
    }

    override fun getItemCount(): Int {
        return newsList?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = newsList?.get(position)
       holder.bind(item!!)
    }

    fun changeData(newsList: List<ArticlesItem?>?){
        this.newsList=newsList
        notifyDataSetChanged()
    }


    class ViewHolder(val itemBinding :ItemsNewsBinding) :RecyclerView.ViewHolder(itemBinding.root){

        fun bind(newsItem :ArticlesItem){
     itemBinding.newsItem =newsItem
   }
}
}