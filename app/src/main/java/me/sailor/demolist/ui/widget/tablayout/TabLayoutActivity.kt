package me.sailor.demolist.ui.widget.tablayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tab_layout.*
import me.sailor.demolist.R

class TabLayoutActivity : AppCompatActivity() {

    private var strings = arrayOf("a", "b", "c", "d", "e", "f", "h", "i", "e", "k")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)


        for (i in 0..3) {
            val tabItem = tab1.newTab()
            tabItem.tag = i
            tabItem.text = strings[i]
            tab1.addTab(tabItem)
        }

        for (i in 0..4) {
            val tabItem = tab2.newTab()
            tabItem.tag = i
            tabItem.text = strings[i]
            tab2.addTab(tabItem)
        }

        for (i in 0..5) {
            val tabItem = tab3.newTab()
            tabItem.tag = i
            tabItem.text = strings[i]
            tab3.addTab(tabItem)
        }

        for (i in 0..6) {
            val tabItem = tab4.newTab()
            tabItem.tag = i
            tabItem.text = strings[i]
            tab4.addTab(tabItem)
        }

        for (i in 0..7) {
            val tabItem = tab5.newTab()
            tabItem.tag = i
            tabItem.text = strings[i]
            tab5.addTab(tabItem)
        }
    }
}
