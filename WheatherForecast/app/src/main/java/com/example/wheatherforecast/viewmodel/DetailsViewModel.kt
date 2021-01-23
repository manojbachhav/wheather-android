package com.example.wheatherforecast.viewmodel

import android.content.Context
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.wheatherforecast.BR
import com.example.wheatherforecast.R

class DetailsViewModel(context: Context) : BaseObservable() {

    @Bindable
    var emptyDetailsDataInstruction: String? =
        context.getString(R.string.details_empty_data_message)
        set(value) {
            field = value
            notifyPropertyChanged(BR.emptyDetailsDataInstruction)
        }

    @Bindable
    var showDetailsEmptyDataLabel: Boolean? = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.showDetailsEmptyDataLabel)
        }

    init {

    }

}