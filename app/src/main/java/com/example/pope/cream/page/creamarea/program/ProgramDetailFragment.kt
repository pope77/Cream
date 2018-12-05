package com.example.pope.cream.page.creamarea.program


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.jzvd.JzvdStd
import com.bumptech.glide.Glide

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.ProgramBean
import com.example.pope.cream.page.creamarea.program.adapter.ActorListAdapter
import kotlinx.android.synthetic.main.fragment_program_detail.*

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class ProgramDetailFragment(programBean: ProgramBean) : Fragment() {

    val mProgramBean = programBean

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_program_detail, container, false)

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

    }

    override fun onStop() {
        super.onStop()
        videoPlayer.release()
    }

    override fun onPause() {
        super.onPause()
        JzvdStd.goOnPlayOnPause()
    }

    override fun onResume() {
        super.onResume()
        JzvdStd.goOnPlayOnResume()
    }

}
