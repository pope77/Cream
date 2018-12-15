package com.example.pope.cream.biz.creamarea.scenery

import android.content.Context
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.SceneryBean

interface SceneryInterface {

    interface OnGetSceneryDataCallback : BaseDataCallback {

        fun onGetSuccess(sceneryBeans: MutableList<SceneryBean>, isCollectedList: ArrayList<Boolean>)

    }

    fun getSceneryData(context: Context, onGetSceneryDataCallback: OnGetSceneryDataCallback)

    fun changeCollectState(context: Context, type: String, id: String, collectThisScenery: Boolean, baseDataCallback: BaseDataCallback)

    interface OnCollectElementDataCallback : BaseDataCallback {

        fun onGetSuccess(sceneryBeans: MutableList<SceneryBean>, isCollectedList: ArrayList<Boolean>)

    }

    fun getCollectElementData(id: String, onCollectElementDataCallback: OnCollectElementDataCallback)

    fun userViewsPP(context: Context, baseDataCallback: BaseDataCallback)

}