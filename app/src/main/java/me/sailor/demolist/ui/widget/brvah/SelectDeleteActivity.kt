package me.sailor.demolist.ui.widget.brvah

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select_delete.*
import me.sailor.demolist.R

class SelectDeleteActivity : AppCompatActivity() {
    var isSelect = false
    var isSelectAll = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_delete)

        val adpter = SelectDelAdapter(data())
        rv_delect.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        rv_delect.adapter = adpter

        btn_del.setOnClickListener {
            isSelect = !isSelect
            adpter.startSelect(isSelect)
            Log.d("SelectDeleteActivity", "onCreate: ")
        }
        btn_all.setOnClickListener {
            isSelectAll = !isSelectAll
            adpter.selectAll(isSelectAll)
        }

    }

    fun data():MutableList<String>{
        var list:MutableList<String> = ArrayList()
        for (i in 0..9){
            list.add("这是数据$i")
        }
        return list
    }



}
