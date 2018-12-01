package com.example.pope.cream.biz.home

import com.example.pope.cream.biz.base.BaseLogic

class HomeLogic:BaseLogic(),HomeInterface{

    private object Holder {
        internal var INSTANCE = HomeLogic()
    }

    companion object {

        private val INSTANCE: HomeLogic? = null

        val instance: HomeLogic
            get() = Holder.INSTANCE
    }

}
