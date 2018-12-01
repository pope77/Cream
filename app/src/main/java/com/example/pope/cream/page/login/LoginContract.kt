package com.example.pope.cream.page.login

import com.example.pope.cream.page.base.BasePresenter
import com.example.pope.cream.page.base.BaseView

interface LoginContract {

    interface Presenter : BasePresenter {

        /**
         * 检查是否为老用户
         */
        fun checkIsOldUser(imei: String)

        /**
         * 检查该用户是否添加兴趣点
         */
        fun isSelectedInterest(phoneNum:String)

    }

    interface View : BaseView<Presenter> {

        /**
         * 老用户自动加载手机号
         */
        fun loadNum(phoneNum: String)

        /**
         * 跳转新界面 -1->MainActivity 1->InterestActivity
         */
        fun jump2NewActivity(code:Int)

        /**
         * 获取用户手机IMEI
         */
        fun getUserImei():String

        /**
         * 显示或消失ProgressDialog
         */
        fun showOrHideProgressDialog()

    }

}
