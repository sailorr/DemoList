package me.sailor.demolist.ui.widget.brvah

import android.content.Context
import android.content.Intent
import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import me.sailor.demolist.R
import me.sailor.libcommon.down.DownloadLimitManager
import me.sailor.libcommon.preview.PreviewActivity
import java.util.*

class PreAndDownActivity : PreviewActivity() {

    companion object {
        fun preImgList(ctx: Context, list: ArrayList<CharSequence>?, position: Int) {
            val intent = Intent(ctx, PreAndDownActivity::class.java)
            intent.putExtra(CURRENT_POS, position)
            intent.putCharSequenceArrayListExtra(URL_LIST, list)
            ctx.startActivity(intent)
        }
    }

    override fun initAdapter(urls: ArrayList<CharSequence>): BaseQuickAdapter<CharSequence, BaseViewHolder> {
        this.urls = urls
        adapter = PreandDownAdapter(urls)
        return adapter!!
    }

    var adapter: PreandDownAdapter? = null
    var urls: ArrayList<CharSequence>? = null
    override fun init() {
        super.init()
        adapter?.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.btn_downSingle -> {
                    Log.d("PreAndDownActivity", "init: btn_downSingle")
                    DownloadLimitManager.getInstance().download(urls!![0] as String)
                }
                R.id.btn_downAll -> {
                    Log.d("PreAndDownActivity", "init:btn_downAll ")
                    DownloadLimitManager.getInstance().downList(urls)
                }
            }
        }


    }

}
