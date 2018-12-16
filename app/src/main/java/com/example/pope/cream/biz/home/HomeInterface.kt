package com.example.pope.cream.biz.home

import android.content.Context
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.*

interface HomeInterface {

    interface OnInterestDataCallback : BaseDataCallback {

        fun onGetSuccess(interestList: MutableList<String>)

    }

    fun getInterestData(context: Context, onInterestDataCallback: OnInterestDataCallback)

    interface OnInterestDetailCallback : BaseDataCallback {

        fun onGetSuccess(userBean: UserBean)

    }

    fun getInterestDetail( onInterestDetailCallback: OnInterestDetailCallback)

    interface OnListBeansCallback : BaseDataCallback {

        fun onGetSuccess(beans: ArrayList<CateBean>)

        fun onGetSuccess1(beans: ArrayList<ProgramBean>)

        fun onGetSuccess2(beans: ArrayList<BookBean>)

        fun onGetSuccess3(beans: ArrayList<SceneryBean>)

    }

    fun getCollectionListBeans(type: String, onListBeansCallback: OnListBeansCallback)

    interface OnBannerDataCallback : BaseDataCallback {

        fun onGetSuccess(bannerBeans: MutableList<RecommendMsgBean>)

    }

    fun getBannerData(onBannerDataCallback: OnBannerDataCallback)

    interface OnUserBeanCallback : BaseDataCallback {

        fun onGetSuccess(bean: UserBean)

    }

    fun getUserBean(context: Context, onUserBeanCallback: OnUserBeanCallback)

    fun changeUserName(newName: String, context: Context, baseDataCallback: BaseDataCallback)

    interface OnRandomJumpCallback:BaseDataCallback{

        fun onGetSuccess(type: String,id:String)

    }

    fun getRandomId(type:String,onRandomJumpCallback: OnRandomJumpCallback)

}
