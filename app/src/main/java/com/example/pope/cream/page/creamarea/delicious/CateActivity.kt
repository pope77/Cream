package com.example.pope.cream.page.creamarea.delicious

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.pope.cream.R

class CateActivity : AppCompatActivity() {

    /**
     * 美食与饮品分类标记
     *   1->美食
     *  -1->饮品
     */
    var cateType = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cate)

        val intent = getIntent()
        val fragmentName =  intent.getStringExtra("fragmentName")
        when(fragmentName){
            "美食"->{
                cateType = 1
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_cateActivity_container,CateFragment()).commit()
            }
            "饮品"->{
                cateType = -1
            }
        }

    }
}
