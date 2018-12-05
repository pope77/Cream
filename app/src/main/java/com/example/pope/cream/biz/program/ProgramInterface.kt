package com.example.pope.cream.biz.program

import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.ProgramBean

interface ProgramInterface {

    /**
     * 获取节目推荐监听回调
     */
    interface OnProgramDataCallback:BaseDataCallback{

        fun onGetSuccess(programList: MutableList<ProgramBean>)

    }

    fun getProgramRecommend(programType:Int,onProgramDataCallback: OnProgramDataCallback)

    fun addHit(programBean: ProgramBean,baseDataCallback: BaseDataCallback)

}