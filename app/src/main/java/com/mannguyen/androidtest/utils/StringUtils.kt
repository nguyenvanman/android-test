package com.mannguyen.androidtest.utils

fun String.toHttps(): String {
    return replaceFirst("http://", "https://")
}