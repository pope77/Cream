package com.example.pope.cream.biz.creamarea.music

import android.content.Context
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.UpdateListener
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.beans.MusicBean
import com.example.pope.cream.biz.beans.UserBean

class MusicLogic : BaseLogic(), MusicInterface {

    /**
     * 用户浏览量+1
     */
    override fun userViewsPP(context: Context, baseDataCallback: BaseDataCallback) {
        val query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) baseDataCallback.onGetFailed(p1.toString(), "70049")
                else {
                    p0!!.userViews++
                    p0.update(object : UpdateListener() {
                        override fun done(p0: BmobException?) {
                            if (p0 != null) baseDataCallback.onGetFailed(p0.toString(), "70050")
                        }
                    })
                }
            }
        })
    }

    /**
     * 获取某类型的音乐数据
     */
    override fun getMusicData(musicType: Int, onMusicDataCallback: MusicInterface.OnMusicDataCallback) {

        val query = BmobQuery<MusicBean>()
        query.addWhereEqualTo(MusicBean.MUSIC_TYPE, musicType)
        query.findObjects(object : FindListener<MusicBean>() {
            override fun done(p0: MutableList<MusicBean>?, p1: BmobException?) {
                if (p1 != null) onMusicDataCallback.onGetFailed(p1.toString(), "70011")
                else {
                    onMusicDataCallback.onGetSuccess(p0!!)
                }
            }
        })

    }

    private object Holder {
        internal var INSTANCE = MusicLogic()
    }

    companion object {
        private val INSTANCE: MusicLogic? = null
        val instance: MusicLogic
            get() = Holder.INSTANCE
    }

}