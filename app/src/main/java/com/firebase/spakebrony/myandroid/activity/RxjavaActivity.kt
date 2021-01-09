package com.firebase.spakebrony.myandroid.activity

import android.os.Bundle
import com.firebase.spakebrony.myandroid.BaseActivity
import com.firebase.spakebrony.myandroid.R
import com.firebase.spakebrony.myandroid.nethttp.RetrofitHelper
import kotlinx.android.synthetic.main.activity_rx_java.*
import kotlinx.android.synthetic.main.content_toolbar.*
import rx.Observable.just
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*


class RxjavaActivity : BaseActivity() {


    private var textSubscription : Subscription?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)
        initToolbar()
        initRxJava()
    }


    private fun initRxJava(){




        textSubscription =  RetrofitHelper.appAPI
            .getNum()
            .subscribeOn(Schedulers.newThread()) //请求在新的线程中执行
            .observeOn(Schedulers.io()) //请求完成后在io线程中执行s
            .observeOn(AndroidSchedulers.mainThread()) //最后在主线程中执行
            .subscribe({//请求成功结果
                rx_java_text_num.text = it.toString()
            }, {//请求错误结果
                rx_java_text_num.text = it.message
            })


    }


    private fun initToolbar() {
        toolbar.title = "Rxjava"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) //左侧添加一个默认的返回图标
        supportActionBar!!.setHomeButtonEnabled(true) //设置返回键可用
    }

    override fun onDestroy() {
        super.onDestroy()
        textSubscription?.unsubscribe()
    }
}
