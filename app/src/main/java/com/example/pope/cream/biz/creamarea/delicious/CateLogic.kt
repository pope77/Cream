package com.example.pope.cream.biz.creamarea.delicious

import android.content.Context
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.UpdateListener
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.beans.CateBean
import com.example.pope.cream.biz.beans.RecommendMsgBean
import com.example.pope.cream.biz.beans.UserBean

class CateLogic : BaseLogic(), CateInterface {

    /**
     * 获取Banner数据
     */
    override fun getBannerData(cateType: Int, onBannerDataCallback: CateInterface.OnBannerDataCallback) {
        val query = BmobQuery<RecommendMsgBean>()
        when (cateType) {
            CateBean.CATE_TYPE_FOOD -> query.addWhereEqualTo(RecommendMsgBean.RECOMMEND_POS,
                    RecommendMsgBean.RECOMMEND_POS_FOOD)
            CateBean.CATE_TYPE_DRINK -> query.addWhereEqualTo(RecommendMsgBean.RECOMMEND_POS,
                    RecommendMsgBean.RECOMMEND_POS_DRINK)
        }
        query.findObjects(object : FindListener<RecommendMsgBean>() {
            override fun done(p0: MutableList<RecommendMsgBean>?, p1: BmobException?) {
                if (p1 != null) onBannerDataCallback.onGetFailed(p1.toString(), "70056")
                else {
                    //至多展示4个推荐
                    var beans = arrayListOf<RecommendMsgBean>()
                    if (p0!!.size > 4) {
                        for (i in 0..3) {
                            beans.add(p0[i])
                        }
                    } else {
                        beans = (p0 as ArrayList<RecommendMsgBean>?)!!
                    }
                    val dataBeans = arrayListOf<CateBean>()
                    for (element in beans) {
                        val query = BmobQuery<CateBean>()
                        query.getObject(element.recommendObjectId, object : QueryListener<CateBean>() {
                            override fun done(p0: CateBean?, p1: BmobException?) {
                                if (p1 != null) onBannerDataCallback.onGetFailed(p1.toString(), "70057")
                                else {
                                    dataBeans.add(p0!!)
                                    if (dataBeans.size == beans.size) {
                                        onBannerDataCallback.onGetSuccess(beans, dataBeans)
                                    }
                                }
                            }
                        })
                    }
                }
            }
        })
    }

    /**
     * 用户浏览量+1
     */
    override fun userViewsPP(context: Context, baseDataCallback: BaseDataCallback) {
        val query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) baseDataCallback.onGetFailed(p1.toString(), "70047")
                else {
                    p0!!.userViews++
                    p0.update(object : UpdateListener() {
                        override fun done(p0: BmobException?) {
                            if (p0 != null) baseDataCallback.onGetFailed(p0.toString(), "70048")
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
    override fun checkCollectState(context: Context, id: String, collectStateChangeCallback: CateInterface.CollectStateCheckCallback) {
        val query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) collectStateChangeCallback.onGetFailed(p1.toString(), "70030")
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
                if (p1 != null) baseDataCallback.onGetFailed(p1.toString(), "70028")
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
                                    if (p0 != null) baseDataCallback.onGetFailed(p0.toString(), "70029")
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
                                    if (p0 != null) baseDataCallback.onGetFailed(p0.toString(), "70033")
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
     * 获取饮食类数据逻辑
     */
    override fun getCateRecommendData(cateType: Int, onCateDataCallback: CateInterface.OnCateDataCallback) {
        val query = BmobQuery<CateBean>()
        query.addWhereEqualTo(CateBean.CATE_TYPE, cateType)
        query.findObjects(object : FindListener<CateBean>() {
            override fun done(p0: MutableList<CateBean>?, p1: BmobException?) {
                if (p1 != null) onCateDataCallback.onGetFailed(p1.toString(), "70009")
                else onCateDataCallback.onGetSuccess(p0!!)
            }

        })
    }

    private object Holder {
        internal var INSTANCE = CateLogic()
    }

    companion object {
        private val INSTANCE: CateLogic? = null
        val instance: CateLogic
            get() = Holder.INSTANCE
    }

}