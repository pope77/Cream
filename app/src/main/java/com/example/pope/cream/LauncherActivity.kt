package com.example.pope.cream

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager

import com.example.pope.cream.page.login.LoginActivity

import com.example.pope.cream.biz.beans.UserBean
import com.example.pope.cream.page.home.MainActivity

/**
 * @author pope
 */
class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //使启动页占满全屏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        decorView.systemUiVisibility = uiOptions

        //设置等待时间，单位为毫秒
        val time = 3000
        val handler = Handler()

        //获取用户本地保存的“UserName”
        //新用户与注册但未选择兴趣的用户该字段为空
        //只有选择了兴趣点的老用户该字段不为空且不为“null”
        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        var userName = sharedPreferences.getString(UserBean.USER_NAME, "")

        //当计时结束时，跳转至主界面
        handler.postDelayed({
            when (userName) {
                "" -> startActivity(Intent(this, LoginActivity::class.java))
                "null" -> startActivity(Intent(this, InterestPointActivity::class.java))
                else -> startActivity(Intent(this, MainActivity::class.java))
            }
            this.finish()
        }, time.toLong())

    }
}
