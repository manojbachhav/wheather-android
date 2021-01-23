package com.example.wheatherforecast.viewmodel.details

import android.content.Context
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wheatherforecast.BR
import com.example.wheatherforecast.R
import com.example.wheatherforecast.model.details.WheatherDaysDataModel
import com.example.wheatherforecast.model.home.PlaceResultModel
import com.example.wheatherforecast.utils.constants.AppConstant
import com.example.wheatherforecast.utils.uiutils.DateUtils
import com.example.wheatherforecast.view.details.WeekDaysHistoryAdapter
import com.example.wheatherforecast.view.home.WheatherHistoryAdapter
import java.util.*
import kotlin.collections.ArrayList


class DetailsViewModel(
    context: Context,
    var placeResultModel: PlaceResultModel
) : BaseObservable() {

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


    @Bindable
    val weekDaysHistoryAdapter: WeekDaysHistoryAdapter


    var daysListModel: ArrayList<WheatherDaysDataModel> = ArrayList()

    init {
        showDetailsEmptyDataLabel = placeResultModel == null
        cityName = placeResultModel.name!!
        country = placeResultModel.country!!
        wheatherStatus = placeResultModel.status!!
        temprature =
            placeResultModel.temp!!.toString() + context.getString(R.string.degree_symbol) + " " + placeResultModel.status!!
        wheatherStatusImageUrl = placeResultModel.image!!
        setTimeData()

        createCurrentAndDummyDataForWeek()

        weekDaysHistoryAdapter =
            WeekDaysHistoryAdapter(
                daysListModel,
                context
            )
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

    private fun setTimeData() {
        var timeString = DateUtils.stringFrom(
            placeResultModel.time!! * 1000,
            DateUtils.TIME_FORMAT_ABBREVIATION
        )
        time = "On " +
                DateUtils.getFormattedDateTime(placeResultModel.time!! * 1000)!! + " at " + timeString
    }

    private fun createCurrentAndDummyDataForWeek() {
        var dayNumberOfWeek = getdayOfWeek(placeResultModel.time!!)
        var wheatherDaysDataModel: WheatherDaysDataModel = WheatherDaysDataModel()
        wheatherDaysDataModel.day = getDayByIndex(dayNumberOfWeek!!)
        wheatherDaysDataModel.temp = placeResultModel.temp
        wheatherDaysDataModel.image = placeResultModel.image
        daysListModel.add(wheatherDaysDataModel)
        var minusDay = 0
        if (dayNumberOfWeek == 7) {
            dayNumberOfWeek = 1
            minusDay = 1
        } else {
            dayNumberOfWeek++
        }
        addDummyData(dayNumberOfWeek, minusDay)
    }

    private fun addDummyData(dayNumberOfWeek: Int, minusDay: Int) {
        for (i in dayNumberOfWeek..7 - minusDay) {
            var wheatherDaysDataModel: WheatherDaysDataModel = WheatherDaysDataModel()
            wheatherDaysDataModel.day = getDayByIndex(i)
            wheatherDaysDataModel.temp = getTemp()
            wheatherDaysDataModel.image = getImage(wheatherDaysDataModel.temp!!)
            daysListModel.add(wheatherDaysDataModel)
            Log.v(AppConstant.TAG_WHEATHER_FORECAST, wheatherDaysDataModel.day)
        }

        for (i in 1..dayNumberOfWeek - 1) {
            var wheatherDaysDataModel: WheatherDaysDataModel = WheatherDaysDataModel()
            wheatherDaysDataModel.day = getDayByIndex(i)
            wheatherDaysDataModel.temp = getTemp()
            wheatherDaysDataModel.image = getImage(wheatherDaysDataModel.temp!!)
            daysListModel.add(wheatherDaysDataModel)
            Log.v(AppConstant.TAG_WHEATHER_FORECAST, wheatherDaysDataModel.day)

        }
    }

    private fun getImage(temp: Int): String? {
        if (temp < 10) {
            return "https://assets.weatherstack.com/images/wsymbols01_png_64/wsymbol_0004_black_low_cloud.png"
        } else if (temp < 20) {
            return "https://assets.weatherstack.com/images/wsymbols01_png_64/wsymbol_0002_sunny_intervals.png"
        } else if (temp < 33) {
            return "https://assets.weatherstack.com/images/wsymbols01_png_64/wsymbol_0004_black_low_cloud.png"
        }
        return "https://assets.weatherstack.com/images/wsymbols01_png_64/wsymbol_0002_sunny_intervals.png"

    }

    fun getTemp(): Int {
        return (0..32).random()

    }

    fun getdayOfWeek(epochinSecond: Long): Int? {
        val cal: Calendar = Calendar.getInstance()
        cal.setTimeInMillis(epochinSecond * 1000L)
        cal.setTimeZone(TimeZone.getTimeZone("UTC"))
        val i: Int = cal.get(Calendar.DAY_OF_WEEK)

        return i
    }

    fun getDayByIndex(i: Int): String {
        return when (i) {
            1 -> "Sunday"
            2 -> "Monday"
            3 -> "Tuesady"
            4 -> "Wednesday"
            5 -> "Thursday"
            6 -> "Friday"
            7 -> "Saturday"
            else -> ""
        }
    }

}