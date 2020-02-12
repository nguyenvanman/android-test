package com.mannguyen.androidtest.utils

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mannguyen.androidtest.R

fun View.loadImage() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun AppCompatTextView.setTextFromHtml(html: String?) {
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html ?: "", Html.FROM_HTML_MODE_COMPACT)
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(html ?: "")
    }
}

fun ImageView.loadImage(context: Context, imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
        .placeholder(context.createLoadingDrawable())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.default_thumbnail)
        .into(this)
}
