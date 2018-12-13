package com.example.pope.cream.biz.creamarea.program

import android.content.Context
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.ProgramBean

interface ProgramInterface {

    /**
     * 获取节目推荐监听回调
     */
    interface OnProgramDataCallback : BaseDataCallback {

        fun onGetSuccess(programList: MutableList<ProgramBean>)

    }

    fun getProgramRecommend(programType: Int, onProgramDataCallback: OnProgramDataCallback)

    fun addHit(programBean: ProgramBean, baseDataCallback: BaseDataCallback)

    interface OnCollectStateChangeCallback : BaseDataCallback {

        fun onGetSuccess()

    }

    fun changeCollectState(context: Context, id: String, type: String, collectThisProgram: Boolean, onCollectStateChangeCallback: OnCollectStateChangeCallback)

    interface CollectStateCheckCallback:BaseDataCallback{

        fun onGetSuccess(isCollected:Boolean)

    }

    fun checkCollectState(context: Context,id: String,collectStateChangeCallback: CollectStateCheckCallback)

    fun userViewsPP(context: Context,baseDataCallback: BaseDataCallback)

}