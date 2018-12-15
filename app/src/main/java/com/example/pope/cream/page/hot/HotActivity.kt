package com.example.pope.cream.page.hot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.pope.cream.R

class HotActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hot)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_hotActivity_container,HotFragment()).commit()
    }
}
