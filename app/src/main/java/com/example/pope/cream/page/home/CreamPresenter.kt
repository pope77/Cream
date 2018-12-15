package com.example.pope.cream.page.home

import android.content.Context
import android.util.Log
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.beans.*
import com.example.pope.cream.biz.home.HomeInterface
import com.example.pope.cream.page.PublicViewLogic
import com.example.pope.cream.page.base.BasePresenterImpl

class CreamPresenter(private val creamView: HomeContract.CreamView) : BasePresenterImpl(), HomeContract.CreamPresenter {

    /**
     * 佛性跳转
     */
    override fun randomJump(type: String) {

        homeInterface.getRandomId(type,object :HomeInterface.OnRandomJumpCallback{
            override fun onGetSuccess(type: String, id: String) {
                Log.i("test7","type=$type id=$id")
                PublicViewLogic.specialJump(type, id)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                creamView.toast("error$errorCode")
            }
        })

    }

    /**
     * 获取Banner数据
     */
    override fun getBannerData() {
        homeInterface.getBannerData(object : HomeInterface.OnBannerDataCallback {
            override fun onGetSuccess(bannerBeans: MutableList<RecommendMsgBean>) {
                creamView.initBanner(bannerBeans)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                creamView.toast("error$errorCode")
            }
        })
    }

    /**
     * 获取兴趣标签数据
     */
    override fun getInterestData(context: Context) {

        /**
         * 获取兴趣标签数据
         */
        homeInterface.getInterestData(context, object : HomeInterface.OnInterestDataCallback {
            override fun onGetSuccess(interestList: MutableList<String>) {

                creamView.loadInterestData(interestList)

            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                creamView.toast("error$errorCode")
            }

        })

    }

    private val homeInterface = ModelFactory.homeInterface

    init {
        creamView.bindPresenter(this)
    }

}