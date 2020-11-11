package com.rainita.androidassignment.feature.home

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.rainita.androidassignment.R
import com.rainita.androidassignment.feature.login.AuthScreenActivity
import kotlinx.android.synthetic.main.activity_main.*


class HomeScreenActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listeners()
    }

    private fun listeners(){
        btnLogout.setOnClickListener(this)
    }

    companion object{
        fun startActivity(context : Context){
            context.startActivity(Intent(context,
                HomeScreenActivity::class.java))
        }
    }

    override fun onClick(p0: View?) {
        when(p0){
            btnLogout -> {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this@HomeScreenActivity)
                builder.setTitle(getString(R.string.btn_logout))
                builder.setMessage(getString(R.string.msg_do_you_want_to_logout))

                //Yes Button
                builder.setPositiveButton(getString(R.string.btn_yes),
                    DialogInterface.OnClickListener { dialog, which ->
                        AuthScreenActivity.startActivity(this)
                        finishAffinity()
                    })

                //No Button

                //No Button
                builder.setNegativeButton(getString(R.string.btn_no),
                    DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    })
                //Cancel Button

                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
            }
        }
    }
}