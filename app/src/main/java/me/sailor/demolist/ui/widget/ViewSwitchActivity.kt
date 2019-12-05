package me.sailor.demolist.ui.widget

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_view_switch.*
import me.sailor.demolist.R

class ViewSwitchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_switch)

        img_blue.setOnClickListener {
            vs_op.displayedChild = 1
        }

        img_greed.setOnClickListener {
            Log.d("ViewSwitchActivity", "onCreate: ")

            vs_op.displayedChild = 0
        }

        Thread(Runnable {
            while (true) {
                runOnUiThread {
                    //显示前面的布局
                    //vs_op.displayedChild = 1  这个值>0
                    //可以用静态常量 如: vs_op.displayedChild = SHOW_COMFIRM(显示确定按钮)
                    vs_op.showPrevious()
                }
                Thread.sleep(500)
                runOnUiThread {
                    //显示后面的布局
                    //                    vs_op.displayedChild = 0
                    vs_op.showNext()
                }
                Thread.sleep(500)
            }
        }).start()
    }
}
