package com.firebase.spakebrony.myandroid.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.location.LocationManager.NETWORK_PROVIDER
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.alibaba.fastjson.JSONObject
import com.firebase.spakebrony.myandroid.BaseActivity
import com.firebase.spakebrony.myandroid.R
import com.firebase.spakebrony.myandroid.utilities.Tools
import com.permissionx.guolindev.PermissionX
import kotlinx.android.synthetic.main.activity_gps.*
import kotlinx.android.synthetic.main.content_toolbar.*
import java.text.DecimalFormat
import java.util.*


class GpsActivity : BaseActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gps)
        initToolbar()
        PermissionX.init(this)
            .permissions( Manifest.permission.ACCESS_FINE_LOCATION ,Manifest.permission.ACCESS_COARSE_LOCATION)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(deniedList, "核心基础基于这些权限", "同意", "取消")
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(deniedList, "您需要在设置中手动允许必要的权限", "同意", "取消")
            }
            .request { allGranted, _, deniedList ->
                if (allGranted) {
                    Thread(Runnable {
                        initGps()
                    }).start()
                    Toast.makeText(this, "已授予所有权限", Toast.LENGTH_LONG).show()
                } else {
                    finish()
                    Toast.makeText(this, "这些权限被拒绝: $deniedList", Toast.LENGTH_LONG).show()
                }
            }

    }
    private var  i = 0
    private fun initGps() {
         val  context  =  this
        val jsonObject = JSONObject()
        if (!Tools.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            val locationManager =
                context!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val providers =
                locationManager.getProviders(true)
            if (providers.contains(LocationManager.GPS_PROVIDER)) {
                val criteria = Criteria()
                criteria.accuracy = Criteria.ACCURACY_FINE
                criteria.isAltitudeRequired = false
                criteria.isBearingRequired = false
                criteria.isCostAllowed = true
                criteria.powerRequirement = Criteria.POWER_LOW

//                String provider = locationManager.getBestProvider(criteria, true);
//                Location location = locationManager.getLastKnownLocation(provider);
                var location: Location? = null
                for (provider in providers) {
                    val l =
                        locationManager.getLastKnownLocation(provider) ?: continue
                    if (location == null || l.accuracy < location.accuracy) {
                        // Found best last known location: %s", l);
                        location = l
                    }
                }
                var latitude = -1.0
                var longitude = -1.0
                if (null != location) {
                    latitude = location.latitude
                    longitude = location.longitude
                    val gpsObj = JSONObject()
                    gpsObj["latitude"] = latitude
                    gpsObj["longitude"] = longitude
                    jsonObject["gps"] = gpsObj
                } else {
                    location =
                        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    if (null != location) {
                        latitude = location.latitude
                        longitude = location.longitude
                        val netObj =
                            JSONObject()
                        netObj["latitude"] = latitude
                        netObj["longitude"] = longitude
                        jsonObject["gps"] = netObj
                    }
                }
                try {
                    val geocoder = Geocoder(context, Locale.getDefault())
                    val df = DecimalFormat()
                    df.maximumFractionDigits = 3
                    val lat = df.format(latitude).toDouble()
                    val lon = df.format(longitude).toDouble()
                    val addresses =
                        geocoder.getFromLocation(lat, lon, 1)
                    if (addresses.size > 0) {
                        val address = addresses[0]
                        val country = address.countryName
                        val province = address.adminArea
                        val city = address.subAdminArea
                        val bigDirect = address.locality
                        val smallDirect = address.thoroughfare
                        val detailed = address.getAddressLine(0)
                        jsonObject["gps_address_province"] = isNullText(province)
                        jsonObject["gps_address_city"] = isNullText(city)
                        jsonObject["gps_address_large_district"] = isNullText(bigDirect)
                        jsonObject["gps_address_small_district"] = isNullText(smallDirect)
                        jsonObject["gps_address_street"] = isNullText(detailed)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {

                var latitude = -1.0
                var longitude = -1.0
               val location =
                    locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if (null != location) {
                    latitude = location.latitude
                    longitude = location.longitude
                    val netObj =
                        JSONObject()
                    netObj["latitude"] = latitude
                    netObj["longitude"] = longitude
                    jsonObject["gps"] = netObj
                }
            }
        }
       
        runOnUiThread {

            gps_text.text = jsonObject.toJSONString()
        }
    }


    private fun initToolbar() {
        toolbar.title = "获取GPS定位"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) //左侧添加一个默认的返回图标
        supportActionBar!!.setHomeButtonEnabled(true) //设置返回键可用
    }

    fun isNullText(text: String?): String? {
        if (null == text) {
            return ""
        }
        return if (TextUtils.isEmpty(text)) {
            ""
        } else text
    }
}
