package me.sailor.demolist.ui.widget.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity;
import kotlinx.android.synthetic.main.content_fragment.*
import me.sailor.demolist.R


class FragmentActivity : AppCompatActivity() {
    private val titles = arrayOf("最新", "热门", "推荐", "图片", "视频", "我的")
    private val fragments: MutableList<Fragment> = ArrayList()
    private var viewAdapter: ViewAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        for (i in 0 until titles.size) {
            tab_bottom.addTab(tab_bottom.newTab())
            fragments.add(TabFragment.NEWINSTANCE(titles[i]))
        }
        viewAdapter = ViewAdapter(supportFragmentManager, fragments, titles)
        pager.adapter = viewAdapter

        tab_bottom.setupWithViewPager(pager)
        tab_bottom.setTabsFromPagerAdapter(viewAdapter)
    }


    companion object {
        fun start(context: Context) {
            val starter = Intent(context, FragmentActivity::class.java)
            context.startActivity(starter)
        }
    }

}
