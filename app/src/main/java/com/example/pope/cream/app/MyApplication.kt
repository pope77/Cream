package com.example.pope.cream.app

import android.app.Application

import com.example.pope.cream.R

import cn.bmob.v3.Bmob
import com.example.pope.cream.biz.ModelFactory

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

        //Bmob初始化
        Bmob.initialize(this, getString(R.string.application_id))

        //初始化ModelFactory
        ModelFactory.init(this)

    }

    companion object {

        lateinit var INSTANCE: Application
    }
}
