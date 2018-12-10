package com.example.pope.cream.biz.creamarea.scenery

import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.SceneryBean

interface SceneryInterface{

    interface OnGetSceneryDataCallback:BaseDataCallback{

        fun onGetSuccess(sceneryBeans:MutableList<SceneryBean>)

    }

    fun getSceneryData(onGetSceneryDataCallback: OnGetSceneryDataCallback)

}