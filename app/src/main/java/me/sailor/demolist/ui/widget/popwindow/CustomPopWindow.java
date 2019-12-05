package me.sailor.demolist.ui.widget.popwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.PopupWindow;

/**
 * @author Administrator on2019/2/20 14:56
 * @desc
 */
public class CustomPopWindow extends PopupWindow  {

    @Override
    public int getWidth() {
            return super.getWidth();
    }

    public CustomPopWindow(Context context) {

    }

    public CustomPopWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomPopWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomPopWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomPopWindow() {
    }
}
