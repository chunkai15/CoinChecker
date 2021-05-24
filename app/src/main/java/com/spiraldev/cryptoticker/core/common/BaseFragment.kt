package com.spiraldev.cryptoticker.core.common

import android.widget.Toast
import androidx.fragment.app.Fragment
import es.dmoral.toasty.Toasty


interface InitViews {
    fun initializeViews()
    fun observeViewModel()
}

abstract class BaseFragment : Fragment(), InitViews {

    fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        context?.let {
            Toasty.success(
                it,
                message,
                duration
            ).show()
        }
    }
}