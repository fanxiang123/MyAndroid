package com.firebase.spakebrony.myandroid.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.firebase.spakebrony.myandroid.BaseActivity
import com.firebase.spakebrony.myandroid.R
import com.firebase.spakebrony.myandroid.utilities.NotificationUtil
import com.permissionx.guolindev.PermissionX
import com.thefinestartist.finestwebview.FinestWebView
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

        gps.setOnClickListener {
            startActivity(Intent(this,GpsActivity::class.java))
        }

        web.setOnClickListener {
            FinestWebView.Builder(this)
                .showSwipeRefreshLayout(false)
                .show("https://www.baidu.com/")
        }

        permission.setOnClickListener {

            PermissionX.init(this)
                .permissions(Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE)
                .onExplainRequestReason { scope, deniedList ->
                    scope.showRequestReasonDialog(deniedList, "核心基础基于这些权限", "同意", "取消")
                }
                .onForwardToSettings { scope, deniedList ->
                    scope.showForwardToSettingsDialog(deniedList, "您需要在设置中手动允许必要的权限", "同意", "取消")
                }
                .request { allGranted, _, deniedList ->
                    if (allGranted) {
                        Toast.makeText(this, "已授予所有权限", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "这些权限被拒绝: $deniedList", Toast.LENGTH_LONG).show()
                    }
                }
        }

        notification.setOnClickListener {
            val notificationUtils = NotificationUtil(this)
            notificationUtils.sendNotification("通知栏的title", "通知栏的信息")
        }
    }

    private fun initToolbar(){
         setSupportActionBar(toolbar)
         toolbar.title = "My Android"
    }


}
