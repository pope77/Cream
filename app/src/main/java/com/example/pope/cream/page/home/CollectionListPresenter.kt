package com.example.pope.cream.page.home

import android.content.Context
import android.util.Log
import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.beans.BookBean
import com.example.pope.cream.biz.beans.CateBean
import com.example.pope.cream.biz.beans.ProgramBean
import com.example.pope.cream.biz.beans.SceneryBean
import com.example.pope.cream.biz.home.HomeInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class CollectionListPresenter(val collectionListView: HomeContract.CollectionListView)
    : BasePresenterImpl(), HomeContract.CollectionListPresenter {

    /**
     * 获取数据bean
     */
    override fun getBeans(context: Context, type: String) {

        homeInterface.getCollectionListBeans(context, type, object : HomeInterface.OnListBeansCallback {
            override fun onGetSuccess(beans: ArrayList<CateBean>) {
                collectionListView.loadBeans(beans)
            }

            override fun onGetSuccess1(beans: ArrayList<ProgramBean>) {
                collectionListView.loadBeans1(beans)
            }

            override fun onGetSuccess2(beans: ArrayList<BookBean>) {
                collectionListView.loadBeans2(beans)
            }

            override fun onGetSuccess3(beans: ArrayList<SceneryBean>) {
                collectionListView.loadBeans3(beans)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                collectionListView.toast("error$errorCode")
            }
        })

    }

    /**
     * 获取数据Bean
     */
    override fun getBeans(type: String, idList: MutableList<String>) {

        homeInterface.getCollectionListBeans(type, idList, object : HomeInterface.OnListBeansCallback {
            override fun onGetSuccess(beans: ArrayList<CateBean>) {
                collectionListView.loadBeans(beans)
            }

            override fun onGetSuccess1(beans: ArrayList<ProgramBean>) {
                collectionListView.loadBeans1(beans)
            }

            override fun onGetSuccess2(beans: ArrayList<BookBean>) {
                collectionListView.loadBeans2(beans)
            }

            override fun onGetSuccess3(beans: ArrayList<SceneryBean>) {
                collectionListView.loadBeans3(beans)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                collectionListView.toast("error$errorCode")
            }
        })

    }

    private val homeInterface = ModelFactory.homeInterface

    init {
        collectionListView.bindPresenter(this)
    }

}