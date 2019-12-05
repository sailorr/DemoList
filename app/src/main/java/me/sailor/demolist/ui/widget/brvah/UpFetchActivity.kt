package me.sailor.demolist.ui.widget.brvah

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_up_fetch.*
import me.sailor.demolist.R
import me.sailor.demolist.bean.Book
import me.sailor.libcommon.base.BaseActivity
import kotlin.collections.ArrayList

class UpFetchActivity : BaseActivity() {
    companion object {
        fun start(ctx: Context) {
            val starter = Intent(ctx, UpFetchActivity::class.java)
            ctx.startActivity(starter)
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_up_fetch
    }
    var mAdapter = FetchAdapter()
    var data = ArrayList<Book>()
    override fun init() {

        rv_upfetch.layoutManager = LinearLayoutManager(this@UpFetchActivity)
        rv_upfetch.adapter = mAdapter
        mAdapter.setNewData(genData())
        mAdapter.isUpFetchEnable = true
        mAdapter.setStartUpFetchPosition(0)
        mAdapter.setUpFetchListener {
            startFetch()
        }
    }

    var count: Int = 0
    private fun startFetch() {
        count++
        mAdapter.isUpFetching = true
        rv_upfetch.postDelayed({
            mAdapter.addData(0, genData())
            mAdapter.isUpFetching = false
            if (count > 5) {
                mAdapter.isUpFetchEnable = false
            }
        }, 1000)
    }


    private fun genData(): List<Book> {
        Log.e("TAG","start get data")
        val list = ArrayList<Book>()
        for (i in 0..9) {
            val name = "Legend$i"
            var book = Book(name, i, i.toString())
            list.add(book)
        }
        return list
    }
}
