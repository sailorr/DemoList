package me.sailor.demolist.ui.media

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import kotlinx.android.synthetic.main.activity_video_play.*
import me.sailor.demolist.R

class VideoPlayActivity : AppCompatActivity() {

    private var orientationUtils: OrientationUtils? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //不显示程序的标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        //不显示系统的标题栏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_video_play)
        init()
    }

    private fun init() {

//        val photo = intent.getStringExtra(PARAM_PHOTO)
        val source1 =
                "https://apd-videohy.apdcdn.tc.qq.com/vlive.qqvideo.tc.qq.com/1006_550380dbeddc47dfa22d8629e58dd06a.f20.mp4?vkey=EA6103D8680AAD63F9B73CC38734FAD6E107DA8A79E9BDB9BEBA90144E9D4601A71006E2C2C5CB063979E223CE70256BCF88F96245AEA5E4358A71AE1DB605F8A0E3DAE3FCA3172DCCF15B8E7E4051930DA8D007B5FD6569"
        video_player.setUp(source1, true, "")

        //增加封面
//        val imageView = ImageView(this)
//        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//        Glide.with(imageView).load(photo).into(imageView)
//        video_player.setThumbImageView(imageView)
        //增加title
        video_player.getTitleTextView().setVisibility(View.VISIBLE)
        //设置返回键
        video_player.getBackButton().setVisibility(View.VISIBLE)
        orientationUtils = OrientationUtils(this, video_player)
        //设置旋转
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        video_player.getFullscreenButton().setOnClickListener(View.OnClickListener {
            orientationUtils?.resolveByClick()
        })
        //是否可以滑动调整
        video_player.setIsTouchWiget(true)
        //设置返回按键功能
        video_player.getBackButton().setOnClickListener(View.OnClickListener { onBackPressed() })
        video_player.startPlayLogic()
    }

    override fun onPause() {
        super.onPause()
        video_player?.onVideoPause()
    }

    override fun onResume() {
        super.onResume()
        video_player.onVideoResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
        if (orientationUtils != null)
            orientationUtils!!.releaseListener()
    }

    override fun onBackPressed() {
        //先返回正常状态
        if (orientationUtils!!.getScreenType() === ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            video_player.getFullscreenButton().performClick()
            return
        }
        //释放所有
        video_player.setVideoAllCallBack(null)
        super.onBackPressed()
    }

//    companion object {
//        var PARAM_VIDEO = "videourl"
//        var PARAM_PHOTO = "photourl"
//
//        fun start(context: Context, url: String) {
//            val starter = Intent(context, VideoPlayActivity::class.java)
//            starter.putExtra(PARAM_VIDEO, url)
////            starter.putExtra(PARAM_PHOTO, thumb)
//            context.startActivity(starter)
//        }
//    }
}
