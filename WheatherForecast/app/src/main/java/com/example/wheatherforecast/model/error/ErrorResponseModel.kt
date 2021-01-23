package com.example.wheatherforecast.model.error

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ErrorResponseModel : Serializable {

    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("error")
    @Expose
    var error: ErrorModel? = null

}