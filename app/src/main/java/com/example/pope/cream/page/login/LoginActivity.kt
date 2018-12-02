package com.example.pope.cream.page.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.example.pope.cream.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //直接加载Fragment
        supportFragmentManager.beginTransaction().replace(R.id.container_login,LoginFragment()).commit()


    }

}
