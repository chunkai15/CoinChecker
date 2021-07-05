package com.spiraldev.cryptoticker.ui.home.user

import android.R.attr.left
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.spiraldev.cryptoticker.FireBase.FirebaseUtils
import com.spiraldev.cryptoticker.FireBase.FirebaseUtils.firebaseAuth
import com.spiraldev.cryptoticker.R
import com.spiraldev.cryptoticker.ui.home.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class Login : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        if(FirebaseUtils.firebaseUser != null) {
            val intent = Intent(this, ProfileAuth::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        progressBar.visibility = View.GONE

        button.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            var email = editTextTextEmailAddress.editText?.text.toString()
            var password = editTextTextPassword.editText?.text.toString()
            if(email.trim().isNotEmpty() && password.trim().isNotEmpty()){
                login(email, password)
            }
        }

        button_intent_reg.setOnClickListener{
            val intent = Intent(this, register::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
        }
    }


    fun login(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this@Login) { task->
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                if(task.isSuccessful){
                    val mFA = SettingsFragment()
                    supportFragmentManager.beginTransaction().add(R.id.FrameLayout, mFA).commit()
                    overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                }else{
                    Toast.makeText(this, "Tài khoản không đúng", Toast.LENGTH_SHORT).show()
                }


            }
    }
}