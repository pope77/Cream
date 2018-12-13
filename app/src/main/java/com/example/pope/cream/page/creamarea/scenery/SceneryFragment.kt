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
    override fun loadSceneryData(sceneryBeans: MutableList<SceneryBean>, isCollectedList: ArrayList<Boolean>) {
        //“抖音”式布局
        recyclerView_seneryList.layoutManager = ViewPagerLayoutManager(activity, 1)
        recyclerView_seneryList.adapter = SceneryListAdapter(sceneryBeans, activity,isCollectedList)

        //处理ScrollView与RecyclerView滑动冲突
        //重写Touch监听
        recyclerView_seneryList.addOnItemTouchListener(object:RecyclerView.OnItemTouchListener{
            override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {

            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

            }

            //将获取到的event坐标点给ViewHolder判断是否拦截
            override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
                if (e!=null){
                    //找到被点击位置的item的rootView
                    val view = recyclerView_seneryList!!.findChildViewUnder(e.x,e.y)
                    if (view!=null){
                        //通过rootView找到对应的ViewHolder
                        val holder = recyclerView_seneryList.getChildViewHolder(view) as SceneryListAdapter.ViewHolder
                        //由ViewHolder决定要不要请求不拦截，如果不拦截 event就会一路传到rootView中
                        recyclerView_seneryList.requestDisallowInterceptTouchEvent(holder.isTouchScrollView(e.rawX,e.rawY))
                    }
                }
                return false
            }
        })

        (recyclerView_seneryList.adapter as SceneryListAdapter).setOnCollectionListener { bean, collectThisScenery ->
            mPresenter!!.collectStateChange(activity,"风景",bean.objectId,collectThisScenery)
        }

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

        mPresenter!!.getSceneryData(activity)

        toolbar_sceneryFragment.setNavigationIcon(R.mipmap.ic_arrow_back_white)
        toolbar_sceneryFragment.setNavigationOnClickListener {
            activity.finish()
        }

    }

}
