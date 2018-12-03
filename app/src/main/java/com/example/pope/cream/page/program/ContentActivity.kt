package com.example.pope.cream.page.program

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.ProgramBean

class ContentActivity : AppCompatActivity() {

    var programType = 0
    lateinit var programBean:ProgramBean
    private lateinit var programFragment: ProgramFragment

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
        var fragmentName = intent.getStringExtra("fragmentName")
        when (fragmentName) {
            "美食" -> {
            }
            "电影" -> {
                programType = ProgramBean.PROGRAM_TYPE_MOVIE
                programFragment = ProgramFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_contentActivity_container,programFragment).commit()
            }
            "外卖" -> {
            }
            "音乐" -> {
            }
            "饮品" -> {
            }
            "综艺" -> {
                programType = ProgramBean.PROGRAM_TYPE_VIRTY
                programFragment = ProgramFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_contentActivity_container,programFragment).commit()
            }
            "书籍" -> {
            }
            "网文" -> {
            }
            "软件" -> {
            }
            "硬件" -> {
            }
            "生活" -> {
            }
            "风景" -> {
            }
        }

    }

    fun changeFragment(fragment:Any){

        when(fragment){
            is ProgramDetailFragment ->{
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_contentActivity_container,fragment).commit()
            }
        }

    }

}
