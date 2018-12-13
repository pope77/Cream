package com.example.pope.cream.page.creamarea.book

import android.content.Context
import com.example.pope.cream.biz.beans.BookBean
import com.example.pope.cream.page.base.BasePresenter
import com.example.pope.cream.page.base.BaseView

interface BookContract{

    interface BookListPresenter:BasePresenter{

        fun getBookListData(typeCode: Int)

    }

    interface BookListView:BaseView<BookListPresenter>{

        fun loadData(bookBeans: MutableList<BookBean>)

    }

    interface BookDetailPresenter:BasePresenter{

        fun collectBook(id:String,context: Context)

        fun checkIsCollected(id: String, context: Context)

        fun uncollectThisBook(id: String, context: Context)

        fun userViewsPP(context: Context)

    }

    interface BookDetailView:BaseView<BookDetailPresenter>{

        fun collectSuccess()

        fun checkResult(isCollected:Boolean)

        fun uncollectSuccess()

    }

}