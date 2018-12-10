package com.example.pope.cream.biz.creamarea.scenery

import android.util.Log
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.beans.SceneryBean

class SceneryLogic : BaseLogic(), SceneryInterface {

    /**
     * 获取风景数据
     */
    override fun getSceneryData(onGetSceneryDataCallback: SceneryInterface.OnGetSceneryDataCallback) {

        val query = BmobQuery<SceneryBean>()
        query.addWhereNotEqualTo(SceneryBean.SCENERY_UICODE,0)
        query.findObjects(object:FindListener<SceneryBean>(){
            override fun done(p0: MutableList<SceneryBean>?, p1: BmobException?) {
                if (p1!=null) onGetSceneryDataCallback.onGetFailed(p1.toString(),"70012")
                else{
                    onGetSceneryDataCallback.onGetSuccess(p0!!)
                }
            }
        })

    }

    private object Holder {
        internal var INSTANCE = SceneryLogic()
    }

    companion object {

        private val INSTANCE: SceneryLogic? = null

        val instance: SceneryLogic
            get() = Holder.INSTANCE

    }

}