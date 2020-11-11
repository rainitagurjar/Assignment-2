package com.rainita.androidassignment.feature.forgotpassword

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.rainita.androidassignment.R
import com.rainita.androidassignment.feature.login.AuthScreenActivity
import kotlinx.android.synthetic.main.activity_forgot_password_screen.*

class ForgotPasswordScreenActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_screen)

        listener()
    }

    private fun listener(){
        btnSend.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0){
            btnSend -> {
                Toast.makeText(this,getString(R.string.msg_link_sent),Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    companion object{
        fun startActivity(context : Context){
            context.startActivity(
                Intent(context,
                    ForgotPasswordScreenActivity::class.java)
            )
        }
    }
}