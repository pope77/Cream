package com.example.pope.cream.page.creamarea.music

import android.content.Context
import android.util.Log
import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.MusicBean
import com.example.pope.cream.biz.creamarea.music.MusicInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class MusicListPresenter(val listView: MusicContract.ListView) : BasePresenterImpl(), MusicContract.ListPresenter {

    /**
     * 用户浏览量+1
     */
    override fun userViewsPP(context: Context) {

        musicInterface.userViewsPP(context,object : BaseDataCallback {
            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                listView.toast("error$errorCode")
                Log.i("error$errorCode",errorMsg)
            }
        })
    }

    /**
     * 获取某类型的音乐数据
     */
    override fun getMusicData(musicType: Int) {

        musicInterface.getMusicData(musicType,object:MusicInterface.OnMusicDataCallback{
            override fun onGetSuccess(musicBeans: MutableList<MusicBean>) {
                listView.loadMusicMsgToList(musicBeans)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                listView.toast("error$errorCode")
                Log.i("error$errorCode",errorMsg)
            }
        })

    }

    private val musicInterface = ModelFactory.musicInterface

    init {
        listView.bindPresenter(this)
    }

}