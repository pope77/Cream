package com.example.pope.cream.page.creamarea.book


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.BookBean
import kotlinx.android.synthetic.main.fragment_book_detail.*

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class BookDetailFragment(val bookBean: BookBean): Fragment() {

    val bean = bookBean

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar_bookDetail.setNavigationIcon(R.mipmap.ic_arrow_back_black)
        toolbar_bookDetail.setNavigationOnClickListener {
            (activity as BookActivity).onBackPressed()
        }

        Glide.with(activity).load(bean.bookCoverUrl).into(imageView_bookCover)
        textView_bookDetail_bookName.text = bean.bookName
        textView_bookDetail_score.text = "${bean.bookScore}分"
        textView_bookDetail_introduce.text = bean.bookIntroduce
        textView_bookDetail_authorIntroduce.text = bean.authorIntroduce
        textView_shortComment1.text = bean.shortCommend[0]
        textView_shortComment2.text = bean.shortCommend[1]
        textView_shortComment3.text = bean.shortCommend[2]
        textView_longComment1.text = bean.longCommend[0]
        textView_longComment2.text = bean.longCommend[1]
        textView_longComment3.text = bean.longCommend[2]

    }

}
