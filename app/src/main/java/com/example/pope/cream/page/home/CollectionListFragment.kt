package com.example.pope.cream.page.home


import android.annotation.SuppressLint
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

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter!!.getBeans(type, idList)
    }

}
