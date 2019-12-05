package me.sailor.demolist.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;


import me.sailor.demolist.R;

/**
 * -description:
 * -author: created by tang on 2019/11/7 9:29
 */
public class CountDownView extends android.support.v7.widget.AppCompatTextView {

    private Thread mThread;
    private Animation mAnimation;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setVisibility(View.GONE);
        mAnimation = AnimationUtils.loadAnimation(context, R.anim.tv_cut);
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what < mDuration) {
                startAnimation(mAnimation);
                setVisibility(VISIBLE);
                setText(String.valueOf(mDuration - msg.what));
            } else {
                setVisibility(GONE);
                mCallBack.complete();
            }
            return true;
        }
    });


    private int mDuration;

    public void startCount(final int duration, final CallBack callBack) {
        this.mDuration = duration;
        this.mCallBack = callBack;
        if (mThread!=null&&mThread.getState() == Thread.State.TIMED_WAITING) {
            return;
        }
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < duration) {
                    try {
                        handler.sendEmptyMessage(i);
                        Thread.sleep(1000);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(i);
            }
        });
        mThread.start();
    }

    private CallBack mCallBack;

    public interface CallBack {
        void complete();
    }

}
