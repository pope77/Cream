package com.example.pope.cream.page.creamarea.takeout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.example.pope.cream.R


/**
 * @author popeg
 */
class TakeOutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_out)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_takeOut_container,TakeOutCardFragment()).commit()
    }
}
