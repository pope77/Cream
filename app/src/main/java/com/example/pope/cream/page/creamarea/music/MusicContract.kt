package com.example.pope.cream.page.creamarea.music

import com.example.pope.cream.biz.beans.MusicBean
import com.example.pope.cream.page.base.BasePresenter
import com.example.pope.cream.page.base.BaseView

interface MusicContract {

    interface Presenter : BasePresenter {

    }

    interface View : BaseView<Presenter> {

    }

    interface ListPresenter : BasePresenter {

        fun getMusicData(musicType:Int)

    }

    interface ListView:BaseView<ListPresenter>{

        fun loadMusicMsgToList(musicBeans:MutableList<MusicBean>)

    }

}