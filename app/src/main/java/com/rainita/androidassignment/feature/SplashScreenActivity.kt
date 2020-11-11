package com.rainita.androidassignment.feature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rainita.androidassignment.R
import com.rainita.androidassignment.feature.login.AuthScreenActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_acvity)
        handler.postDelayed(runnable, 2000)
    }

    private val handler = Handler()

    private val runnable = {
        nextScreen()
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }

    override fun onRestart() {
        super.onRestart()
        nextScreen()
    }

    private fun nextScreen() {
        AuthScreenActivity.startActivity(this)
    }
}