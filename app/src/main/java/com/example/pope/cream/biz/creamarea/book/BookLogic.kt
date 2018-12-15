package com.example.pope.cream.biz.creamarea.book

import android.content.Context
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.UpdateListener
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.beans.BookBean
import com.example.pope.cream.biz.beans.UserBean

class BookLogic : BaseLogic(), BookInterface {

    /**
     * 用户浏览量+1
     */
    override fun userViewsPP(context: Context, baseDataCallback: BaseDataCallback) {
        val query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) baseDataCallback.onGetFailed(p1.toString(), "70045")
                else {
                    p0!!.userViews++
                    p0.update(object : UpdateListener() {
                        override fun done(p0: BmobException?) {
                            if (p0 != null) baseDataCallback.onGetFailed(p0.toString(), "70046")
                            else baseDataCallback.onGetSuccess()
                        }
                    })
                }
            }
        })
    }

    /**
     * 不收藏此书
     */
    override fun uncollectThisBook(id: String, context: Context, baseDataCallback: BaseDataCallback) {

        val query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) baseDataCallback.onGetFailed(p1.toString(), "70021")
                else {
                    val userBean = p0
                    var interestList = userBean!!.userInterestPoint
                    for ((index, element) in interestList.withIndex()) {
                        if (element == "书籍") {
                            userBean.pointItemNum[index]--
                            break
                        }
                    }
                    //使用remove会发生同时迭代冲突异常
                    //userBean.pointId.remove(element)
                    val newIdList = arrayListOf<String>()
                    for (element in userBean.pointId) {
                        if (element != id) {
                            newIdList.add(element)
                        }
                    }
                    userBean.pointId = newIdList
                    userBean.userCollection--
                    userBean.update(object : UpdateListener() {
                        override fun done(p0: BmobException?) {
                            if (p0 != null) baseDataCallback.onGetFailed(p0.toString(), "70022")
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
    override fun collectStateCheck(id: String, context: Context, collectStateCheckCallback: BookInterface.CollectStateCheckCallback) {

        val query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) collectStateCheckCallback.onGetFailed(p1.toString(), "70023")
                else {
                    val idList = p0!!.pointId
                    for (element in idList) {
                        if (element == id) {
                            collectStateCheckCallback.onGetSuccess(true)
                            return
                        }
                    }
                    collectStateCheckCallback.onGetSuccess(false)
                }
            }
        })

    }

    /**
     * 收藏此书
     */
    override fun collectThisBook(id: String, context: Context, baseDataCallback: BaseDataCallback) {
        val query = BmobQuery<UserBean>()
        query.getObject(getLocalUserObjId(context), object : QueryListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 != null) baseDataCallback.onGetFailed(p1.toString(), "70019")
                else {
                    val userBean = p0
                    var count = 0
                    var interestList = userBean!!.userInterestPoint
                    for ((index, element) in interestList.withIndex()) {
                        if (element == "书籍") {
                            userBean!!.pointItemNum[index]++
                            userBean!!.pointId.add(count, id)
                            break
                        } else {
                            count += userBean.pointItemNum[index]
                        }
                    }
                    userBean.userCollection++
                    userBean.update(object : UpdateListener() {
                        override fun done(p0: BmobException?) {
                            if (p0 != null) baseDataCallback.onGetFailed(p0.toString(), "70020")
                            else baseDataCallback.onGetSuccess()
                        }
                    })
                }
            }
        })
    }

    /**
     * 获取书籍列表数据
     */
    override fun getBookListData(bookType: Int, onBookListDataCallback: BookInterface.OnBookListDataCallback) {

        val query = BmobQuery<BookBean>()
        query.addWhereEqualTo(BookBean.BOOK_TYPE, bookType)
        query.findObjects(object : FindListener<BookBean>() {
            override fun done(p0: MutableList<BookBean>?, p1: BmobException?) {
                if (p1 != null) onBookListDataCallback.onGetFailed(p1.toString(), "70013")
                else onBookListDataCallback.onGetSuccess(p0!!)
            }
        })

    }

    private object Holder {
        internal var INSTANCE = BookLogic()
    }

    companion object {

        private val INSTANCE: BookLogic? = null

        val instance: BookLogic
            get() = Holder.INSTANCE
    }

}