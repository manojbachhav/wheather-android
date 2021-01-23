package com.example.wheatherforecast.view.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheatherforecast.R
import com.example.wheatherforecast.databinding.ActivityHomeBinding
import com.example.wheatherforecast.model.WheatherResultModel
import com.example.wheatherforecast.utils.AppConstant
import com.example.wheatherforecast.view.details.DetailsActivity
import com.example.wheatherforecast.viewmodel.home.HomeViewModel


class HomeActivity : AppCompatActivity(), WheatherHistoryAdapterListener {

    var activityHomeBinding: ActivityHomeBinding? = null
    var homeViewModel: HomeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = HomeViewModel(this, this)
        activityHomeBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_home
        )
        activityHomeBinding!!.homeViewModel = homeViewModel
        initUi()
    }

    fun initUi() {
        val layoutManager =
            LinearLayoutManager(this)
        activityHomeBinding!!.recycleViewWheatherHistory.layoutManager = layoutManager
    }

    override fun navigateToDetailsScreen(wheatherResultModel: WheatherResultModel) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(AppConstant.WHEATHER_RESULT_MODEL, wheatherResultModel)
        startActivity(intent)
    }
}
