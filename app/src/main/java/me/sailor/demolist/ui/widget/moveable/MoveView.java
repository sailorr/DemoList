package me.sailor.demolist.ui.widget.moveable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;

import me.sailor.demolist.R;

/**
 * -description:
 * -author: created by tang on 2019/11/21 11:46
 */
public class MoveView extends LinearLayoutCompat {

    private int width;
    private int height;  

    public MoveView(Context context) {
        this(context, null);
    }

    public MoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_moveview,this);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private float mx, my;
    private float TOLERANCE = 3;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mx = event.getX();
                my = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float xDistance = event.getX() - mx;
                final float yDistance = event.getY() - my;
                int l,r,t,b;
                if (Math.abs(xDistance) > TOLERANCE || Math.abs(yDistance) > TOLERANCE) {
                    l = (int) (getLeft() + xDistance);
                    r = l+width;
                    t = (int) (getTop() + yDistance);
                    b = t+height;
                    Log.d("MoveText", "onTouchEvent: x:"+event.getX()+" l:"+l);
                    this.layout(l,t,r,b);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }


}
