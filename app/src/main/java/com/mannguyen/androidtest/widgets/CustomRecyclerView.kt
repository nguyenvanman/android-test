package com.mannguyen.androidtest.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerView : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun onScrolledToBottom(onLoadMore: () -> Unit) {
        addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                if (layoutManager.findLastCompletelyVisibleItemPosition() == (recyclerView.adapter?.itemCount ?: 0) - 1) {
                    onLoadMore.invoke()
                }
            }
        })
    }

}