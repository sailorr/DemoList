package me.sailor.demolist.ui.system_about;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;

public class CanvasActivity extends Activity {
    private int SCREEN_W;
    private int SCREEN_H;
    private int Pen = 1;
    private int Eraser = 2;
    private int Circle = 3;
    private int SQUARE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.YELLOW);

        LinearLayout btnLayout = new LinearLayout(this);
        btnLayout.setOrientation(LinearLayout.HORIZONTAL);
        btnLayout.setBackgroundColor(Color.GREEN);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);

        Button paint = new Button(this);
        paint.setText("画笔");
        btnLayout.addView(paint, params);
        Button eraser = new Button(this);
        eraser.setText("橡皮");
        btnLayout.addView(eraser, params);
        Button circle = new Button(this);
        circle.setText("圆形");
        btnLayout.addView(circle, params);
        Button Square = new Button(this);
        Square.setText("矩形");
        btnLayout.addView(Square, params);

        layout.addView(btnLayout);

        final MyView myView = new MyView(this);
        layout.addView(myView);
        setContentView(layout);

        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.setMode(Pen);
            }
        });

        eraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.setMode(Eraser);
            }
        });
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.setMode(Circle);
            }
        });
        Square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.setMode(SQUARE);
            }
        });
    }

    //MyView就是自定义的画板
    class MyView extends View {
        private int mMode = 1;
        private Bitmap mBitmap;
        private Canvas mCanvas;
        private Paint mEraserPaint;
        private Paint mPaint;
        private Paint mPointPaint;
        private Path mPath;
        private float mX, mY;
        private float cXStart, cYStart, cXEnd, cYEnd;
        private static final float TOUCH_TOLERANCE = 1;

        public MyView(Context context) {
            super(context);
            setFocusable(true);
            setScreenWH();
            initPaint();
        }

        private void setScreenWH() {
            DisplayMetrics dm = new DisplayMetrics();
            dm = this.getResources().getDisplayMetrics();
            int screenWidth = dm.widthPixels;
            int screenHeight = dm.heightPixels;
            SCREEN_W = screenWidth;
            SCREEN_H = screenHeight;
        }

        //设置绘制模式是“画笔”还是“橡皮擦”
        public void setMode(int mode) {
            this.mMode = mode;
        }

        private void initPaint() {
            //画笔
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(2);
            mPaint.setTextSize(30);

            //画笔
            mPointPaint = new Paint();
            mPointPaint.setAntiAlias(true);
            mPointPaint.setStyle(Paint.Style.STROKE);
            mPointPaint.setStrokeCap(Paint.Cap.ROUND);
            mPointPaint.setStrokeJoin(Paint.Join.ROUND);
            mPointPaint.setColor(Color.GREEN);
            mPointPaint.setStrokeWidth(40);

            //橡皮擦
            mEraserPaint = new Paint();
            mEraserPaint.setAlpha(0);
            //这个属性是设置paint为橡皮擦重中之重
            //这是重点
            //下面这句代码是橡皮擦设置的重点
            mEraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            //上面这句代码是橡皮擦设置的重点（重要的事是不是一定要说三遍）
            mEraserPaint.setAntiAlias(true);
            mEraserPaint.setDither(true);
            mEraserPaint.setStyle(Paint.Style.STROKE);
            mEraserPaint.setStrokeJoin(Paint.Join.ROUND);
            mPointPaint.setStrokeCap(Paint.Cap.ROUND);
            mEraserPaint.setStrokeWidth(60);


            mPath = new Path();
            mBitmap = Bitmap.createBitmap(SCREEN_W, SCREEN_H, Bitmap.Config.ARGB_8888);
            Log.d("MyView", "initPaint: " + SCREEN_W + " " + SCREEN_H);
            //这个canvas 是新new的一个空白canvas
            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(Color.GREEN);
//            mCanvas.drawBitmap(mBitmap, 500, 1000, mPaint);
            mCanvas.drawText("画在涂鸦画板上，可擦除", 300, 1500, mPaint);
            drawBezier();
            drawCircle();
        }


        @Override
        protected void onDraw(Canvas canvas) {
            if (mBitmap != null) {
                //这个canvas 是指背景的canvas
                canvas.drawBitmap(mBitmap, 0, 0, mPaint);
                canvas.drawText("这是画在的背景上，不可擦除", 300, 300, mPaint);
            }
        }

        private void touch_start(float x, float y) {
//            Log.d("MyView", "touch_start: x-->" + x + "  y-->" + y);


            mPaint.setStyle(Paint.Style.STROKE);
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
            cXStart = x;
            cYStart = y;

        }


        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
//                Log.d("MyView", "touch_move: x-->" + x + "  y-->" + y + " mx-->" + mX + " my-->" + mY);
//                Log.d("MyView", "touch_move: mx-->" + mX + " my-->" + mY + " (x + mX) / 2-->" + (x + mX) / 2 + "  (y + mY) / 2-->" + (y + mY) / 2);
                mX = x;
                mY = y;
                if (mMode == Pen) {
                    mCanvas.drawPath(mPath, mPaint);
                }
                if (mMode == Eraser) {
                    mCanvas.drawPath(mPath, mEraserPaint);
                }
                if (mMode == Circle) {
                    mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                    for (int i = 0; i < mList.size(); i++) {
                        mCanvas.drawCircle(mList.get(i)[0], mList.get(i)[1], mList.get(i)[2], mPaint);
                    }
                    float x0 = x - cXStart;
                    float y0 = y - cYStart;
                    xCenter = x0 / 2 + cXStart;
                    yCenter = y0 / 2 + cYStart;
                    r = (float) Math.sqrt(x0 * x0 + y0 * y0) / 2;
                    Log.d("MyView", "touch_move: cx:" + cXStart + " cy:" + cYStart + " x:" + x + " y:" + y + " r:" + r);
                    mCanvas.drawCircle(xCenter, yCenter, r, mPaint);
                }
                if (mMode == SQUARE) {
                    mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                    mCanvas.drawRect(cXStart, cYStart, x, y, mPaint);
                }
            }
        }

        private float xCenter, yCenter;
        private float w, h;
        private float r;
        private ArrayList<float[]> mList = new ArrayList<>();

        private void touch_up() {
            mPath.lineTo(mX, mY);
            if (mMode == Pen) {
                mCanvas.drawPath(mPath, mPaint);
            }
            if (mMode == Eraser) {
                mCanvas.drawPath(mPath, mEraserPaint);
            }
            if (mMode == Circle) {
                float[] floats = new float[]{xCenter, yCenter, r};
                mList.add(floats);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    invalidate();
                    break;
            }
            return true;
        }

        //模拟手指滑动画贝塞尔曲线，实际上没有画到手指抬起的那个位置
        private void drawBezier() {
            mCanvas.drawPoint(300, 800, mPointPaint);
            mCanvas.drawPoint(550, 400, mPointPaint);
            mCanvas.drawPoint(800, 800, mPointPaint);
            mCanvas.drawPoint(500, 900, mPointPaint);
            mCanvas.drawText("300,800 起点", 320, 800, mPaint);
            mCanvas.drawText("550,200 控制点1", 570, 400, mPaint);
            mCanvas.drawText("800,800 终点", 820, 800, mPaint);
            mCanvas.drawText("550,900 控制点12", 570, 900, mPaint);

            mPath.reset();
            mPath.moveTo(300, 800);
            mPath.quadTo(550, 200, 800, 800);
            mCanvas.drawPath(mPath, mPaint);

            mPath.reset();
            mPath.moveTo(300, 800);
            mPath.quadTo(550, 900, 800, 800);
            mCanvas.drawPath(mPath, mPaint);


            mPath.reset();
            mPath.quadTo(0, 0, 50, 25);
            mPath.quadTo(100, 50, 150, 75);
            mPath.quadTo(200, 100, 250, 150);
            mPath.quadTo(300, 200, 325, 250);
            mPath.quadTo(350, 300, 325, 350);
            mPath.quadTo(300, 400, 250, 450);
            mCanvas.drawPath(mPath, mPaint);
        }


        //测试 canvas save 与 restore 作用
        private void drawCircle() {
            mPaint.setStyle(Paint.Style.FILL);
            mCanvas.drawCircle(40, 40, 40, mPaint);

            mCanvas.save();//保存当前画布状态

            mPaint.setColor(Color.BLUE);
            //画布右移mWidth/2,此时画布的原点位置由原来的（0,0）移动至（mWidth/2,0）
            mCanvas.translate(SCREEN_W / 2, 0);
            mCanvas.drawCircle(0, 40, 40, mPaint);

            mCanvas.restore();

            mPaint.setColor(Color.GREEN);
            mCanvas.drawCircle(40, SCREEN_H / 2, 40, mPaint);
            mPaint.setColor(Color.RED);
            mCanvas.drawCircle(SCREEN_W / 2, SCREEN_H / 2, 40, mPaint);
        }
    }

}
