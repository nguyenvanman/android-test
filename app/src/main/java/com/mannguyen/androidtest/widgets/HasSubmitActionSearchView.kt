package com.mannguyen.androidtest.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.SearchView
import com.mannguyen.androidtest.utils.hideKeyboard

class HasSubmitActionSearchView : SearchView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun onSearchSubmit(onSearch: (String) -> Unit) {
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    hideKeyboard()
                    onSearch.invoke(it)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

}