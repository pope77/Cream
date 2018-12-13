package com.example.pope.cream.page.creamarea.program


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.jzvd.JzvdStd
import com.bumptech.glide.Glide

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.ProgramBean
import com.example.pope.cream.page.base.BaseFragment
import com.example.pope.cream.page.creamarea.program.adapter.ActorListAdapter
import kotlinx.android.synthetic.main.fragment_program_detail.*
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class ProgramDetailFragment(programBean: ProgramBean) : BaseFragment<ProgramContract.ProgramDetailPresenter>(), ProgramContract.ProgramDetailView {

    /**
     * 收藏状态修改器
     */
    override fun collectStateModifier(isCollected: Boolean) {
        this.isCollected = isCollected
        changeCollectUi()
    }

    override fun toast(msg: String, length: Int) {
        tst(msg, length)
    }

    val mProgramBean = programBean
    var isCollected = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_program_detail, container, false)
        ProgramDetailPresenter(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //加载视频并自动播放
        videoPlayer.setUp(mProgramBean.programSourceUrl, mProgramBean.programVideoTitle, JzvdStd.SCREEN_WINDOW_NORMAL)
        Glide.with(activity!!).load(mProgramBean.programPosterUrl).into(videoPlayer.thumbImageView)
        videoPlayer.startVideo()
        textView_programDetail_name.text = mProgramBean.programName
        textView_programDetail_score.text = "${mProgramBean.programScore}分"
        textView_programDetail_introduce.text = mProgramBean.programIntroduce
        textView_programComment1.text = mProgramBean.programComments[0]
        textView_programComment2.text = mProgramBean.programComments[1]
        textView_programComment3.text = mProgramBean.programComments[2]

        //加载演职员表
        val linearLayout = LinearLayoutManager(activity)
        linearLayout.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView_program_actor.layoutManager = linearLayout
        recyclerView_program_actor.adapter = ActorListAdapter(mProgramBean, activity)

        //检查该节目是否被收藏
        mPresenter!!.collectStateCheck(activity,mProgramBean.objectId)
        //当用户点击进入查看节目详情时 用户浏览量+1
        mPresenter!!.userViewsPP(activity)

        //收藏按钮点击监听
        imageView_program_collect.setOnClickListener {
            var type = ""
            when (mProgramBean.programType) {
                ProgramBean.PROGRAM_TYPE_MOVIE -> type = "电影"
                ProgramBean.PROGRAM_TYPE_VIRTY -> type = "综艺"
            }
            if (isCollected) {
                isCollected = false
                mPresenter!!.collectStateChange(activity,type,mProgramBean.objectId,isCollected)
                changeCollectUi()
            } else {
                isCollected = true
                mPresenter!!.collectStateChange(activity,type,mProgramBean.objectId,isCollected)
                changeCollectUi()
            }
        }
        textView_program_collect.setOnClickListener {
            var type = ""
            when (mProgramBean.programType) {
                ProgramBean.PROGRAM_TYPE_MOVIE -> type = "电影"
                ProgramBean.PROGRAM_TYPE_VIRTY -> type = "综艺"
            }
            if (isCollected) {
                isCollected = false
                mPresenter!!.collectStateChange(activity,type,mProgramBean.objectId,isCollected)
                changeCollectUi()
            } else {
                isCollected = true
                mPresenter!!.collectStateChange(activity,type,mProgramBean.objectId,isCollected)
                changeCollectUi()
            }
        }

    }

    /**
     * 更改收藏按键显示样式
     */
    private fun changeCollectUi() {
        if (isCollected) {
            textView_program_collect.text = "已收藏"
            imageView_program_collect.setImageResource(R.mipmap.ic_collection_black_selected)
        } else {
            textView_program_collect.text = "收藏"
            imageView_program_collect.setImageResource(R.mipmap.ic_collection_black_unselected)
        }
    }

    /**
     * 碎片停止时将播放器释放
     */
    override fun onStop() {
        super.onStop()
        videoPlayer.release()
    }

    /**
     * 该碎片在fragmentManager中处于show还是hide状态的监听
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        //如果隐藏则暂停 如果显示则继续
        if (hidden) JzvdStd.goOnPlayOnPause()
        else JzvdStd.goOnPlayOnResume()
    }

    /**
     * 碎片处于暂停的生命周期时 视频播放器也暂停
     */
    override fun onPause() {
        super.onPause()
        JzvdStd.goOnPlayOnPause()
    }

    /**
     * 碎片处于继续的生命周期时 视频播放器也继续播放
     */
    override fun onResume() {
        super.onResume()
        JzvdStd.goOnPlayOnResume()
    }

}
