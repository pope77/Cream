package com.example.pope.cream.biz.home

import android.content.Context
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.UpdateListener
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.beans.*
import java.util.*

class HomeLogic : BaseLogic(), HomeInterface {

    /**
     * 获得随机数据bean的id
     */
    override fun getRandomId(type: String, onRandomJumpCallback: HomeInterface.OnRandomJumpCallback) {
        when (type) {

            "美食", "饮品" -> {
                val query = BmobQuery<CateBean>()
                if (type == "美食") {
                    query.addWhereEqualTo(CateBean.CATE_TYPE, CateBean.CATE_TYPE_FOOD)
                } else {
                    query.addWhereEqualTo(CateBean.CATE_TYPE, CateBean.CATE_TYPE_DRINK)
                }
                query.findObjects(object : FindListener<CateBean>() {
                    override fun done(p0: MutableList<CateBean>?, p1: BmobException?) {
                        if (p1 != null) onRandomJumpCallback.onGetFailed(p1.toString(), "70071")
                        else {
                            if (p0!!.isEmpty()) {
                                onRandomJumpCallback.onGetFailed("获取type=$type 类的数据集时，数据为空，请尽快添加数据", "20001")
                            } else {
                                val bean = p0[Random().nextInt(p0.size)]
                                onRandomJumpCallback.onGetSuccess(type, bean.objectId)
                            }
                        }
                    }
                })
            }
            "电影", "综艺" -> {
                val query = BmobQuery<ProgramBean>()
                if (type == "电影") {
                    query.addWhereEqualTo(ProgramBean.PROGRAM_TYPE, ProgramBean.PROGRAM_TYPE_MOVIE)
                } else {
                    query.addWhereEqualTo(ProgramBean.PROGRAM_TYPE, ProgramBean.PROGRAM_TYPE_VIRTY)
                }
                query.findObjects(object : FindListener<ProgramBean>() {
                    override fun done(p0: MutableList<ProgramBean>?, p1: BmobException?) {
                        if (p1 != null) onRandomJumpCallback.onGetFailed(p1.toString(), "70072")
                        else {
                            if (p0!!.isEmpty()) {
                                onRandomJumpCallback.onGetFailed("获取type=$type 类的数据集时，数据为空，请尽快添加数据", "20002")
                            } else {
                                val bean = p0[Random().nextInt(p0.size)]
                                onRandomJumpCallback.onGetSuccess(type, bean.objectId)
                            }
                        }
                    }
                })
            }
            "风景" -> {
                val query = BmobQuery<SceneryBean>()
                query.addWhereNotEqualTo(SceneryBean.SCENERY_UICODE, 666)
                query.findObjects(object : FindListener<SceneryBean>() {
                    override fun done(p0: MutableList<SceneryBean>?, p1: BmobException?) {
                        if (p1 != null) onRandomJumpCallback.onGetFailed(p1.toString(), "70073")
                        else {
                            if (p0!!.isEmpty()) {
                                onRandomJumpCallback.onGetFailed("获取type=$type 类的数据集时，数据为空，请尽快添加数据", "20003")
                            } else {
                                val bean = p0[Random().nextInt(p0.size)]
                                onRandomJumpCallback.onGetSuccess(type,bean.objectId)
                            }
                        }
                    }
                })
            }
            "书籍"->{
                val query = BmobQuery<BookBean>()
                query.addWhereNotEqualTo(BookBean.BOOK_TYPE,1666)
                query.findObjects(object : FindListener<BookBean>() {
                    override fun done(p0: MutableList<BookBean>?, p1: BmobException?) {
                        if (p1 != null) onRandomJumpCallback.onGetFailed(p1.toString(), "70074")
                        else {
                            if (p0!!.isEmpty()) {
                                onRandomJumpCallback.onGetFailed("获取type=$type 类的数据集时，数据为空，请尽快添加数据", "20004")
                            } else {
                                val bean = p0[Random().nextInt(p0.size)]
                                onRandomJumpCallback.onGetSuccess(type,bean.objectId)
                            }
                        }
                    }
                })
            }

        }
    }

