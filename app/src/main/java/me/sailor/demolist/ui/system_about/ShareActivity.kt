package me.sailor.demolist.ui.system_about

import android.content.ContentResolver
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_share.*
import me.sailor.demolist.R
import me.sailor.libcommon.utils.FileUtils
import java.io.File


class ShareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        txt.setOnClickListener {
            val textIntent = Intent(Intent.ACTION_SEND)
            textIntent.type = "text/plain"
            textIntent.putExtra(Intent.EXTRA_TEXT, "这是一段分享的文字")
            startActivity(Intent.createChooser(textIntent, "分享"))
        }

        pic.setOnClickListener {
//            val path = getResourcesUri(R.mipmap.iconwangjian)

            //创建临时图片
            var file = File(FileUtils.BASEPATH,"1555580753122.jpg")
            val photoOutputUri = FileProvider.getUriForFile(
                    this,
                    "$packageName.fileprovider",
                    file)
            Log.d("ShareActivity", "onCreate: $photoOutputUri")
            val imageIntent = Intent(Intent.ACTION_SEND)
            imageIntent.type = "image/*"
            imageIntent.putExtra(Intent.EXTRA_STREAM, photoOutputUri)
            startActivity(Intent.createChooser(imageIntent, "分享"))
        }
    }

    private fun getResourcesUri(@DrawableRes id: Int): String {
        val resources = resources
        val uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id)
        Toast.makeText(this, "Uri:$uriPath", Toast.LENGTH_SHORT).show()
        return uriPath
    }
}
