package com.example.pope.cream.biz

import android.content.Context
import android.util.Log
import android.widget.Toast
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.SaveListener
import cn.bmob.v3.listener.UpdateListener
import com.example.pope.cream.biz.beans.HotBean

/**
 * 公共逻辑类
 * 存放不止一个fragment或activity所需要的数据逻辑
 */
class PublicLogic {

    companion object {

        /**
         * 增加点击量
         */
        fun addHits(context: Context, type: String, id: String) {

            val query = BmobQuery<HotBean>()
            query.addWhereEqualTo(HotBean.HOT_OBJID, id)
            query.findObjects(object : FindListener<HotBean>() {
                override fun done(p0: MutableList<HotBean>?, p1: BmobException?) {
                    if (p1 != null) logicFailed(context, p1.toString(), "10001")
                    else {
                        if (p0!!.isEmpty()) {
                            val bean = HotBean(type, id, 0, 1)
                            bean.save(object : SaveListener<String>() {
                                override fun done(p0: String?, p1: BmobException?) {
                                    if (p1 != null) logicFailed(context, p1.toString(), "10002")
                                }
                            })
                        } else {
                            val query = BmobQuery<HotBean>()
                            query.getObject(p0[0].objectId,object :QueryListener<HotBean>(){
                                override fun done(p0: HotBean?, p1: BmobException?) {
                                    if (p1!=null) logicFailed(context,p1.toString(),"10003")
                                    else{
                                        p0!!.hotHits++
                                        p0.update(object :UpdateListener(){
                                            override fun done(p0: BmobException?) {
                                                if (p0!=null) logicFailed(context,p0.toString(),"10007")
                                            }
                                        })
                                    }
                                }
                            })

                        }
                    }
                }
            })
        }

        /**
         * 加减收藏量
         */
        fun addOrSubtractCollection(addOrSubtract: String, context: Context, type: String, id: String) {
            val query = BmobQuery<HotBean>()
            query.addWhereEqualTo(HotBean.HOT_OBJID, id)
            query.findObjects(object : FindListener<HotBean>() {
                override fun done(p0: MutableList<HotBean>?, p1: BmobException?) {
                    if (p1 != null) logicFailed(context, p1.toString(), "10004")
                    else {
                        if (p0!!.isEmpty()) {
                            val hotBean = HotBean(type, id, 1, 0)
                            hotBean.save(object : SaveListener<String>() {
                                override fun done(p0: String?, p1: BmobException?) {
                                    if (p1 != null) logicFailed(context, p1.toString(), "10005")
                                }
                            })
                        } else {
                            val hotBean = p0[0]
                            when (addOrSubtract) {
                                "+" -> {
                                    hotBean.hotCollection++
                                }
                                "-" -> {
                                    hotBean.hotCollection--
                                }
                            }
                            hotBean.update(object : UpdateListener() {
                                override fun done(p0: BmobException?) {
                                    if (p0 != null) logicFailed(context, p0.toString(), "10006")
                                }
                            })
                        }
                    }
                }
            })

        }

        /**
         * 逻辑错误 报错反馈
         */
        private fun logicFailed(context: Context, errorMsg: String, errorCode: String, showTime: Int = Toast.LENGTH_SHORT) {
            Toast.makeText(context, "error$errorCode", showTime).show()
            Log.i("error$errorCode", errorMsg)
        }

    }

}