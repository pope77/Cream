package com.example.pope.cream.page.creamarea.delicious

import android.content.Context
import com.example.pope.cream.biz.beans.CateBean
import com.example.pope.cream.page.base.BasePresenter
import com.example.pope.cream.page.base.BaseView

interface CateContract {

    interface Presenter:BasePresenter{

        fun getCateRecommendMsg(cateType:Int)

    }

    interface View:BaseView<Presenter>{

        fun loadRecyclerView(cateBeans:MutableList<CateBean>)

    }

    interface CateDetailPresenter:BasePresenter{

        fun collectStateChange(context: Context, type:String, id:String, collectThisScenery:Boolean)

        fun collectStateCheck(context: Context, id:String)

    }

    interface CateDetailView:BaseView<CateDetailPresenter>{

        fun collectStateModifier(isCollected:Boolean)

    }

}