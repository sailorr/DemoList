package me.sailor.demolist.ui.system_about

import me.sailor.demolist.base.BaseListButtonActivity
import me.sailor.demolist.bean.ItemModel
import me.sailor.demolist.ui.system_about.anim.AnimationActivity
import me.sailor.demolist.ui.system_about.callback.CallBackActivity
import me.sailor.demolist.ui.system_about.db.DatabaseActivity
import me.sailor.demolist.ui.system_about.setting.SettingActivity

class SystemAboutActivity : BaseListButtonActivity() {

    override fun setData(): MutableList<ItemModel> {
        itemList.add(ItemModel("Click_Animation", ClickAnimationActivity::class.java))
        itemList.add(ItemModel("Open_Office", OfficeActivity::class.java))
        itemList.add(ItemModel("DataBase",DatabaseActivity::class.java))
        itemList.add(ItemModel("Setting", SettingActivity::class.java))
        itemList.add(ItemModel("share", ShareActivity::class.java))
        itemList.add(ItemModel("callback",CallBackActivity::class.java))
        itemList.add(ItemModel("Permission", PermissionActivity::class.java))
        itemList.add(ItemModel("AnimationActivity", AnimationActivity::class.java))
        itemList.add(ItemModel("CanvasActivity", CanvasActivity::class.java))
        return itemList
    }

}
