package com.example.pope.cream.biz.creamarea.book

import android.content.Context
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.BookBean

interface BookInterface {

    interface OnBookListDataCallback : BaseDataCallback {

        fun onGetSuccess(bookBeans: MutableList<BookBean>)

    }

    fun getBookListData(bookType: Int, onBookListDataCallback: OnBookListDataCallback)

    fun collectThisBook(id: String, context: Context, baseDataCallback: BaseDataCallback)

    fun uncollectThisBook(id: String, context: Context, baseDataCallback: BaseDataCallback)

    interface CollectStateCheckCallback : BaseDataCallback {

        fun onGetSuccess(isCollected: Boolean)

    }

    fun collectStateCheck(id: String, context: Context, collectStateCheckCallback: CollectStateCheckCallback)

    fun userViewsPP(context: Context, baseDataCallback: BaseDataCallback)

}