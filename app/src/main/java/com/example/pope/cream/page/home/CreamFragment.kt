package com.example.pope.cream.page.home


import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.*
import com.example.pope.cream.page.PublicViewLogic
import com.example.pope.cream.page.base.BaseFragment
import com.example.pope.cream.page.creamarea.book.BookActivity
import com.example.pope.cream.page.creamarea.delicious.CateActivity
import com.example.pope.cream.page.creamarea.music.MusicActivity
import com.example.pope.cream.page.home.adapter.CreamAreaAdapter
import com.example.pope.cream.page.creamarea.program.ProgramActivity
import com.example.pope.cream.page.creamarea.scenery.SceneryActivity
import com.example.pope.cream.page.creamarea.takeout.TakeOutActivity
import com.example.pope.cream.page.hot.HotActivity
import com.example.pope.cream.utils.ScreenUtil

import kotlinx.android.synthetic.main.fragment_cream.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 * @author popeg
 */
class CreamFragment : BaseFragment<HomeContract.CreamPresenter>(), HomeContract.CreamView {

    /**
     * 初始化Banner
     */
    override fun initBanner(bannerBeans: MutableList<RecommendMsgBean>) {

        val layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ScreenUtil.getScreenWidth(activity) / 2)
        xBanner_cream_recommend.layoutParams = layoutParams
        //初始化banner加载的图片URL数据
        val picUrlList = arrayListOf<String>()
        val titleList = arrayListOf<String>()
        val idList = arrayListOf<String>()
        val typeList = arrayListOf<String>()
        for (element in bannerBeans) {
            picUrlList.add(element.recommendPicUrl)
            titleList.add("            ${element.recommendTitle}")
            idList.add(element.recommendObjectId)
            typeList.add(element.recommendType)
        }
        xBanner_cream_recommend.setData(picUrlList, titleList)

        xBanner_cream_recommend.setOnItemClickListener { banner, model, view, position ->
            //被点击响应 进行特殊跳转
            PublicViewLogic.specialJump(typeList[position], idList[position])
        }

        xBanner_cream_recommend.loadImage { banner, model, view, position ->
            //加载图片资源 可网络请求  可本地
            Glide.with(activity).load(model as String).into(view as ImageView)
        }

        xBanner_cream_recommend.setAutoPlayAble(true)

    }

    /**
     * 加载兴趣标签数据
     */
    override fun loadInterestData(interestList: MutableList<String>) {

        var picList = ArrayList<Int>()
        for (element in interestList) {
            when (element) {
                "美食" -> picList.add(R.mipmap.bg_type_food)
                "电影" -> picList.add(R.mipmap.bg_type_movie)
                "外卖" -> picList.add(R.mipmap.bg_type_take_out)
                "音乐" -> picList.add(R.mipmap.bg_type_music)
                "饮品" -> picList.add(R.mipmap.bg_type_drink)
                "综艺" -> picList.add(R.mipmap.bg_type_variety)
                "书籍" -> picList.add(R.mipmap.bg_type_book)
                "网文" -> picList.add(R.mipmap.bg_type_internet_article)
                "软件" -> picList.add(R.mipmap.bg_type_software)
                "硬件" -> picList.add(R.mipmap.bg_type_hardware)
                "生活" -> picList.add(R.mipmap.bg_type_life)
                "风景" -> picList.add(R.mipmap.bg_type_scenery)
            }
        }
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView_creamArea.layoutManager = layoutManager
        val areaAdapter = CreamAreaAdapter(interestList, picList, activity)
        recyclerView_creamArea.adapter = areaAdapter

        //去掉RecyclerView的嵌套滑动能力
        recyclerView_creamArea.isNestedScrollingEnabled = false
        //设置RecyclerView当前不获取Focus
        recyclerView_creamArea.isFocusable = false
        //设置ScrollView获取Focus
        scrollView_cream.isFocusable = true
        //设置ScrollView为可获得Focus
        scrollView_cream.isFocusableInTouchMode = true
        //ScrollView请求Focus
        scrollView_cream.requestFocus()

        areaAdapter.setItemOnClickListener { title ->
            //各个按键监听
            when (title!!) {
                "电影" -> {
                    val intent = Intent(activity, ProgramActivity::class.java)
                    intent.putExtra("fragmentName", title)
                    startActivity(intent)
                }
                "综艺" -> {
                    val intent = Intent(activity, ProgramActivity::class.java)
                    intent.putExtra("fragmentName", title)
                    startActivity(intent)
                }
                "美食" -> {
                    val intent = Intent(activity, CateActivity::class.java)
                    intent.putExtra("fragmentName", title)
                    startActivity(intent)
                }
                "饮品" -> {
                    val intent = Intent(activity, CateActivity::class.java)
                    intent.putExtra("fragmentName", title)
                    startActivity(intent)
                }
                "外卖" -> {
                    startActivity(Intent(activity, TakeOutActivity::class.java))
                }
                "音乐" -> {
                    startActivity(Intent(activity, MusicActivity::class.java))
                }
                "书籍" -> {
                    startActivity(Intent(activity, BookActivity::class.java))
                }
                "风景" -> {
                    startActivity(Intent(activity, SceneryActivity::class.java))
                }
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cream, container, false)
        //初始化Presenter
        CreamPresenter(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //设置滑动监听
        setScrollListener()
        //获取并初始化Banner
        mPresenter!!.getBannerData()
        //获取兴趣数据
        mPresenter!!.getInterestData(activity!!)

        //初始化“热门排行”、“佛性浏览”两个卡片
        initTwoCard()
    }

    private fun initTwoCard() {
        //对“热门排行”设置点击监听
        cardView_cream_hot.setOnClickListener {
            startActivity(Intent(activity, HotActivity::class.java))
        }
        //对“佛性浏览”设置点击监听
        cardView_cream_browse.setOnClickListener {
            val types = arrayListOf(
                    "美食", "饮品", "书籍", "风景", "电影", "综艺", "音乐", "外卖",
                    "美食", "饮品", "书籍", "风景", "电影", "综艺", "音乐", "外卖",
                    "美食", "饮品", "书籍", "风景", "电影", "综艺", "音乐", "外卖"
            )
            //随机生成浏览哪种类型
            val type = types[Random().nextInt(24)]
            //如果随机生成的是外卖则跳转外卖 若不是 则通过Presenter获取数据后随机生成再特殊跳转
            if (type == "外卖") {
                startActivity(Intent(activity, TakeOutActivity::class.java))
            } else {
                mPresenter!!.randomJump(type)
            }

        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun setScrollListener() {

        //滑动监听
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
