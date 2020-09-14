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

class MyAdapter  : RecyclerView.Adapter<MyAdapter.LoadingViewHolder?>() {



    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadingViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_sample_item, parent, false)
        return LoadingViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(@NonNull myViewHolder: LoadingViewHolder, i: Int) {


        myViewHolder.imageView.setImageResource(ListActivity.mDataList[i].imageRes)
        myViewHolder.tvPrice.text = "¥ " + ListActivity.mDataList[i].price.toString()
        myViewHolder.tvTitle.text = ListActivity.mDataList[i].title
        myViewHolder.tvDescription.text = ListActivity.mDataList[i].description
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