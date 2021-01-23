package com.example.wheatherforecast.model

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


    //----------Temporary fields
    @SerializedName("name")
    @Expose
    var name: String? = "Pune"

    @SerializedName("country")
    @Expose
    var country: String? = "India"

    @SerializedName("localtime_epoch")
    @Expose
    var time: Long? = 1611371100

    @SerializedName("temp")
    @Expose
    var temp: Int? = 34
    @SerializedName("image")
    @Expose
    var image: String? =
        "https://assets.weatherstack.com//images//wsymbols01_png_64//wsymbol_0004_black_low_cloud.png"
    @SerializedName("status")
    @Expose
    var status: String? = "Thunder"

}
