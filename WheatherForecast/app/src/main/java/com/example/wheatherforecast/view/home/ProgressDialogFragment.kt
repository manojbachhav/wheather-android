package com.example.wheatherforecast.view.home


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.wheatherforecast.R


class ProgressDialogFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.progress_dialog, container, false)

        return view
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }


    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window
                ?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    companion object {
        private const val FRAGMENT_TAG = "ProgressDialogFragment"
        fun showProgressDialog(activity: AppCompatActivity?) {
            if (activity == null) {
                return
            }
            var fragment =
                activity.supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) as ProgressDialogFragment?
            if (fragment == null) {
                fragment = ProgressDialogFragment()
                fragment.isCancelable = false
                activity.supportFragmentManager.beginTransaction()
                    .add(fragment, FRAGMENT_TAG)
                    .commitAllowingStateLoss()
            }
        }

        fun hideProgressDialog(activity: AppCompatActivity?) {
            if (activity == null) {
                return
            }
            val fragment =
                activity.supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) as ProgressDialogFragment?
            if (fragment != null) {
                activity.supportFragmentManager.beginTransaction().remove(fragment)
                    .commitAllowingStateLoss()
            }
        }
    }
}
