package com.example.pope.cream.page.home

import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.page.base.BasePresenterImpl

class CenterPresenter(private val view: HomeContract.CenterView) : BasePresenterImpl(), HomeContract.CenterPresenter {
    private val homeInterface = ModelFactory.homeInterface

    init {
        view.bindPresenter(this)
    }
}
