package com.example.pope.cream.biz.home

import android.content.Context
import com.example.pope.cream.biz.base.BaseDataCallback

interface HomeInterface{

    interface OnInterestDataCallback:BaseDataCallback{

        fun onGetSuccess(interestList: MutableList<String>)

    }

    fun getInterestData(context: Context,onInterestDataCallback: OnInterestDataCallback)

}
