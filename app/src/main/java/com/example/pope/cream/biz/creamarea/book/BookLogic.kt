package com.example.pope.cream.biz.creamarea.book

import cn.bmob.v3.BmobObject
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.beans.BookBean

class BookLogic:BaseLogic(),BookInterface {

    /**
     * 获取书籍列表数据
     */
    override fun getBookListData(bookType: Int, onBookListDataCallback: BookInterface.OnBookListDataCallback) {

        val query = BmobQuery<BookBean>()
        query.addWhereEqualTo(BookBean.BOOK_TYPE,bookType)
        query.findObjects(object:FindListener<BookBean>(){
            override fun done(p0: MutableList<BookBean>?, p1: BmobException?) {
                if (p1!=null) onBookListDataCallback.onGetFailed(p1.toString(),"70013")
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