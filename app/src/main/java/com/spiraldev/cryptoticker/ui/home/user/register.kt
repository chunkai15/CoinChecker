package com.spiraldev.cryptoticker.ui.home.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.spiraldev.cryptoticker.FireBase.FirebaseUtils.firebaseAuth
import com.spiraldev.cryptoticker.FireBase.FirebaseUtils.firebaseUser
import com.spiraldev.cryptoticker.R
import com.spiraldev.cryptoticker.ui.home.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.editTextTextEmailAddress
import kotlinx.android.synthetic.main.activity_login.editTextTextPassword
import kotlinx.android.synthetic.main.activity_login.progressBar
import kotlinx.android.synthetic.main.activity_register.*


class register : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        progressBar.visibility = View.GONE

        button_reg.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            var email = editTextTextEmailAddress.editText?.text.toString()
            var password = editTextTextPassword.editText?.text.toString()
            var Repassword = editTextTextRePassword.editText?.text.toString()
            if (password == Repassword) {
                reg(email, password)
            }else{
                Toast.makeText(this, "Mật khẩu nhập lại không trùng!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun reg(email: String,password: String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this@register) { task->
                progressBar.visibility = View.GONE
                if(task.isSuccessful){
                    Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                    overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                }else{
                    Toast.makeText(this, "Vui lòng nhập đúng định dạng!", Toast.LENGTH_SHORT).show()
                }


            }
    }
}