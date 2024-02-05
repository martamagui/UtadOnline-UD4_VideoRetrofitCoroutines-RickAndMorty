package com.mmag.ud4_videoretrofitconcoroutines.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mmag.ud4_videoretrofitconcoroutines.R

fun ImageView.loadFromUrl(url:String, context: Context){
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .into(this)
}