package com.firebase.spakebrony.myandroid.utilities

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.firebase.spakebrony.myandroid.R

class NotificationUtil(var mContext: Context) {
    private var mManager: NotificationManager? = null
    fun sendNotification(title: String?, content: String?) {
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel()
            val notification = getNotification_26(title, content).build()
            getmManager()!!.notify(1, notification)
        } else {
            val notification = getNotification_25(title, content).build()
            getmManager()!!.notify(1, notification)
        }
    }

    private fun getmManager(): NotificationManager? {
        if (mManager == null) {
            mManager =
                mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        return mManager
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun createNotificationChannel() {
        val channel = NotificationChannel(
            sID,
            sName,
            NotificationManager.IMPORTANCE_HIGH
        )
        getmManager()!!.createNotificationChannel(channel)
    }

    fun getNotification_25(
        title: String?,
        content: String?
    ): NotificationCompat.Builder {

        // 以下是展示大图的通知
        val style = NotificationCompat.BigPictureStyle()
        style.setBigContentTitle("BigContentTitle")
        style.setSummaryText("SummaryText")
        style.bigPicture(
            BitmapFactory.decodeResource(
                mContext.resources,
                R.mipmap.ic_launcher
            )
        )

        // 以下是展示多文本通知
        val style1 = NotificationCompat.BigTextStyle()
        style1.setBigContentTitle(title)
        style1.bigText(content)
        return NotificationCompat.Builder(mContext)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    mContext.resources,
                    R.mipmap.ic_launcher
                )
            )
            .setStyle(style)
            .setAutoCancel(true)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun getNotification_26(
        title: String?,
        content: String?
    ): Notification.Builder {
        return Notification.Builder(mContext, sID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    mContext.resources,
                    R.mipmap.ic_launcher
                )
            )
            .setStyle(
                Notification.BigPictureStyle().bigPicture(
                    BitmapFactory.decodeResource(
                        mContext.resources,
                        R.mipmap.ic_launcher
                    )
                )
            )
            .setNumber(1)
            .setAutoCancel(true)
    }

    companion object {
        const val sID = "channel_1"
        const val sName = "channel_name_1"
    }

}