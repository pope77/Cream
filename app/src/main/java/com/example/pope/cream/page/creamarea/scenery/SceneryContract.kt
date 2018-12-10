package com.example.pope.cream.page.creamarea.scenery

import com.example.pope.cream.biz.beans.SceneryBean
import com.example.pope.cream.page.base.BasePresenter
import com.example.pope.cream.page.base.BaseView

interface SceneryContract {

    interface Presenter:BasePresenter{

        fun getSceneryData()

    }

    interface View:BaseView<Presenter>{

        fun loadSceneryData(sceneryBeans:MutableList<SceneryBean>)

    }

}