package com.example.wheatherforecast.view.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.wheatherforecast.R
import com.example.wheatherforecast.databinding.ActivityDetailsBinding
import com.example.wheatherforecast.model.WheatherDataModel
import com.example.wheatherforecast.utils.constants.AppConstant
import com.example.wheatherforecast.viewmodel.details.DetailsViewModel

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var detailsViewModel =
            DetailsViewModel(this)
        var activityDetailsBinding: ActivityDetailsBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_details
        )
        var model: WheatherDataModel =
            intent.getSerializableExtra(AppConstant.WHEATHER_DATA_MODEL) as WheatherDataModel
        if (model != null) {
            Toast.makeText(this, "Data Received", Toast.LENGTH_SHORT).show()
        }
        activityDetailsBinding.detailsViewModel = detailsViewModel
    }
}
