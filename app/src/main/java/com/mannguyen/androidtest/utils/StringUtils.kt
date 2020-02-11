package com.mannguyen.androidtest.utils

fun String.toHttps(): String {
    if (startsWith("http")) {
        return replaceFirst("http", "https")
    }

    return this
}