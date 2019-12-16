package me.sailor.demolist.ui.widget.rocker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import me.sailor.demolist.R;

/**
 * @author sailor
 * @description
 * @time 2018/12/18
 */
public class AddandSubtractView extends ConstraintLayout implements View.OnClickListener {
    private TextView titleTv;
    private ImageView add, subtract;


    public AddandSubtractView(Context context) {
        this(context, null);
    }

    public AddandSubtractView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddandSubtractView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.item_addandsubtrat, this);
        titleTv = findViewById(R.id.title);
        add = findViewById(R.id.add);
        add.setOnClickListener(this);
        subtract = findViewById(R.id.subtract);
        subtract.setOnClickListener(this);
    }

    public void setTitle(String title) {
        titleTv.setText(title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                mListener.addClick(this);
                break;
            case R.id.subtract:
                mListener.subtractClick(this);
                break;
            default:

        }
    }

    private ControlListener mListener;

    public void setControlListener(ControlListener controlListener) {
        this.mListener = controlListener;
    }

    public interface ControlListener {
        void addClick(AddandSubtractView view);

        void subtractClick(AddandSubtractView view);
    }
}
