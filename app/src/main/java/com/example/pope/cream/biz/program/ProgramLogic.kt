package com.example.pope.cream.biz.program

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.UpdateListener
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.beans.ProgramBean

class ProgramLogic:BaseLogic(),ProgramInterface {
    override fun addHit(programBean: ProgramBean, baseDataCallback: BaseDataCallback) {
        programBean.programHits = programBean.programHits++
        programBean.update(object: UpdateListener(){
            override fun done(p0: BmobException?) {
                if (p0!=null) {
                    baseDataCallback.onGetFailed(p0.toString(),"70008")
                }
            }

        })
    }

    /**
     * 获取推荐节目数据
     */
    override fun getProgramRecommend(programType: Int, onProgramDataCallback: ProgramInterface.OnProgramDataCallback) {
        var query = BmobQuery<ProgramBean>()
        query.addWhereEqualTo(ProgramBean.PROGRAM_TYPE,programType)
        query.findObjects(object :FindListener<ProgramBean>(){
            override fun done(p0: MutableList<ProgramBean>?, p1: BmobException?) {
                if (p1!=null) onProgramDataCallback.onGetFailed(p1.toString(),"70007")
                else {
                    onProgramDataCallback.onGetSuccess(p0!!)
                }
            }
        })
    }

    private object Holder {
        internal var INSTANCE = ProgramLogic()
    }

    companion object {

        private val INSTANCE: ProgramLogic? = null

        val instance: ProgramLogic
            get() = Holder.INSTANCE
    }

}