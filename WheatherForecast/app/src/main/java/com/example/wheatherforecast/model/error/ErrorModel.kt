package com.example.wheatherforecast.model.error

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ErrorModel : Serializable {

    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("info")
    @Expose
    var info: String? = null

}