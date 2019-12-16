package me.sailor.demolist.ui.system_about.callback

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*
import me.sailor.demolist.R

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        CallbackManager.INSTANCE.setCallback(callback)


        change.setOnClickListener {
            CallbackManager.INSTANCE.callbacks.forEach {
                it.callback("改变后的数据。")
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        CallbackManager.INSTANCE.remove(callback)
    }

    private var callback = ValueCallback {
        tv2.text = it
    }
}
