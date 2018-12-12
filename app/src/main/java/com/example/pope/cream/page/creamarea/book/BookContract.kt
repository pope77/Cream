package com.example.pope.cream.page.creamarea.book

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

}