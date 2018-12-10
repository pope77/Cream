package com.example.pope.cream.page.creamarea.scenery


import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.helper.ItemTouchHelper.Callback
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.dingmouren.layoutmanagergroup.echelon.EchelonLayoutManager
import com.dingmouren.layoutmanagergroup.skidright.SkidRightLayoutManager
import com.dingmouren.layoutmanagergroup.slide.SlideLayoutManager
import com.dingmouren.layoutmanagergroup.viewpager.ViewPagerLayoutManager

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.SceneryBean
import com.example.pope.cream.page.base.BaseFragment
import com.example.pope.cream.page.creamarea.scenery.adapter.SceneryListAdapter
import kotlinx.android.synthetic.main.fragment_scenery.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SceneryFragment : BaseFragment<SceneryContract.Presenter>(), SceneryContract.View {

    /**
     * 加载风景数据
     */
    override fun loadSceneryData(sceneryBeans: MutableList<SceneryBean>) {
        //“抖音”式布局
        recyclerView_seneryList.layoutManager = ViewPagerLayoutManager(activity, 1)
        recyclerView_seneryList.adapter = SceneryListAdapter(sceneryBeans, activity)
        //处理ScrollView与RecyclerView滑动冲突
        recyclerView_seneryList.addOnItemTouchListener(object:RecyclerView.OnItemTouchListener{
            override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {

            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

            }

            override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
                if (e!=null){
                    val view = recyclerView_seneryList!!.findChildViewUnder(e.x,e.y)
                    if (view!=null){
                        val holder = recyclerView_seneryList.getChildViewHolder(view) as SceneryListAdapter.ViewHolder
                        recyclerView_seneryList.requestDisallowInterceptTouchEvent(holder.isTouchScrollView(e.rawX,e.rawY))
                    }
                }
                return false
            }
        })

    }


    override fun toast(msg: String, length: Int) {
        tst(msg, length)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_scenery, container, false)
        SceneryPresenter(this)

        //更改状态栏为透明并将状态栏图标颜色改为暗色图标
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = activity!!.window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            decorView.systemUiVisibility = option
            activity!!.window.statusBarColor = Color.TRANSPARENT
        }

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter!!.getSceneryData()

        toolbar_sceneryFragment.setNavigationIcon(R.mipmap.ic_arrow_back_white)
        toolbar_sceneryFragment.setNavigationOnClickListener {
            activity.finish()
        }

    }

}
