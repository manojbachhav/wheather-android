package com.example.wheatherforecast.network.utils

import android.content.Context
import android.util.Log
import com.example.wheatherforecast.model.error.ErrorModel
import com.example.wheatherforecast.model.error.ErrorResponseModel
import com.example.wheatherforecast.utils.constants.AppConstant
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCall {


    companion object {
        private const val TAG_SUCCESS = "success"
        private const val TAG_ERROR = "error"
        private const val TAG_CODE = "code"
        private const val TAG_INFO = "info"
        private const val TAG_TYPE = "type"

        private var instance: ApiCall? = null
        @Synchronized
        fun getInstance(): ApiCall {
            if (instance == null) {
                instance = ApiCall()
            }
            return instance as ApiCall
        }
    }

    fun <T> call(
        context: Context,
        dataSupplyHelper: DataSupplyHelper<Call<T>>?,
        dataCallbackHelper: DataCallbackHelper<T>?,
        vararg requestTag: String
    ) {

        val call = dataSupplyHelper?.get()

        call?.request()?.newBuilder()?.tag(requestTag)?.build()

        call?.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>?) {

                if (call.isCanceled) {
                    return
                }
                if (response!!.isSuccessful) {
                    dataCallbackHelper?.onSuccess(response.body())
                } else {
                    handleErrorResponse(response, dataCallbackHelper)
                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {

                if (call.isCanceled) {
                    return
                }
                var error: ErrorResponseModel = ErrorResponseModel()
                var errorModel: ErrorModel = ErrorModel()
                errorModel.code = 10001
                errorModel.info = "Something Went Wrong"
                errorModel.type = "Unknown"
                error.error = errorModel
                error.success = false
                dataCallbackHelper?.onError(error)
            }
        })
    }

    private fun <T> handleErrorResponse(
        response: Response<T>?,
        dataCallbackHelper: DataCallbackHelper<T>?
    ) {
        var errorList: ErrorResponseModel? = null
        try {
            if (response?.errorBody() != null) {
                errorList = parseErrorJsonArray(response.errorBody()!!.string())
            }
        } catch (e: Exception) {
            Log.e(AppConstant.TAG_WHEATHER_FORECAST, e.toString())
        }

        if (errorList != null) {
            dataCallbackHelper?.onError(errorList)
        }
    }

    fun parseErrorJsonArray(jsonString: String?): ErrorResponseModel {

        val errorResponseModel = ErrorResponseModel()
        val errorModel = ErrorModel()

        try {
            val jsonObject = JSONObject(jsonString)
            val success = jsonObject.getBoolean(TAG_SUCCESS)
            val jsonObjectError = jsonObject.getJSONObject(TAG_ERROR)
            val code = jsonObjectError.getInt(TAG_CODE)
            val info = jsonObjectError.getString(TAG_INFO)
            val type = jsonObjectError.getString(TAG_TYPE)
            errorModel.info = info
            errorModel.code = code
            errorModel.type = type
            errorResponseModel.success = success
            errorResponseModel.error = errorModel

        } catch (e: JSONException) {
            Log.e(AppConstant.TAG_WHEATHER_FORECAST, e.toString())
        }

        return errorResponseModel
    }

}
