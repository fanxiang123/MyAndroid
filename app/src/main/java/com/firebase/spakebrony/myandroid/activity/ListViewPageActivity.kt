package com.firebase.spakebrony.myandroid.activity

import android.os.Bundle
import com.firebase.spakebrony.myandroid.BaseActivity
import com.firebase.spakebrony.myandroid.R
import com.firebase.spakebrony.myandroid.activity.fragment.ListListFragment
import com.firebase.spakebrony.myandroid.nethttp.RetrofitHelper
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.activity_list_view_page.*
import kotlinx.android.synthetic.main.activity_rx_java.*
import kotlinx.android.synthetic.main.content_toolbar.*
import rx.Observable.just
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*


class ListViewPageActivity : BaseActivity() {


    private val tabList = arrayOf(
        "热门", "男装", "女装", "鞋包", "手机", "电器", "食品", "百货", "服饰", "汽车", "家装", "运动",
        "母婴", "水果", "内衣", "家纺", "美妆"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_page)
        initToolbar()
        initData()
    }


    private fun initData(){

        val pages = FragmentPagerItems(this)
        for (string in tabList) {
            pages.add(FragmentPagerItem.of(string, ListListFragment::class.java))
        }

        val adapter = FragmentPagerItemAdapter(
            supportFragmentManager, pages
        )

        viewPage.adapter = adapter
        recyclerTabLayout.setViewPager(viewPage)


    }


    private fun initToolbar() {
        toolbar.title = "不规则列表(FaceImage控件，Tab,ViewPage)"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) //左侧添加一个默认的返回图标
        supportActionBar!!.setHomeButtonEnabled(true) //设置返回键可用
    }

}
