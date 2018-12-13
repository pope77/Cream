package com.example.pope.cream.page.creamarea.book

import android.content.Context
import android.util.Log
import com.example.pope.cream.biz.ModelFactory
import com.example.pope.cream.biz.base.BaseDataCallback
import com.example.pope.cream.biz.creamarea.book.BookInterface
import com.example.pope.cream.page.base.BasePresenterImpl

class BookDetailPresenter(val bookDetailView:BookContract.BookDetailView):
        BasePresenterImpl(),BookContract.BookDetailPresenter{

    /**
     * 用户浏览量+1
     */
    override fun userViewsPP(context: Context) {

        bookInterface.userViewsPP(context,object :BaseDataCallback{
            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                bookDetailView.toast("error$errorCode")
                Log.i("error$errorCode",errorMsg)
            }
        })

    }

    /**
     * 检查是否收藏
     */
    override fun checkIsCollected(id: String, context: Context) {

        bookInterface.collectStateCheck(id,context, object : BookInterface.CollectStateCheckCallback {
            override fun onGetSuccess(isCollected: Boolean) {
                bookDetailView.checkResult(isCollected)
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                bookDetailView.toast("error$errorCode")
                Log.i("error$errorCode",errorMsg)
            }

        })

    }

    /**
     * 取消收藏此书
     */
    override fun uncollectThisBook(id: String, context: Context) {
        bookInterface.uncollectThisBook(id,context,object :BookInterface.OnUncollectCallback{
            override fun onGetSuccess() {
                bookDetailView.toast("取消收藏成功")
                bookDetailView.uncollectSuccess()
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                bookDetailView.toast("error$errorCode")
                Log.i("error$errorCode",errorMsg)
            }
        })
    }

    override fun collectBook(id: String, context: Context) {

        bookInterface.collectThisBook(id,context, object : BookInterface.OnCollectCallback {
            override fun onGetSuccess() {
                bookDetailView.collectSuccess()
                bookDetailView.toast("收藏成功")
            }

            override fun onGetFailed(errorMsg: String, errorCode: String) {
                super.onGetFailed(errorMsg, errorCode)
                bookDetailView.toast("error$errorCode")
                Log.i("error$errorCode",errorMsg)
            }
        })

    }


    private val bookInterface = ModelFactory.bookInterface

    init {
        bookDetailView.bindPresenter(this)
    }

}