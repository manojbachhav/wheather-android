package com.example.wheatherforecast.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.wheatherforecast.R
import com.example.wheatherforecast.databinding.ActivityHomeBinding
import com.example.wheatherforecast.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var homeViewModel = HomeViewModel(this)
        var activityHomeBinding: ActivityHomeBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_home
        )
        activityHomeBinding.homeViewModel = homeViewModel
    }
}
