package com.mannguyen.androidtest.services.datasources

import android.annotation.SuppressLint
import com.mannguyen.androidtest.models.BookVolume
import com.mannguyen.androidtest.models.BookVolumes
import com.mannguyen.androidtest.services.implementations.VolumeService

object VolumeDataSource {

    private const val maxResults = 20

    var currentQuery: String? = null

    var currentPage = 0

    private fun getVolumes(
        onError: ((Throwable) -> Unit)? = null,
        onSuccess: ((BookVolumes?) -> Unit)? = null
    ) {
        currentQuery?.apply {
            VolumeService.getVolumes(this, currentPage * maxResults, maxResults)
                ?.subscribe({
                    onSuccess?.invoke(it.body())
                }, onError)
        }
    }

    @SuppressLint("CheckResult")
    fun getVolume(
        volumeId: String,
        onError: ((Throwable) -> Unit)? = null,
        onSuccess: ((BookVolume?) -> Unit)? = null
    ) {
        VolumeService.getVolume(volumeId)
            ?.subscribe({
                onSuccess?.invoke(it.body())
            }, onError)
    }

    fun searchVolumes(
        query: String,
        onError: ((Throwable) -> Unit)? = null,
        onSuccess: ((BookVolumes?) -> Unit)? = null
    ) {
        if (query.isNotEmpty()) {
            currentPage = 0
            currentQuery = query
            getVolumes(
                onError = onError,
                onSuccess = onSuccess
            )
        }
    }

    fun nextPage(
        onError: ((Throwable) -> Unit)? = null,
        onSuccess: ((BookVolumes?) -> Unit)? = null
    ) {
        currentPage++
        getVolumes(
            onError = {
                currentPage--
                onError?.invoke(it)
            },
            onSuccess = onSuccess
        )
    }

}