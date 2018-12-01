package com.example.pope.cream.page.base

import android.widget.Toast

/**
 * @author popeg
 */
interface BaseView<T> {

    fun bindPresenter(presenter: T)

    fun toast(msg: String,length:Int = Toast.LENGTH_SHORT)

}
