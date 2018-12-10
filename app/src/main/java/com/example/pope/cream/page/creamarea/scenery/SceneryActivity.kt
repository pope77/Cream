package com.example.pope.cream.page.creamarea.scenery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pope.cream.R

class SceneryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scenery)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_sceneryActivity_container,SceneryFragment()).commit()
        Log.i("test7","activity已加载碎片")

    }
}
