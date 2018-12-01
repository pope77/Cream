package com.example.pope.cream

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.UpdateListener
import com.example.pope.cream.biz.beans.UserBean
import com.example.pope.cream.page.home.MainActivity
import com.igalata.bubblepicker.BubblePickerListener
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.BubbleGradient
import com.igalata.bubblepicker.model.PickerItem
import kotlinx.android.synthetic.main.activity_interset_point.*

/**
 * @author popeg
 */
class InterestPointActivity : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog
    private var interestList = arrayListOf<String>()
    private val boldTypeface by lazy { Typeface.createFromAsset(assets, ROBOTO_BOLD) }
    private val mediumTypeface by lazy { Typeface.createFromAsset(assets, ROBOTO_MEDIUM) }

    companion object {
        private const val ROBOTO_BOLD = "roboto_bold.ttf"
        private const val ROBOTO_MEDIUM = "roboto_medium.ttf"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interset_point)

        progressDialog = ProgressDialog(this)

        //更改状态栏为透明并将状态栏图标颜色改为暗色图标
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = this.window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            decorView.systemUiVisibility = option
            this.window.statusBarColor = Color.TRANSPARENT
        }


        titleTextView.typeface = boldTypeface
        subtitleTextView.typeface = boldTypeface
        hintTextView.typeface = boldTypeface
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            subtitleTextView.letterSpacing = 0.06f
            hintTextView.letterSpacing = 0.05f
        }

        val pickerPoints = resources.getStringArray(R.array.pickerPoints)
        val pickerColors = resources.obtainTypedArray(R.array.pickerColors)
        val pickerImages = resources.obtainTypedArray(R.array.pickerImages)

        interest_picker.adapter = object : BubblePickerAdapter {
            override val totalCount = pickerPoints.size

            override fun getItem(position: Int): PickerItem {

                return PickerItem().apply {
                    title = pickerPoints[position]
                    textSize = 48F
                    gradient = BubbleGradient(pickerColors.getColor((position * 2) % 8, 0),
                            pickerColors.getColor((position * 2) % 8 + 1, 0), BubbleGradient.VERTICAL)
                    typeface = mediumTypeface
                    textColor = ContextCompat.getColor(this@InterestPointActivity, android.R.color.white)
                    backgroundImage = ContextCompat.getDrawable(this@InterestPointActivity, pickerImages.getResourceId(position, 0))
                }

            }

        }

        pickerColors.recycle()
        pickerImages.recycle()

        interest_picker.bubbleSize = 35
        interest_picker.listener = object : BubblePickerListener {
            override fun onBubbleDeselected(item: PickerItem) {
                interestList.remove(item.title!!)
            }

            override fun onBubbleSelected(item: PickerItem) {
                interestList.add(item.title!!)
            }


        }

        //“完成”按钮的监听
        textView_interestpoint_finish.setOnClickListener {

            progressDialog.setTitle("请稍等")
            progressDialog.setMessage("正在加载")
            progressDialog.show()
            if (interestList.size < 3) {
                Toast.makeText(this@InterestPointActivity, "请至少选择三个感兴趣的标签", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            } else {
                val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
                val query = BmobQuery<UserBean>()
                query.getObject(sharedPreferences.getString(UserBean.USER_OBJID,""), object : QueryListener<UserBean>() {
                    override fun done(p0: UserBean?, p1: BmobException?) {
                        if (p1 != null) {
                            Toast.makeText(this@InterestPointActivity, "error70004", Toast.LENGTH_SHORT).show()
                            Log.i("error70004", p1.toString())
                            progressDialog.dismiss()
                        } else {
                            p0!!.userInterestPoint = interestList
                            p0!!.update(object : UpdateListener() {
                                override fun done(p0: BmobException?) {
                                    if (p0 != null) {
                                        Toast.makeText(this@InterestPointActivity, "error70005", Toast.LENGTH_SHORT).show()
                                        Log.i("error70005", p1.toString())
                                        progressDialog.dismiss()
                                    } else {
                                        finishSelected()
                                    }
                                }

                            })
                        }
                    }

                })
            }
        }

    }


    /**
     * 结束兴趣点选择
     */
    private fun finishSelected() {

        //将本地用户名保存为“UserName”用来与注册了未选择兴趣点的用户做区分
        val editer = getSharedPreferences("user", Context.MODE_PRIVATE).edit()
        editer.putString(UserBean.USER_NAME,"UserName")
        editer.apply()

        startActivity(Intent(this, MainActivity::class.java))
        progressDialog.dismiss()
        finish()
    }

    override fun onResume() {
        super.onResume()
        interest_picker.onResume()
    }

    override fun onPause() {
        super.onPause()
        interest_picker.onPause()
    }

}
