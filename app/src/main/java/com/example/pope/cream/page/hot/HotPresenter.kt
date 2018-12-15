package com.example.pope.cream.page.hot

import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.beans.HotBean
import com.example.pope.cream.biz.hot.HotInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class HotPresenter(val hotView: HotContract.HotView):BasePresenterImpl(),HotContract.HotPresenter {

    /**
     * 获取排行榜数据
     */
    override fun getListBeans() {
        hotInterface.getHotListBeans(object :HotInterface.OnListBeansCallback{
            override fun onGetSuccess(hitsBeans: MutableList<HotBean>, collectionBeans: MutableList<HotBean>, hitsListTitles: ArrayList<String>, collectionListTitles: ArrayList<String>) {

            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                hotView.toast("error$errorCode")
            }
        })
    }

    private val hotInterface = ModelFactory.hotInterface

    init {
        hotView.bindPresenter(this)
    }

}