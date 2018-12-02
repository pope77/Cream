package com.example.pope.cream.biz.home

import android.content.Context
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.beans.UserBean

class HomeLogic : BaseLogic(), HomeInterface {
    override fun getInterestData(context: Context, onInterestDataCallback: HomeInterface.OnInterestDataCallback) {

        var query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) onInterestDataCallback.onGetFailed(p1.toString(), "70006")
                else {
                    onInterestDataCallback.onGetSuccess(p0!!.userInterestPoint)
                }
            }

        })

    }

    private object Holder {
        internal var INSTANCE = HomeLogic()
    }

    companion object {

        private val INSTANCE: HomeLogic? = null

        val instance: HomeLogic
            get() = Holder.INSTANCE
    }

}
