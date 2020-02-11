package com.mannguyen.androidtest.utils

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun Context.loadingDrawable(): CircularProgressDrawable {
    return CircularProgressDrawable(this).apply {
        strokeWidth = 5f
        centerRadius = 20f
        start()
    }
}