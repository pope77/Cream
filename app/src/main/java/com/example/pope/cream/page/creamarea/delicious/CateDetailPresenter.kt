package com.example.pope.cream.page.creamarea.delicious

import android.content.Context
import android.util.Log
import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.creamarea.delicious.CateInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class CateDetailPresenter(val cateDetailView: CateContract.CateDetailView) : BasePresenterImpl(), CateContract.CateDetailPresenter {

    /**
     * 用户浏览量+1
     */
    override fun userViewsPP(context: Context) {

        cateInterface.userViewsPP(context,object : BaseDataCallback {
            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                cateDetailView.toast("error$errorCode")
                Log.i("error$errorCode",errorMsg)
            }
        })
    }

    /**
     * 收藏状态检查
     */
    override fun collectStateCheck(context: Context, id: String) {

        cateInterface.checkCollectState(context, id, object : CateInterface.CollectStateCheckCallback {
            override fun onGetSuccess(isCollected: Boolean) {
                cateDetailView.collectStateModifier(isCollected)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                cateDetailView.toast("error$errorCode")
                Log.i("error$errorCode", errorMsg)
            }
        })

    }

    /**
     * 改变收藏状态
     */
    override fun collectStateChange(context: Context, type: String, id: String, collectThisScenery: Boolean) {

        cateInterface.changeCollectState(context, id, type, collectThisScenery, object : CateInterface.OnCollectStateChangeCallback {
            override fun onGetSuccess() {
                if (collectThisScenery) {
                    cateDetailView.toast("收藏成功")
                } else {
                    cateDetailView.toast("取消收藏成功")
                }
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                cateDetailView.toast("error$errorCode")
                Log.i("error$errorCode", errorMsg)
            }
        })

    }

    private val cateInterface = ModelFactory.cateInterface

    init {
        cateDetailView.bindPresenter(this)
    }

}