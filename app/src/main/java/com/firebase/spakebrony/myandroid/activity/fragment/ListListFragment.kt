package com.firebase.spakebrony.myandroid.activity.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.firebase.spakebrony.myandroid.R
import com.firebase.spakebrony.myandroid.adapter.RecyclerPinsCardAdapter
import com.firebase.spakebrony.myandroid.baen.PinsMainEntity
import kotlinx.android.synthetic.main.fragment_list_list.*
import java.util.ArrayList


class ListListFragment : Fragment() {


    private var root: View? = null


    private var mAdapter: RecyclerPinsCardAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (root == null)
            root = inflater.inflate(R.layout.fragment_list_list, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val position = FragmentPagerItem.getPosition(arguments)

        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        listGoRecyclerView.layoutManager = layoutManager
        mAdapter =  RecyclerPinsCardAdapter(listGoRecyclerView);
        listGoRecyclerView.adapter = mAdapter

        val mList : ArrayList<PinsMainEntity> =  ArrayList()
        val a = PinsMainEntity()
        a.file_url = "https://img0.baidu.com/it/u=4281903617,3664271605&fm=26&fmt=auto"
        a.file_url_w = 500
        a.file_url_h = 750
        mList.add(a)

        val a2 = PinsMainEntity()
        a2.file_url = "https://img1.baidu.com/it/u=749287967,85837294&fm=26&fmt=auto"
        a2.file_url_w = 640
        a2.file_url_h = 300
        mList.add(a2)
        mList.add(a)
        mList.add(a2)
        mList.add(a)
        mList.add(a2)
        mList.add(a)
        mList.add(a2)
        mList.add(a)
        mList.add(a2)
        mList.add(a)
        mList.add(a2)
        mList.add(a)
        mList.add(a2)
        mList.add(a)
        mList.add(a2)
        mList.add(a)
        mList.add(a2)
        mList.add(a)
        mList.add(a2)
        mList.add(a)
        mList.add(a2)
        mAdapter!!.setList(mList)
    }
}