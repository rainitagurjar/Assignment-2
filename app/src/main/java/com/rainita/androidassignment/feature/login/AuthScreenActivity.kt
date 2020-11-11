package com.rainita.androidassignment.feature.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rainita.androidassignment.R
import com.rainita.androidassignment.feature.home.HomeScreenActivity
import com.rainita.androidassignment.feature.registration.RegistrationScreenActivity

import kotlinx.android.synthetic.main.activity_auth_screen.*


class AuthScreenActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_screen)

        listeners()
    }

    private fun listeners(){
        btSignIn.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0){
            btSignIn -> {
                HomeScreenActivity.startActivity(this)
            }

            btnSignUp -> {
                RegistrationScreenActivity.startActivity(this)
            }
        }
    }

    companion object{
        fun startActivity(context : Context){
            context.startActivity(
                Intent(context,
                    AuthScreenActivity::class.java)
            )
        }
    }

}