package com.example.wheatherforecast.view.home

import com.example.wheatherforecast.model.WheatherResultModel

interface WheatherHistoryAdapterListener {

    fun navigateToDetailsScreen(wheatherResultModel: WheatherResultModel)

}
