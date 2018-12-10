package com.example.pope.cream.page.creamarea.music


import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.MusicBean
import com.example.pope.cream.page.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_music.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 *
 */
class MusicFragment : BaseFragment<MusicContract.Presenter>(), MusicContract.View {

    override fun toast(msg: String, length: Int) {
        tst(msg, length)
    }

    val mediaPlayer = MediaPlayer()
    var isPlay = false
    var titles = arrayListOf(
            "流行", "摇滚", "民谣", "电子", "布鲁斯", "爵士", "轻音乐", "说唱", "金属", "朋克"
    )
    lateinit var fragments: MutableList<Fragment>
    var isClick = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_music, container, false)
        MusicPresenter(this)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //更改状态栏为透明并将状态栏图标颜色改为暗色图标
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = activity!!.window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            decorView.systemUiVisibility = option
            activity!!.window.statusBarColor = Color.TRANSPARENT
        }

        initToolbar()
        initPlayer()
        initTabLayout()
    }

    private fun initToolbar() {

        toolbar_music.title = "音乐"
        toolbar_music.setNavigationIcon(R.mipmap.ic_arrow_back_black)
        toolbar_music.setNavigationOnClickListener {
            activity.finish()
        }

    }

    private fun initTabLayout() {

        fragments = arrayListOf(
                MusicListFragment.newInstance(1),
                MusicListFragment.newInstance(2),
                MusicListFragment.newInstance(3),
                MusicListFragment.newInstance(4),
                MusicListFragment.newInstance(5),
                MusicListFragment.newInstance(6),
                MusicListFragment.newInstance(7),
                MusicListFragment.newInstance(8),
                MusicListFragment.newInstance(9),
                MusicListFragment.newInstance(10)
        )

        val adapter = object : FragmentPagerAdapter(fragmentManager) {
            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getCount(): Int {
                return fragments.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return titles[position]
            }

        }
        viewPager_music.adapter = adapter
        tabLayout_music.setupWithViewPager(viewPager_music)
        tabLayout_music.setTabsFromPagerAdapter(adapter)

    }

    private fun initPlayer() {

        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        } catch (e: Exception) {
            Log.i("error", e.toString())
        }

        imageView_player_play.setOnClickListener {
            changePlayIcon()
        }

        group_playerCard.visibility = View.GONE

    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.reset()
        mediaPlayer.release()
    }

    fun playMusic(musicBean: MusicBean) {

        if (!isPlay){
            changePlayIcon()
        }

        if (!isClick){
            group_playerCard.visibility = View.VISIBLE
            isClick =true
        }

        Glide.with(activity).load(musicBean.musicCover).into(imageView_player_cover)
        textView_player_songName.text = musicBean.musicName
        textView_player_singer.text = musicBean.musicSingerName

        try {
            mediaPlayer.reset()
            mediaPlayer.setDataSource(musicBean.musicUrl)
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (e: Exception) {
            tst("error70010")
            Log.i("error70010", e.toString())
        }

    }

    private fun changePlayIcon(){

        if (isPlay){
            imageView_player_play.startAnimation(AnimationUtils.loadAnimation(activity,R.anim.rotate_and_alpha_showbtn))
            imageView_player_pause.startAnimation(AnimationUtils.loadAnimation(activity,R.anim.rotate_and_alpha_hidebtn))
            isPlay = false
            if (mediaPlayer.isPlaying){
                mediaPlayer.pause()
            }
        }else{
            imageView_player_play.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.rotate_and_alpha_hidebtn))
            imageView_player_pause.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.rotate_and_alpha_showbtn))
            isPlay = true
            mediaPlayer.start()
        }

    }

}
