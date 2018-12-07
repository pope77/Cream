package com.example.pope.cream.page.home


import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.example.pope.cream.R
import com.example.pope.cream.page.base.BaseFragment
import com.example.pope.cream.page.creamarea.delicious.CateActivity
import com.example.pope.cream.page.home.adapter.CreamAreaAdapter
import com.example.pope.cream.page.creamarea.program.ProgramActivity
import com.example.pope.cream.page.creamarea.takeout.TakeOutActivity
import com.example.pope.cream.utils.ScreenUtil

import java.util.ArrayList

import kotlinx.android.synthetic.main.fragment_cream.*

/**
 * A simple [Fragment] subclass.
 *
 * @author popeg
 */
class CreamFragment : BaseFragment<HomeContract.CreamPresenter>(), HomeContract.CreamView {

    /**
     * 加载兴趣标签数据
     */
    override fun loadInterestData(interestList: MutableList<String>) {

        var picList = ArrayList<Int>()
        for (element in interestList) {
            when (element) {
                "美食" -> picList.add(R.mipmap.bg_type_food)
                "电影" -> picList.add(R.mipmap.bg_type_movie)
                "外卖" -> picList.add(R.mipmap.bg_type_take_out)
                "音乐" -> picList.add(R.mipmap.bg_type_music)
                "饮品" -> picList.add(R.mipmap.bg_type_drink)
                "综艺" -> picList.add(R.mipmap.bg_type_variety)
                "书籍" -> picList.add(R.mipmap.bg_type_book)
                "网文" -> picList.add(R.mipmap.bg_type_internet_article)
                "软件" -> picList.add(R.mipmap.bg_type_software)
                "硬件" -> picList.add(R.mipmap.bg_type_hardware)
                "生活" -> picList.add(R.mipmap.bg_type_life)
                "风景" -> picList.add(R.mipmap.bg_type_scenery)
            }
        }
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView_creamArea.layoutManager = layoutManager
        val areaAdapter = CreamAreaAdapter(interestList, picList, activity)
        recyclerView_creamArea.adapter = areaAdapter
        //TODO 根据用户兴趣数量更改最小高度
//        changeCreamAreaMinHeight(576)
        recyclerView_creamArea.isNestedScrollingEnabled = false
        recyclerView_creamArea.isFocusable = false

        scrollView_cream.isFocusable = true
        scrollView_cream.isFocusableInTouchMode = true
        scrollView_cream.requestFocus()

        areaAdapter.setItemOnClickListener { title ->
            //TODO 各个按键监听
            when (title!!) {
                "电影" -> {
                    val intent = Intent(activity, ProgramActivity::class.java)
                    intent.putExtra("fragmentName", title)
                    startActivity(intent)
                }
                "综艺" -> {
                    val intent = Intent(activity, ProgramActivity::class.java)
                    intent.putExtra("fragmentName", title)
                    startActivity(intent)
                }
                "美食" -> {
                    val intent = Intent(activity, CateActivity::class.java)
                    intent.putExtra("fragmentName", title)
                    startActivity(intent)
                }
                "饮品" -> {
                    val intent = Intent(activity, CateActivity::class.java)
                    intent.putExtra("fragmentName", title)
                    startActivity(intent)
                }
                "外卖" -> {
                    startActivity(Intent(activity, TakeOutActivity::class.java))
                }
//                    "音乐" ->
//                    "书籍" ->
//                    "网文" ->
//                    "软件" ->
//                    "硬件" ->
//                    "生活" ->
//                    "风景" ->
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cream, container, false)

        CreamPresenter(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setScrollListener()
        initBanner()
        mPresenter!!.getInterestData(activity!!)
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun setScrollListener() {

        scrollView_cream.setOnScrollChangeListener { view, i, i1, i2, i3 ->
            val x = i1 - i3
            val mainActivity = activity as MainActivity
            if (x >= 10) {
                //向上滑的情况
                mainActivity.moveBottomBar(1)
            }
            if (x <= -10) {
                //向下滑的情况
                mainActivity.moveBottomBar(-1)
            }
        }

    }

    private fun changeCreamAreaMinHeight(minHeight: Int) {
        recyclerView_creamArea!!.minimumHeight = minHeight
    }

    /**
     * 初始化Banner
     */
    private fun initBanner() {

        val layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ScreenUtil.getScreenWidth(activity) / 2)
        xBanner_cream_recommend.layoutParams = layoutParams

        xBanner_cream_recommend.setOnItemClickListener { banner, model, view, position ->
            //TODO 被点击响应
        }

        xBanner_cream_recommend.loadImage { banner, model, view, position ->
            //TODO 加载图片资源 可网络请求  可本地
            //加载本地图片展示
            (view as ImageView).setImageResource(model as Int)
        }

        initLocalImage()

    }

    /**
     * 加载本地图片
     */
    private fun initLocalImage() {
        val data = ArrayList<Int>()
        data.add(R.mipmap.banner_kayak)
        data.add(R.mipmap.banner_movie)
        data.add(R.mipmap.banner_watch)
        xBanner_cream_recommend.setData(data, null)
        xBanner_cream_recommend.setAutoPlayAble(true)
    }

    override fun onResume() {
        super.onResume()
        xBanner_cream_recommend.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        xBanner_cream_recommend.stopAutoPlay()
    }

    override fun toast(msg: String, length: Int) {
        tst(msg, length)
    }

}
