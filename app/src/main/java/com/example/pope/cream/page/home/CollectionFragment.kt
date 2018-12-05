package com.example.pope.cream.page.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.pope.cream.R
import com.example.pope.cream.page.base.BaseFragment


/**
 * A simple [Fragment] subclass.
 * @author popeg
 */
class CollectionFragment : BaseFragment<HomeContract.CollectionPresenter>(),HomeContract.CollectionView {
    override fun toast(msg: String, length: Int) {
        tst(msg,length)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_collection, container, false)
        CollectionPresenter(this)
        return view

    }

}
