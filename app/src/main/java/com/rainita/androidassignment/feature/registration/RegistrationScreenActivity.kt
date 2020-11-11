package com.rainita.androidassignment.feature.registration

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rainita.androidassignment.R
import com.rainita.androidassignment.feature.home.HomeScreenActivity
import kotlinx.android.synthetic.main.activity_registration_screen.*

class RegistrationScreenActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_screen)

        listener()
    }

    private fun listener(){
        btnSignIn.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)
    }

    companion object{
        fun startActivity(context : Context){
            context.startActivity(
                Intent(context,
                    RegistrationScreenActivity::class.java)
            )
        }
    }

    override fun onClick(p0: View?) {
        when(p0){
            btnSignIn -> {
                finish()
            }

            btnSignUp -> {
                HomeScreenActivity.startActivity(this)
            }
        }
    }
}