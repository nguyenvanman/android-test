package com.mannguyen.androidtest.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mannguyen.androidtest.R

fun View.loadImage() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun ImageView.loadImage(context: Context, imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
        .placeholder(context.createLoadingDrawable())
        .error(R.drawable.default_thumbnail)
        .into(this)
}
