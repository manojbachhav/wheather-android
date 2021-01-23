package com.example.wheatherforecast.viewmodel

import android.content.Context
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.wheatherforecast.BR
import com.example.wheatherforecast.R

class HomeViewModel(context:Context):BaseObservable() {

    @Bindable
    var emptyDataInstruction:String? = context.getString(R.string.home_empty_data_message)
        set(value) {
            field = value
            notifyPropertyChanged(BR.emptyDataInstruction)
        }

    @Bindable
    var showEmptyDataLabel:Boolean? = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.showEmptyDataLabel)
        }

    init {

    }

}