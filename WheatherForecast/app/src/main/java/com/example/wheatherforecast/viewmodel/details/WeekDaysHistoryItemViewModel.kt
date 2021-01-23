package com.example.wheatherforecast.viewmodel.details

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.wheatherforecast.BR
import com.example.wheatherforecast.R
import com.example.wheatherforecast.model.details.WheatherDaysDataModel
import com.example.wheatherforecast.utils.constants.AppConstant


class WeekDaysHistoryItemViewModel(
    var context: Context,
    var dayItemModel: WheatherDaysDataModel
) :
    BaseObservable() {

    @Bindable
    var day = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.day)
        }

    @Bindable
    var temp = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.temp)
        }

    @Bindable
    var image = AppConstant.EMPTY_IMAGE_URL
        set(value) {
            field = value
            notifyPropertyChanged(BR.image)
        }

    init {
        setData()
    }

    private fun setData() {
        day = dayItemModel.day!!
        temp = dayItemModel.temp!!.toString() + context.getString(R.string.degree_symbol)
        image = dayItemModel.image!!
    }

    companion object {

        @BindingAdapter("statusImage")
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