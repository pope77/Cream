package com.example.pope.cream.biz.hot

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListener
import com.example.pope.cream.biz.base.BaseLogic
import com.example.pope.cream.biz.beans.*

class HotLogic : BaseLogic(), HotInterface {

    /**
     * 获取热门排行榜数据
     */
    override fun getHotListBeans(onListBeansCallback: HotInterface.OnListBeansCallback) {
        var flag = 0
        var hitsBeans = arrayListOf<HotBean>()
        var collectionBeans = arrayListOf<HotBean>()
        val query = BmobQuery<HotBean>()
        query.addWhereNotEqualTo(HotBean.HOT_HITS, 0)
        query.findObjects(object : FindListener<HotBean>() {
            override fun done(p0: MutableList<HotBean>?, p1: BmobException?) {
                if (p1 != null) onListBeansCallback.onGetFailed(p1.toString(), "70061")
                else {
                    hitsBeans = sortHotList("点击", p0!!)
                    flag++
                    if (flag == 2) {
                        getListBeanTitles(hitsBeans, collectionBeans, onListBeansCallback)
                    }
                }
            }
        })
        val query1 = BmobQuery<HotBean>()
        query1.addWhereNotEqualTo(HotBean.HOT_COLLECTION, 0)
        query1.findObjects(object : FindListener<HotBean>() {
            override fun done(p0: MutableList<HotBean>?, p1: BmobException?) {
                if (p1 != null) onListBeansCallback.onGetFailed(p1.toString(), "70062")
                else {
                    collectionBeans = sortHotList("收藏", p0!!)
                    flag++
                    if (flag == 2) {
                        getListBeanTitles(hitsBeans, collectionBeans, onListBeansCallback)
                    }
                }
            }
        })
    }

