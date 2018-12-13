package com.example.pope.cream.page.creamarea.program


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.ProgramBean
import com.example.pope.cream.page.base.BaseFragment
import com.example.pope.cream.page.creamarea.program.adapter.ProgramListAdapter
import kotlinx.android.synthetic.main.fragment_program.*
import kotlinx.android.synthetic.main.fragment_program_detail.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ProgramFragment : BaseFragment<ProgramContract.ProgramPresenter>(), ProgramContract.ProgramView {


    /**
     * 加载节目数据
     */
    override fun loadProgramData(programList: MutableList<ProgramBean>) {

        recyclerView_programList.layoutManager = LinearLayoutManager(activity)
        val adapter = ProgramListAdapter(activity, programList)
        recyclerView_programList.adapter = adapter
        adapter.setCardOnClickListener { programBean ->
            (activity as ProgramActivity).changeFragment(ProgramDetailFragment(programBean))
            mPresenter!!.addHit(programBean)
         }
        adapter.setOnPlayListener { programBean ->
            (activity as ProgramActivity).changeFragment(ProgramDetailFragment(programBean))
            mPresenter!!.addHit(programBean)
        }
    }

    override fun toast(msg: String, length: Int) {
        tst(msg,length)
    }

    var isCollected: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_program, container, false)

        ProgramPresenter(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //初始化ToolBar
        toolbar_movieList.setNavigationIcon(R.mipmap.ic_arrow_back_white)
        toolbar_movieList.setNavigationOnClickListener{
            activity!!.finish()
        }
        //查询节目数据
        mPresenter!!.getProgramRecommend((activity as ProgramActivity).programType)

    }


    companion object {
        fun newInstance():ProgramFragment{
            return ProgramFragment()
        }
    }

}
