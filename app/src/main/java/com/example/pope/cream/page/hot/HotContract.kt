package com.example.pope.cream.page.hot

import com.example.pope.cream.biz.beans.HotBean
import com.example.pope.cream.page.base.BasePresenter
import com.example.pope.cream.page.base.BaseView

interface HotContract {

    interface HotPresenter:BasePresenter{

        fun getListBeans()

    }

    interface HotView:BaseView<HotPresenter>{

        fun initListFragment(hitsBeans:MutableList<HotBean>,collectionBeans:MutableList<HotBean>,titles:ArrayList<String>)

    }

}