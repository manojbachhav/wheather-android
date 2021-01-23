package com.example.wheatherforecast.network.proxy.wheather.helpers

import android.content.Context
import com.example.wheatherforecast.model.home.WheatherResultModel
import com.example.wheatherforecast.network.utils.DataCallbackHelper


interface WheatherProxyHelper {

    fun searchCity(
        context: Context,
        dataCallback: DataCallbackHelper<WheatherResultModel>,
        searchString: String
    )

}