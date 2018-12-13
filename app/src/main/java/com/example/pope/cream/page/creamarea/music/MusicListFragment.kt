package com.example.pope.cream.page.creamarea.music


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.MusicBean
import com.example.pope.cream.page.base.BaseFragment
import com.example.pope.cream.page.creamarea.music.adapter.MusicListAdapter
import kotlinx.android.synthetic.main.fragment_music_list.*

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class MusicListFragment(musicType: Int) : BaseFragment<MusicContract.ListPresenter>(), MusicContract.ListView {

    /**
     * 加载音乐数据
     */
    override fun loadMusicMsgToList(musicBeans: MutableList<MusicBean>) {

        recyclerView_music_list.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView_music_list.adapter = MusicListAdapter(musicBeans, activity)
        //列表点击监听
        (recyclerView_music_list.adapter as MusicListAdapter).setOnItemClickListener {position ->
            (activity as MusicActivity).playMusic(musicBeans.get(position))
            //当用户点击一首音乐时 浏览量+1
            mPresenter!!.userViewsPP(activity)
        }

    }

    override fun toast(msg: String, length: Int) {
        tst(msg, length)
    }

    val type = musicType

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_music_list, container, false)
        MusicListPresenter(this)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //获取音乐列表数据
        mPresenter!!.getMusicData(type)
    }

    companion object {
        fun newInstance(musicType: Int): MusicListFragment {
            return MusicListFragment(musicType)
        }
    }

}
