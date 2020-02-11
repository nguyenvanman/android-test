package com.mannguyen.androidtest.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun Context.loadingDrawable(): CircularProgressDrawable {
    return CircularProgressDrawable(this).apply {
        strokeWidth = 5f
        centerRadius = 20f
        start()
    }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}