    /**
     * 获取排行榜数据列表的每一项的title并回调数据
     */
    private fun getListBeanTitles(hitsBeans: ArrayList<HotBean>, collectionBeans: ArrayList<HotBean>, onListBeansCallback: HotInterface.OnListBeansCallback) {

        val hitsListTitles = arrayListOf<String>()
        val collectionListTitles = arrayListOf<String>()
        var flag = 0
        var titleNum1 = 0
        var titleNum2 = 0
        if (!hitsBeans.isEmpty()) {
            for ((pos,hitBean) in hitsBeans.withIndex()) {
                hitsListTitles.add("")
                when(hitBean.hotType){
                    "美食","饮品"->{
                        val query = BmobQuery<CateBean>()
                        query.getObject(hitBean.hotObjId,object :QueryListener<CateBean>(){
                            override fun done(p0: CateBean?, p1: BmobException?) {
                                if (p1!=null) onListBeansCallback.onGetFailed(p1.toString(),"70063")
                                else{
                                    hitsListTitles.set(pos,p0!!.cateName)
                                    titleNum1++
                                    if (titleNum1==hitsBeans.size){
                                        flag++
                                        if (flag==2){
                                            onListBeansCallback.onGetSuccess(hitsBeans,collectionBeans, hitsListTitles, collectionListTitles)
                                        }
                                    }
                                }
                            }
                        })
                    }
                    "电影","综艺"->{
                        val query = BmobQuery<ProgramBean>()
                        query.getObject(hitBean.hotObjId,object :QueryListener<ProgramBean>(){
                            override fun done(p0: ProgramBean?, p1: BmobException?) {
                                if (p1!=null) onListBeansCallback.onGetFailed(p1.toString(),"70064")
                                else{
                                    hitsListTitles.set(pos,p0!!.programName)
                                    titleNum1++
                                    if (titleNum1==hitsBeans.size){
                                        flag++
                                        if (flag==2){
                                            onListBeansCallback.onGetSuccess(hitsBeans,collectionBeans, hitsListTitles, collectionListTitles)
                                        }
                                    }
                                }
                            }
                        })
                    }
                    "风景"->{
                        val query = BmobQuery<SceneryBean>()
                        query.getObject(hitBean.hotObjId,object :QueryListener<SceneryBean>(){
                            override fun done(p0: SceneryBean?, p1: BmobException?) {
                                if (p1!=null) onListBeansCallback.onGetFailed(p1.toString(),"70065")
                                else{
                                    hitsListTitles.set(pos,p0!!.sceneryName)
                                    titleNum1++
                                    if (titleNum1==hitsBeans.size){
                                        flag++
                                        if (flag==2){
                                            onListBeansCallback.onGetSuccess(hitsBeans,collectionBeans, hitsListTitles, collectionListTitles)
                                        }
                                    }
                                }
                            }
                        })
                    }
                    "书籍"->{
                        val query = BmobQuery<BookBean>()
                        query.getObject(hitBean.hotObjId,object :QueryListener<BookBean>(){
                            override fun done(p0: BookBean?, p1: BmobException?) {
                                if (p1!=null) onListBeansCallback.onGetFailed(p1.toString(),"70066")
                                else{
                                    hitsListTitles.set(pos,p0!!.bookName)
                                    titleNum1++
                                    if (titleNum1==hitsBeans.size){
                                        flag++
                                        if (flag==2){
                                            onListBeansCallback.onGetSuccess(hitsBeans,collectionBeans, hitsListTitles, collectionListTitles)
                                        }
                                    }
                                }
                            }
                        })
                    }

                }
            }
        }
        if (!collectionBeans.isEmpty()) {
            for ((pos,collectionBean)in collectionBeans.withIndex()) {
                collectionListTitles.add("")
                when(collectionBean.hotType){
                    "美食","饮品"->{
                        val query = BmobQuery<CateBean>()
                        query.getObject(collectionBean.hotObjId,object :QueryListener<CateBean>(){
                            override fun done(p0: CateBean?, p1: BmobException?) {
                                if (p1!=null) onListBeansCallback.onGetFailed(p1.toString(),"70067")
                                else{
                                    collectionListTitles.set(pos,p0!!.cateName)
                                    titleNum2++
                                    if (titleNum2==collectionBeans.size){
                                        flag++
                                        if (flag==2){
                                            onListBeansCallback.onGetSuccess(hitsBeans,collectionBeans, hitsListTitles, collectionListTitles)
                                        }
                                    }
                                }
                            }
                        })
                    }
                    "电影","综艺"->{
                        val query = BmobQuery<ProgramBean>()
                        query.getObject(collectionBean.hotObjId,object :QueryListener<ProgramBean>(){
                            override fun done(p0: ProgramBean?, p1: BmobException?) {
                                if (p1!=null) onListBeansCallback.onGetFailed(p1.toString(),"70068")
                                else{
                                    collectionListTitles.set(pos,p0!!.programName)
                                    titleNum2++
                                    if (titleNum2==collectionBeans.size){
                                        flag++
                                        if (flag==2){
                                            onListBeansCallback.onGetSuccess(hitsBeans,collectionBeans, hitsListTitles, collectionListTitles)
                                        }
                                    }
                                }
                            }
                        })
                    }
                    "风景"->{
                        val query = BmobQuery<SceneryBean>()
                        query.getObject(collectionBean.hotObjId,object :QueryListener<SceneryBean>(){
                            override fun done(p0: SceneryBean?, p1: BmobException?) {
                                if (p1!=null) onListBeansCallback.onGetFailed(p1.toString(),"70069")
                                else{
                                    collectionListTitles.set(pos,p0!!.sceneryName)
                                    titleNum2++
                                    if (titleNum2==collectionBeans.size){
                                        flag++
                                        if (flag==2){
                                            onListBeansCallback.onGetSuccess(hitsBeans,collectionBeans, hitsListTitles, collectionListTitles)
                                        }
                                    }
                                }
                            }
                        })
                    }
                    "书籍"->{
                        val query = BmobQuery<BookBean>()
                        query.getObject(collectionBean.hotObjId,object :QueryListener<BookBean>(){
                            override fun done(p0: BookBean?, p1: BmobException?) {
                                if (p1!=null) onListBeansCallback.onGetFailed(p1.toString(),"70070")
                                else{
                                    collectionListTitles.set(pos,p0!!.bookName)
                                    titleNum2++
                                    if (titleNum2==collectionBeans.size){
                                        flag++
                                        if (flag==2){
                                            onListBeansCallback.onGetSuccess(hitsBeans,collectionBeans, hitsListTitles, collectionListTitles)
                                        }
                                    }
                                }
                            }
                        })
                    }

                }
            }
        }

    }

    /**
     * 对数据bean进行排序并控制数据量至多50条
     */
    private fun sortHotList(type: String, beans: MutableList<HotBean>): ArrayList<HotBean> {

        val sortBeans = arrayListOf<HotBean>()
        when (type) {
            "点击" -> {
                for (x in beans) {
                    if (sortBeans.isEmpty()) {
                        sortBeans.add(x)
                    } else {
                        for ((index, y) in sortBeans.withIndex()) {
                            if (x.hotHits > y.hotHits) {
                                sortBeans.add(index, x)
                                break
                            } else {
                                if (index == sortBeans.size - 1) {
                                    sortBeans.add(index, x)
                                    break
                                }
                            }
                        }
                    }
                }
            }
            "收藏" -> {
                for (x in beans) {
                    if (sortBeans.isEmpty()) {
                        sortBeans.add(x)
                    } else {
                        for ((index, y) in sortBeans.withIndex()) {
                            if (x.hotCollection > y.hotCollection) {
                                sortBeans.add(index, x)
                                break
                            } else {
                                if (index == sortBeans.size - 1) {
                                    sortBeans.add(index, x)
                                    break
                                }
                            }
                        }
                    }
                }
            }
        }
        var copySortBeans = arrayListOf<HotBean>()
        if (sortBeans.size > 50) {
            for (i in 0..49) {
                copySortBeans.add(sortBeans[i])
            }
        } else {
            copySortBeans = sortBeans
        }
        return copySortBeans

    }


    private object Holder {
        internal var INSTANCE = HotLogic()
    }

    companion object {

        private val INSTANCE: HotLogic? = null

        val instance: HotLogic
            get() = Holder.INSTANCE
    }

}