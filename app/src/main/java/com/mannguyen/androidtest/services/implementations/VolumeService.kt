package com.mannguyen.androidtest.services.implementations

import com.mannguyen.androidtest.models.BookVolume
import com.mannguyen.androidtest.models.BookVolumes
import com.mannguyen.androidtest.services.ApiClient
import com.mannguyen.androidtest.services.interfaces.IVolumeService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

object VolumeService {

    fun getVolumes(query: String, startIndex: Int = 0, maxResults: Int = 20): Observable<Response<BookVolumes>>? {
        return ApiClient.createService(IVolumeService::class.java)
            ?.getVolumes(query, startIndex, maxResults)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
    }

    fun getVolume(volumeId: String): Observable<Response<BookVolume>>? {
        return ApiClient.createService(IVolumeService::class.java)
            ?.getVolume(volumeId)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
    }

}