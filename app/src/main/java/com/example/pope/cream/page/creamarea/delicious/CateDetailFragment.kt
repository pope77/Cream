package com.example.pope.cream.page.creamarea.delicious


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.CateBean
import com.example.pope.cream.page.base.BaseFragment
import com.example.pope.cream.page.creamarea.delicious.adapter.CateDetailHotSellAdapter
import com.example.pope.cream.page.creamarea.program.ProgramDetailPresenter
import kotlinx.android.synthetic.main.fragment_cate_detail.*

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class CateDetailFragment(cateBean: CateBean) : BaseFragment<CateContract.CateDetailPresenter>(), CateContract.CateDetailView {

    /**
     * 收藏状态修改器
     */
    override fun collectStateModifier(isCollected: Boolean) {
        this.isCollected = isCollected
        changeCollectUi()
    }

    /**
     * 改变收藏按键UI
     */
    private fun changeCollectUi() {
        if (isCollected) {
            textView_cateDetail_collect.text = "已收藏"
            imageView_cateDetail_collect.setImageResource(R.mipmap.ic_collection_selected)
        } else {
            textView_cateDetail_collect.text = "收藏"
            imageView_cateDetail_collect.setImageResource(R.mipmap.ic_collection)
        }
    }

    override fun toast(msg: String, length: Int) {
        tst(msg, length)
    }

    var cateBean: CateBean = cateBean

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cate_detail, container, false)
        CateDetailPresenter(this)
        return view
    }

    var isCollected = false

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMsg()
        //检查收藏状态
        mPresenter!!.collectStateCheck(activity,cateBean.objectId)
        //收藏按键点击监听
        textView_cateDetail_collect.setOnClickListener {
            var type = ""
            when(cateBean.cateType){
                CateBean.CATE_TYPE_DRINK-> type = "饮品"
                CateBean.CATE_TYPE_FOOD -> type = "美食"
            }
            if (isCollected){
                isCollected = false
                mPresenter!!.collectStateChange(activity,type,cateBean.objectId,isCollected)
                changeCollectUi()
            }else{
                isCollected = true
                mPresenter!!.collectStateChange(activity,type,cateBean.objectId,isCollected)
                changeCollectUi()
            }
        }
        imageView_cateDetail_collect.setOnClickListener {
            var type = ""
            when(cateBean.cateType){
                CateBean.CATE_TYPE_DRINK-> type = "饮品"
                CateBean.CATE_TYPE_FOOD -> type = "美食"
            }
            if (isCollected){
                isCollected = false
                mPresenter!!.collectStateChange(activity,type,cateBean.objectId,isCollected)
                changeCollectUi()
            }else{
                isCollected = true
                mPresenter!!.collectStateChange(activity,type,cateBean.objectId,isCollected)
                changeCollectUi()
            }
        }
    }

    private fun loadMsg() {

        Glide.with(activity).load(cateBean.cateDetailBigPic).into(imageView_cateDetail_bigPic)
        textView_cateDetail_name.text = cateBean.cateName
        textView_cateDeatail_score.text = "${cateBean.cateScore}分"
        when (cateBean.cateScore) {
            5.0 -> imageView_cateDetail_score.setImageResource(R.mipmap.ic_score_five_black)
            in 4.0..4.9 -> imageView_cateDetail_score.setImageResource(R.mipmap.ic_score_four_black)
            in 3.0..3.9 -> imageView_cateDetail_score.setImageResource(R.mipmap.ic_score_three_black)
            in 2.0..2.9 -> imageView_cateDetail_score.setImageResource(R.mipmap.ic_score_two_black)
            in 1.0..1.9 -> imageView_cateDetail_score.setImageResource(R.mipmap.ic_score_one_black)
            else -> Toast.makeText(activity, "error评分有问题", Toast.LENGTH_SHORT).show()
        }
        textView_cateDeatail_aWord.text = cateBean.cateAWord
        textView_cateDetail_likes.text = cateBean.cateLikes.toString()
        textView_cateDetail_dislike.text = cateBean.cateDislikes.toString()
        textView_cateDetail_price.text = cateBean.catePrice.toString()
        textView_cateDetail_introduce.text = cateBean.cateIntroduce

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView_cateDetail_hotSell.layoutManager = layoutManager
        recyclerView_cateDetail_hotSell.adapter = CateDetailHotSellAdapter(activity, cateBean)

    }

}
