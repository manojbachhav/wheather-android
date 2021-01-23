package com.example.wheatherforecast.view.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheatherforecast.R
import com.example.wheatherforecast.databinding.ActivityDetailsBinding
import com.example.wheatherforecast.model.home.WheatherDataModel
import com.example.wheatherforecast.utils.constants.AppConstant
import com.example.wheatherforecast.viewmodel.details.DetailsViewModel

class DetailsActivity : AppCompatActivity() {

    var activityDetailsBinding: ActivityDetailsBinding? = null
    var model: WheatherDataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailsBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_details
        )

        getIntentData()
        var detailsViewModel =
            DetailsViewModel(this, model!!)
        activityDetailsBinding!!.viewModel = detailsViewModel
        initUi()
    }


    fun initUi() {
        val layoutManager =
            LinearLayoutManager(this)
        activityDetailsBinding!!.recycleViewWeekDaysHistory.layoutManager = layoutManager
    }

    private fun getIntentData() {
        model =
            intent.getSerializableExtra(AppConstant.WHEATHER_DATA_MODEL) as WheatherDataModel
    }
}
