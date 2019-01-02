package com.example.pope.cream.page.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * @author popeg
 */
open class BaseFragment<T : BasePresenter> : Fragment() {

    protected var progressDialog: ProgressDialog? = null

    var mPresenter: T? = null

    //toast显示 默认时间short
    fun tst(msg: String, length: Int = Toast.LENGTH_SHORT) {
        var s = "error"
        for (element in 1..80) {
            s += element
            if (s == msg) {
                return
            }
        }
        Toast.makeText(activity, msg, length).show()
    }

    /**
     * 显示ProgressDialog
     * 默认标题：请稍等
     * 默认内容：正在加载...
     */
    fun showProgressDialog(title: String = "请稍等", msg: String = "正在加载...") {
        progressDialog?.setTitle(title)
        progressDialog?.setMessage(msg)
        progressDialog?.show()
    }

    /**
     * 隐藏ProgressDialog
     */
    fun hideProgressDialog() {
        progressDialog?.dismiss()
    }

    /**
     * 绑定Presenter
     */
    fun bindPresenter(presenter: T) {
        mPresenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter?.onCreate()
    }

    override fun onStart() {
        super.onStart()
        mPresenter?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mPresenter?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mPresenter?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()
    }

}
