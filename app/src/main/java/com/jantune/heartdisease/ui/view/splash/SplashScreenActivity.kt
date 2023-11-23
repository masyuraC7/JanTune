package com.jantune.heartdisease.ui.view.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jantune.heartdisease.R
import com.jantune.heartdisease.ui.view.auth.AuthActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val intentToAuthActivity = Intent(this, AuthActivity::class.java)
        startActivity(intentToAuthActivity)
    }
}