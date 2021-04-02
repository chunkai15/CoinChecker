package com.spiraldev.cryptoticker.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.spiraldev.cryptoticker.R
import com.spiraldev.cryptoticker.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_splashscreen.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val SPLASH_SCREEN: Long = 3000;
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        //Full man hinh
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val TopAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        val Fadein = AnimationUtils.loadAnimation(this,R.anim.fadein_animation)


        logo_icon.startAnimation(TopAnim)
        tw_title.startAnimation(Fadein)

        Handler().postDelayed({
            val mIntent = Intent(this@SplashScreen, HomeActivity::class.java)
            startActivity(mIntent)
            finish()
        }, SPLASH_SCREEN)
    }
}