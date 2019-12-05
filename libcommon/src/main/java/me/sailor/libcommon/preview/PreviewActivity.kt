package me.sailor.libcommon.preview

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_preview.*
import me.sailor.libcommon.R
import me.sailor.libcommon.base.BaseActivity
import java.util.*

class PreviewActivity : BaseActivity() {

    companion object {
        fun preImgList(ctx: Context, list: ArrayList<CharSequence>?, position: Int) {
            val intent = Intent(ctx, PreviewActivity::class.java)
            intent.putExtra("position", position)
            intent.putCharSequenceArrayListExtra("urls", list)
            ctx.startActivity(intent)
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_preview
    }


    override fun init() {
        val urls: ArrayList<CharSequence> = intent.getCharSequenceArrayListExtra("urls")
        val position = intent.getIntExtra("position", 0)
        val mAdapter = PreviewRvAdapter(urls)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        rv_pre.layoutManager = linearLayoutManager
        rv_pre.adapter = mAdapter
        rv_pre.scrollToPosition(position)

        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            if(view.id==R.id.btn_downSingle){
                Log.d("PreviewActivity", "init: down all")
                if (PreListenerManager.INSTANCE.mDownClickListener!=null){
                    PreListenerManager.INSTANCE.mDownClickListener.downSigle(urls[position] as String)
                }
            }else if (view.id==R.id.btn_downAll){
                if (PreListenerManager.INSTANCE.mDownClickListener!=null){
                    PreListenerManager.INSTANCE.mDownClickListener.downAll(urls)
                }
            }
        }

        val pagerHelper = PhotoPagerHelper()
        pagerHelper.attachToRecyclerView(rv_pre)
        pagerHelper.setListener {
            tv_position.text = String.format("%s/%s", it + 1, urls.size)
        }
    }



}
