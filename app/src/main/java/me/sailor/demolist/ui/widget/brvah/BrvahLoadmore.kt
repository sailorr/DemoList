package me.sailor.demolist.ui.widget.brvah

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.chad.library.adapter.base.listener.OnItemSwipeListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_brvah_avtivity.*
import me.sailor.demolist.R
import me.sailor.demolist.bean.PhotoBean
import me.sailor.demolist.bean.Result
import me.sailor.demolist.contant.Api
import me.sailor.libcommon.base.BaseActivity
import me.sailor.libcommon.net.retrofit.RetrofitManager


//https://www.jianshu.com/p/b343fcff51b0 使用文档
class BrvahLoadmore : BaseActivity() {
    companion object {
        fun start(ctx: Context) {
            val starter = Intent(ctx, BrvahLoadmore::class.java)
            ctx.startActivity(starter)
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_brvah_avtivity
    }

    private var mAdapter: BrvahAdapter? = null
    private val mResults: ArrayList<Result> = ArrayList()
    override fun init() {
        mAdapter = BrvahAdapter(mResults)
        val itemDragAndSwipeCallback = ItemDragAndSwipeCallback(mAdapter)
        val itemTouchHelper = ItemTouchHelper(itemDragAndSwipeCallback)
        itemTouchHelper.attachToRecyclerView(rv_brvah)

        mAdapter?.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
//        mAdapter?.setLoadMoreView(CustomLoadMoreView())
        //允许左滑移除item
        mAdapter?.enableSwipeItem()
        mAdapter?.setOnItemSwipeListener(object : OnItemSwipeListener {
            override fun clearView(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder?, pos: Int) {
                android.util.Log.d("BrvahLoadmore", "clearView: ")
            }

            override fun onItemSwiped(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder?, pos: Int) {
                android.util.Log.d("BrvahLoadmore", "onItemSwiped: ")
            }

            override fun onItemSwipeStart(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder?, pos: Int) {
                android.util.Log.d("BrvahLoadmore", "onItemSwipeStart: ")
            }

            override fun onItemSwipeMoving(canvas: Canvas?, viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder?, dX: Float, dY: Float, isCurrentlyActive: Boolean) {
                android.util.Log.d("BrvahLoadmore", "onItemSwipeMoving: ")
            }

        })
        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, rv_brvah)
        rv_brvah.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        rv_brvah.adapter = mAdapter
        loadData()

        mAdapter?.setOnItemClickListener { adapter, view, position ->
            PreAndDownActivity.preImgList(this@BrvahLoadmore, imgList, position)
        }


    }

    fun loadData() {
        RetrofitManager.getInstance().getServer(Api::class.java)
                .imgs
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ jsonRootBean ->
                    mResults.clear()
                    mThumbViewInfoList.clear()
                    setThumbViewInfoList(jsonRootBean.results)
                    mResults.addAll(jsonRootBean.results)
                    mAdapter?.notifyDataSetChanged()
                }, { throwable -> Log.d("RetrofitActivity", "accept: " + throwable.message.toString()) })
    }


    fun loadMore() {
        RetrofitManager.getInstance().getServer(Api::class.java)
                .imgs
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ jsonRootBean ->
                    setThumbViewInfoList(jsonRootBean.results)
                    mResults.addAll(jsonRootBean.results)
                    mAdapter?.loadMoreComplete()
                }, { throwable -> Log.d("RetrofitActivity", "accept: " + throwable.message.toString()) })
    }
    private val mThumbViewInfoList = arrayListOf<PhotoBean>()

    private val imgList:ArrayList<CharSequence> =ArrayList()

    private fun setThumbViewInfoList(list:List<Result>){
        list.forEach {

            imgList.add(it.url)
//            val photoBean = PhotoBean(it.url)
//            mThumbViewInfoList.add(photoBean)
        }
    }
}
