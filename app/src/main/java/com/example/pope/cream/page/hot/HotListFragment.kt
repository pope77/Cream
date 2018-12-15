package com.example.pope.cream.page.hot


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.HotBean
import com.example.pope.cream.page.hot.adapter.HotListAdapter
import kotlinx.android.synthetic.main.fragment_hot_list.*

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class HotListFragment(val beans:ArrayList<HotBean>,val titles:ArrayList<String>) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView_hotList.layoutManager = LinearLayoutManager(activity)
        recyclerView_hotList.adapter = HotListAdapter(beans,titles)
        (recyclerView_hotList.adapter as HotListAdapter).setOnItemClickListener { id, type ->
            Toast.makeText(activity,"id=$id type=$type",Toast.LENGTH_SHORT).show()
        }
    }

}
