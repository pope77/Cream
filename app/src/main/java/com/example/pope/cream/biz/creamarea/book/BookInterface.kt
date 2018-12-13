package com.example.pope.cream.biz.creamarea.book

import android.content.Context
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.BookBean

interface BookInterface {

    interface OnBookListDataCallback:BaseDataCallback{

        fun onGetSuccess(bookBeans:MutableList<BookBean>)

    }

    fun getBookListData(bookType:Int,onBookListDataCallback: OnBookListDataCallback)

    interface OnCollectCallback:BaseDataCallback{

        fun onGetSuccess()

    }

    fun collectThisBook(id:String,context: Context,onCollectCallback: OnCollectCallback)

    interface OnUncollectCallback:BaseDataCallback{

        fun onGetSuccess()

    }

    fun uncollectThisBook(id: String,context: Context,onUncollectCallback: OnUncollectCallback)

    interface CollectStateCheckCallback:BaseDataCallback{

        fun onGetSuccess(isCollected:Boolean)

    }

    fun collectStateCheck(id: String,context: Context,collectStateCheckCallback: CollectStateCheckCallback)

}