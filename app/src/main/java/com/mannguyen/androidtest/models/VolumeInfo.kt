package com.mannguyen.androidtest.models

import com.google.gson.annotations.SerializedName

data class VolumeInfo(
    @SerializedName("title") val title: String? = null,
    @SerializedName("subTitle") val subTitle: String? = null,
    @SerializedName("authors") val authors: MutableList<String> = mutableListOf(),
    @SerializedName("publisher") val publisher: String? = null,
    @SerializedName("publishedDate") val publishDate: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("pageCount") val pageCount: Int? = null,
    @SerializedName("categories") val categories: MutableList<String> = mutableListOf(),
    @SerializedName("imageLinks") val imageLinks: ImageLinks? = null
)