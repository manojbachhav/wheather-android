package com.example.wheatherforecast.view.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.wheatherforecast.R
import com.example.wheatherforecast.databinding.ActivityDetailsBinding
import com.example.wheatherforecast.viewmodel.DetailsViewModel

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var detailsViewModel = DetailsViewModel(this)
        var activityDetailsBinding: ActivityDetailsBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_details
        )
        activityDetailsBinding.detailsViewModel = detailsViewModel
    }
}
