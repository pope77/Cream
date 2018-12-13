package com.example.pope.cream.biz.creamarea.delicious

import android.content.Context
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.CateBean

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

}