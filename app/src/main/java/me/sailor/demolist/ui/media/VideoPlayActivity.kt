package me.sailor.demolist.ui.media

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
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
        val source1 = "https://jdplay.lecloud.com/play.videocache.lecloud.com/274/21/80/letv-uts/14/ver_00_22-1124461912-avc-2997494-aac-128000-5763042-2260181450-2317146d3a00e072dfd4127d7657bb3e-1575683324147.mp4?crypt=78aa7f2e492&b=1314&nlh=4096&nlt=60&bf=30&p2p=1&video_type=mp4&termid=1&tss=no&platid=3&splatid=345&its=0&qos=3&fcheck=0&amltag=6&mltag=6&uid=1871249407.rp&keyitem=GOw_33YJAAbXYE-cnQwpfLlv_b2zAkYctFVqe5bsXQpaGNn3T1-vhw..&ntm=1576493400&nkey=32543ba192bcc1628169fe47be0099d3&nkey2=c8b50bb45279ba8e5495d6d5499ef304&auth_key=1576493400-1-0-3-345-9701021ccdb6f49f369662ef12c373aa&geo=CN-7-0-3&ch=letv&playid=0&pay=0&uidx=0&errc=0&gn=50038&ndtype=2&vrtmcd=102&buss=6"
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
