package com.example.pope.cream.page.creamarea.scenery

import android.util.Log
import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.beans.SceneryBean
import com.example.pope.cream.biz.creamarea.scenery.SceneryInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class SceneryPresenter(val view:SceneryContract.View):BasePresenterImpl(),SceneryContract.Presenter{

    /**
     * 获取风景数据
     */
    override fun getSceneryData() {

        sceneryInterface.getSceneryData(object:SceneryInterface.OnGetSceneryDataCallback{
            override fun onGetSuccess(sceneryBeans: MutableList<SceneryBean>) {
                view.loadSceneryData(sceneryBeans)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                view.toast("error$errorCode")
                Log.i("error$errorCode",errorMsg)
            }
        })

    }

    private val sceneryInterface = ModelFactory.sceneryInterface

    init {
        view.bindPresenter(this)
    }

}