package me.sailor.demolist.ui.widget.brvah

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import me.sailor.demolist.R
import me.sailor.libcommon.manager.GlideManager
import java.util.*

/**
 *  -description:
 *  -author: created by tang on 2019/10/18 15:33
 */
class PreandDownAdapter(data: ArrayList<CharSequence>?) : BaseQuickAdapter<CharSequence, BaseViewHolder>(R.layout.item_predown, data) {
    override fun convert(helper: BaseViewHolder?, item: CharSequence?) {
        val ptView = helper?.itemView?.findViewById<ImageView>(R.id.pt_img)
        helper?.addOnClickListener(R.id.btn_downAll)
        helper?.addOnClickListener(R.id.btn_downSingle)
        GlideManager.getInstance().load(mContext, item as String?, ptView)
    }
}