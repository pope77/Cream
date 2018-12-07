package com.example.pope.cream.page.creamarea.delicious

import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.beans.CateBean
import com.example.pope.cream.biz.creamarea.delicious.CateInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class CatePresenter(val view:CateContract.View) :BasePresenterImpl(),CateContract.Presenter{
    override fun getCateRecommendMsg(cateType: Int) {
        cateInterface.getCateRecommendData(cateType,object:CateInterface.OnCateDataCallback{
            override fun onGetSuccess(cateBeans: MutableList<CateBean>) {
                view.loadRecyclerView(cateBeans)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                view.toast("error$errorCode")
            }

        })
    }

    private val cateInterface = ModelFactory.cateInterface

    init {
        view.bindPresenter(this)
    }
}