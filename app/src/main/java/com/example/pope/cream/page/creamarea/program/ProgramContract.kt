package com.example.pope.cream.page.creamarea.program

import android.content.Context
import com.example.pope.cream.biz.beans.ProgramBean
import com.example.pope.cream.page.base.BasePresenter
import com.example.pope.cream.page.base.BaseView

interface ProgramContract {

    interface ProgramPresenter : BasePresenter {

        fun getProgramRecommend(programType: Int)

    }

    interface ProgramView : BaseView<ProgramPresenter> {

        fun loadProgramData(programList: MutableList<ProgramBean>)

    }

    interface ProgramDetailPresenter : BasePresenter {

        fun collectStateChange(context: Context, type: String, id: String, collectThisScenery: Boolean)

        fun collectStateCheck(context: Context, id: String)

        fun userViewsPP(context: Context, type: String, id: String)

    }

    interface ProgramDetailView : BaseView<ProgramDetailPresenter> {

        fun collectStateModifier(isCollected: Boolean)

    }

}