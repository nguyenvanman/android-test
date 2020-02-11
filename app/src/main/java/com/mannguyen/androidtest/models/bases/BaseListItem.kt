package com.mannguyen.androidtest.models.bases

abstract class BaseListItem {

    abstract fun getType() : Int

    object ItemTypes {
        const val TYPE_NORMAL_ITEM = 1
        const val TYPE_LOADING_ITEM = 2
    }

}