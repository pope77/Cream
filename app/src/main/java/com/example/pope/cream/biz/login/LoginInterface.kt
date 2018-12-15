package com.example.pope.cream.biz.login

import com.example.pope.cream.biz.base.BaseDataCallback

/**
 * @author popeg
 */
interface LoginInterface {

    /**
     * 检查是否为老用户的回调
     */
    interface OnCheckIsOldUserCallback : BaseDataCallback {

        fun onGetSuccess(msg: String)

    }

    /**
     * 检查是否为老用户
     */
    fun checkIsOldUser(imei: String, onCheckIsOldUserCallback: OnCheckIsOldUserCallback)

    /**
     * 检查是否选择兴趣点的回调
     */
    interface OnCheckInterestSelectedCallback : BaseDataCallback {

        fun onGetSuccess(isSelected: Boolean)

        fun isNewUser()

    }

    /**
     * 检查是否选择兴趣点
     */

    fun isSelectedInterest(phoneNum: String, onCheckInterestSelectedCallback: OnCheckInterestSelectedCallback)

    /**
     * 创建新用户
     */
    fun createNewUser(phoneNum: String, userImei: String, baseDataCallback: BaseDataCallback)

}
