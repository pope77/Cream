package com.example.pope.cream.page.creamarea.program

import android.content.Context
import android.util.Log
import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.creamarea.program.ProgramInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class ProgramDetailPresenter(val programDetailView: ProgramContract.ProgramDetailView) : BasePresenterImpl(), ProgramContract.ProgramDetailPresenter {

    /**
     * 用户浏览量+1
     */
    override fun userViewsPP(context: Context) {

        programInterface.userViewsPP(context,object : BaseDataCallback {
            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                programDetailView.toast("error$errorCode")
                Log.i("error$errorCode",errorMsg)
            }
        })
    }

    /**
     * 收藏状态检查
     */
    override fun collectStateCheck(context: Context, id: String) {

        programInterface.checkCollectState(context,id,object: ProgramInterface.CollectStateCheckCallback{
            override fun onGetSuccess(isCollected: Boolean) {
                programDetailView.collectStateModifier(isCollected)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                programDetailView.toast("error$errorCode")
                Log.i("error$errorCode",errorMsg)
            }
        })

    }

    /**
     * 改变收藏状态
     */
    override fun collectStateChange(context: Context, type: String, id: String, collectThisScenery: Boolean) {

        programInterface.changeCollectState(context, id, type, collectThisScenery, object : ProgramInterface.OnCollectStateChangeCallback {
            override fun onGetSuccess() {
                if (collectThisScenery) {
                    programDetailView.toast("收藏成功")
                } else {
                    programDetailView.toast("取消收藏成功")
                }
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                programDetailView.toast("error$errorCode")
                Log.i("error$errorCode", errorMsg)
            }
        })

    }

    private val programInterface = ModelFactory.programInterface

    init {
        programDetailView.bindPresenter(this)
    }

}