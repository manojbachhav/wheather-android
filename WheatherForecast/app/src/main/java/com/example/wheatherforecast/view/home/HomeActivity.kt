package com.example.wheatherforecast.view.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheatherforecast.R
import com.example.wheatherforecast.databinding.ActivityHomeBinding
import com.example.wheatherforecast.model.home.WheatherDataModel
import com.example.wheatherforecast.utils.constants.AppConstant
import com.example.wheatherforecast.view.details.DetailsActivity
import com.example.wheatherforecast.viewmodel.home.HomeViewModel


class HomeActivity : AppCompatActivity(), WheatherHistoryListener {

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

    override fun navigateToDetailsScreen(wheatherDataModel: WheatherDataModel) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(AppConstant.WHEATHER_DATA_MODEL, wheatherDataModel)
        startActivity(intent)
    }

    override fun hideKeyboard() {
        val inputManager: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (currentFocus != null) {
            inputManager.hideSoftInputFromWindow(
                currentFocus!!
                    .windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    override fun showProgressbar() {
        ProgressDialogFragment.showProgressDialog(this)
    }

    override fun hideProgressbar() {
        ProgressDialogFragment.hideProgressDialog(this)
    }

}