    /**
     * 修改昵称
     */
    override fun changeUserName(newName: String, context: Context, baseDataCallback: BaseDataCallback) {
        val query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) baseDataCallback.onGetFailed(p1.toString(), "70059")
                else {
                    p0!!.userName = newName
                    p0.update(object : UpdateListener() {
                        override fun done(p0: BmobException?) {
                            if (p0 != null) baseDataCallback.onGetFailed(p0.toString(), "70060")
                            else baseDataCallback.onGetSuccess()
                        }
                    })
                }
            }
        })
    }

    /**
     * 获取用户bean
     */
    override fun getUserBean(context: Context, onUserBeanCallback: HomeInterface.OnUserBeanCallback) {
        val query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) onUserBeanCallback.onGetFailed(p1.toString(), "70058")
                else onUserBeanCallback.onGetSuccess(p0!!)
            }
        })
    }

    /**
     * 获取Banner数据
     */
    override fun getBannerData(onBannerDataCallback: HomeInterface.OnBannerDataCallback) {
        val query = BmobQuery<RecommendMsgBean>()
        query.addWhereEqualTo(RecommendMsgBean.RECOMMEND_POS, RecommendMsgBean.RECOMMEND_POS_HOME)
        query.findObjects(object : FindListener<RecommendMsgBean>() {
            override fun done(p0: MutableList<RecommendMsgBean>?, p1: BmobException?) {
                if (p1 != null) onBannerDataCallback.onGetFailed(p1.toString(), "70055")
                else {
                    //至多展示4个推荐
                    if (p0!!.size > 4) {
                        val beans = arrayListOf<RecommendMsgBean>()
                        for (i in 0..3) {
                            beans.add(p0[i])
                        }
                        onBannerDataCallback.onGetSuccess(beans)
                    } else {
                        onBannerDataCallback.onGetSuccess(p0)
                    }
                }
            }
        })
    }

    /**
     * 获取收藏列表的bean
     */
    override fun getCollectionListBeans(context: Context, type: String, onListBeansCallback: HomeInterface.OnListBeansCallback) {

        val query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) onListBeansCallback.onGetFailed(p1.toString(), "70040")
                else {
                    val interestList = p0!!.userInterestPoint
                    val itemNumList = p0.pointItemNum
                    val idList = p0.pointId
                    val childIdList = arrayListOf<String>()
                    var count = 0
                    for ((index, element) in interestList.withIndex()) {
                        if (element == type) {
                            val num = itemNumList[index]
                            if (num == 0) {
                                //该类别下无收藏数据
                                onListBeansCallback.onGetSuccess(arrayListOf())
                            } else {
                                for (i in 1..num) {
                                    childIdList.add(idList[count + i - 1])
                                }
                            }
                            break
                        } else {
                            count += itemNumList[index]
                        }
                    }
                    when (type) {
                        "美食", "饮品" -> {
                            val beanList = arrayListOf<CateBean>()
                            for (element in childIdList) {
                                val query = BmobQuery<CateBean>()
                                query.getObject(element, object : QueryListener<CateBean>() {
                                    override fun done(p0: CateBean?, p1: BmobException?) {
                                        if (p1 != null) onListBeansCallback.onGetFailed(p1.toString(), "70041")
                                        else {
                                            beanList.add(p0!!)
                                            if (beanList.size == childIdList.size) {
                                                onListBeansCallback.onGetSuccess(beanList)
                                            }
                                        }
                                    }
                                })
                            }
                        }
                        "电影", "综艺" -> {
                            val beanList = arrayListOf<ProgramBean>()
                            for (element in childIdList) {
                                val query = BmobQuery<ProgramBean>()
                                query.getObject(element, object : QueryListener<ProgramBean>() {
                                    override fun done(p0: ProgramBean?, p1: BmobException?) {
                                        if (p1 != null) onListBeansCallback.onGetFailed(p1.toString(), "70042")
                                        else {
                                            beanList.add(p0!!)
                                            if (beanList.size == childIdList.size) {
                                                onListBeansCallback.onGetSuccess1(beanList)
                                            }
                                        }
                                    }
                                })
                            }
                        }
                        "书籍" -> {
                            val beanList = arrayListOf<BookBean>()
                            for (element in childIdList) {
                                val query = BmobQuery<BookBean>()
                                query.getObject(element, object : QueryListener<BookBean>() {
                                    override fun done(p0: BookBean?, p1: BmobException?) {
                                        if (p1 != null) onListBeansCallback.onGetFailed(p1.toString(), "70043")
                                        else {
                                            beanList.add(p0!!)
                                            if (beanList.size == childIdList.size) {
                                                onListBeansCallback.onGetSuccess2(beanList)
                                            }
                                        }
                                    }
                                })
                            }
                        }
                        "风景" -> {
                            val beanList = arrayListOf<SceneryBean>()
                            for (element in childIdList) {
                                val query = BmobQuery<SceneryBean>()
                                query.getObject(element, object : QueryListener<SceneryBean>() {
                                    override fun done(p0: SceneryBean?, p1: BmobException?) {
                                        if (p1 != null) onListBeansCallback.onGetFailed(p1.toString(), "70044")
                                        else {
                                            beanList.add(p0!!)
                                            if (beanList.size == childIdList.size) {
                                                onListBeansCallback.onGetSuccess3(beanList)
                                            }
                                        }
                                    }
                                })
                            }
                        }
                    }
                }
            }
        })
    }

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
