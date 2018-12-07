package com.example.pope.cream.page.creamarea.music

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.pope.cream.R

class MusicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_musicActivity_container,MusicFragment()).commit()

    }
}
