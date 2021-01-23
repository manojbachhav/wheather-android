package com.example.wheatherforecast.view.home

import com.example.wheatherforecast.model.WheatherDataModel

interface WheatherHistoryListener {

    fun navigateToDetailsScreen(wheatherDataModel: WheatherDataModel)
    fun hideKeyboard()

}
