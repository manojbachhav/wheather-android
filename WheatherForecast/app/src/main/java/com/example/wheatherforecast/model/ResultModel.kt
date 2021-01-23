package com.example.wheatherforecast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ResultModel : Serializable {

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("query")
    @Expose
    var query: String? = null

    @SerializedName("language")
    @Expose
    var language: String? = null

    @SerializedName("unit")
    @Expose
    var unit: String? = null


}
/*

{
    "type":"City",
    "query":"New York",
    "language":"en",
    "unit":"m"
},*/
