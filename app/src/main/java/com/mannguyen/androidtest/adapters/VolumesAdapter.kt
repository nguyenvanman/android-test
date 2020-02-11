package com.mannguyen.androidtest.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mannguyen.androidtest.R
import com.mannguyen.androidtest.activities.VolumeDetailActivity
import com.mannguyen.androidtest.adapters.holders.LoadingViewHolder
import com.mannguyen.androidtest.adapters.holders.VolumeViewHolder
import com.mannguyen.androidtest.constants.IntentKeys
import com.mannguyen.androidtest.models.BookVolume
import com.mannguyen.androidtest.models.Loading
import com.mannguyen.androidtest.models.bases.BaseListItem
import com.mannguyen.androidtest.services.datasources.VolumeDataSource


class VolumesAdapter(
    private val context: Context,
    private val items: MutableList<BaseListItem> = mutableListOf()
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoadingMore = false

    var onLoadMoreError: ((String?) -> Unit)? = null

    private fun addData(input: MutableList<BookVolume>) {
        items.addAll(input)
        notifyDataSetChanged()
    }

    private fun setData(input: MutableList<BookVolume>) {
        items.clear()
        addData(input)
    }

    fun search(
        query: String,
        onStart: (() -> Unit)? = null,
        onCompleted: (() -> Unit)? = null,
        onError: ((String?) -> Unit)? = null
    ) {
        onStart?.invoke()
        VolumeDataSource.searchVolumes(
            query = query,
            onSuccess = {
                onCompleted?.invoke()
                setData(it.items)
            },
            onError = onError
        )
    }

    fun loadMore() {
        if (!isLoadingMore) {
            showLoadMoreItem()
            VolumeDataSource.nextPage(
                onSuccess = {
                    hideLoadMoreItem()
                    addData(it.items)
                },
                onError = {
                    hideLoadMoreItem()
                    onLoadMoreError?.invoke(it)
                }
            )
        }
    }

    private fun showLoadMoreItem() {
        if (!isLoadingMore) {
            isLoadingMore = true
            items.add(Loading())
            notifyDataSetChanged()
        }
    }

    private fun hideLoadMoreItem() {
        if (isLoadingMore) {
            isLoadingMore = false
            items.dropLast(1)
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == BaseListItem.ItemTypes.TYPE_NORMAL_ITEM) {
            val view = layoutInflater.inflate(R.layout.item_volume, parent, false)
            VolumeViewHolder(view).apply {
                onClick = this@VolumesAdapter::onItemClick
            }
        } else {
            val view = layoutInflater.inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(view)
        }
    }

    private fun onItemClick(bookVolume: BookVolume?) {
        val intent = Intent(context, VolumeDetailActivity::class.java).apply {
            putExtra(IntentKeys.VolumeId, bookVolume?.id)
        }

        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == BaseListItem.ItemTypes.TYPE_NORMAL_ITEM) {
            (holder as VolumeViewHolder).bookVolume = items[position] as BookVolume
        }
    }

}