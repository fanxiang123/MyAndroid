package com.firebase.spakebrony.myandroid.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.firebase.spakebrony.myandroid.R
import com.firebase.spakebrony.myandroid.activity.ListActivity
import me.samlss.broccoli.Broccoli
import me.samlss.broccoli.BroccoliGradientDrawable
import me.samlss.broccoli.PlaceholderParameter

class LoadingAdapter  : RecyclerView.Adapter<LoadingAdapter.LoadingViewHolder?>() {



    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadingViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_sample_item, parent, false)
        return LoadingViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(@NonNull myViewHolder: LoadingViewHolder, i: Int) {
        var broccoli: Broccoli? = ListActivity.mViewPlaceholderManager[myViewHolder.itemView]
        if (broccoli == null) {
            broccoli = Broccoli()
            ListActivity.mViewPlaceholderManager[myViewHolder.itemView] = broccoli
        }
        broccoli.removeAllPlaceholders()
        broccoli.addPlaceholder(
            PlaceholderParameter.Builder()
                .setView(myViewHolder.tvTitle)
                .setDrawable(
                    BroccoliGradientDrawable(
                        Color.parseColor("#DDDDDD"),
                        Color.parseColor("#CCCCCC"), 0.0f, 1000, LinearInterpolator()
                    )
                )
                .build()
        )
        broccoli.addPlaceholder(
            PlaceholderParameter.Builder()
                .setView(myViewHolder.imageView)
                .setDrawable(
                    BroccoliGradientDrawable(
                        Color.parseColor("#DDDDDD"),
                        Color.parseColor("#CCCCCC"), 0.0f, 1000, LinearInterpolator()
                    )
                )
                .build()
        )
        broccoli.addPlaceholder(
            PlaceholderParameter.Builder()
                .setView(myViewHolder.tvPrice)
                .setDrawable(
                    BroccoliGradientDrawable(
                        Color.parseColor("#DDDDDD"),
                        Color.parseColor("#CCCCCC"), 0.0f, 1000, LinearInterpolator()
                    )
                )
                .build()
        )
        broccoli.addPlaceholder(
            PlaceholderParameter.Builder()
                .setView(myViewHolder.tvDescription)
                .setDrawable(
                    BroccoliGradientDrawable(
                        Color.parseColor("#DDDDDD"),
                        Color.parseColor("#CCCCCC"), 0.0f, 1000, LinearInterpolator()
                    )
                )
                .build()
        )
        broccoli.show()


        //delay to show the data
        var task: Runnable? = ListActivity.mTaskManager[myViewHolder.itemView]
        if (task == null) {
            val finalBroccoli = broccoli
            task =
                Runnable { //when you need to update data, you must to call the remove or the clear method.
                    finalBroccoli.removeAllPlaceholders()
                    if (ListActivity.mDataList.isEmpty()) {
                        return@Runnable
                    }
                }
            ListActivity.mTaskManager[myViewHolder.itemView] = task
        } else {
            myViewHolder.itemView.removeCallbacks(task)
        }
        myViewHolder.itemView.postDelayed(task, 20000)
    }

    override fun getItemCount(): Int {
        return ListActivity.mDataList.size
    }

    class LoadingViewHolder(@NonNull itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_image)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_price)

    }
}