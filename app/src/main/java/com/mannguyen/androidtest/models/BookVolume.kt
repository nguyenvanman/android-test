package com.mannguyen.androidtest.models

import com.google.gson.annotations.SerializedName

data class BookVolume(
    @SerializedName("kind") val kind: String? = null,
    @SerializedName("id") val id: String,
    @SerializedName("selfLink") val selfLink: String? = null,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo? = null
)