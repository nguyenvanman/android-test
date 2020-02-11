package com.mannguyen.androidtest.adapters.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.mannguyen.androidtest.R
import com.mannguyen.androidtest.models.BookVolume
import com.mannguyen.androidtest.utils.show
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_volume.*

class VolumeViewHolder(override val containerView: View?) :
    RecyclerView.ViewHolder(containerView!!),
    LayoutContainer {

    var bookVolume: BookVolume? = null
        set(value) {
            field = value
            display()
        }

    var onClick: ((BookVolume?) -> Unit)? = null

    init {
        itemView.setOnClickListener {
            onClick?.invoke(bookVolume)
        }
    }

    private fun display() {
        bookVolume?.volumeInfo?.apply {
            tvTitle.text = title
            tvDescription.text = description
            imgThumbnail.show(itemView.context, imageLinks?.smallThumbnail?.replace("http", "https"))
        }
    }
}