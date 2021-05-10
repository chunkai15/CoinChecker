package com.spiraldev.cryptoticker.ui.home.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.spiraldev.cryptoticker.R
import com.spiraldev.cryptoticker.core.common.MainNavigationFragment
import com.spiraldev.cryptoticker.databinding.ActivityNewsCoinBinding
import com.spiraldev.cryptoticker.ui.home.settings.SettingsViewModel

class NewsCoin : MainNavigationFragment() {

    //private val viewModel: NewsCoinViewModel by viewModels()
    private lateinit var binding: ActivityNewsCoinBinding
    override fun onBackPressed() {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityNewsCoinBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                //viewModel = this@NewsCoin.viewModel
                webview.webViewClient = WebViewClient()
                webview.loadUrl("https://blogtienao.com/tin-tuc/")
                val webSettings = webview.settings
                webSettings.javaScriptEnabled = true
            }
        return binding.root
    }

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    override fun observeViewModel() {
        TODO("Not yet implemented")
    }
}