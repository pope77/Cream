package com.example.pope.cream.page.creamarea.delicious


import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.CateBean
import com.example.pope.cream.biz.beans.RecommendMsgBean
import com.example.pope.cream.page.base.BaseFragment
import com.example.pope.cream.page.creamarea.delicious.adapter.CateRecommendAdapter
import com.example.pope.cream.utils.ScreenUtil
import kotlinx.android.synthetic.main.fragment_cate.*
import kotlinx.android.synthetic.main.fragment_cream.*
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 *
 */
class CateFragment : BaseFragment<CateContract.Presenter>(),CateContract.View {

    /**
     * 初始化Banner
     */
    override fun initBanner(bannerBeans: MutableList<RecommendMsgBean>, dataBeans: MutableList<CateBean>) {
        val layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ScreenUtil.getScreenWidth(activity) / 2)
        xBanner_cateFragment.layoutParams = layoutParams

        val picUrls = arrayListOf<String>()
        val titles = arrayListOf<String>()
        for (element in bannerBeans){
            picUrls.add(element.recommendPicUrl)
            titles.add("")
        }

        xBanner_cateFragment.setData(picUrls,titles)

        xBanner_cateFragment.setOnItemClickListener { banner, model, view, position ->
            //点击响应
            (activity as CateActivity).changeFragment(CateDetailFragment(dataBeans[position]))
        }

        xBanner_cateFragment.loadImage { banner, model, view, position ->
            //加载图片资源
            Glide.with(activity).load(model as String).into(view as ImageView)
        }

        xBanner_cateFragment.setAutoPlayAble(true)
    }

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
        //初始化toolbar
        initToolbar()
        //获取banner数据并初始化banner
        mPresenter!!.getBannerData((activity as CateActivity).cateType)
        //获取数据
        mPresenter!!.getCateRecommendMsg((activity as CateActivity).cateType)
    }

    private fun initToolbar() {
        toolbar_cateFragment.setNavigationIcon(R.mipmap.ic_arrow_back_black)
        toolbar_cateFragment.setNavigationOnClickListener{
            activity.finish()
        }
    }

    override fun onResume() {
        super.onResume()
        xBanner_cateFragment.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        xBanner_cateFragment.stopAutoPlay()
    }

}
