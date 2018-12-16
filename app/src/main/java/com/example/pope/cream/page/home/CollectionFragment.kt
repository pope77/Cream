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

        val innerBean = cleanUncollectData(interestList as ArrayList<String>, itemNumList as ArrayList<Int>)
        interestList = innerBean.interestList

        if (interestList.isEmpty()) {
            textView_collection_noData.visibility = View.VISIBLE
            tabLayout_collectionFragment.visibility = View.GONE
            return
        } else {
            tabLayout_collectionFragment.visibility = View.VISIBLE
            textView_collection_noData.visibility = View.GONE
        }

        var fragmentList: ArrayList<CollectionListFragment> = arrayListOf()

        for (element in interestList) {
            fragmentList.add(CollectionListFragment(element))
        }

        val adapter = object : FragmentPagerAdapter(childFragmentManager) {
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

        var doItAgain = false
        for ((index1, element1) in itemNumList.withIndex()) {
            if (element1 == 0) {
                interestList.remove(interestList[index1])
                itemNumList.removeAt(index1)
                doItAgain = true
                break
            }
        }
        if (doItAgain) return cleanUncollectData(interestList, itemNumList)
        else return InnerBean(interestList, itemNumList)
    }

    class InnerBean(interestList: ArrayList<String>, itemNumList: ArrayList<Int>) {

        val interestList = interestList
        val itemNumList = itemNumList

    }

    override fun onResume() {
        super.onResume()
        mPresenter!!.getInterestDetailData()
    }

}
