package me.sailor.demolist.ui.widget

import me.sailor.demolist.base.BaseListButtonActivity
import me.sailor.demolist.bean.ItemModel
import me.sailor.demolist.ui.widget.brvah.SelectDeleteActivity
import me.sailor.demolist.ui.widget.calendar.CalendarActivity
import me.sailor.demolist.ui.widget.circle.CircleLineActivity
import me.sailor.demolist.ui.widget.doublerecycle.DoubleRecycleActivity
import me.sailor.demolist.ui.widget.moveable.MovebleActivity
import me.sailor.demolist.ui.widget.rocker.RockerActivity
import me.sailor.demolist.ui.widget.rxbind.RxBindActivity
import me.sailor.demolist.ui.widget.select.SelectActivity
import me.sailor.demolist.ui.widget.selectall.SelectAllActivity
import me.sailor.demolist.ui.widget.tablayout.TabLayoutActivity

class WidgetActivity : BaseListButtonActivity() {
    override fun setData(): MutableList<ItemModel> {
        itemList.add(ItemModel("Rxbinding", RxBindActivity::class.java))
        itemList.add(ItemModel("DoubleRecycle", DoubleRecycleActivity::class.java))
        itemList.add(ItemModel("Calendar", CalendarActivity::class.java))
        itemList.add(ItemModel("Rocker", RockerActivity::class.java))
        itemList.add(ItemModel("TitleRadioView", TitleRadioViewActivity::class.java))
        itemList.add(ItemModel("SelectAll", SelectAllActivity::class.java))
        itemList.add(ItemModel("SelectAct", SelectActivity::class.java))
        itemList.add(ItemModel("SelectDelete", SelectDeleteActivity::class.java))
        itemList.add(ItemModel("ViewSwitch", ViewSwitchActivity::class.java))
        itemList.add(ItemModel("TabLayoutActivity", TabLayoutActivity::class.java))
        itemList.add(ItemModel("CircleLineActivity", CircleLineActivity::class.java))
        itemList.add(ItemModel("CanvaTestActivity", CanvaTestActivity::class.java))
        itemList.add(ItemModel("MovebleActivity", MovebleActivity::class.java))
        return itemList
    }

}
