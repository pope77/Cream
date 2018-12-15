package com.example.pope.cream.page

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import com.example.pope.cream.app.MyApplication.Companion.application
import com.example.pope.cream.page.creamarea.book.BookActivity
import com.example.pope.cream.page.creamarea.delicious.CateActivity
import com.example.pope.cream.page.creamarea.program.ProgramActivity
import com.example.pope.cream.page.creamarea.scenery.SceneryActivity

object PublicViewLogic {

    fun specialJump(type:String,id:String){

        when (type) {
            "美食", "饮品" -> {
                val intent = Intent(application, CateActivity::class.java)
                intent.putExtra("特殊", true)
                intent.putExtra("id", id)
                startActivity(application,intent,null)
            }
            "电影", "综艺" -> {
                val intent = Intent(application, ProgramActivity::class.java)
                intent.putExtra("特殊", true)
                intent.putExtra("id", id)
                startActivity(application,intent,null)
            }
            "书籍" -> {
                val intent = Intent(application, BookActivity::class.java)
                intent.putExtra("特殊", true)
                intent.putExtra("id", id)
                startActivity(application,intent,null)
            }
            "风景" -> {
                val intent = Intent(application, SceneryActivity::class.java)
                intent.putExtra("特殊", true)
                intent.putExtra("id", id)
                startActivity(application,intent,null)
            }
        }

    }

}