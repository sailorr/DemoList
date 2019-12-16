package me.sailor.demolist.ui.widget.selectall

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select_all.*
import me.sailor.demolist.R

class SelectAllActivity : AppCompatActivity() {


    companion object{
        fun start (ctx: Context){
            ctx.startActivity(Intent(ctx,SelectAllActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_all)
        text_txt.setText(text_txt.text,TextView.BufferType.SPANNABLE)
    }
}
