package com.mannguyen.androidtest.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText
import com.mannguyen.androidtest.utils.hideKeyboard

class SearchingEditText : AppCompatEditText {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun onActionSearch(onSearch: ((String) -> Unit)? = null) {
        setOnEditorActionListener { v, actionId, _ ->
            context.hideKeyboard(v)

            val query = v.text.toString()

            if (actionId == EditorInfo.IME_ACTION_SEARCH && query.isNotEmpty()) {
                onSearch?.invoke(query)
                true
            } else {
                false
            }
        }
    }

}