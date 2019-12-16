package me.sailor.libcommon.preview

import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_preview.*
import me.sailor.libcommon.R
import me.sailor.libcommon.base.BaseActivity
import java.util.*


open class PreviewActivity : BaseActivity() {

    companion object {
        const val CURRENT_POS = "position"
        const val URL_LIST = "urls"

        fun preImgList(ctx: Context, list: ArrayList<CharSequence>?, position: Int) {
            val intent = Intent(ctx, PreviewActivity::class.java)
            intent.putExtra(CURRENT_POS, position)
            intent.putCharSequenceArrayListExtra(URL_LIST, list)
            ctx.startActivity(intent)
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_preview
    }

    var rv: androidx.recyclerview.widget.RecyclerView? = null
    var tv: TextView? = null

    open fun initAdapter(urls: ArrayList<CharSequence>): BaseQuickAdapter<CharSequence, BaseViewHolder> {
        return PreviewRvAdapter(urls)
    }

    override fun init() {
        val urls: ArrayList<CharSequence> = intent.getCharSequenceArrayListExtra(URL_LIST)
        val position = intent.getIntExtra(CURRENT_POS, 0)
        rv = rv_pre
        tv = tv_position
        val mAdapter = initAdapter(urls)
        val linearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        linearLayoutManager.orientation = androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
        rv?.layoutManager = linearLayoutManager
        rv?.adapter = mAdapter
        rv?.scrollToPosition(position)
        val pagerHelper = PhotoPagerHelper()
        pagerHelper.attachToRecyclerView(rv)
        pagerHelper.setListener {
            tv?.text = String.format("%s/%s", it + 1, urls.size)
        }

    }

}
