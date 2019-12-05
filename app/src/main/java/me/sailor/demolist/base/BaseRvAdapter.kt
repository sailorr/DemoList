package me.sailor.demolist.base

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import me.sailor.demolist.R
import me.sailor.demolist.bean.ItemModel

/**
 * -description:
 * -author: created by tang on 2019/10/16 9:52
 */
class BaseRvAdapter(data: MutableList<ItemModel>?) :
        BaseQuickAdapter<ItemModel, BaseViewHolder>(R.layout.item_recycler, data) {

    override fun convert(helper: BaseViewHolder?, item: ItemModel?) {
        helper?.setText(R.id.item_btn,item?.title)
    }

}
