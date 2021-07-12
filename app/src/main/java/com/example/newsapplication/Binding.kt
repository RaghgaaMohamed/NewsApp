package com.example.newsapplication

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("android:UrltoImage")
fun setImagefromUrl(Image :ImageView,url :String){
     Glide.with(Image)
        .load(url)
        .into(Image)
}