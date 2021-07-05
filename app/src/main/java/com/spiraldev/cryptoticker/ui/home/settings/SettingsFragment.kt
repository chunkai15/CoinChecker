package com.spiraldev.cryptoticker.ui.home.settings

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.spiraldev.cryptoticker.FireBase.FirebaseUtils
import com.spiraldev.cryptoticker.FireBase.FirebaseUtils.firebaseAuth
import com.spiraldev.cryptoticker.FireBase.FirebaseUtils.firebaseUser
import com.spiraldev.cryptoticker.R
import com.spiraldev.cryptoticker.core.common.MainNavigationFragment
import com.spiraldev.cryptoticker.databinding.FragmentSettingsBinding
import com.spiraldev.cryptoticker.ui.home.HomeActivity
import com.spiraldev.cryptoticker.ui.home.user.Login
import com.spiraldev.cryptoticker.ui.home.user.ProfileAuth
import com.spiraldev.cryptoticker.ui.home.user.register
import com.spiraldev.cryptoticker.util.ImageLoader
import com.spiraldev.cryptoticker.util.ThemeHelper
import com.spiraldev.cryptoticker.util.ThemeMode
import com.spiraldev.cryptoticker.util.extensions.doOnChange
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_profile_auth.*
import kotlinx.android.synthetic.main.fragment_settings.*
import timber.log.Timber


@AndroidEntryPoint
class SettingsFragment : MainNavigationFragment() {

    private val viewModel: SettingsViewModel by viewModels()
    private lateinit var binding: FragmentSettingsBinding


    override fun onBackPressed() {
        TODO("Not yet implemented")
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = this@SettingsFragment.viewModel
            }
        observeViewModel()
        return binding.root
    }

    fun updateUIauthON(){
        ProfileUser.visibility = View.VISIBLE
        firebaseUser?.let {
            usernameTextView.text = firebaseUser.email
            textDesUsername.text = "Hồ sơ cá nhân"
            ImageLoader.loadImage(profileCircleImageView, FirebaseUtils.firebaseUser?.photoUrl.toString() ?: "")
        }
    }

    fun updateUIauthOff(){
        ProfileUser.visibility = View.GONE
        firebaseUser?.let {
            usernameTextView.text = "Tài Khoản"
            textDesUsername.text = "Đăng ký hoặc đăng nhập"
        }
    }

    fun check_auth_user(){
        firebaseUser?.reload()
        if(firebaseUser != null) {
            updateUIauthON()
            profile_ui.setOnClickListener {
                activity?.let{
                    val intent = Intent (it, ProfileAuth::class.java)
                    it.startActivity(intent)
                }
                getActivity()?.overridePendingTransition(R.anim.slide_in_from_left,
                    R.anim.slide_out_to_right);
            }

            buttonLogout.setOnClickListener {
                firebaseAuth.signOut()
                updateUIauthOff()
            }

        }else {
            ProfileUser.visibility = View.GONE
            profile_ui.setOnClickListener {
                activity?.let{
                    val intent = Intent (it, Login::class.java)
                    it.startActivity(intent)
                }
                getActivity()?.overridePendingTransition(R.anim.slide_in_from_left,
                    R.anim.slide_out_to_right);
            }
        }
    }

    override fun onStart() {
        super.onStart()
        check_auth_user()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()

    }



    override fun initializeViews() {

    }

    override fun observeViewModel() {
        viewModel.isDarkMode.doOnChange(this) {
            Timber.d("On Theme changed")
            ThemeHelper.applyTheme(if (it) ThemeMode.Dark else ThemeMode.Light)
        }
    }
}