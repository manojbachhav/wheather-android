package com.example.wheatherforecast.network.proxy.wheather

import android.content.Context
import com.example.wheatherforecast.BuildConfig
import com.example.wheatherforecast.model.WheatherResultModel
import com.example.wheatherforecast.network.proxy.wheather.helpers.WheatherApiCallHelper
import com.example.wheatherforecast.network.proxy.wheather.helpers.WheatherProxyHelper
import com.example.wheatherforecast.network.utils.ApiCall
import com.example.wheatherforecast.network.utils.ApiClient
import com.example.wheatherforecast.network.utils.DataCallbackHelper
import com.example.wheatherforecast.network.utils.DataSupplyHelper
import retrofit2.Call
import retrofit2.Retrofit
import java.util.*

class WheatherApiProxy private constructor() :
    WheatherProxyHelper {
    protected val client: Retrofit

    init {
        client = ApiClient.initRetrofit()

    }

    companion object {

        private var instance: WheatherApiProxy? = null

        @Synchronized
        fun getInstance(): WheatherApiProxy {
            if (instance == null) {
                instance = WheatherApiProxy()
            }
            return instance as WheatherApiProxy
        }
    }

    private fun getHeaderParams(): Map<String, String> {
        val headerHashMap = HashMap<String, String>()
        headerHashMap["Content-Type"] = "application/json"
        return headerHashMap
    }

    override fun searchCity(
        context: Context,
        dataCallback: DataCallbackHelper<WheatherResultModel>,
        searchString: String
    ) {
        ApiCall.getInstance()
            .call(
                context,
                object : DataSupplyHelper<Call<WheatherResultModel>> {
                    override fun get(): Call<WheatherResultModel> {
                        return client.create(WheatherApiCallHelper::class.java).searchCity(
                            getHeaderParams(),
                            BuildConfig.ACCESS_KEY,
                            searchString
                        )
                    }
                }, dataCallback
            )
    }

}