package com.example.pope.cream.page.creamarea.book


import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.BookBean
import com.example.pope.cream.page.creamarea.book.adapter.BookTypeAdapter
import kotlinx.android.synthetic.main.fragment_book_type.*

/**
 * A simple [Fragment] subclass.
 *
 */
class BookTypeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_type, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar_bookTypeList.setNavigationIcon(R.mipmap.ic_arrow_back_black)
        toolbar_bookTypeList.setNavigationOnClickListener {
            (activity as BookActivity).onBackPressed()
        }

        val pics = arrayListOf(
                R.mipmap.card_booktype1, R.mipmap.card_booktype2, R.mipmap.card_booktype3,
                R.mipmap.card_booktype4, R.mipmap.card_booktype5, R.mipmap.card_booktype6,
                R.mipmap.card_booktype7, R.mipmap.card_booktype8, R.mipmap.card_booktype9,
                R.mipmap.card_booktype10, R.mipmap.card_booktype11, R.mipmap.card_booktype12,
                R.mipmap.card_booktype13, R.mipmap.card_booktype14, R.mipmap.card_booktype15,
                R.mipmap.card_booktype16, R.mipmap.card_booktype17, R.mipmap.card_booktype18,
                R.mipmap.card_booktype19, R.mipmap.card_booktype20, R.mipmap.card_booktype21,
                R.mipmap.card_booktype22, R.mipmap.card_booktype23, R.mipmap.card_booktype24
        )

        recyclerView_bookType.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView_bookType.adapter = BookTypeAdapter(pics)
        (recyclerView_bookType.adapter as BookTypeAdapter).setOnItemClickListener { picId ->
            when (picId) {
                R.mipmap.card_booktype1 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_YOUTH))
                }
                R.mipmap.card_booktype2 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_NOVEL))
                }
                R.mipmap.card_booktype3 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_LITERATURE))
                }
                R.mipmap.card_booktype4 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_ART))
                }
                R.mipmap.card_booktype5 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_HUMOR))
                }
                R.mipmap.card_booktype6 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_FASHION))
                }
                R.mipmap.card_booktype7 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_TOURISM))
                }
                R.mipmap.card_booktype8 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_GEOGRAPHY))
                }
                R.mipmap.card_booktype9 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_LAW))
                }
                R.mipmap.card_booktype10 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_ECONOMICS))
                }
                R.mipmap.card_booktype11 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_ADMINISTRATION))
                }
                R.mipmap.card_booktype12 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_SPORTS))
                }
                R.mipmap.card_booktype13 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_HEALTHCARE))
                }
                R.mipmap.card_booktype14 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_PARENTING))
                }
                R.mipmap.card_booktype15 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_LOVE))
                }
                R.mipmap.card_booktype16 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_LIFE))
                }
                R.mipmap.card_booktype17 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_POLITICS))
                }
                R.mipmap.card_booktype18 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_MILITARY))
                }
                R.mipmap.card_booktype19 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_PHILOSOPHY))
                }
                R.mipmap.card_booktype20 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_RELIGION))
                }
                R.mipmap.card_booktype21 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_SOCIOLOGY))
                }
                R.mipmap.card_booktype22 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_ANCIENTBOOKS))
                }
                R.mipmap.card_booktype23 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_HISTORY))
                }
                R.mipmap.card_booktype24 -> {
                    (activity as BookActivity).changeFragment(BookListFragment(BookBean.BOOK_TYPE_BIOGRAPHY))
                }
            }
        }

    }

}
