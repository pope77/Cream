package com.example.pope.cream.page.creamarea.music

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.MusicBean

class MusicActivity : AppCompatActivity() {

    val musicFragment = MusicFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_musicActivity_container,musicFragment).commit()
    }

    public fun playMusic(musicBean: MusicBean){
        musicFragment.playMusic(musicBean)
    }

}
