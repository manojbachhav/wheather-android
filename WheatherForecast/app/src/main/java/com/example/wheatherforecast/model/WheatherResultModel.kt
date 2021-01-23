package com.example.wheatherforecast.model

import com.example.wheatherforecast.model.error.ErrorModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class WheatherResultModel : Serializable {

    @SerializedName("request")
    @Expose
    var request: ResultModel? = null

    @SerializedName("location")
    @Expose
    var location: LocationModel? = null

    @SerializedName("current")
    @Expose
    var current: CurrentModel? = null


    // Error state
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("error")
    @Expose
    var error: ErrorModel? = null
}
