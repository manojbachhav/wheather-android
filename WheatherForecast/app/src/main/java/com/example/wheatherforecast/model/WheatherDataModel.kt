package com.example.wheatherforecast.model

import java.io.Serializable


class WheatherDataModel : Serializable {
    var wheatherResultModel: WheatherResultModel? = null
    var name: String? = null
    var country: String? = null
    var time: Long? = null
    var temp: Int? = null
    var image: String? = null
    var status: String? = null
}
