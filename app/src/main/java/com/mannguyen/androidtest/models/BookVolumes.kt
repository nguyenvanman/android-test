package com.mannguyen.androidtest.models

import com.google.gson.annotations.SerializedName

data class BookVolumes(
    @SerializedName("totalItems") val totalItems: Int? = null,
    @SerializedName("items") val items: MutableList<BookVolume> = mutableListOf()
)