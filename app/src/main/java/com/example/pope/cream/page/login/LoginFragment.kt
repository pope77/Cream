package com.example.pope.cream.page.login


import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pope.cream.InterestPointActivity

import com.example.pope.cream.R
import com.example.pope.cream.page.base.BaseFragment
import com.example.pope.cream.page.home.MainActivity
import com.example.pope.cream.utils.SystemUtil
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass.
 * @author popeg
 */
class LoginFragment : BaseFragment<LoginContract.Presenter>(), LoginContract.View {

    /**
     * 显示或消失ProgressDialog
     */
    override fun showOrHideProgressDialog() {
        if (progressDialog == null) {progressDialog = ProgressDialog(activity)}
        if (progressDialog!!.isShowing) hideProgressDialog() else showProgressDialog()
    }

    override fun toast(msg: String, length: Int) {
        tst(msg, length)
    }

    /**
     * 加载老用户手机号码
     */
    override fun loadNum(phoneNum: String) {
        editText_login_phoneNumber.setText(phoneNum)
        toast("已为你自动加载手机号")
    }

    /**
     * 界面跳转 1->兴趣选择  -1->主页
     */
    override fun jump2NewActivity(code: Int) {
        when (code) {
            1 -> startActivity(Intent(activity, InterestPointActivity::class.java))
            -1 -> startActivity(Intent(activity, MainActivity::class.java))
        }
        activity.finish()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_login, container, false)

        LoginPresenter(this)
        initSomething()

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    /**
     * 初始化监听器
     */
    private fun initListener() {
        //“一键登录”按钮的监听
        textView_login_enter.setOnClickListener {
            showOrHideProgressDialog()
            //先检测用户输入的手机号是否选择了兴趣点
            mPresenter!!.isSelectedInterest(editText_login_phoneNumber.text.toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initSomething() {

        //更改状态栏为透明并将状态栏图标颜色改为暗色图标
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = activity.window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            decorView.systemUiVisibility = option
            activity.window.statusBarColor = Color.TRANSPARENT
        }

        mPresenter!!.checkIsOldUser(SystemUtil.getIMEI(activity))

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getUserImei(): String {
        return SystemUtil.getIMEI(activity)
    }

    companion object {

        fun newInstance(): LoginFragment {
            return LoginFragment()
        }

    }

}
