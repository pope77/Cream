package com.example.pope.cream.page.creamarea.scenery

import android.content.Context
import android.util.Log
import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.SceneryBean
import com.example.pope.cream.biz.creamarea.scenery.SceneryInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class SceneryPresenter(val view:SceneryContract.View):BasePresenterImpl(),SceneryContract.Presenter{

    /**
     * 用户浏览量+1
     */
    override fun userViewsPP(context: Context) {

        sceneryInterface.userViewsPP(context,object : BaseDataCallback {
            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                view.toast("error$errorCode")
                Log.i("error$errorCode",errorMsg)
            }
        })
    }

    /**
     * 从收藏列表跳转过来 获取被点击的项的bean并单独显示
     */
    override fun getCollectElemntData(id: String) {

        sceneryInterface.getCollectElmentData(id,object :SceneryInterface.OnCollectElmentDataCallback{
            override fun onGetSuccess(sceneryBeans: MutableList<SceneryBean>, isCollectedList: ArrayList<Boolean>) {
                view.loadSceneryData(sceneryBeans,isCollectedList)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                view.toast("error$errorCode")
                Log.i("error$errorCode",errorMsg)
            }
        })

    }

    /**
     * 收藏状态改变
     */
    override fun collectStateChange(context: Context,type:String,id:String,collectThisScenery:Boolean) {

        sceneryInterface.changeCollectState(context,type,id,collectThisScenery,object:SceneryInterface.OnCollectStateChangeCallback{
            override fun onGetSuccess() {
                if (collectThisScenery){
                    view.toast("收藏成功")
                }else{
                    view.toast("取消收藏成功")
                }
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                view.toast("error$errorCode")
                Log.i("error$errorCode",errorMsg)
            }
        })

    }

    /**
     * 获取风景数据
     */
    override fun getSceneryData(context: Context) {

        sceneryInterface.getSceneryData(context,object:SceneryInterface.OnGetSceneryDataCallback{
            override fun onGetSuccess(sceneryBeans: MutableList<SceneryBean>, isCollectedList: ArrayList<Boolean>) {
                view.loadSceneryData(sceneryBeans,isCollectedList)
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