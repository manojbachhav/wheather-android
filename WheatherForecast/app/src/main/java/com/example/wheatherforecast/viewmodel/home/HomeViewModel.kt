package com.example.wheatherforecast.viewmodel.home

import android.content.Context
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wheatherforecast.BR
import com.example.wheatherforecast.R
import com.example.wheatherforecast.model.WheatherResultModel
import com.example.wheatherforecast.view.home.WheatherHistoryAdapter
import com.example.wheatherforecast.view.home.WheatherHistoryAdapterListener

class HomeViewModel(
    var context: Context,
    var wheatherHistoryAdapterListener: WheatherHistoryAdapterListener
) : BaseObservable() {

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

    @Bindable
    val wheatherHistoryAdapter: WheatherHistoryAdapter
    var wheatherResultModelList: ArrayList<WheatherResultModel> = ArrayList()


    init {
        wheatherResultModelList.add(WheatherResultModel())
        wheatherResultModelList.add(WheatherResultModel())
        wheatherHistoryAdapter =
            WheatherHistoryAdapter(
                wheatherHistoryAdapterListener,
                wheatherResultModelList,
                context
            )
        showEmptyDataLabel = false
    }

    companion object {
        @JvmStatic
        @BindingAdapter("setAdapter")
        fun bindRecycleViewAdapter(
            recyclerView: RecyclerView,
            adapter: WheatherHistoryAdapter
        ) {
            recyclerView.adapter = adapter
        }
    }

    fun onSearchTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        searchText = s.trim().toString()!!
    }

    fun onSearchButtonClicked() {
        showMessageMessage(searchText!!)
        //TODO:Call Wheather Stack API and navigate to details screen on success
    }

    fun showMessageMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}