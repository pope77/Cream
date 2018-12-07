package com.example.pope.cream.page.creamarea.delicious


import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.CateBean
import com.example.pope.cream.page.base.BaseFragment
import com.example.pope.cream.page.creamarea.delicious.adapter.CateRecommendAdapter
import com.example.pope.cream.utils.ScreenUtil
import kotlinx.android.synthetic.main.fragment_cate.*
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 *
 */
class CateFragment : BaseFragment<CateContract.Presenter>(),CateContract.View {

    override fun loadRecyclerView(cateBeans: MutableList<CateBean>) {
        recyclerView_cateFragment_cateList.layoutManager = LinearLayoutManager(activity)
        recyclerView_cateFragment_cateList.adapter = CateRecommendAdapter(cateBeans, activity)
        (recyclerView_cateFragment_cateList.adapter as CateRecommendAdapter).setOnItemClickListener {pos->
            (activity as CateActivity).changeFragment(CateDetailFragment(cateBeans[pos]))
        }
    }

    override fun toast(msg: String, length: Int) {
        tst(msg,length)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cate, container, false)
        CatePresenter(this)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initXBanner()
        //获取数据
        mPresenter!!.getCateRecommendMsg((activity as CateActivity).cateType)
    }

    private fun initToolbar() {
        toolbar_cateFragment.setNavigationIcon(R.mipmap.ic_arrow_back_black)
        toolbar_cateFragment.setNavigationOnClickListener{
            activity.finish()
        }
    }

    private fun initXBanner() {
        val layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ScreenUtil.getScreenWidth(activity) / 2)
        xBanner_cateFragment.layoutParams = layoutParams

        xBanner_cateFragment.setOnItemClickListener { banner, model, view, position ->
            //TODO 点击响应
        }

        xBanner_cateFragment.loadImage { banner, model, view, position ->
            //TODO 加载图片资源
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
        xBanner_cateFragment.setData(data, null)
        xBanner_cateFragment.setAutoPlayAble(true)
    }

}
