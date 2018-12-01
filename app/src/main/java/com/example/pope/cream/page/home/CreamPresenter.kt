package com.example.pope.cream.page.home

import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.page.base.BasePresenterImpl

class CreamPresenter(val creamView:HomeContract.CreamView):BasePresenterImpl(), HomeContract.CreamPresenter {

    private val homeInterface = ModelFactory.homeInterface

    init {
        creamView.bindPresenter(this)
    }

}