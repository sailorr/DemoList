package me.sailor.demolist.ui.widget.preview

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pre_photo.*
import me.sailor.demolist.R
import me.sailor.libcommon.manager.GlideManager
import me.sailor.libcommon.net.okhttp.HttpDownCallback
import me.sailor.libcommon.net.okhttp.HttpManager
import me.sailor.libcommon.utils.FileUtils
import java.io.File
import java.lang.Exception

class PrePhotoActivity : Activity() {

    val mHandler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg!!.what == 1) {
                progressbar.setVisibility(View.VISIBLE)
                progresstv.setText(msg!!.arg1.toString()+"%")
            } else if (msg.what == 2) {
                progressbar.setVisibility(GONE)
                progresstv.setVisibility(View.GONE)
            }

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_photo)
        var url = intent.getStringExtra("url")
        GlideManager.getInstance().load(this, url, pre_img as ImageView?)


        var filename: String = FileUtils.BASEPATH + File.separator + System.currentTimeMillis().toString() + ".jpg"
        down.setOnClickListener {
            HttpManager.getInstance().DOWN(url, filename, object : HttpDownCallback {
                override fun onProgress(progress: Int) {
                    Log.d("Photo", "progress:${progress}")
                    var msg = Message()
                    msg.what = 1
                    msg.arg1 = progress
                    mHandler.sendMessage(msg)

                }

                override fun onSuccess(file: File?) {
                    var msg = Message()
                    msg.what = 2
                    mHandler.sendMessage(msg)
                    Looper.prepare()
                    Toast.makeText(this@PrePhotoActivity, "下载完成", Toast.LENGTH_SHORT).show()
                    Looper.loop()
                }

                override fun onFailure(responseCode: Int) {
                }

                override fun onError(e: Exception?) {
                }

            })
        }
    }
}


