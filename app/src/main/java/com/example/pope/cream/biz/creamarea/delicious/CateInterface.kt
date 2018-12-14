package com.example.pope.cream.biz.creamarea.delicious

import android.content.Context
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.CateBean
import com.example.pope.cream.biz.beans.RecommendMsgBean

interface CateInterface {

    interface OnCateDataCallback:BaseDataCallback{

        fun onGetSuccess(cateBeans: MutableList<CateBean>)

    }

    fun getCateRecommendData(cateType:Int,onCateDataCallback: OnCateDataCallback)

    interface OnCollectStateChangeCallback : BaseDataCallback {

        fun onGetSuccess()

    }

    fun changeCollectState(context: Context, id: String, type: String, collectThisProgram: Boolean, onCollectStateChangeCallback: OnCollectStateChangeCallback)

    interface CollectStateCheckCallback:BaseDataCallback{

        fun onGetSuccess(isCollected:Boolean)

    }

    fun checkCollectState(context: Context, id: String, collectStateChangeCallback: CollectStateCheckCallback)

    fun userViewsPP(context: Context,baseDataCallback: BaseDataCallback)


    interface OnBannerDataCallback:BaseDataCallback{

        fun onGetSuccess(bannerBeans:MutableList<RecommendMsgBean>,dataBeans:MutableList<CateBean>)

    }

    fun getBannerData(cateType:Int,onBannerDataCallback: OnBannerDataCallback)
}