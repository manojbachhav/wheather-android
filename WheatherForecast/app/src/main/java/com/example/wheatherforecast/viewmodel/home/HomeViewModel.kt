package com.example.wheatherforecast.viewmodel.home

import android.R.id
import android.content.Context
import android.graphics.PorterDuff
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wheatherforecast.BR
import com.example.wheatherforecast.R
import com.example.wheatherforecast.database.AppDatabase
import com.example.wheatherforecast.database.PlaceResultModelDao
import com.example.wheatherforecast.model.error.ErrorResponseModel
import com.example.wheatherforecast.model.home.PlaceResultModel
import com.example.wheatherforecast.model.home.WheatherResultModel
import com.example.wheatherforecast.network.proxy.wheather.WheatherApiProxy
import com.example.wheatherforecast.network.utils.DataCallbackHelper
import com.example.wheatherforecast.view.home.WheatherHistoryAdapter
import com.example.wheatherforecast.view.home.WheatherHistoryListener
import java.util.*
import kotlin.collections.ArrayList


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
    var placeResultModelList: ArrayList<PlaceResultModel> = ArrayList()

    var appDatabase: AppDatabase? = null
    var placeResultModelDao: PlaceResultModelDao? = null

    init {
        appDatabase = AppDatabase.getDatabase(context)
        placeResultModelDao = appDatabase!!.placeResultModelDao()
        wheatherHistoryAdapter =
            WheatherHistoryAdapter(
                wheatherHistoryListener,
                placeResultModelList,
                context
            )
        showEmptyDataLabel = false
    }

    fun loadData() {
        placeResultModelList.clear()
        GetSearchHistoryAsyncTask(this).execute()
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
        if (searchText!!.trim().length > 0) {
            wheatherHistoryListener.showProgressbar()
            checkInternetAndCallSearchAPI(searchText!!.trim())
        } else {
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
                    wheatherHistoryListener.hideProgressbar()
                    if (response!!.success != null && !response!!.success!!) {
                        var errorResponseModel = mapErrorModelToErrorResponseModel(response)
                        showMessage(errorResponseModel!!.error!!.info!!)
                    } else {
                        var placeResultModel = mapResultModelToUiResultModel(response)
                        InsertSearchResultToDbAsyncTask(placeResultModelDao!!).execute(
                            placeResultModel
                        )
                        wheatherHistoryListener.navigateToDetailsScreen(placeResultModel)
                    }
                }

                override fun onError(errorResponseModel: ErrorResponseModel?) {
                    wheatherHistoryListener.hideProgressbar()
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

    private fun mapResultModelToUiResultModel(response: WheatherResultModel?): PlaceResultModel {
        var placeResultModel: PlaceResultModel =
            PlaceResultModel(
                Calendar.getInstance().timeInMillis,
                response!!.location!!.name,
                response.location!!.country,
                response.location!!.localtimeEpoch,
                response.current!!.temperature,
                response.current!!.weatherIcons!!.get(0),
                response.current!!.weatherDescriptions!!.get(0)
            )

        return placeResultModel
    }

    fun showMessage(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        val view: View = toast.view
        view.getBackground()
            .setColorFilter(context.getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN)
        val text: TextView = view.findViewById(id.message)
        text.setTextColor(context.getColor(R.color.backgroundColorWhite))
        text.gravity = Gravity.CENTER
        toast.show()
    }

    internal class InsertSearchResultToDbAsyncTask(var placeResultModelDao: PlaceResultModelDao) :
        AsyncTask<PlaceResultModel?, Int?, String>() {
        override fun doInBackground(vararg params: PlaceResultModel?): String {
            placeResultModelDao.insert(params.get(0)!!)
            return ""
        }

    }

    internal class GetSearchHistoryAsyncTask(
        viewmodel: HomeViewModel
    ) : AsyncTask<Void?, Int?, List<PlaceResultModel>>() {

        private var homeViewmodel: HomeViewModel? = null

        init {
            this.homeViewmodel = viewmodel!!
        }

        override fun doInBackground(vararg params: Void?): List<PlaceResultModel> {
            return homeViewmodel!!.placeResultModelDao!!.getAll()
        }

        override fun onPostExecute(result: List<PlaceResultModel>?) {
            super.onPostExecute(result)
            homeViewmodel!!.placeResultModelList.addAll(result!!)
            homeViewmodel!!.wheatherHistoryAdapter.updateData(homeViewmodel!!.placeResultModelList)
        }

    }
}