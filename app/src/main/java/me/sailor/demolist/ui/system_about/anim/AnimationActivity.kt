package me.sailor.demolist.ui.system_about.anim

import android.animation.Animator
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_animation.*
import me.sailor.demolist.R
import me.sailor.demolist.ui.system_about.anim.CountDownProgressBar.*
import me.sailor.demolist.ui.widget.CountDownView

class AnimationActivity : AppCompatActivity() {


    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        val int = 10;
        var anim = AnimationUtils.loadAnimation(this, R.anim.tv_cut)
        btn_start.setOnClickListener {
//            cpb_countdown.setDuration(8000) {
//
//            }

            count.startCount(5) {
                Log.d("AnimationActivity", "complete: "+Thread.currentThread().name)
            }
//            var i: Int = 0
//            Thread(Runnable {
//                while (i<int) {
//                    runOnUiThread {
//                        if (tv_time.visibility == View.GONE){
//                            tv_time.visibility = View.VISIBLE
//                        }
//                        tv_time.text = ((int-i).toString())
//                        tv_time.startAnimation(anim)
//                    }
//                    Thread.sleep(1000)
//                    i++
//                }
//
//            }).start()



        }
    }
}
