package com.firebase.spakebrony.myandroid.utilities

import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar

public class Tools {

    companion object {
        fun showSnackbar(view : View,
        text:String){
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        }

        fun checkSelfPermission(
            context: Context?,
            permission: String?
        ): Boolean {
            return ActivityCompat.checkSelfPermission(
                    context!!,
                    permission!!
                ) != PackageManager.PERMISSION_GRANTED
        }
    }
}