package com.example.pope.cream.page.program


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.ProgramBean

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class ProgramDetailFragment(programBean: ProgramBean) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_program_detail, container, false)



        return view
    }


}
