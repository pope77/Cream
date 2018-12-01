package com.example.pope.cream.biz.base

import android.util.Log

interface BaseDataCallback {

    fun onGetFailed(errorMsg: String,errorCode:String){
        Log.i("error$errorCode",errorMsg)
    }
}