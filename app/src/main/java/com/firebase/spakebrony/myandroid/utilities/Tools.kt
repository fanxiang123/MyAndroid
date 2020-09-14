package com.firebase.spakebrony.myandroid.utilities

import android.app.Activity
import android.view.View
import android.widget.Toolbar
import androidx.annotation.Nullable
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.content_toolbar.*

public class Tools {

    companion object {
        fun showSnackbar(view : View,
        text:String){
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        }

    }
}