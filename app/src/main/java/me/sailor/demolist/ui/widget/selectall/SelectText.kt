package me.sailor.demolist.ui.widget.selectall

import android.content.Context
import android.text.Selection
import android.text.Spannable
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView

/**
 *  -description:
 *  -author: created by tang on 2019/9/19 9:05
 */
class SelectText : TextView {
    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)


    override fun performLongClick(): Boolean {
        Log.d("SelectText", "performLongClick: ")
        Selection.setSelection(text as Spannable?,0,text.length)
        return super.performLongClick()
    }
}

