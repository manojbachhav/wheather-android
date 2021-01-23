package com.example.wheatherforecast.network.utils

import com.example.wheatherforecast.model.error.ErrorResponseModel

interface DataCallbackHelper<T> {

    fun onSuccess(response: T?)

    fun onError(errorResponseModel: ErrorResponseModel?)


}
