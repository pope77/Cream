package com.example.pope.cream.page.home


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.UserBean
import com.example.pope.cream.page.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_collection.*


/**
 * A simple [Fragment] subclass.
 * @author popeg
 */
class CollectionFragment : BaseFragment<HomeContract.CollectionPresenter>(), HomeContract.CollectionView {

    /**
     * 加载兴趣数据细节及加载收藏列表
     */
    override fun loadInterestData(userBean: UserBean) {

        var interestList = userBean.userInterestPoint
        var itemNumList = userBean.pointItemNum
        val idList = userBean.pointId

        val innerBean = cleanUncollectData(interestList as ArrayList<String>, itemNumList as ArrayList<Int>)
        interestList = innerBean.interestList
        itemNumList = innerBean.itemNumList

        var fragmentList: ArrayList<CollectionListFragment> = arrayListOf()

        for ((index, element) in itemNumList.withIndex()) {
            val childIdList: ArrayList<String> = arrayListOf()
            if (element == 1) {
                childIdList.add(idList[0])
                idList.removeAt(0)
                fragmentList.add(CollectionListFragment(interestList[index], childIdList))
            } else {
                for (i in 1..element) {
                    childIdList.add(idList[0])
                    idList.removeAt(0)
                }
                fragmentList.add(CollectionListFragment(interestList[index], childIdList))
            }
        }

        if (fragmentList.isEmpty()) {
            textView_collection_noData.visibility = View.VISIBLE
        } else {
            textView_collection_noData.visibility = View.GONE
        }

        val adapter = object : FragmentPagerAdapter(fragmentManager) {
            override fun getItem(position: Int): Fragment {
                return fragmentList[position]
            }

            override fun getCount(): Int {
                return fragmentList.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return interestList[position]
            }
        }

        viewPager_collectionFragment.adapter = adapter
        tabLayout_collectionFragment.setupWithViewPager(viewPager_collectionFragment)
        tabLayout_collectionFragment.setTabsFromPagerAdapter(adapter)
        if (fragmentList.size <= 3) {
            tabLayout_collectionFragment.tabMode = TabLayout.MODE_FIXED
        }

    }

    override fun toast(msg: String, length: Int) {
        tst(msg, length)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_collection, container, false)
        CollectionPresenter(this)
        return view

    }

    /**
     * 去掉外卖 音乐 及未做的模块 及收藏数量为0的板块
     */
    private fun cleanUncollectData(interestList: ArrayList<String>, itemNumList: ArrayList<Int>): InnerBean {

        var interestList = interestList
        var itemNumList = itemNumList
        var doItAgain = false
        loop@ for ((index, element) in interestList.withIndex()) {
            when (element) {
                "外卖", "软件", "硬件", "生活", "网文", "音乐" -> {
                    //removeAt报迭代器冲突异常
                    //interestList.removeAt(index)
                    //itemNumList.removeAt(index)
                    if (interestList.remove(element)) {
                        itemNumList.removeAt(index)
                        doItAgain = true
                        break@loop
                    }

                }
            }
        }
        if (!doItAgain) {
            for ((index1, element1) in itemNumList.withIndex()) {
                if (element1 == 0) {
                    interestList.remove(interestList[index1])
                    itemNumList.removeAt(index1)
                    doItAgain = true
                    break
                }
            }
        }
        if (doItAgain) return cleanUncollectData(interestList, itemNumList)
        else return InnerBean(interestList, itemNumList)
    }

    class InnerBean(interestList: ArrayList<String>, itemNumList: ArrayList<Int>) {

        val interestList = interestList
        val itemNumList = itemNumList

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter!!.getInterestDetailData(activity)
    }

}
