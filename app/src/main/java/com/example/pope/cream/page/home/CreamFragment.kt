package com.example.pope.cream.page.home


import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.example.pope.cream.R
import com.example.pope.cream.page.base.BaseFragment
import com.example.pope.cream.page.home.adapter.CreamAreaAdapter
import com.example.pope.cream.utils.ScreenUtil

import java.util.ArrayList
import java.util.Arrays

import kotlinx.android.synthetic.main.fragment_cream.*

/**
 * A simple [Fragment] subclass.
 *
 * @author popeg
 */
class CreamFragment : BaseFragment<HomeContract.CreamPresenter>(), HomeContract.CreamView {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_cream, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
        initCreamArea()
        setScrollListener()
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

    private fun initCreamArea() {

        val titleList = Arrays.asList(*resources.getStringArray(R.array.pickerPoints))
        val picList = ArrayList<Int>()
        picList.add(R.mipmap.bg_type_food)
        picList.add(R.mipmap.bg_type_movie)
        picList.add(R.mipmap.bg_type_take_out)
        picList.add(R.mipmap.bg_type_music)
        picList.add(R.mipmap.bg_type_drink)
        picList.add(R.mipmap.bg_type_variety)
        picList.add(R.mipmap.bg_type_book)
        picList.add(R.mipmap.bg_type_internet_article)
        picList.add(R.mipmap.bg_type_software)
        picList.add(R.mipmap.bg_type_hardware)
        picList.add(R.mipmap.bg_type_life)
        picList.add(R.mipmap.bg_type_scenery)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView_creamArea.layoutManager = layoutManager
        val areaAdapter = CreamAreaAdapter(titleList, picList, activity)
        recyclerView_creamArea.adapter = areaAdapter
        //TODO 根据用户兴趣数量更改最小高度
        changeCreamAreaMinHeight(576)
        recyclerView_creamArea.isNestedScrollingEnabled = false
        recyclerView_creamArea.isFocusable = false

        scrollView_cream.isFocusable = true
        scrollView_cream.isFocusableInTouchMode = true
        scrollView_cream.requestFocus()

    }

    private fun changeCreamAreaMinHeight(minHeight: Int) {
        recyclerView_creamArea!!.minimumHeight = minHeight
    }

    private fun initBanner() {

        val layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ScreenUtil.getScreenWidth(activity) / 2)
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
