package com.mannguyen.androidtest.services.interfaces

import com.mannguyen.androidtest.environments.Api
import com.mannguyen.androidtest.models.BookVolume
import com.mannguyen.androidtest.models.BookVolumes
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IVolumeService {

    @GET(Api.Volumes.GetVolumesPath)
    fun getVolumes(
        @Query("q") queryString: String,
        @Query("startIndex") startIndex: Int = 0,
        @Query("maxResults") maxResults: Int = 20
    ): Observable<Response<BookVolumes>>

    @GET(Api.Volumes.GetVolumePath)
    fun getVolume(@Path("volume_id") volumeId: String) : Observable<Response<BookVolume>>

}