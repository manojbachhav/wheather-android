package com.example.wheatherforecast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class LocationModel : Serializable {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("region")
    @Expose
    var region: String? = null

    @SerializedName("lat")
    @Expose
    var latitude: Double? = null

    @SerializedName("lon")
    @Expose
    var longitude: Double? = null

    @SerializedName("timezone_id")
    @Expose
    var timezoneId: String? = null

    @SerializedName("localtime")
    @Expose
    var localTime: String? = null

    @SerializedName("localtime_epoch")
    @Expose
    var localtimeEpoch: Long? = null

    @SerializedName("utc_offset")
    @Expose
    var utc_offset: String? = null


}/*
{
    "name":"New York",
    "country":"United States of America",
    "region":"New York",
    "lat":"40.714",
    "lon":"-74.006",
    "timezone_id":"America\/New_York",
    "localtime":"2021-01-23 03:05",
    "localtime_epoch":1611371100,
    "utc_offset":"-5.0"
},*/
