package com.rainita.androidassignment.feature.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.rainita.androidassignment.R
import com.rainita.androidassignment.feature.forgotpassword.ForgotPasswordScreenActivity
import com.rainita.androidassignment.feature.home.HomeScreenActivity
import com.rainita.androidassignment.feature.registration.RegistrationScreenActivity

import kotlinx.android.synthetic.main.activity_auth_screen.*


class AuthScreenActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_screen)

        listeners()
    }

    private fun listeners() {
        btSignIn.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)
        btnSkip.setOnClickListener(this)
        tvForgotPassword.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            btSignIn -> {
                val error = checkFields()
                if (TextUtils.isEmpty(error)) {
                    HomeScreenActivity.startActivity(this)
                } else{
                    Toast.makeText(this,error,Toast.LENGTH_LONG).show()
                }
            }

            btnSignUp -> {
                RegistrationScreenActivity.startActivity(this)
            }

            btnSkip -> {
                HomeScreenActivity.startActivity(this)
            }

            tvForgotPassword -> {
                ForgotPasswordScreenActivity.startActivity(this)
            }
        }
    }

    private fun checkFields() = when {
        TextUtils.isEmpty(etEmail.text?.trim()) -> {
            etEmail.requestFocus()
            getString(R.string.error_email_address_or_mobile)
        }
        TextUtils.isEmpty(etPassword.text?.trim()) -> {
            etPassword.requestFocus()
            getString(R.string.error_password)
        }
        else -> null
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(
                Intent(
                    context,
                    AuthScreenActivity::class.java
                )
            )
        }
    }

}