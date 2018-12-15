package com.example.pope.cream.page.creamarea.delicious

import android.util.Log
import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.beans.CateBean
import com.example.pope.cream.biz.beans.RecommendMsgBean
import com.example.pope.cream.biz.creamarea.delicious.CateInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class CatePresenter(val view: CateContract.View) : BasePresenterImpl(), CateContract.Presenter {

    /**
     * 获取Banner数据
     */
    override fun getBannerData(cateType: Int) {

        cateInterface.getBannerData(cateType, object : CateInterface.OnBannerDataCallback {
            override fun onGetSuccess(bannerBeans: MutableList<RecommendMsgBean>, dataBeans: MutableList<CateBean>) {
                view.initBanner(bannerBeans, dataBeans)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                view.toast("error$errorCode")
            }
        })

    }

    /**
     * 获取推荐的饮食列表数据
     */
    override fun getCateRecommendMsg(cateType: Int) {
        cateInterface.getCateRecommendData(cateType, object : CateInterface.OnCateDataCallback {
            override fun onGetSuccess(cateBeans: MutableList<CateBean>) {
                view.loadRecyclerView(cateBeans)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                view.toast("error$errorCode")
            }

        })
    }

    private val cateInterface = ModelFactory.cateInterface

    init {
        view.bindPresenter(this)
    }
}