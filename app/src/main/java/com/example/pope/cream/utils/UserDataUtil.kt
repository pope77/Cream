package com.example.pope.cream.utils

import android.content.Context
import com.example.pope.cream.biz.beans.UserBean

/**
 * 用户数据工具类
 */
open class UserDataUtil {

    /**
     * 获取用户保存到本地的UserObjectId
     */
    fun getLocalUserObjId(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        return sharedPreferences.getString(UserBean.USER_OBJID, "null")
    }

    fun getLocalUserName(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        return sharedPreferences.getString(UserBean.USER_NAME, "null")
    }

}