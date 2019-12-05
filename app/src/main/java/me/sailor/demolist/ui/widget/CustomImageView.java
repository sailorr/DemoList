package me.sailor.demolist.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class CustomImageView extends ImageView {
    public CustomImageView(Context context) {
        this(context,null);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = Bitmap.createBitmap(1000,1000, Bitmap.Config.ARGB_8888);
        Canvas canvas1 = new Canvas(bitmap);
        Paint paint  = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(30);
        paint.setStyle(Paint.Style.STROKE);
        canvas1.drawText("写了个字",300,300,paint);
        canvas.drawBitmap(bitmap,0,0,null);
    }
}
