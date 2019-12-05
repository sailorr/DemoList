package me.sailor.demolist.ui.widget.select

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag_select.*
import me.sailor.demolist.R

/**
 *  -description:
 *  -author: created by tang on 2019/9/25 15:08
 */
class SelectFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("SelectFragment", "onCreate: ")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("SelectFragment", "onCreateView: ")
        return inflater.inflate(R.layout.frag_select, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("SelectFragment", "onViewCreated: ")
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    var mList: MutableList<SelectBean> = ArrayList()
    var adapter = SelectAdapter(mList)

    private fun init() {
        rv_select.layoutManager = LinearLayoutManager(activity)
        rv_select.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        rv_select.adapter = adapter

    }

    fun setData(list: MutableList<SelectBean>) {
        Log.d("SelectFragment", "setData1:$list ")
        mList.clear()
        mList.addAll(list)
        Log.d("SelectFragment", "setData2:$list ")
        adapter.setNewData(mList)
    }

    fun getData():MutableList<SelectBean>{
        return  mList
    }

}