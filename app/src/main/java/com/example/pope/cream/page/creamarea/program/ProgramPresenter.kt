package com.example.pope.cream.page.creamarea.program

import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.ProgramBean
import com.example.pope.cream.biz.program.ProgramInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class ProgramPresenter(val programView: ProgramContract.ProgramView):BasePresenterImpl(), ProgramContract.ProgramPresenter {

    override fun addHit(programBean: ProgramBean) {

        programInterface.addHit(programBean,object :BaseDataCallback{
            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                programView.toast("error$errorCode")
            }
        })

    }

    /**
     * 获取推荐节目数据
     */
    override fun getProgramRecommend(programType: Int) {
        programInterface.getProgramRecommend(programType,object:ProgramInterface.OnProgramDataCallback{
            override fun onGetSuccess(programList: MutableList<ProgramBean>) {
                programView.loadProgramData(programList)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                programView.toast("error$errorCode")
            }

        })
    }

    private val programInterface = ModelFactory.programInterface

    init {
        programView.bindPresenter(this)
    }

}