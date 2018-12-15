package com.example.pope.cream.page.hot


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.HotBean
import com.example.pope.cream.page.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * A simple [Fragment] subclass.
 *
 */
class HotFragment : BaseFragment<HotContract.HotPresenter>(),HotContract.HotView {

    /**
     * 初始化排行榜列表fragment
     */
    override fun initListFragment(hitsBeans: MutableList<HotBean>, collectionBeans: MutableList<HotBean>, hitsTitles: ArrayList<String>, collectionTitles: ArrayList<String>) {

        val tabTitle = arrayListOf("点击量排行榜","收藏量排行榜")
        val fragments = arrayListOf(
                HotListFragment(hitsBeans as ArrayList<HotBean>,hitsTitles),
                HotListFragment(collectionBeans as ArrayList<HotBean>,collectionTitles))
        val adapter = object:FragmentPagerAdapter(fragmentManager){
            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getCount(): Int {
                return fragments.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return tabTitle[position]
            }

        }
        viewPager_hotFragment.adapter = adapter
        tabLayout_hotFragment.setupWithViewPager(viewPager_hotFragment)
        tabLayout_hotFragment.setTabsFromPagerAdapter(adapter)

    }

    override fun toast(msg: String, length: Int) {
        tst(msg,length)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hot, container, false)
        HotPresenter(this)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar_hotFragment.setNavigationIcon(R.mipmap.ic_arrow_back_black)
        toolbar_hotFragment.setNavigationOnClickListener {
            activity.finish()
        }

        mPresenter!!.getListBeans()
    }

}
