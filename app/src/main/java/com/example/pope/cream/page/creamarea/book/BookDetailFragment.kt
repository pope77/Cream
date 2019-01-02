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
import com.example.pope.cream.page.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_book_detail.*

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class BookDetailFragment(val bookBean: BookBean) : BaseFragment<BookContract.BookDetailPresenter>(), BookContract.BookDetailView {

    /**
     * 取消收藏成功
     */
    override fun uncollectSuccess() {
        uncollectThisBook()
    }

    /**
     * 检查是否收藏结果
     */
    override fun checkResult(isCollected: Boolean) {
        if (isCollected) collectThisBook()
        else uncollectThisBook()
    }

    /**
     * 收藏成功
     */
    override fun collectSuccess() {
        collectThisBook()
    }

    override fun toast(msg: String, length: Int) {
        tst(msg, length)
    }

    val bean = bookBean
    var isCollected = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book_detail, container, false)
        BookDetailPresenter(this)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //初始化toolbar
        toolbar_bookDetail.setNavigationIcon(R.mipmap.ic_arrow_back_black)
        toolbar_bookDetail.setNavigationOnClickListener {
            (activity as BookActivity).onBackPressed()
        }

        //数据加载
        Glide.with(activity).load(bean.bookCoverUrl).into(imageView_bookCover)
        textView_bookDetail_bookName.text = bean.bookName
        textView_bookDetail_score.text = "${bean.bookScore}分"
        textView_bookDetail_introduce.text = bean.bookIntroduce
        textView_bookDetail_authorIntroduce.text = bean.authorIntroduce
        textView_shortComment1.text = bean.shortComment[0]
        textView_shortComment2.text = bean.shortComment[1]
        textView_shortComment3.text = bean.shortComment[2]
        textView_longComment1.text = bean.longComment[0]
        textView_longComment2.text = bean.longComment[1]
        textView_longComment3.text = bean.longComment[2]

        //检查该书是否被收藏
        mPresenter!!.checkIsCollected(bean.objectId, activity)
        //打开详情页 用户浏览量+1
        mPresenter!!.userViewsPP("书籍", bean.objectId, activity)

        //收藏按键监听
        imageView_bookDetail_collection.setOnClickListener {
            if (isCollected) {
                mPresenter!!.uncollectThisBook(bean.objectId, activity)
                uncollectThisBook()
            } else {
                mPresenter!!.collectBook(bean.objectId, activity)
                collectThisBook()
            }
        }
        textView_bookDetail_collection.setOnClickListener {
            if (isCollected) {
                mPresenter!!.uncollectThisBook(bean.objectId, activity)
                uncollectThisBook()
            } else {
                mPresenter!!.collectBook(bean.objectId, activity)
                collectThisBook()
            }
        }

    }

    /**
     * 已收藏此书
     */
    private fun collectThisBook() {
        imageView_bookDetail_collection.setImageResource(R.mipmap.ic_collection_black_selected)
        textView_bookDetail_collection.text = "已收藏"
        isCollected = true
    }

    /**
     * 为收藏此书
     */
    private fun uncollectThisBook() {
        imageView_bookDetail_collection.setImageResource(R.mipmap.ic_collection_black_unselected)
        textView_bookDetail_collection.text = "收藏"
        isCollected = false
    }

}
