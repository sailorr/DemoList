package me.sailor.demolist.ui.widget.tablayout

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v7.widget.LinearLayoutCompat
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.TableLayout
import kotlinx.android.synthetic.main.view_customtab.view.*
import me.sailor.demolist.R

/**
 *  -description:
 *  -author: created by tang on 2019/10/24 11:19
 */
class FontAttrSettingView : LinearLayoutCompat {
    private var title = arrayOf("键盘", "样式", "字体")

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_customtab, this)
        initView()
    }

    private fun initView() {
        initTab()

        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                when (p0?.tag) {
                    0 -> {
                        Log.d("FontAttrSettingView", "onTabReselected: 键盘")
                    }
                    1 -> {
                        Log.d("FontAttrSettingView", "onTabReselected: 样式")
                    }
                    2-> {
                        Log.d("FontAttrSettingView", "onTabReselected: 字体")
                    }
                }
            }
        })
    }

    private fun initTab() {
        for (i in 0 until title.size) {
            val tabItem = tab.newTab()
            tabItem.tag = i
            tabItem.text = title[i]
            tab.addTab(tabItem)
        }
    }
}