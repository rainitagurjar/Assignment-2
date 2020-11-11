package com.rainita.androidassignment.feature.forgotpassword

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.rainita.androidassignment.R
import com.rainita.androidassignment.feature.home.HomeScreenActivity
import com.rainita.androidassignment.feature.login.AuthScreenActivity
import com.rainita.androidassignment.utils.CommonUtils
import kotlinx.android.synthetic.main.activity_forgot_password_screen.*
import kotlinx.android.synthetic.main.activity_forgot_password_screen.etEmail
import kotlinx.android.synthetic.main.activity_registration_screen.*

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
                val error = checkFields()
                if (TextUtils.isEmpty(error)) {
                    Toast.makeText(this,getString(R.string.msg_link_sent),Toast.LENGTH_LONG).show()
                    finish()
                } else{
                    Toast.makeText(this,error, Toast.LENGTH_LONG).show()
                }



            }
        }
    }

    private fun checkFields() = when {
        TextUtils.isEmpty(etEmail.text?.trim()) -> {
            etEmail.requestFocus()
            getString(R.string.error_email_address)
        }
        !CommonUtils.isValidEmail(etEmail.text?.trim().toString()) -> {
            etEmail.requestFocus()
            getString(R.string.error_invalid_email_address)
        }
        else -> null
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