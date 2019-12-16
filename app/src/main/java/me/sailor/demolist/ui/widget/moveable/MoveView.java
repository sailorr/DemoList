package me.sailor.demolist.ui.widget.moveable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;

import androidx.appcompat.widget.LinearLayoutCompat;

import me.sailor.demolist.R;
import me.sailor.libcommon.utils.DensityUtils;

/**
 * -description:
 * -author: created by tang on 2019/11/21 11:46
 */
public class MoveView extends LinearLayoutCompat {

    private int width;
    private int height;
    private int screen_width;
    private int screen_height;

    public MoveView(Context context) {
        this(context, null);
    }

    public MoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_moveview, this);
        screen_width = DensityUtils.getScreenWidth(getContext());
        screen_height = DensityUtils.getScreenHeight(getContext());

        Log.d("MoveView", String.format("width=%s,height=%s", screen_width, screen_height));
        Log.d("MoveView", "MoveView: width:" + screen_width + "height:" + screen_height);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        Log.d("MoveView", "onMeasure: width->" + width + " height->" + height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private float mx, my;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float TOLERANCE = 3;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mx = event.getX();
                my = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float xDistance = event.getX() - mx;
                final float yDistance = event.getY() - my;
                int l, r, t, b;
                if (Math.abs(xDistance) > TOLERANCE || Math.abs(yDistance) > TOLERANCE) {
                    l = (int) (getLeft() + xDistance);
                    if (l < 0) {
                        l = 0;
                    } else if (l > screen_width - width) {
                        l = screen_width - width;
                    }
                    r = l + width;
                    t = (int) (getTop() + yDistance);
                    if (t < 0) {
                        t = 0;
                    } else if (t > screen_height - height) {
                        t = screen_height - height;
                    }
                    b = t + height;
                    Log.d("MoveText", "onTouchEvent: x:" + event.getX() + " l:" + l);
                    this.layout(l, t, r, b);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d("MoveView", "onLayout: l:" + l + " t:" + t);
    }
}
