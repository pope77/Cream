package com.example.pope.cream.page.creamarea.delicious

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.CateBean

class CateActivity : AppCompatActivity() {

    /**
     * 美食与饮品分类标记
     *   1->美食
     *  -1->饮品
     */
    var cateType = 0
    var currentPosition = 0
    lateinit var cateDetailFragment: CateDetailFragment
    lateinit var cateFragment: CateFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cate)

        val intent = getIntent()
        val fragmentName = intent.getStringExtra("fragmentName")
        when (fragmentName) {
            "美食" -> {
                cateType = CateBean.CATE_TYPE_FOOD
                currentPosition = 1
                cateFragment = CateFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_cateActivity_container, cateFragment).commit()
            }
            "饮品" -> {
                cateType = CateBean.CATE_TYPE_DRINK
                currentPosition = 1
                cateFragment = CateFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_cateActivity_container, cateFragment).commit()
            }
        }

    }

    /**
     * 更换fragment
     */
    fun changeFragment(fragment: Any) {

        when (fragment) {
            is CateDetailFragment -> {
                cateDetailFragment = fragment
                supportFragmentManager.beginTransaction().hide(cateFragment)
                        .replace(R.id.frameLayout_cateActivity_container, fragment).commit()
                currentPosition = 2
            }
            is CateFragment -> {
                supportFragmentManager.beginTransaction().remove(cateDetailFragment)
                        .replace(R.id.frameLayout_cateActivity_container, cateFragment).commit()
                currentPosition = 1
            }
        }

    }

    override fun onBackPressed() {

        when (currentPosition) {
            1 -> finish()
            2 -> changeFragment(cateFragment)
        }

    }

}
