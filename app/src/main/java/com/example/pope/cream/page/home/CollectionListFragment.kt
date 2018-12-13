package com.example.pope.cream.page.home


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.BookBean
import com.example.pope.cream.biz.beans.CateBean
import com.example.pope.cream.biz.beans.ProgramBean
import com.example.pope.cream.biz.beans.SceneryBean
import com.example.pope.cream.page.base.BaseFragment
import com.example.pope.cream.page.creamarea.book.BookActivity
import com.example.pope.cream.page.creamarea.delicious.CateActivity
import com.example.pope.cream.page.creamarea.program.ProgramActivity
import com.example.pope.cream.page.creamarea.scenery.SceneryActivity
import com.example.pope.cream.page.home.adapter.CollectionListAdapter
import kotlinx.android.synthetic.main.fragment_collection_list.*
import kotlinx.android.synthetic.main.item_collectionlist.*

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class CollectionListFragment(collectionType: String, pointIdList: ArrayList<String>) :
        BaseFragment<HomeContract.CollectionListPresenter>(), HomeContract.CollectionListView {

    override fun loadBeans(beans: ArrayList<CateBean>) {
        val picUrls = arrayListOf<String>()
        val titles = arrayListOf<String>()
        for (element in beans) {
            picUrls.add(element.cateDetailBigPic)
            titles.add(element.cateName)
        }
        recyclerView_collectionList.layoutManager = LinearLayoutManager(activity)
        recyclerView_collectionList.adapter = CollectionListAdapter(type, idList, picUrls, titles, activity)
        (recyclerView_collectionList.adapter as CollectionListAdapter).setOnItemClickListener {  id ->
            val intent = Intent(activity,CateActivity::class.java)
            intent.putExtra("特殊",true)
            intent.putExtra("id",id)
            startActivity(intent)
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
        recyclerView_collectionList.adapter = CollectionListAdapter(type, idList, picUrls, titles, activity)
        (recyclerView_collectionList.adapter as CollectionListAdapter).setOnItemClickListener {
            val intent = Intent(activity,ProgramActivity::class.java)
            intent.putExtra("特殊",true)
            intent.putExtra("id",it)
            startActivity(intent)
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
        recyclerView_collectionList.adapter = CollectionListAdapter(type, idList, picUrls, titles, activity)
        (recyclerView_collectionList.adapter as CollectionListAdapter).setOnItemClickListener {  id ->
            val intent = Intent(activity,BookActivity::class.java)
            intent.putExtra("特殊",true)
            intent.putExtra("id",id)
            startActivity(intent)
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
        recyclerView_collectionList.adapter = CollectionListAdapter(type, idList, picUrls, titles, activity)
        (recyclerView_collectionList.adapter as CollectionListAdapter).setOnItemClickListener{id ->
            val intent = Intent(activity,SceneryActivity::class.java)
            intent.putExtra("特殊",true)
            intent.putExtra("id",id)
            startActivity(intent)
        }

    }

    override fun toast(msg: String, length: Int) {
        tst(msg, length)
    }

    val type = collectionType
    val idList = pointIdList

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_collection_list, container, false)
        CollectionListPresenter(this)
        return view
    }

    var isFirstLunch = true

    override fun onResume() {
        super.onResume()
        if (isFirstLunch) {
            mPresenter!!.getBeans(type, idList)
            isFirstLunch = false
        }else{
            mPresenter!!.getBeans(activity,type)
        }
    }

}
