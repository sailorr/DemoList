package me.sailor.demolist.ui.widget.brvah

import android.view.View
import android.widget.CheckBox
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import me.sailor.demolist.R

/**
 *  -description:
 *  -author: created by tang on 2019/9/29 11:49
 */
class SelectDelAdapter(data: MutableList<String>?)
    : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_select_del, data) {
    private var allowSelect: Boolean = false
    var isSelectAll = false
    override fun convert(helper: BaseViewHolder, item: String) {
        helper.setText(R.id.item_text, item)
        val checkBox: CheckBox = helper.itemView.findViewById(R.id.item_check)
        checkBox.isChecked = isSelectAll
        checkBox.visibility = if (allowSelect) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun startSelect(allowSelect: Boolean) {
        this.allowSelect = allowSelect
        notifyDataSetChanged()
    }

    fun selectAll(selectall: Boolean) {
        this.isSelectAll = selectall
        notifyDataSetChanged()
    }
}