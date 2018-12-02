package com.example.pope.cream.biz.base

import android.content.Context
import com.example.pope.cream.utils.UserDataUtil

import java.lang.ref.WeakReference

import io.reactivex.annotations.Nullable

/**
 * 基础逻辑类，所有的逻辑类都将继承该类
 * @author popeg
 */
open class BaseLogic : UserDataUtil() {

    protected val context: Context
        @Nullable
        get() = weakReference!!.get()!!

    companion object {

        private var weakReference: WeakReference<Context>? = null

        fun initialize(context: Context) {
            weakReference = WeakReference(context)
        }
    }

}
