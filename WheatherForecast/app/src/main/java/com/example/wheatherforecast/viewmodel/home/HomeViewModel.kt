package com.example.wheatherforecast.viewmodel.home

import android.content.Context
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.wheatherforecast.BR
import com.example.wheatherforecast.R

class HomeViewModel(var context: Context) : BaseObservable() {

    @Bindable
    var emptyDataInstruction: String? = context.getString(R.string.home_empty_data_message)
        set(value) {
            field = value
            notifyPropertyChanged(BR.emptyDataInstruction)
        }

    @Bindable
    var showEmptyDataLabel: Boolean? = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.showEmptyDataLabel)
        }
    @Bindable
    var searchText: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.searchText)
        }

    init {
        // Empty Imlementation
    }


    fun onSearchTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        searchText = s.trim().toString()!!
    }

    fun onCancelButtonClicked() {
        showMessageMessage(searchText!!)
        //TODO:Call Wheather Stack API and navigate to details screen on success
    }

    fun showMessageMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}