package com.example.pope.cream.page.home


import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.UserBean
import com.example.pope.cream.page.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_center.*


/**
 * A simple [Fragment] subclass.
 * @author popeg
 */
class CenterFragment : BaseFragment<HomeContract.CenterPresenter>(), HomeContract.CenterView {

    /**
     * 加载用户数据
     */
    override fun loadUserBean(bean: UserBean) {
        var name = bean.userName
        if (name == "UserName") {
            name = "点击即可修改昵称"
        }
        editText_center_userName.setText(name)
        textView_center_phoneNum.text = bean.userPhoneNum
        textView_center_collections.text = bean.userCollection.toString()
        textView_center_views.text = bean.userViews.toString()
        //设置不可获取焦点
        editText_center_userName.isFocusable = false
        editText_center_userName.isFocusableInTouchMode = false
    }

    override fun toast(msg: String, length: Int) {
        tst(msg, length)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_center, container, false)
        CenterPresenter(this)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //获取用户bean并加载
        mPresenter!!.getUserBean(activity)
        //设置点击监听
        editText_center_userName.setOnFocusChangeListener { view, isFocus ->
            if (isFocus) {
                editText_center_userName.setOnEditorActionListener(object : TextView.OnEditorActionListener {
                    override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                        if (p1 == 6) {
                            if (p0!!.text.toString() == "UserName" || p0.text.toString() == "") {
                                tst("新昵称违规 请重新输入")
                            }
                            mPresenter!!.changeUserName(p0!!.text.toString(), activity)
                        }
                        return false
                    }
                })
            }
        }
        //点击监听
        editText_center_userName.setOnClickListener {
            //修改昵称
            changeUserName()
        }
        textView_center_changeName.setOnClickListener {
            //修改昵称
            changeUserName()
        }
        textView_center_changeNameIcon.setOnClickListener {
            //修改昵称
            changeUserName()
        }
    }

    /**
     * 修改昵称
     */
    private fun changeUserName() {
        //将Bottombar隐藏 以防视图被遮挡以及被点击
        (activity as MainActivity).moveBottomBar(3)
        //清空输入框
        editText_center_userName.setText("")
        tst("请输入新昵称")
        editText_center_userName.isFocusableInTouchMode = true
        editText_center_userName.isFocusable = true
        editText_center_userName.requestFocus()
        editText_center_userName.isCursorVisible = true

    }

    /**
     * 名字修改成功
     */
    override fun nameChangeSuccess() {
        editText_center_userName.isFocusable = false
        editText_center_userName.isFocusableInTouchMode = false
        tst("修改成功")
        //让bottombar复原
        (activity as MainActivity).moveBottomBar(4)
    }

}
