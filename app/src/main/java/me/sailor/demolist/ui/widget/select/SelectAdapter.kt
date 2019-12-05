package me.sailor.demolist.ui.widget.select

import android.util.Log
import android.view.View
import android.widget.CheckBox
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import me.sailor.demolist.R

/**
 *  -description:
 *  -author: created by tang on 2019/9/25 15:41
 */
class SelectAdapter(data: MutableList<SelectBean>) :
        BaseQuickAdapter<SelectBean, BaseViewHolder>(R.layout.item_select, data) {
    override fun convert(helper: BaseViewHolder, item: SelectBean) {
        Log.d("SelectAdapter", "convert:$item ")
        helper.setText(R.id.item_title, item.name)
        val check = helper.itemView.findViewById<CheckBox>(R.id.item_check)
        helper.itemView.setOnClickListener {
            Log.d("SelectAdapter", "setOnItemClickListener: ")
            if (item.selectStatus != SelectBean.DEFAULT_SELECTED) {
                when (item.selectStatus) {
                    SelectBean.SELECTED -> {
                        check.visibility = View.GONE
                        item.selectStatus = SelectBean.NONE
                    }
                    SelectBean.NONE -> {
                        check.visibility = View.VISIBLE
                        item.selectStatus = SelectBean.SELECTED
                    }
                }
            }

        }
        when (item.selectStatus) {
            SelectBean.DEFAULT_SELECTED -> check.isChecked = false
//            SelectBean.SELECTED -> check.isChecked = true
            SelectBean.NONE -> check.visibility = View.GONE
        }
    }
}