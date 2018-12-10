package com.example.pope.cream.biz.creamarea.music

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.beans.MusicBean

class MusicLogic:BaseLogic(),MusicInterface{

    /**
     * 获取某类型的音乐数据
     */
    override fun getMusicData(musicType: Int, onMusicDataCallback: MusicInterface.OnMusicDataCallback) {

        val query = BmobQuery<MusicBean>()
        query.addWhereEqualTo(MusicBean.MUSIC_TYPE,musicType)
        query.findObjects(object: FindListener<MusicBean>() {
            override fun done(p0: MutableList<MusicBean>?, p1: BmobException?) {
                if (p1!=null) onMusicDataCallback.onGetFailed(p1.toString(),"70011")
                else {
                    onMusicDataCallback.onGetSuccess(p0!!)
                }
            }
        })

    }

    private object Holder{
        internal var INSTANCE = MusicLogic()
    }

    companion object {
        private val INSTANCE : MusicLogic? = null
        val instance:MusicLogic
        get() = Holder.INSTANCE
    }

}