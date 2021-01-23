package com.example.wheatherforecast.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class CurrentModel : Serializable {

    @SerializedName("observation_time")
    @Expose
    var observation_time: String? = null

    @SerializedName("temperature")
    @Expose
    var temperature: Int? = null

    @SerializedName("weather_code")
    @Expose
    var weather_code: Int? = null

    @SerializedName("weather_icons")
    @Expose
    var weatherIcons: ArrayList<String>? = null

    @SerializedName("weather_descriptions")
    @Expose
    var weatherDescriptions: ArrayList<String>? = null

    @SerializedName("wind_speed")
    @Expose
    var windSpeed: Int? = null

    @SerializedName("wind_degree")
    @Expose
    var windDegree: Int? = null

    @SerializedName("wind_dir")
    @Expose
    var windDir: String? = null

    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null

    @SerializedName("precip")
    @Expose
    var precip: Int? = null

    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null

    @SerializedName("cloudcover")
    @Expose
    var cloudcover: Int? = null

    @SerializedName("feelslike")
    @Expose
    var feelslike: Int? = null

    @SerializedName("uv_index")
    @Expose
    var uvIndex: Int? = null

    @SerializedName("visibility")
    @Expose
    var visibility: Int? = null

    @SerializedName("is_day")
    @Expose
    var isDay: String? = null

}

/*

{
"observation_time":"08:05 AM",
"temperature":1,
"weather_code":122,
"weather_icons":[
"https:\/\/assets.weatherstack.com\/images\/wsymbols01_png_64\/wsymbol_0004_black_low_cloud.png"
],
"weather_descriptions":[
"Overcast"
],
"wind_speed":13,
"wind_degree":300,
"wind_dir":"WNW",
"pressure":1014,
"precip":0,
"humidity":40,
"cloudcover":100,
"feelslike":-4,
"uv_index":1,
"visibility":16,
"is_day":"no"
},*/
