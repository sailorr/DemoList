package me.sailor.demolist.base

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_baselist.*
import me.sailor.demolist.R
import me.sailor.demolist.bean.ItemModel
import me.sailor.libcommon.base.BaseActivity

/**
 *  -description:
 *  -author: created by tang on 2019/10/16 9:49
 */
abstract class BaseListButtonActivity : BaseActivity() {
    override fun setLayoutId(): Int {
        return R.layout.activity_baselist
    }

    private var mAdapter: BaseRvAdapter? = null
    protected var itemList: MutableList<ItemModel> = ArrayList()
    override fun init() {
        itemList = setData()
        mAdapter = BaseRvAdapter(itemList)
        rv_base.layoutManager = LinearLayoutManager(this)
        rv_base.adapter = mAdapter
        mAdapter?.setOnItemClickListener { _, _, position ->
            Log.d("BaseListButtonActivity", "init:$position ")
            startActivity(Intent(this@BaseListButtonActivity, itemList[position].activity))
        }
    }



    abstract fun setData(): MutableList<ItemModel>
}