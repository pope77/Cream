package com.example.pope.cream.page.creamarea.program

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.ProgramBean

class ProgramActivity : AppCompatActivity() {

    /**
     * 电影与综艺分类标记
     *  1->电影
     * -1->综艺
     */
    var programType = 0
    var currentFragmentCode: Int = 0
    private lateinit var programFragment: ProgramFragment
    private lateinit var programDetailFragment: ProgramDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        //更改状态栏为透明并将状态栏图标颜色改为暗色图标
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = this.window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            this.window.statusBarColor = Color.TRANSPARENT
        }

        //从上一个活动获取点击的类型信息 用来判断在这个活动加载哪个Fragment
        val intent = getIntent()
        val fragmentName = intent.getStringExtra("fragmentName")
        when (fragmentName) {
            "电影" -> {
                programType = ProgramBean.PROGRAM_TYPE_MOVIE
                programFragment = ProgramFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_contentActivity_container, programFragment).commit()
            }
            "综艺" -> {
                programType = ProgramBean.PROGRAM_TYPE_VIRTY
                programFragment = ProgramFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_contentActivity_container, programFragment).commit()
            }
        }

    }

    fun changeFragment(fragment: Any) {

        when (fragment) {
            is ProgramDetailFragment -> {
                programDetailFragment = fragment
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_contentActivity_container, fragment).commit()
                currentFragmentCode = 1
            }
            is ProgramFragment -> {
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_contentActivity_container, programFragment).commit()
                currentFragmentCode = 2
            }
        }

    }

    override fun onBackPressed() {
        when (currentFragmentCode) {
            0 -> finish()
            1 -> {
                changeFragment(programFragment)
                currentFragmentCode = 0
            }
        }
    }

}
