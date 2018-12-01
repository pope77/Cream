package com.example.pope.cream.page.home

import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.page.base.BasePresenterImpl

class CollectionPresenter(private val view: HomeContract.CollectionView) : BasePresenterImpl(), HomeContract.CollectionPresenter {
    private val homeInterface = ModelFactory.homeInterface

    init {
        view.bindPresenter(this)
    }
}