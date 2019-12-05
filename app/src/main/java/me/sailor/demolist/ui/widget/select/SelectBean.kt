package me.sailor.demolist.ui.widget.select

/**
 *  -description:
 *  -author: created by tang on 2019/9/25 15:33
 */
class SelectBean(var name: String, var selectStatus: Int = NONE) {
    companion object {
        const val DEFAULT_SELECTED = 1
        const val SELECTED = 2
        const val NONE = 3
    }

    override fun toString(): String {
        return "name:$name  selectStatus:$selectStatus "
    }
}