package me.sailor.demolist.ui.media

import me.sailor.demolist.base.BaseListButtonActivity
import me.sailor.demolist.bean.ItemModel
import me.sailor.demolist.ui.media.h5.BaseWebActivity
import me.sailor.demolist.ui.media.okhttp.HttpActivity
import me.sailor.demolist.ui.media.socket.SocketActivity
import me.sailor.demolist.ui.widget.brvah.BrvahLoadmore
import me.sailor.demolist.ui.widget.brvah.UpFetchActivity


/**
 *  -description:
 *  -author: created by tang on 2019/10/16 10:42
 */
class MediaActivity : BaseListButtonActivity() {
    override fun setData(): MutableList<ItemModel> {
        itemList.add(ItemModel("Ok Http", HttpActivity::class.java))
        itemList.add(ItemModel("BrvahLoadmore", BrvahLoadmore::class.java))
        itemList.add(ItemModel("Fetch", UpFetchActivity::class.java))
        itemList.add(ItemModel("Socket", SocketActivity::class.java))
        itemList.add(ItemModel("H5", BaseWebActivity::class.java))
        itemList.add(ItemModel("Video Player", VideoPlayActivity::class.java))

        return itemList
    }
}