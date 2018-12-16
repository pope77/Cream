package com.example.pope.cream.page.home


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bmob.v3.BmobObject

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.BookBean
import com.example.pope.cream.biz.beans.CateBean
import com.example.pope.cream.biz.beans.ProgramBean
import com.example.pope.cream.biz.beans.SceneryBean
import com.example.pope.cream.page.PublicViewLogic
import com.example.pope.cream.page.base.BaseFragment
import com.example.pope.cream.page.creamarea.book.BookActivity
import com.example.pope.cream.page.creamarea.delicious.CateActivity
import com.example.pope.cream.page.creamarea.program.ProgramActivity
import com.example.pope.cream.page.creamarea.scenery.SceneryActivity
import com.example.pope.cream.page.home.adapter.CollectionListAdapter
import kotlinx.android.synthetic.main.fragment_collection_list.*

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class CollectionListFragment(collectionType: String) :
        BaseFragment<HomeContract.CollectionListPresenter>(), HomeContract.CollectionListView {

    override fun loadBeans(beans: ArrayList<CateBean>) {
        val picUrls = arrayListOf<String>()
        val titles = arrayListOf<String>()
        for (element in beans) {
            picUrls.add(element.cateDetailBigPic)
            titles.add(element.cateName)
        }
        recyclerView_collectionList.layoutManager = LinearLayoutManager(activity)
        val idList = arrayListOf<String>()
        for (element in beans) {
            idList.add(element.objectId)
        }
        recyclerView_collectionList.adapter = CollectionListAdapter(type, idList, picUrls, titles, activity)
        (recyclerView_collectionList.adapter as CollectionListAdapter).setOnItemClickListener { id ->
            PublicViewLogic.specialJump("美食", id)
        }
    }

    override fun loadBeans1(beans: ArrayList<ProgramBean>) {
        val picUrls = arrayListOf<String>()
        val titles = arrayListOf<String>()
        for (element in beans) {
            picUrls.add(element.programPosterUrl)
            titles.add(element.programName)
        }
        recyclerView_collectionList.layoutManager = LinearLayoutManager(activity)
        val idList = arrayListOf<String>()
        for (element in beans) {
            idList.add(element.objectId)
        }
        recyclerView_collectionList.adapter = CollectionListAdapter(type, idList, picUrls, titles, activity)
        (recyclerView_collectionList.adapter as CollectionListAdapter).setOnItemClickListener { id ->
            PublicViewLogic.specialJump("电影", id)
        }

    }

    override fun loadBeans2(beans: ArrayList<BookBean>) {
        val picUrls = arrayListOf<String>()
        val titles = arrayListOf<String>()
        for (element in beans) {
            picUrls.add(element.bookCoverUrl)
            titles.add(element.bookName)
        }
        recyclerView_collectionList.layoutManager = LinearLayoutManager(activity)
        val idList = arrayListOf<String>()
        for (element in beans) {
            idList.add(element.objectId)
        }
        recyclerView_collectionList.adapter = CollectionListAdapter(type, idList, picUrls, titles, activity)
        (recyclerView_collectionList.adapter as CollectionListAdapter).setOnItemClickListener { id ->
            PublicViewLogic.specialJump("书籍", id)
        }
    }

    override fun loadBeans3(beans: ArrayList<SceneryBean>) {
        val picUrls = arrayListOf<String>()
        val titles = arrayListOf<String>()
        for (element in beans) {
            picUrls.add(element.sceneryPicUrl)
            titles.add(element.sceneryName)
        }
        recyclerView_collectionList.layoutManager = LinearLayoutManager(activity)
        val idList = arrayListOf<String>()
        for (element in beans) {
            idList.add(element.objectId)
        }
        recyclerView_collectionList.adapter = CollectionListAdapter(type, idList, picUrls, titles, activity)
        (recyclerView_collectionList.adapter as CollectionListAdapter).setOnItemClickListener { id ->
            PublicViewLogic.specialJump("风景", id)
        }

    }

    override fun toast(msg: String, length: Int) {
        tst(msg, length)
    }

    val type = collectionType

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_collection_list, container, false)
        CollectionListPresenter(this)
        Log.i("test7", "   onCreateView")
        return view
    }

    override fun onResume() {
        super.onResume()
        //获取收藏数据
        mPresenter!!.getBeans(type)
    }

}
