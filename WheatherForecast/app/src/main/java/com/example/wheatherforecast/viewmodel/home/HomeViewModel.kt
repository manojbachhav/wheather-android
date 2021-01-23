package com.example.wheatherforecast.viewmodel.home

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wheatherforecast.BR
import com.example.wheatherforecast.R
import com.example.wheatherforecast.model.WheatherDataModel
import com.example.wheatherforecast.model.WheatherResultModel
import com.example.wheatherforecast.model.error.ErrorResponseModel
import com.example.wheatherforecast.network.proxy.wheather.WheatherApiProxy
import com.example.wheatherforecast.network.utils.DataCallbackHelper
import com.example.wheatherforecast.view.home.WheatherHistoryAdapter
import com.example.wheatherforecast.view.home.WheatherHistoryListener


class HomeViewModel(
    var context: Context,
    var wheatherHistoryListener: WheatherHistoryListener
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

    @Bindable
    val wheatherHistoryAdapter: WheatherHistoryAdapter
    var wheatherDataModelList: ArrayList<WheatherDataModel> = ArrayList()


    init {
        wheatherHistoryAdapter =
            WheatherHistoryAdapter(
                wheatherHistoryListener,
                wheatherDataModelList,
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
        wheatherHistoryListener.hideKeyboard()
        if(searchText!!.trim().length>0){
            checkInternetAndCallSearchAPI(searchText!!.trim())
        }else{
            showMessage(context.getString(R.string.search_hint))
        }
    }

    fun checkInternetAndCallSearchAPI(searchString: String) {
        if (isNetworkConnected()) {
            callSearchApi(searchString!!)
            searchText = ""
            notifyPropertyChanged(BR.searchText)
        } else {
            showMessage(context.getString(R.string.no_internet_connection))
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected

    }

    private fun callSearchApi(searchString: String) {
        val wheatherApiProxy = WheatherApiProxy.getInstance()
        wheatherApiProxy.searchCity(
            context,
            object : DataCallbackHelper<WheatherResultModel> {
                override fun onSuccess(response: WheatherResultModel?) {
                    if (response!!.success != null && !response!!.success!!) {
                        var errorResponseModel = mapErrorModelToErrorResponseModel(response)
                        showMessage(errorResponseModel!!.error!!.info!!)
                    } else {
                        var wheatherDataModel = mapResultModelToUiResultModel(response)

                        wheatherHistoryAdapter.addData(wheatherDataModel)
                    }
                }

                override fun onError(errorResponseModel: ErrorResponseModel?) {
                    showMessage(errorResponseModel!!.error!!.info!!)
                }
            }, searchString
        )
    }

    private fun mapErrorModelToErrorResponseModel(response: WheatherResultModel): ErrorResponseModel {
        var errorResponseModel: ErrorResponseModel = ErrorResponseModel()
        errorResponseModel.success = response.success
        errorResponseModel.error = response.error
        return errorResponseModel
    }

    private fun mapResultModelToUiResultModel(response: WheatherResultModel?): WheatherDataModel {
        var wheatherDataModel: WheatherDataModel = WheatherDataModel()
        wheatherDataModel.wheatherResultModel = response
        wheatherDataModel.name = response!!.location!!.name
        wheatherDataModel.country = response!!.location!!.country
        wheatherDataModel.time = response!!.location!!.localtimeEpoch
        wheatherDataModel.temp = response!!.current!!.temperature
        wheatherDataModel.image = response!!.current!!.weatherIcons!!.get(0)
        wheatherDataModel.status = response!!.current!!.weatherDescriptions!!.get(0)
        return wheatherDataModel
    }

    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}