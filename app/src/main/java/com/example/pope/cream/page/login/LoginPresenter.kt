package com.example.pope.cream.page.login

import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.login.LoginInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class LoginPresenter
/**
 * 结构体中同时完成View和Presenter的双向绑定
 * @param view view视图
 */
(private val view: LoginContract.View) : BasePresenterImpl(), LoginContract.Presenter {

    /**
     * 检测是否选择了兴趣点
     */
    override fun isSelectedInterest(phoneNum: String) {

        when (phoneNum.length) {
            //未输入内容
            0 -> {
                view.toast("请输入您的手机号")
                view.showOrHideProgressDialog()
            }
            //输入手机号位数不够
            in 1..10 -> {
                view.toast("手机号码格式不正确，请检查是否输入有误")
                view.showOrHideProgressDialog()
            }
            //输入手机号位数过多
            in 12..20 -> {
                view.toast("手机号码格式不正确，请检查是否输入有误")
                view.showOrHideProgressDialog()
            }
            //当手机号码格式正确时
            11 -> {
                loginInterface.isSelectedInterest(phoneNum, object : LoginInterface.OnCheckInterestSelectedCallback {
                    override fun isNewUser() {

                        loginInterface.createNewUser(phoneNum, view.getUserImei(), object : LoginInterface.OnCreateNewUserCallback {
                            override fun onGetSuccess() {
                                view.jump2NewActivity(1)
                                view.showOrHideProgressDialog()
                            }

                            override fun onGetFailed(errorMsg: String, errorCode: String) {
                                super.onGetFailed(errorMsg, errorCode)
                                view.toast("error$errorCode")
                                view.showOrHideProgressDialog()
                            }

                        })

                    }

                    override fun onGetSuccess(isSelected: Boolean) {

                        when (isSelected) {
                            //已经选择
                            true -> view.jump2NewActivity(-1)
                            //还未选择
                            false -> view.jump2NewActivity(1)
                        }
                        view.showOrHideProgressDialog()
                    }

                    override fun onGetFailed(errorMsg: String, errorCode: String) {
                        super.onGetFailed(errorMsg, errorCode)
                        view.toast("error$errorCode")
                        view.showOrHideProgressDialog()
                    }

                })

            }
        }

    }

    override fun checkIsOldUser(imei: String) {

        loginInterface.checkIsOldUser(imei, object : LoginInterface.OnCheckIsOldUserCallback {

            override fun onGetSuccess(msg: String) {
                //调用View层加载手机号码
                view.loadNum(msg)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                view.toast("error$errorCode")
            }

        })

    }


    private val loginInterface = ModelFactory.loginInterface

    init {
        view.bindPresenter(this)
    }

}
