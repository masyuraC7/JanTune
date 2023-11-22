package com.jantune.heartdisease.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jantune.heartdisease.R
import com.jantune.heartdisease.ui.auth.login.LoginFragment

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val fragmentManager = supportFragmentManager
        val loginFragment = LoginFragment()
        val fragment = fragmentManager.findFragmentByTag(LoginFragment::class.java.simpleName)

        if (fragment !is LoginFragment) {
            Log.d("AuthActivity", "Fragment Name :" + LoginFragment::class.java.simpleName)
            fragmentManager
                .beginTransaction()
                .add(R.id.fragment_auth, loginFragment, LoginFragment::class.java.simpleName)
                .commit()
        }
    }
}