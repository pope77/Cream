package com.example.pope.cream.page.hot


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.HotBean
import com.example.pope.cream.page.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 *
 */
class HotFragment : BaseFragment<HotContract.HotPresenter>(),HotContract.HotView {

    /**
     * 初始化排行榜列表fragment
     */
    override fun initListFragment(hitsBeans: MutableList<HotBean>, collectionBeans: MutableList<HotBean>, titles: ArrayList<String>) {

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
        mPresenter!!.getListBeans()
    }

}
