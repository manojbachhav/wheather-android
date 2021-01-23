package com.example.wheatherforecast.view.home

import com.example.wheatherforecast.model.home.WheatherDataModel

interface WheatherHistoryListener {

    fun navigateToDetailsScreen(wheatherDataModel: WheatherDataModel)
    fun hideKeyboard()
    fun showProgressbar()
    fun hideProgressbar()

}
