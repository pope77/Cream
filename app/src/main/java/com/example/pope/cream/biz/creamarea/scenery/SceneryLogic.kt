package com.example.pope.cream.biz.creamarea.scenery

import android.content.Context
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.UpdateListener
import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.beans.SceneryBean
import com.example.pope.cream.biz.beans.UserBean

class SceneryLogic : BaseLogic(), SceneryInterface {

    /**
     * 改变收藏状态
     */
    override fun changeCollectState(context: Context, type: String, id: String, collectThisScenery: Boolean, onCollectStateChangeCallback: SceneryInterface.OnCollectStateChangeCallback) {
        val query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) onCollectStateChangeCallback.onGetFailed(p1.toString(), "70025")
                else {
                    val interestList = p0!!.userInterestPoint
                    val itemNumList = p0!!.pointItemNum
                    val idList = p0!!.pointId
                    when (collectThisScenery) {
                        //收藏
                        true -> {
                            var count = 0
                            for ((index, element) in interestList.withIndex()) {
                                if (type == element) {
                                    itemNumList[index]++
                                    idList.add(count,id)
                                    break
                                }else{
                                    count += itemNumList[index]
                                }
                            }
                            p0.pointItemNum = itemNumList
                            p0.pointId = idList
                            p0.userCollection++
                            p0.update(object:UpdateListener(){
                                override fun done(p0: BmobException?) {
                                    if (p0!=null) onCollectStateChangeCallback.onGetFailed(p0.toString(),"70026")
                                    else onCollectStateChangeCallback.onGetSuccess()
                                }
                            })
                        }
                        //取消收藏
                        false -> {
                            for ((index,element) in interestList.withIndex()){
                                if (element == type){
                                    itemNumList[index]--
                                    break
                                }
                            }
                            idList.remove(id)
                            p0.pointItemNum = itemNumList
                            p0.pointId = idList
                            p0.userCollection--
                            p0.update(object:UpdateListener(){
                                override fun done(p0: BmobException?) {
                                    if (p0!=null) onCollectStateChangeCallback.onGetFailed(p0.toString(),"70027")
                                    else onCollectStateChangeCallback.onGetSuccess()
                                }
                            })
                        }
                    }
                }
            }
        })
    }

    /**
     * 获取风景数据
     */
    override fun getSceneryData(context: Context, onGetSceneryDataCallback: SceneryInterface.OnGetSceneryDataCallback) {

        var flag = 0
        var beans = arrayListOf<SceneryBean>()
        var bean: UserBean? = null
        val query = BmobQuery<SceneryBean>()
        query.addWhereNotEqualTo(SceneryBean.SCENERY_UICODE, 0)
        query.findObjects(object : FindListener<SceneryBean>() {
            override fun done(p0: MutableList<SceneryBean>?, p1: BmobException?) {
                if (p1 != null) onGetSceneryDataCallback.onGetFailed(p1.toString(), "70012")
                else {
                    flag++
                    beans = (p0 as ArrayList<SceneryBean>?)!!
                    if (flag == 2) {
                        val idList = bean!!.pointId
                        val isCollectedList = arrayListOf<Boolean>()
                        for (element in beans) {
                            val id = element.objectId
                            for ((index,element1 )in idList.withIndex()) {
                                if (element1 == id) {
                                    isCollectedList.add(true)
                                    break
                                }
                                if (index == idList.size-1){
                                    isCollectedList.add(false)
                                }
                            }
                        }
                        onGetSceneryDataCallback.onGetSuccess(beans, isCollectedList)
                    }
                }
            }
        })
        val query1 = BmobQuery<UserBean>()
        query1.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) onGetSceneryDataCallback.onGetFailed(p1.toString(), "70024")
                else {
                    flag++
                    bean = p0
                    if (flag == 2) {
                        val idList = bean!!.pointId
                        val isCollectedList = arrayListOf<Boolean>()
                        for (element in beans) {
                            val id = element.objectId
                            for ((index,element1 )in idList.withIndex()) {
                                if (element1 == id) {
                                    isCollectedList.add(true)
                                    break
                                }
                                if (index == idList.size-1){
                                    isCollectedList.add(false)
                                }
                            }
                        }
                        onGetSceneryDataCallback.onGetSuccess(beans, isCollectedList)
                    }
                }
            }
        })
    }

    private object Holder {
        internal var INSTANCE = SceneryLogic()
    }

    companion object {

        private val INSTANCE: SceneryLogic? = null

        val instance: SceneryLogic
            get() = Holder.INSTANCE

    }

}