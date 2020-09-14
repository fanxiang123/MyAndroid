package com.firebase.spakebrony.myandroid.activity

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.spakebrony.myandroid.BaseActivity
import com.firebase.spakebrony.myandroid.R
import com.firebase.spakebrony.myandroid.adapter.LoadingAdapter
import com.firebase.spakebrony.myandroid.adapter.MyAdapter
import com.firebase.spakebrony.myandroid.baen.DataBean
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.content_toolbar.*
import me.samlss.broccoli.Broccoli
import java.util.*
import kotlin.collections.HashMap


class ListActivity : BaseActivity() {


    companion object {

        public val sImageIds = intArrayOf(
            R.mipmap.photo_1, R.mipmap.photo_2,
            R.mipmap.photo_3, R.mipmap.photo_4,
            R.mipmap.photo_5
        )

        public val sPrices = intArrayOf(
            549, 1499, 1199, 1699, 3388
        )

        public val sTitles = arrayOf(
            "honor/荣耀 畅玩7",
            "Huawei/华为 畅想MAX",
            "honor/荣耀 荣耀9i",
            "Huawei/华为 畅想9 PLUS",
            "Huawei/华为 P20"
        )

        public val sDescriptions = arrayOf(
            "2018.05上市 | 免举证退换货",
            "2018.10上市 | 免举证退换货",
            "2018.06上市 | 免举证退换货",
            "2018.10上市 | 免举证退换货",
            "2018.04上市 | 免举证退换货"
        )
        public var loadingAdapter: LoadingAdapter? = null
        public var myAdapter: MyAdapter? = null
        public val mDataList: ArrayList<DataBean> = ArrayList<DataBean>()


        public val mViewPlaceholderManager: MutableMap<View, Broccoli> =
            HashMap()
        public val mTaskManager: MutableMap<View, Runnable> =
            HashMap()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initToolbar()

        initData()
        initRecyclerView()
    }


    override fun onDestroy() {
        super.onDestroy()
        mDataList.clear()
        for (view in mTaskManager.keys) {
            view.removeCallbacks(mTaskManager[view])
        }

        //防止使用BroccoliGradientDrawable时内存泄露
        for (broccoli in mViewPlaceholderManager.values) {
            broccoli.removeAllPlaceholders()
        }
        mViewPlaceholderManager.clear()
        mTaskManager.clear()
    }

    private fun initData() {
        for (i in 0..19) {
            val dataBean = DataBean()
            dataBean.imageRes =
                sImageIds[i % sImageIds.size]
            dataBean.title =
                sTitles[i % sTitles.size]
            dataBean.description =
                sDescriptions[i % sDescriptions.size]
            dataBean.price =
                sPrices[i % sPrices.size]
            mDataList.add(dataBean)
        }
    }

    private fun initRecyclerView() {
        recyclerview_loading.layoutManager = LinearLayoutManager(applicationContext)
        loadingAdapter = LoadingAdapter()
        recyclerview_loading.adapter = loadingAdapter

        object : Thread() {
            override fun run() {
                super.run()
                sleep(3000) //休眠3秒
                runOnUiThread {
                    recyclerview_loading.visibility = View.GONE
                    recyclerview.layoutManager = LinearLayoutManager(applicationContext)
                    myAdapter = MyAdapter()
                    recyclerview.adapter = myAdapter
                }
            }
        }.start()
    }


    private fun initToolbar() {
        toolbar.title = "占位列表预加载"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) //左侧添加一个默认的返回图标
        supportActionBar!!.setHomeButtonEnabled(true) //设置返回键可用
    }


}
