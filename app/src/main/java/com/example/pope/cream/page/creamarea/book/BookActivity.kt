package com.example.pope.cream.page.creamarea.book

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
import com.example.pope.cream.biz.beans.BookBean

class BookActivity : AppCompatActivity() {

    private var currentPos = 0
    val bookTypeFragment = BookTypeFragment()
    var bookListFragment: BookListFragment? = null
    var bookDetailFragment: BookDetailFragment? = null
    var isSpecial = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        //从intent中获取特殊标记查看本次activity启动是否由收藏列表跳转来的
        val intent = getIntent()
        isSpecial = intent.getBooleanExtra("特殊", false)
        if (isSpecial) {
            val id = intent.getStringExtra("id")
            val query = BmobQuery<BookBean>()
            query.getObject(id, object : QueryListener<BookBean>() {
                override fun done(p0: BookBean?, p1: BmobException?) {
                    if (p1 != null) {
                        Toast.makeText(this@BookActivity, "error70036", Toast.LENGTH_SHORT).show()
                        Log.i("error70036", p1.toString())
                    } else {
                        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_bookActivity_container, BookDetailFragment(p0!!)).commit()
                        currentPos = 3
                    }
                }
            })
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout_bookActivity_container, bookTypeFragment).commit()
            currentPos = 1
            changeColor()
        }

    }

    private fun changeColor() {

        when (currentPos) {
            1, 3 -> {
                //更改状态栏为透明并将状态栏图标颜色改为暗色图标
                if (Build.VERSION.SDK_INT >= 21) {
                    val decorView = this.window.decorView
                    val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    decorView.systemUiVisibility = option
                    this.window.statusBarColor = Color.TRANSPARENT
                }
            }
            2 -> {
                //更改状态栏为透明并将状态栏图标颜色改为暗色图标
                if (Build.VERSION.SDK_INT >= 21) {
                    val decorView = this.window.decorView
                    val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    decorView.systemUiVisibility = option
                    this.window.statusBarColor = Color.TRANSPARENT
                }
            }
        }

    }

    fun changeFragment(fragment: Any) {
        when (fragment) {
            is BookListFragment -> {
                bookListFragment = fragment
                supportFragmentManager.beginTransaction().hide(bookTypeFragment).replace(R.id.frameLayout_bookActivity_container, bookListFragment).commit()
                currentPos = 2
            }
            is BookDetailFragment -> {
                bookDetailFragment = fragment
                supportFragmentManager.beginTransaction().hide(bookListFragment).replace(R.id.frameLayout_bookActivity_container, bookDetailFragment).commit()
                currentPos = 3
            }
        }
        changeColor()
    }

    override fun onBackPressed() {
        when (currentPos) {
            3 -> {
                if (isSpecial) finish()
                else {
                    supportFragmentManager.beginTransaction().hide(bookDetailFragment).replace(R.id.frameLayout_bookActivity_container, bookListFragment).commit()
                    currentPos = 2
                    bookDetailFragment = null
                }
            }
            2 -> {
                supportFragmentManager.beginTransaction().hide(bookListFragment).replace(R.id.frameLayout_bookActivity_container, bookTypeFragment).commit()
                currentPos = 1
                bookListFragment = null
            }
            1 -> {
                finish()
            }
        }
    }

}