package com.example.wheatherforecast.network.proxy.wheather.helpers

import com.example.wheatherforecast.model.WheatherResultModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface WheatherApiCallHelper {


    @GET("current")
    fun searchCity(
        @HeaderMap headerMap: Map<String, String>,
        @Query("access_key") accessKey: String,
        @Query("query") query: String
    ): Call<WheatherResultModel>


}
