package com.example.pope.cream.page.creamarea.scenery

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pope.cream.R

class SceneryActivity : AppCompatActivity() {

    var isSpecial = false
    var elementId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scenery)

        val intent = intent
        isSpecial = intent.getBooleanExtra("特殊",false)
        if (isSpecial){
            elementId = intent.getStringExtra("id")
        }
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_sceneryActivity_container,SceneryFragment()).commit()

    }
}
