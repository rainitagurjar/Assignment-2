package com.rainita.androidassignment.feature.registration

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.rainita.androidassignment.R
import com.rainita.androidassignment.feature.home.HomeScreenActivity
import com.rainita.androidassignment.utils.CommonUtils
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



    override fun onClick(p0: View?) {
        when(p0){
            btnSignIn -> {
                finish()
            }

            btnSignUp -> {
                val error = checkFields()
                if (TextUtils.isEmpty(error)) {
                    HomeScreenActivity.startActivity(this)
                } else{
                    Toast.makeText(this,error, Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    private fun checkFields() = when {
        TextUtils.isEmpty(etFirstName.text?.trim()) -> {
            etFirstName.requestFocus()
            getString(R.string.error_first_name)
        }
        TextUtils.isEmpty(etLastName.text?.trim()) -> {
            etLastName.requestFocus()
            getString(R.string.error_last_name)
        }
        TextUtils.isEmpty(etEmail.text?.trim()) -> {
            etEmail.requestFocus()
            getString(R.string.error_email_address)
        }
        !CommonUtils.isValidEmail(etEmail.text?.trim().toString()) -> {
            etEmail.requestFocus()
            getString(R.string.error_invalid_email_address)
        }
        TextUtils.isEmpty(etMobileNumber.text?.trim()) -> {
            etMobileNumber.requestFocus()
            getString(R.string.error_mobile_number)
        }
        TextUtils.isEmpty(etPassword.text?.trim()) -> {
            etPassword.requestFocus()
            getString(R.string.error_password)
        }
        etPassword.text?.trim()!!.length < 6 -> {
            etPassword.requestFocus()
            getString(R.string.error_invalid_password)
        }
        else -> null
    }

    companion object{
        fun startActivity(context : Context){
            context.startActivity(
                Intent(context,
                    RegistrationScreenActivity::class.java)
            )
        }
    }


}