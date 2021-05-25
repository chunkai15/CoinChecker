package com.spiraldev.cryptoticker.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType
import com.spiraldev.cryptoticker.R
import com.spiraldev.cryptoticker.ui.home.HomeActivity

class MyCustomAppIntro : AppIntro2() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransformer(AppIntroPageTransformerType.Fade)
        isColorTransitionsEnabled = true
        setImmersiveMode()

        setTransformer(AppIntroPageTransformerType.Parallax(
            titleParallaxFactor = 1.0,
            imageParallaxFactor = -1.0,
            descriptionParallaxFactor = 2.0
        ))

        // Make sure you don't call setContentView!

        // Call addSlide passing your Fragments.
        // You can use AppIntroFragment to use a pre-built fragment
        addSlide(AppIntroFragment.newInstance(
            description = "Ứng dụng theo dõi đồng tiền điện tử của bạn nhanh chóng!",
            imageDrawable = R.drawable.item_onb_1,
            titleColor = Color.WHITE,
            descriptionColor = Color.WHITE,
            backgroundColor = Color.BLACK
            ))
        addSlide(AppIntroFragment.newInstance(
            description = "Tích hợp biểu đồ giá và nhiều chức năng khác. Giúp bạn có thể phân tích thị trường! ",
            imageDrawable = R.drawable.item_onb_2,
            titleColor = Color.WHITE,
            descriptionColor = Color.WHITE,
            backgroundColor = Color.BLACK
        ))

    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        val mIntent = Intent(this@MyCustomAppIntro, HomeActivity::class.java)
        startActivity(mIntent)
        // Decide what to do when the user clicks on "Skip"
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        val mIntent = Intent(this@MyCustomAppIntro, HomeActivity::class.java)
        startActivity(mIntent)
        finish()
    }
}
