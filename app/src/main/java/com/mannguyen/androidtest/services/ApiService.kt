package com.mannguyen.androidtest.services

import android.annotation.SuppressLint
import io.reactivex.Observable
import retrofit2.Response

object ApiService {

    @SuppressLint("CheckResult")
    fun <T> call(
        observable: Observable<Response<T>>?,
        onSuccess: ((T?) -> Unit)? = null,
        onError: ((String?) -> Unit)? = null
    ) {
        observable?.subscribe({
            if (it.isSuccessful) {
                onSuccess?.invoke(it.body())
            } else {
                onError?.invoke(it.errorBody()?.string())
            }
        }, {
            onError?.invoke(it.message)
        })
    }

}