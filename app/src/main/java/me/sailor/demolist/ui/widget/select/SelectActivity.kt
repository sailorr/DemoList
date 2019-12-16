package me.sailor.demolist.ui.widget.select

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select.*
import me.sailor.demolist.R

class SelectActivity : AppCompatActivity() {

    companion object{
        fun start(ctx:Context){
            ctx.startActivity(Intent(ctx,SelectActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)
        var fragment = SelectFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.frag, fragment)
                .commit()
        fragment.setData(getData())

        tv_select.setOnClickListener {
            Log.d("SelectActivity", "onCreate: ${fragment.getData()}")
        }
    }

    var list: MutableList<SelectBean> = ArrayList()
    fun getData(): MutableList<SelectBean> {
        list.clear()
        var a = SelectBean("aaaa", SelectBean.DEFAULT_SELECTED)
        var b = SelectBean("bbbb", SelectBean.SELECTED)
        var c = SelectBean("cccc", SelectBean.NONE)
        var d = SelectBean("dddd", SelectBean.NONE)
        var e = SelectBean("eeee", SelectBean.NONE)
        list.add(a)
        list.add(b)
        list.add(c)
        list.add(d)
        list.add(e)
        Log.d("SelectActivity", "getData:$list ")
        return list
    }
}
