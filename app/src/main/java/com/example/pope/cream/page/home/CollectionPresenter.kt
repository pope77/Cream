package com.example.pope.cream.page.home

import android.content.Context
import android.util.Log
import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.beans.UserBean
import com.example.pope.cream.biz.home.HomeInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class CollectionPresenter(private val collectionView: HomeContract.CollectionView) : BasePresenterImpl(), HomeContract.CollectionPresenter {


    /**
     * 获取兴趣数据
     */
    override fun getInterestDetailData(context: Context) {

        homeInterface.getInterestDetail(context, object : HomeInterface.OnInterestDetailCallback {
            override fun onGetSuccess(userBean: UserBean) {
                collectionView.loadInterestData(userBean)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                collectionView.toast("error$errorCode")
            }
        })

    }

    private val homeInterface = ModelFactory.homeInterface

    init {
        collectionView.bindPresenter(this)
    }
}