package com.example.pope.cream.page.home

import android.support.v4.app.Fragment
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AnimationUtils

import com.example.pope.cream.R
import com.example.pope.cream.page.home.adapter.HomeViewPagerAdapter

import java.util.ArrayList

import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author popeg
 */
class MainActivity : AppCompatActivity() {

    /**
     * 保存BottomBar当前显示状态
     */
    private var isShowing: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //初始化
        initSomething(this)

        //更改状态栏为透明并将状态栏图标颜色改为暗色图标
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = this.window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            decorView.systemUiVisibility = option
            this.window.statusBarColor = Color.TRANSPARENT
        }

    }

    /**
     * 初始化
     */
    private fun initSomething(context: Context) {

        val pageList = ArrayList<Fragment>()
        pageList.add(CreamFragment())
        pageList.add(CollectionFragment())
        pageList.add(CenterFragment())
        //设置监听
        viewPager_mainActivity.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                changeSelectedBottomBar(position + 1)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        viewPager_mainActivity.adapter = HomeViewPagerAdapter(supportFragmentManager, this, pageList)
        viewPager_mainActivity.currentItem = 0
        changeSelectedBottomBar(1)

        //BottomBar的点击监听
        imageView_bottombar_cream.setOnClickListener{changeSelectedBottomBar(1)}
        imageView_bottombar_collection.setOnClickListener{changeSelectedBottomBar(2)}
        imageView_bottombar_center.setOnClickListener{changeSelectedBottomBar(3)}

    }

    /**
     * 改变bottombar被选择状态
     *
     * @param newPos
     */
    private fun changeSelectedBottomBar(newPos: Int) {
        when (newPos) {
            1 -> {
                imageView_bottombar_cream.setImageResource(R.mipmap.ic_bottombar_cream_blue)
                imageView_bottombar_collection.setImageResource(R.mipmap.ic_bottombar_collection_grey)
                imageView_bottombar_center.setImageResource(R.mipmap.ic_bottombar_center_grey)
                viewPager_mainActivity.currentItem = 0
            }
            2 -> {
                imageView_bottombar_cream.setImageResource(R.mipmap.ic_bottombar_cream_grey)
                imageView_bottombar_collection.setImageResource(R.mipmap.ic_bottombar_collection_blue)
                imageView_bottombar_center.setImageResource(R.mipmap.ic_bottombar_center_grey)
                viewPager_mainActivity.currentItem = 1
            }
            3 -> {
                imageView_bottombar_cream.setImageResource(R.mipmap.ic_bottombar_cream_grey)
                imageView_bottombar_collection.setImageResource(R.mipmap.ic_bottombar_collection_grey)
                imageView_bottombar_center.setImageResource(R.mipmap.ic_bottombar_center_blue)
                viewPager_mainActivity.currentItem = 2
            }
        }

    }

    /**
     * BottomBar的动画
     */
    fun moveBottomBar(direction: Int) {

        val moveDown = AnimationUtils.loadAnimation(this, R.anim.transanim_movedown)
        val moveUp = AnimationUtils.loadAnimation(this, R.anim.transanim_moveup)

        if (direction == 1 && isShowing) {
            cardView_bottombar.startAnimation(moveDown)
            isShowing = false

        }
        if (direction == -1 && !isShowing) {
            cardView_bottombar.startAnimation(moveUp)
            isShowing = true
        }

    }

}
