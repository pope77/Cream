package com.example.pope.cream.page.home

import android.content.Context
import android.util.Log
import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.UserBean
import com.example.pope.cream.biz.home.HomeInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class CenterPresenter(private val view: HomeContract.CenterView) : BasePresenterImpl(), HomeContract.CenterPresenter {

    /**
     * 修改昵称
     */
    override fun changeUserName(newName: String, context: Context) {
        homeInterface.changeUserName(newName, context, object : BaseDataCallback {
            override fun onGetSuccess() {
                view.nameChangeSuccess()
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                view.toast("error$errorCode")
            }
        })
    }

    /**
     * 获取用户bean
     */
    override fun getUserBean(context: Context) {

        homeInterface.getUserBean(context, object : HomeInterface.OnUserBeanCallback {
            override fun onGetSuccess(bean: UserBean) {
                view.loadUserBean(bean)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                view.toast("error$errorCode")
            }
        })

    }

    private val homeInterface = ModelFactory.homeInterface

    init {
        view.bindPresenter(this)
    }
}
