package me.sailor.demolist.ui.widget.moveable

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import me.sailor.libcommon.utils.DensityUtils.dip2px

/**
 *  -description:
 *  -author: created by tang on 2019/11/22 14:09
 */
class CirlceLineView : View {
    private var mPaint: Paint? = null

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = dip2px(context, 18f).toFloat()
        val height = dip2px(context, 10f).toFloat()

        Log.d("CirlceLineView", "onDraw: $width $height")
        val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.strokeWidth = dip2px(context, 1f).toFloat()
        mPaint.isDither = true
        mPaint.style = Paint.Style.FILL
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.strokeCap = Paint.Cap.ROUND
        val five = dip2px(context, 5f).toFloat()
        val two = dip2px(context, 2f).toFloat()
        //画黑边圆
        mPaint.setARGB(80,0,0,0)
        canvas.drawCircle(five, five, five, mPaint)

        //画实心白圆
        mPaint.color = Color.WHITE
        canvas.drawCircle(five, five, two, mPaint)

        //画线
        mPaint.style = Paint.Style.STROKE
        val path = Path()
        path.moveTo(five,five)
        path.lineTo(dip2px(context,20f).toFloat(),five)
        canvas.drawPath(path, mPaint)


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d("CirlceLineView", "onMeasure: width:$measuredWidth height:$measuredHeight")
    }
}