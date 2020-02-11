package com.mannguyen.androidtest.models

import com.google.gson.annotations.SerializedName
import com.mannguyen.androidtest.models.bases.BaseListItem

data class BookVolume(
    @SerializedName("kind") val kind: String? = null,
    @SerializedName("id") val id: String,
    @SerializedName("selfLink") val selfLink: String? = null,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo? = null
) : BaseListItem() {

    override fun getType(): Int {
        return ItemTypes.TYPE_NORMAL_ITEM
    }

}