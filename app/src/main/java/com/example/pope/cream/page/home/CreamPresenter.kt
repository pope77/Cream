package com.example.pope.cream.page.home

import android.content.Context
import android.util.Log
import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.home.HomeInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class CreamPresenter(private val creamView:HomeContract.CreamView):BasePresenterImpl(), HomeContract.CreamPresenter {

    /**
     * 获取兴趣标签数据
     */
    override fun getInterestData(context: Context) {

        /**
         * 获取兴趣标签数据
         */
        homeInterface.getInterestData(context,object:HomeInterface.OnInterestDataCallback{
            override fun onGetSuccess(interestList: MutableList<String>) {

                creamView.loadInterestData(interestList)

            }

        })

    }

    private val homeInterface = ModelFactory.homeInterface

    init {
        creamView.bindPresenter(this)
    }

}