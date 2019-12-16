package me.sailor.demolist.ui.system_about.callback

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_call_back.*
import me.sailor.demolist.R

class CallBackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_back)

        CallbackManager.INSTANCE.setCallback(callback)

        btn_intent.setOnClickListener {
            startActivity(Intent(this@CallBackActivity, Main2Activity::class.java))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        CallbackManager.INSTANCE.remove(callback)
    }

    private var callback = ValueCallback {
        tv1.text = it
    }
}
