package com.example.pope.cream.biz

import android.content.Context

import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.home.HomeInterface
import com.example.pope.cream.biz.home.HomeLogic
import com.example.pope.cream.biz.login.LoginInterface
import com.example.pope.cream.biz.login.LoginLogic
import com.example.pope.cream.biz.program.ProgramInterface
import com.example.pope.cream.biz.program.ProgramLogic

/**
 * 获取M层接口唯一对象的集合类
 * @author popeg
 */
object ModelFactory {

    //获取接口唯一对象
    val loginInterface: LoginInterface
        get() = LoginLogic.instance

    val homeInterface: HomeInterface
        get() = HomeLogic.instance

    val programInterface: ProgramInterface
        get() = ProgramLogic.instance

    fun init(context: Context) {
        BaseLogic.initialize(context)
    }

}
