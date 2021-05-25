package com.spiraldev.cryptoticker.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.spiraldev.cryptoticker.R
import com.spiraldev.cryptoticker.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_splashscreen.*


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val SPLASH_SCREEN: Long = 3000
        val PREFS_NAME = "MyPrefsFile"
        val settings = getSharedPreferences(PREFS_NAME, 0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        //Full man hinh
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val TopAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val Fadein = AnimationUtils.loadAnimation(this, R.anim.fadein_animation)


        logo_icon.startAnimation(TopAnim)
        tw_title.startAnimation(Fadein)

        Handler().postDelayed({
            if (settings.getBoolean("my_first_time", true)) { //check first-time users to your app
                val mIntent = Intent(this@SplashScreen, MyCustomAppIntro::class.java)
                startActivity(mIntent)
                finish()
                settings.edit().putBoolean("my_first_time", false).commit();
            }else{
                val mIntent = Intent(this@SplashScreen, HomeActivity::class.java)
                startActivity(mIntent)
                finish()
            }
        }, SPLASH_SCREEN)
    }
}