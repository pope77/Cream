package com.example.pope.cream.page.creamarea.takeout


import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast

import com.example.pope.cream.R
import com.example.pope.cream.biz.beans.TakeOutBean
import com.leochuan.CircleLayoutManager
import com.leochuan.CircleScaleLayoutManager
import kotlinx.android.synthetic.main.activity_interset_point.view.*
import kotlinx.android.synthetic.main.fragment_take_out_card.*

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class TakeOutCardFragment : Fragment() {

    var isPlayIcon = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_take_out_card, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //更改状态栏为透明并将状态栏图标颜色改为暗色图标
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = activity!!.window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            decorView.systemUiVisibility = option
            activity!!.window.statusBarColor = Color.TRANSPARENT
        }

        toolbar_takeoutFragment.setNavigationIcon(R.mipmap.ic_arrow_back_black)
        toolbar_takeoutFragment.title = "外卖随机选择器"
        toolbar_takeoutFragment.setNavigationOnClickListener{
            activity.finish()
        }

        initSpinCard()
        initPlayBtn()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initSpinCard() {

        var pics = arrayListOf(
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/7a1f2c8240ae9fe7807030ad61248d9e.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/b1f5854540314e2f808182832728d0d0.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/c8fff0f44022da9680075d6829ca4b76.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/df74fef8408b4cca80cfebeee58e228f.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/79f8da0a406929528098ce228c029d84.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/7e5e37d0400f7732804a9307e27713a6.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/9d05d8e44048718580d597579e311cc3.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/0e8b7f1d407484c580179f869922f453.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/86ab9d614053ac128022977ccc9ae53d.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/6897622a4035bef480a1b488a59deda7.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/0c068dfa40f0e595802455fe4214dbd9.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/fa0ebad94082ecba80230f817e160d2e.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/ee52cc4540c4be0b80452632762d8379.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/07bb4da640e708b980ac940b0b2ff7c1.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/8cf6e99e402be5678072e94b3ac92784.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/02d75d9f40964ddd8042c11859161bcf.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/870043664008cd5680813c602676bafb.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/6ae1280d40ccdc7080c1593a5d73db9b.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/e418ff9440a3adc580569dd767b4d037.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/db8932a3406cd5bd8072e2f95a71c237.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/dace17b0402a0b02806c3437b8f0f571.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/37470b56407c9201801afb5d490dd5a8.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/97d36d4b40bb9095803124b4fc4a72cc.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/d126edc740ffaf4b80d8fd9a698b20e9.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/266ae399407e9aff8000435ae8b61391.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/3f911fb8403cb76e80804e62241f8b46.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/66911b81401d028c802bd3c52ec8d425.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/ed84dff14035273d80dd392397594f11.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/13f3b33540f0b54a8001ab8e339f5c48.png",
                "http://bmob-cdn-22675.b0.upaiyun.com/2018/12/05/59e7073240270e7f8008cb432e2426f9.png"
        )
        var names = arrayListOf(
                "杨国福麻辣烫", "奈何过桥米线", "张亮麻辣烫", "食其家牛丼咖喱","芝心乐披萨", "拼一碗！蛋包饭",
                "肯德基", "十分粥道", "董小姐韩式炸鸡", "北京烤鸭", "沙县小吃", "胖哥俩肉蟹煲",
                "重庆小面", "萤火烧烤", "东北饺子馆", "川味坊麻辣香锅", "张姐烤肉拌饭","台湾花甲王",
                "阿三烧烤","杭粥西湖","川锦汇麻辣拌","必胜客","野鳗鲜森","蔬卡轻食","三米粥铺",
                "1号大食堂中式快餐","林葱烤鱼饭","舌尖上的川菜","麦当劳","淮南牛头汤特色锅贴"
        )

        val takeoutBeans = ArrayList<TakeOutBean>()
        for (i in 0..29){
            takeoutBeans.add(TakeOutBean(names[i],pics[i]))
        }

        recyclerView_spinCard.layoutManager = CircleScaleLayoutManager.Builder(activity)
                .setRadius(4000)
                .setDistanceToBottom(500)
                .setCenterScale(1.0F)
                .setAngleInterval(10)
                .setMaxVisibleItemCount(10)
                .build()
        recyclerView_spinCard.adapter = TakeoutAdapter(takeoutBeans,activity)
    }

    private fun initPlayBtn() {
        imageView_takeout_pause.setOnClickListener {
            if (isPlayIcon) {
                imageView_takeout_play.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.rotate_and_alpha_hidebtn))
                imageView_takeout_pause.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.rotate_and_alpha_showbtn))
                isPlayIcon = false
                recyclerView_spinCard.start()
            } else {
                imageView_takeout_play.startAnimation(AnimationUtils.loadAnimation(activity,R.anim.rotate_and_alpha_showbtn))
                imageView_takeout_pause.startAnimation(AnimationUtils.loadAnimation(activity,R.anim.rotate_and_alpha_hidebtn))
                isPlayIcon = true
                recyclerView_spinCard.pause()
            }
        }
    }
}
