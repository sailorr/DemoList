package me.sailor.libcommon.preview

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import me.sailor.libcommon.R
import me.sailor.libcommon.manager.GlideManager
import java.util.*

/**
 *  -description:
 *  -author: created by tang on 2019/10/18 15:33
 */
class PreviewRvAdapter(data: ArrayList<CharSequence>?) : BaseQuickAdapter<CharSequence, BaseViewHolder>(R.layout.item_pre, data) {
    override fun convert(helper: BaseViewHolder?, item: CharSequence?) {
        val ptView = helper?.itemView?.findViewById<ImageView>(R.id.pt_img)
        GlideManager.getInstance().load(mContext, item as String?, ptView)
    }
}