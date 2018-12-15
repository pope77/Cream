package com.example.pope.cream.page.creamarea.book

import android.util.Log
import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.beans.BookBean
import com.example.pope.cream.biz.creamarea.book.BookInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class BookListPresenter(val bookListView: BookContract.BookListView) : BasePresenterImpl(), BookContract.BookListPresenter {

    /**
     * 获取书籍类别列表
     */
    override fun getBookListData(typeCode: Int) {

        bookInterface.getBookListData(typeCode, object : BookInterface.OnBookListDataCallback {
            override fun onGetSuccess(bookBeans: MutableList<BookBean>) {
                bookListView.loadData(bookBeans)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                bookListView.toast("error$errorCode")
                Log.i("error$errorCode", errorMsg)
            }
        })

    }

    private val bookInterface = ModelFactory.bookInterface

    init {
        bookListView.bindPresenter(this)
    }

}