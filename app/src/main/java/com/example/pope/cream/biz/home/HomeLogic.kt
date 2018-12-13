package com.example.pope.cream.biz.home

import android.content.Context
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.beans.*

class HomeLogic : BaseLogic(), HomeInterface {

    /**
     * 获取收藏列表的Bean
     */
    override fun getCollectionListBeans(type: String, idList: MutableList<String>, onListBeansCallback: HomeInterface.OnListBeansCallback) {


        when (type) {
            "美食", "饮品" -> {
                val beanList = arrayListOf<CateBean>()
                for (element in idList) {
                    val query = BmobQuery<CateBean>()
                    query.getObject(element, object : QueryListener<CateBean>() {
                        override fun done(p0: CateBean?, p1: BmobException?) {
                            if (p1 != null) onListBeansCallback.onGetFailed(p1.toString(), "70015")
                            else {
                                beanList.add(p0!!)
                                if (beanList.size == idList.size) {
                                    onListBeansCallback.onGetSuccess(beanList)
                                }
                            }
                        }
                    })
                }
            }
            "电影", "综艺" -> {
                val beanList = arrayListOf<ProgramBean>()
                for (element in idList) {
                    val query = BmobQuery<ProgramBean>()
                    query.getObject(element, object : QueryListener<ProgramBean>() {
                        override fun done(p0: ProgramBean?, p1: BmobException?) {
                            if (p1 != null) onListBeansCallback.onGetFailed(p1.toString(), "70016")
                            else {
                                beanList.add(p0!!)
                                if (beanList.size == idList.size) {
                                    onListBeansCallback.onGetSuccess1(beanList)
                                }
                            }
                        }
                    })
                }
            }
            "书籍" -> {
                val beanList = arrayListOf<BookBean>()
                for (element in idList) {
                    val query = BmobQuery<BookBean>()
                    query.getObject(element, object : QueryListener<BookBean>() {
                        override fun done(p0: BookBean?, p1: BmobException?) {
                            if (p1 != null) onListBeansCallback.onGetFailed(p1.toString(), "70017")
                            else {
                                beanList.add(p0!!)
                                if (beanList.size == idList.size) {
                                    onListBeansCallback.onGetSuccess2(beanList)
                                }
                            }
                        }
                    })
                }
            }
            "风景" -> {
                val beanList = arrayListOf<SceneryBean>()
                for (element in idList) {
                    val query = BmobQuery<SceneryBean>()
                    query.getObject(element, object : QueryListener<SceneryBean>() {
                        override fun done(p0: SceneryBean?, p1: BmobException?) {
                            if (p1 != null) onListBeansCallback.onGetFailed(p1.toString(), "70018")
                            else {
                                beanList.add(p0!!)
                                if (beanList.size == idList.size) {
                                    onListBeansCallback.onGetSuccess3(beanList)
                                }
                            }
                        }
                    })
                }
            }
        }

    }

    /**
     * 获取兴趣数据细节
     */
    override fun getInterestDetail(context: Context, onInterestDetailCallback: HomeInterface.OnInterestDetailCallback) {

        var query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) onInterestDetailCallback.onGetFailed(p1.toString(), "70014")
                else {
                    onInterestDetailCallback.onGetSuccess(p0!!)
                }
            }

        })

    }

    /**
     * 获取兴趣数据
     */
    override fun getInterestData(context: Context, onInterestDataCallback: HomeInterface.OnInterestDataCallback) {

        var query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) onInterestDataCallback.onGetFailed(p1.toString(), "70006")
                else {
                    onInterestDataCallback.onGetSuccess(p0!!.userInterestPoint)
                }
            }

        })

    }

    private object Holder {
        internal var INSTANCE = HomeLogic()
    }

    companion object {

        private val INSTANCE: HomeLogic? = null

        val instance: HomeLogic
            get() = Holder.INSTANCE
    }

}
