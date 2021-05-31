package com.spiraldev.cryptoticker.ui.home.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.spiraldev.cryptoticker.R
import com.spiraldev.cryptoticker.ui.home.HomeActivity
import com.spiraldev.cryptoticker.ui.home.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_login.*

private val auth = FirebaseAuth.getInstance()

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        progressBar.visibility = View.VISIBLE

        button.setOnClickListener {
            var email = editTextTextEmailAddress.text.toString()
            var password = editTextTextPassword.text.toString()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@Login) { task->
                    progressBar.visibility = View.GONE
                    if(task.isSuccessful){
                        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Tài khoản không đúng", Toast.LENGTH_SHORT).show()
                    }


                }
        }
    }

    fun reg(email: String,password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this@Login) { task->
                progressBar.visibility = View.GONE
                if(task.isSuccessful){
                    Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Tài khoản không đúng", Toast.LENGTH_SHORT).show()
                }


            }
    }

    fun login(email: String,password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this@Login) { task->
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                if(task.isSuccessful){
                    val intent = Intent(baseContext, SettingsFragment::class.java)
                    val b = Bundle()
                    b.putString("Email", email)
                    intent.putExtra("data",b)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "Tài khoản không đúng", Toast.LENGTH_SHORT).show()
                }


            }
    }
}