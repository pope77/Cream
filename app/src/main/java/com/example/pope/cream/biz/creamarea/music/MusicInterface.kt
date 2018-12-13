package com.example.pope.cream.biz.creamarea.music

import android.content.Context
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.MusicBean

interface MusicInterface {

    interface OnMusicDataCallback : BaseDataCallback {

        fun onGetSuccess(musicBeans: MutableList<MusicBean>)

    }

    fun getMusicData(musicType: Int, onMusicDataCallback: OnMusicDataCallback)

    fun userViewsPP(context: Context, baseDataCallback: BaseDataCallback)

}