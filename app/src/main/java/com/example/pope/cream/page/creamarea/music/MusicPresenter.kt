package com.example.pope.cream.page.creamarea.music

import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.page.base.BasePresenterImpl

class MusicPresenter(val view:MusicContract.View):BasePresenterImpl(),MusicContract.Presenter {



    private val musicInterface = ModelFactory.musicInterface

    init {
        view.bindPresenter(this)
    }

}