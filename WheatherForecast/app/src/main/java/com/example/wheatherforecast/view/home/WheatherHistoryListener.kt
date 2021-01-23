package com.example.wheatherforecast.view.home

import com.example.wheatherforecast.model.home.PlaceResultModel

interface WheatherHistoryListener {

    fun navigateToDetailsScreen(placeResultModel: PlaceResultModel)
    fun hideKeyboard()
    fun showProgressbar()
    fun hideProgressbar()

}
