package com.example.pope.cream.biz

import android.content.Context

import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.creamarea.book.BookInterface
import com.example.pope.cream.biz.creamarea.book.BookLogic
import com.example.pope.cream.biz.creamarea.delicious.CateInterface
import com.example.pope.cream.biz.creamarea.delicious.CateLogic
import com.example.pope.cream.biz.creamarea.music.MusicInterface
import com.example.pope.cream.biz.creamarea.music.MusicLogic
import com.example.pope.cream.biz.home.HomeInterface
import com.example.pope.cream.biz.home.HomeLogic
import com.example.pope.cream.biz.login.LoginInterface
import com.example.pope.cream.biz.login.LoginLogic
import com.example.pope.cream.biz.creamarea.program.ProgramInterface
import com.example.pope.cream.biz.creamarea.program.ProgramLogic
import com.example.pope.cream.biz.creamarea.scenery.SceneryInterface
import com.example.pope.cream.biz.creamarea.scenery.SceneryLogic

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

    val cateInterface: CateInterface
        get() = CateLogic.instance

    val musicInterface: MusicInterface
        get() = MusicLogic.instance

    val sceneryInterface: SceneryInterface
        get() = SceneryLogic.instance

    val bookInterface: BookInterface
        get() = BookLogic.instance

    fun init(context: Context) {
        BaseLogic.initialize(context)
    }

}
