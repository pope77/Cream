package com.example.pope.cream.biz.creamarea.delicious

import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.CateBean

interface CateInterface {

    interface OnCateDataCallback:BaseDataCallback{

        fun onGetSuccess(cateBeans: MutableList<CateBean>)

    }

    fun getCateRecommendData(cateType:Int,onCateDataCallback: OnCateDataCallback)

}