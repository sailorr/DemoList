package me.sailor.demolist.ui.system_about

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.yanzhenjie.permission.runtime.Permission
import kotlinx.android.synthetic.main.activity_permission.*
import kotlinx.android.synthetic.main.activity_share.pic
import me.sailor.demolist.R
import me.sailor.libcommon.manager.PermissionManager

class PermissionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        var permissions = arrayOf(Permission.CALL_PHONE,Permission.READ_CONTACTS,Permission.RECORD_AUDIO)

        pic.setOnClickListener {
            PermissionManager.getInstance().requestPermission(this@PermissionActivity,Permission.CAMERA) {
                Log.d("PermissionActivity", "onCreate: "+Thread.currentThread().name)
                Log.d("PermissionActivity", "onCreate: get CAMERA")
            }
        }

        file.setOnClickListener {
            PermissionManager.getInstance().requestPermission(this@PermissionActivity,Permission.READ_EXTERNAL_STORAGE){
                Log.d("PermissionActivity", "onCreate: get WRITE_EXTERNAL_STORAGE")
            }
        }

        many.setOnClickListener {
            PermissionManager.getInstance().requestPermission(this@PermissionActivity,permissions){
                Log.d("PermissionActivity", "onCreate: get many")
            }
        }
    }


}
