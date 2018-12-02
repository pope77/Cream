package com.example.pope.cream.page.home

import android.content.Context
import com.example.pope.cream.page.base.BasePresenter
import com.example.pope.cream.page.base.BaseView

interface HomeContract{

    interface CreamPresenter:BasePresenter{

        fun getInterestData(context: Context)

    }

    interface  CreamView:BaseView<CreamPresenter>{

        fun loadInterestData(interestList: MutableList<String>)

    }

    interface CollectionPresenter:BasePresenter

    interface CollectionView:BaseView<CollectionPresenter>

    interface CenterPresenter:BasePresenter

    interface CenterView:BaseView<CenterPresenter>

}
