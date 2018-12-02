package com.example.pope.cream.page.program

import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.beans.ProgramBean
import com.example.pope.cream.biz.program.ProgramInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class ProgramPresenter(val programView: ProgramContract.ProgramView):BasePresenterImpl(), ProgramContract.ProgramPresenter {

    /**
     * 获取推荐节目数据
     */
    override fun getProgramRecommend(programType: Int) {
        programInterface.getProgramRecommend(programType,object:ProgramInterface.OnProgramDataCallback{
            override fun onGetSuccess(programList: MutableList<ProgramBean>) {
                programView.loadProgramData(programList)
            }

        })
    }

    private val programInterface = ModelFactory.programInterface

    init {
        programView.bindPresenter(this)
    }

}