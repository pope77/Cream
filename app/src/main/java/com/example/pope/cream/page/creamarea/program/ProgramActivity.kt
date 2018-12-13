package com.example.pope.cream.page.creamarea.program

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
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
    var isSpecial = false

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
        isSpecial = intent.getBooleanExtra("特殊", false)
        if (isSpecial) {
            val id = intent.getStringExtra("id")
            val query = BmobQuery<ProgramBean>()
            query.getObject(id, object : QueryListener<ProgramBean>() {
                override fun done(p0: ProgramBean?, p1: BmobException?) {
                    if (p1 != null) {
                        Toast.makeText(this@ProgramActivity, "error70038", Toast.LENGTH_SHORT).show()
                        Log.i("error70038", p1.toString())
                    } else {
                        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_contentActivity_container, ProgramDetailFragment(p0!!)).commit()
                    }
                }
            })
        } else {
            val fragmentName = intent.getStringExtra("fragmentName")
            when (fragmentName) {
                "电影" -> {
                    programType = ProgramBean.PROGRAM_TYPE_MOVIE
                    currentFragmentCode = 1
                    programFragment = ProgramFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout_contentActivity_container, programFragment).commit()
                }
                "综艺" -> {
                    programType = ProgramBean.PROGRAM_TYPE_VIRTY
                    currentFragmentCode = 1
                    programFragment = ProgramFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout_contentActivity_container, programFragment).commit()
                }
            }
        }
    }

    fun changeFragment(fragment: Any) {

        when (fragment) {
            is ProgramDetailFragment -> {
                programDetailFragment = fragment
                supportFragmentManager.beginTransaction().hide(programFragment)
                        .replace(R.id.frameLayout_contentActivity_container, fragment).commit()
                currentFragmentCode = 2
            }
            is ProgramFragment -> {
                supportFragmentManager.beginTransaction().remove(programDetailFragment)
                        .replace(R.id.frameLayout_contentActivity_container, programFragment).commit()
                currentFragmentCode = 1
            }
        }
    }

    override fun onBackPressed() {
        if (isSpecial) {
            finish()
        } else {
            when (currentFragmentCode) {
                1 -> finish()
                2 -> {
                    changeFragment(programFragment)
                }
            }
        }
    }

}
