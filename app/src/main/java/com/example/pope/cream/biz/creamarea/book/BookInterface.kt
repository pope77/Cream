package com.example.pope.cream.biz.creamarea.book

import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.beans.BookBean

interface BookInterface {

    interface OnBookListDataCallback:BaseDataCallback{

        fun onGetSuccess(bookBeans:MutableList<BookBean>)

    }

    fun getBookListData(bookType:Int,onBookListDataCallback: OnBookListDataCallback)

}