package com.example.pope.cream.page.home

import android.content.Context
import com.example.pope.cream.biz.beans.*
import com.example.pope.cream.page.base.BasePresenter
import com.example.pope.cream.page.base.BaseView

interface HomeContract{

    interface CreamPresenter:BasePresenter{

        fun getInterestData(context: Context)

        fun getBannerData()

    }

    interface  CreamView:BaseView<CreamPresenter>{

        fun loadInterestData(interestList: MutableList<String>)

        fun initBanner(bannerBeans:MutableList<RecommendMsgBean>)

    }

    interface CollectionPresenter:BasePresenter{

        fun getInterestDetailData(context: Context)

    }

    interface CollectionView:BaseView<CollectionPresenter>{

        fun loadInterestData(userBean: UserBean)

    }

    interface CollectionListPresenter:BasePresenter{

        fun getBeans(type:String,idList: MutableList<String>)

        fun getBeans(context: Context,type: String)

    }

    interface CollectionListView:BaseView<CollectionListPresenter>{

        fun loadBeans(beans:ArrayList<CateBean>)

        fun loadBeans1(beans:ArrayList<ProgramBean>)

        fun loadBeans2(beans:ArrayList<BookBean>)

        fun loadBeans3(beans:ArrayList<SceneryBean>)

    }

    interface CenterPresenter:BasePresenter

    interface CenterView:BaseView<CenterPresenter>

}
