package com.example.wheatherforecast.viewmodel.home

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.wheatherforecast.BR
import com.example.wheatherforecast.R
import com.example.wheatherforecast.model.home.WheatherDataModel
import com.example.wheatherforecast.utils.constants.AppConstant
import com.example.wheatherforecast.utils.uiutils.DateUtils
import com.example.wheatherforecast.view.home.WheatherHistoryListener


class WheatherHistoryItemViewModel(
    var context: Context,
    private var wheatherHistoryListener: WheatherHistoryListener,
    var historyItemModel: WheatherDataModel
) :
    BaseObservable() {


    @Bindable
    var cityName = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.cityName)
        }

    @Bindable
    var time = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.time)
        }


    @Bindable
    var country = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.country)
        }

    @Bindable
    var wheatherStatus = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.wheatherStatus)
        }

    @Bindable
    var temprature = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.temprature)
        }
    @Bindable
    var wheatherStatusImageUrl = AppConstant.EMPTY_IMAGE_URL
        set(value) {
            field = value
            notifyPropertyChanged(BR.wheatherStatusImageUrl)
        }

    init {

        setData()

    }

    private fun setData() {
        cityName = historyItemModel.name!!
        country = historyItemModel.country!!
        wheatherStatus = historyItemModel.status!!
        temprature = historyItemModel.temp!!.toString() + context.getString(R.string.degree_symbol)
        wheatherStatusImageUrl = historyItemModel.image!!
        setTimeData()
    }

    private fun setTimeData() {
        var timeString = DateUtils.stringFrom(
            historyItemModel.time!! * 1000,
            DateUtils.TIME_FORMAT_ABBREVIATION
        )
        time = "On " +
                DateUtils.getFormattedDateTime(historyItemModel.time!! * 1000)!! + " at " + timeString
    }

    fun onItemClick() {
        wheatherHistoryListener.navigateToDetailsScreen(historyItemModel)
    }


    companion object {

        @BindingAdapter("wheatherStatusImage")
        @JvmStatic
        fun loadImage(view: AppCompatImageView, url: String?) {
            if (!url.equals(AppConstant.EMPTY_IMAGE_URL, true)) {
                Glide
                    .with(view.context)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.color.colorPrimaryDark)
                    .into(view);
            } else {
                view.setImageDrawable(view.context.getDrawable(R.color.colorPrimaryDark))
            }
        }

    }

}