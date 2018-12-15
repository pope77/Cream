package com.example.pope.cream.biz.login

import android.content.Context.MODE_PRIVATE
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.SaveListener
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.beans.UserBean

/**
 * @author popeg
 */
class LoginLogic : BaseLogic(), LoginInterface {

    /**
     * 创建新用户 将用户手机号及用户ObjectId保存到本地
     */
    override fun createNewUser(phoneNum: String, userImei: String, baseDataCallback: BaseDataCallback) {

        var userBean = UserBean()
        userBean.userIMEI = userImei
        userBean.userPhoneNum = phoneNum
        userBean.userName = "UserName"
        userBean.userCollection = 0
        userBean.userViews = 0
        userBean.save(object : SaveListener<String>() {
            override fun done(p0: String?, p1: BmobException?) {
                if (p1 != null) baseDataCallback.onGetFailed(p1.toString(), "70003")
                else {
                    var editer = context.getSharedPreferences("user", MODE_PRIVATE).edit()
                    editer.putString(UserBean.USER_PHONE_NUM, phoneNum)
                    editer.putString(UserBean.USER_OBJID, p0!!)
                    editer.putString(UserBean.USER_NAME, "null")
                    editer.apply()
                    baseDataCallback.onGetSuccess()
                }
            }

        })

    }

    /**
     * 检测用户是否选择了至少三个的兴趣点
     */
    override fun isSelectedInterest(phoneNum: String, onCheckInterestSelectedCallback: LoginInterface.OnCheckInterestSelectedCallback) {
        val query = BmobQuery<UserBean>()
        query.addWhereEqualTo(UserBean.USER_PHONE_NUM, phoneNum)
        query.findObjects(object : FindListener<UserBean>() {
            override fun done(p0: MutableList<UserBean>?, p1: BmobException?) {
                if (p1 != null) onCheckInterestSelectedCallback.onGetFailed(p1.toString(), "70002")
                else when (p0!!.isEmpty()) {
                    //未查询到该手机号 即新用户
                    true -> {
                        onCheckInterestSelectedCallback.isNewUser()
                    }
                    //查询到该手机号 检查是否选择兴趣点
                    false -> {
                        val userInterest = p0[0].userInterestPoint
                        if (userInterest == null) onCheckInterestSelectedCallback.onGetSuccess(false)
                        else {
                            var editer = context.getSharedPreferences("user", MODE_PRIVATE).edit()
                            if (userInterest.size < 3) {
                                editer.putString(UserBean.USER_OBJID, p0[0].objectId)
                                editer.putString(UserBean.USER_PHONE_NUM, phoneNum)
                                editer.apply()
                                onCheckInterestSelectedCallback.onGetSuccess(false)
                            } else {
                                editer.putString(UserBean.USER_OBJID, p0[0].objectId)
                                editer.putString(UserBean.USER_PHONE_NUM, phoneNum)
                                editer.putString(UserBean.USER_AVATAR, p0[0].userAvatar)
                                editer.putString(UserBean.USER_NAME, p0[0].userName)
                                editer.putInt(UserBean.USER_COLLECTION, p0[0].userCollection)
                                editer.putInt(UserBean.USER_VIEWS, p0[0].userViews)
                                editer.apply()
                                onCheckInterestSelectedCallback.onGetSuccess(true)
                            }
                        }
                    }
                }
            }
        })
    }


    /**
     * 检查是否为老用户 若是则自动添加手机号到输入框
     */
    override fun checkIsOldUser(imei: String, onCheckIsOldUserCallback: LoginInterface.OnCheckIsOldUserCallback) {

        val query = BmobQuery<UserBean>()
        query.addWhereEqualTo(UserBean.USER_IMEI, imei)
        query.findObjects(object : FindListener<UserBean>() {
            override fun done(p0: MutableList<UserBean>?, p1: BmobException?) {
                if (p1 == null) {
                    when (p0!!.isEmpty()) {
                        false -> onCheckIsOldUserCallback.onGetSuccess(p0[0].userPhoneNum)
                    }
                } else {
                    onCheckIsOldUserCallback.onGetFailed(p1.toString(), "70001")
                }
            }

        })

    }

    private object Holder {
        internal var INSTANCE = LoginLogic()
    }

    companion object {

        private val INSTANCE: LoginLogic? = null

        val instance: LoginLogic
            get() = Holder.INSTANCE
    }

}
