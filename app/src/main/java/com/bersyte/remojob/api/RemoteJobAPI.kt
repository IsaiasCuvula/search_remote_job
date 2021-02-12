package com.bersyte.remojob.api


import com.bersyte.remojob.models.RemoteJob
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RemoteJobAPI {

    @GET("remote-jobs")
    fun getRemoteJob(): Call<RemoteJob>

    @GET("remote-jobs")
    fun searchRemoteJob(@Query("search") query: String?): Call<RemoteJob>

}