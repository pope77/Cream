package com.example.pope.cream.biz.creamarea.program

import android.content.Context
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.UpdateListener
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.beans.ProgramBean
import com.example.pope.cream.biz.beans.UserBean

class ProgramLogic : BaseLogic(), ProgramInterface {

    /**
     * 用户浏览量+1
     */
    override fun userViewsPP(context: Context, baseDataCallback: BaseDataCallback) {
        val query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) baseDataCallback.onGetFailed(p1.toString(), "70051")
                else {
                    p0!!.userViews++
                    p0.update(object : UpdateListener() {
                        override fun done(p0: BmobException?) {
                            if (p0 != null) baseDataCallback.onGetFailed(p0.toString(), "70052")
                            else baseDataCallback.onGetSuccess()
                        }
                    })
                }
            }
        })
    }

    /**
     * 收藏状态检查
     */
    override fun checkCollectState(context: Context, id: String, collectStateChangeCallback: ProgramInterface.CollectStateCheckCallback) {
        val query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) collectStateChangeCallback.onGetFailed(p1.toString(), "70031")
                else {
                    for (element in p0!!.pointId) {
                        if (element == id) {
                            collectStateChangeCallback.onGetSuccess(true)
                            return
                        }
                    }
                    collectStateChangeCallback.onGetSuccess(false)
                }
            }

        })
    }

    /**
     * 改变收藏状态
     */
    override fun changeCollectState(context: Context, id: String, type: String, collectThisProgram: Boolean, baseDataCallback: BaseDataCallback) {
        val query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) baseDataCallback.onGetFailed(p1.toString(), "70034")
                else {
                    val interestList = p0!!.userInterestPoint
                    val itemNumList = p0!!.pointItemNum
                    val idList = p0!!.pointId
                    when (collectThisProgram) {
                        //收藏
                        true -> {
                            var count = 0
                            for ((index, element) in interestList.withIndex()) {
                                if (type == element) {
                                    itemNumList[index]++
                                    idList.add(count, id)
                                    break
                                } else {
                                    count += itemNumList[index]
                                }
                            }
                            p0.pointItemNum = itemNumList
                            p0.pointId = idList
                            p0.userCollection++
                            p0.update(object : UpdateListener() {
                                override fun done(p0: BmobException?) {
                                    if (p0 != null) baseDataCallback.onGetFailed(p0.toString(), "70035")
                                    else baseDataCallback.onGetSuccess()
                                }
                            })
                        }
                        //取消收藏
                        false -> {
                            for ((index, element) in interestList.withIndex()) {
                                if (element == type) {
                                    itemNumList[index]--
                                    break
                                }
                            }
                            idList.remove(id)
                            p0.pointItemNum = itemNumList
                            p0.pointId = idList
                            p0.userCollection--
                            p0.update(object : UpdateListener() {
                                override fun done(p0: BmobException?) {
                                    if (p0 != null) baseDataCallback.onGetFailed(p0.toString(), "70032")
                                    else baseDataCallback.onGetSuccess()
                                }
                            })
                        }
                    }
                }
            }
        })
    }

    /**
     * 获取推荐节目数据
     */
    override fun getProgramRecommend(programType: Int, onProgramDataCallback: ProgramInterface.OnProgramDataCallback) {
        var query = BmobQuery<ProgramBean>()
        query.addWhereEqualTo(ProgramBean.PROGRAM_TYPE, programType)
        query.findObjects(object : FindListener<ProgramBean>() {
            override fun done(p0: MutableList<ProgramBean>?, p1: BmobException?) {
                if (p1 != null) onProgramDataCallback.onGetFailed(p1.toString(), "70007")
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