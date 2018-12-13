package com.example.pope.cream.page.creamarea.scenery

import android.content.Context
import com.example.pope.cream.biz.beans.SceneryBean
import com.example.pope.cream.page.base.BasePresenter
import com.example.pope.cream.page.base.BaseView

interface SceneryContract {

    interface Presenter:BasePresenter{

        fun getSceneryData(context: Context)

        fun collectStateChange(context: Context,type:String,id:String,collectThisScenery:Boolean)

        fun getCollectElemntData(id: String)

        fun userViewsPP(context: Context)

    }

    interface View:BaseView<Presenter>{

        fun loadSceneryData(sceneryBeans:MutableList<SceneryBean>,isCollectedList:ArrayList<Boolean>)

    }

}