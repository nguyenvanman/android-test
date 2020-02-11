package com.mannguyen.androidtest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mannguyen.androidtest.R
import com.mannguyen.androidtest.adapters.holders.VolumeViewHolder
import com.mannguyen.androidtest.models.BookVolume
import com.mannguyen.androidtest.services.datasources.VolumeDataSource


class VolumesAdapter(
    private val context: Context,
    private val items: MutableList<BookVolume> = mutableListOf()
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private fun addData(input: MutableList<BookVolume>?) {
        items.addAll(input ?: mutableListOf())
        notifyDataSetChanged()
    }

    private fun setData(input: MutableList<BookVolume>?) {
        items.clear()
        addData(input)
    }

    fun search(
        query: String,
        onStart: (() -> Unit)? = null,
        onCompleted: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null
    ) {
        onStart?.invoke()
        VolumeDataSource.searchVolumes(
            query = query,
            onSuccess = {
                onCompleted?.invoke()
                setData(it?.items)
            },
            onError = onError
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_volume, parent, false)
        return VolumeViewHolder(view).apply {
            onClick = this@VolumesAdapter::onItemClick
        }
    }

    private fun onItemClick(bookVolume: BookVolume?) {
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as VolumeViewHolder).bookVolume = items[position]
    }

}