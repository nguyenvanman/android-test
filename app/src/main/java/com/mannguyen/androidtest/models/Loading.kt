package com.mannguyen.androidtest.models

import com.mannguyen.androidtest.models.bases.BaseListItem

class Loading : BaseListItem() {

    override fun getType(): Int {
        return ItemTypes.TYPE_LOADING_ITEM
    }

}