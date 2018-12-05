package com.example.pope.cream.page.creamarea.program

import com.example.pope.cream.biz.beans.ProgramBean
import com.example.pope.cream.page.base.BasePresenter
import com.example.pope.cream.page.base.BaseView

interface ProgramContract{

    interface ProgramPresenter:BasePresenter{

        fun getProgramRecommend(programType:Int)

        fun addHit(programBean: ProgramBean)

    }

    interface ProgramView:BaseView<ProgramPresenter>{

        fun loadProgramData(programList: MutableList<ProgramBean>)

    }

}