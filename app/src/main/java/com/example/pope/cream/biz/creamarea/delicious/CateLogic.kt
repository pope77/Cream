package com.example.pope.cream.biz.creamarea.delicious

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.beans.CateBean

class CateLogic:BaseLogic(),CateInterface{

    /**
     * 获取饮食类数据逻辑
     */
    override fun getCateRecommendData(cateType: Int, onCateDataCallback: CateInterface.OnCateDataCallback) {
        val query = BmobQuery<CateBean>()
        query.addWhereEqualTo(CateBean.CATE_TYPE,cateType)
        query.findObjects(object:FindListener<CateBean>(){
            override fun done(p0: MutableList<CateBean>?, p1: BmobException?) {
                if (p1!=null) onCateDataCallback.onGetFailed(p1.toString(),"70009")
                else onCateDataCallback.onGetSuccess(p0!!)
            }

        })
    }

    private object Holder{
        internal var INSTANCE = CateLogic()
    }

    companion object {
        private val INSTANCE:CateLogic? = null
        val instance:CateLogic
        get() = Holder.INSTANCE
    }

}