package com.mannguyen.androidtest.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.mannguyen.androidtest.R

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun ImageView.show(context: Context, imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
        .placeholder(context.loadingDrawable())
        .error(R.drawable.default_thumbnail)
        .into(this)
}
