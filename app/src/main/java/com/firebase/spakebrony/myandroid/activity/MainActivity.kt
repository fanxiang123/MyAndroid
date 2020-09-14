package com.firebase.spakebrony.myandroid.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.firebase.spakebrony.myandroid.BaseActivity
import com.firebase.spakebrony.myandroid.R
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.content_toolbar.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initClick()
    }

    private fun initClick() {
        list.setOnClickListener {
            startActivity(Intent(this,ListActivity::class.java))
        }
    }

    private fun initToolbar(){
         setSupportActionBar(toolbar)
         toolbar.title = "My Android"
    }


}
