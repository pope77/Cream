package com.example.pope.cream.biz.hot

import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.HotBean

interface HotInterface {

    interface OnListBeansCallback:BaseDataCallback{

        fun onGetSuccess(hitsBeans:MutableList<HotBean>,collectionBeans:MutableList<HotBean>,
                         hitsListTitles:ArrayList<String>,collectionListTitles:ArrayList<String>)

    }

    fun getHotListBeans(onListBeansCallback: OnListBeansCallback)

}