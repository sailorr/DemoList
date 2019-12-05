package me.sailor.demolist.ui.widget.rocker;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import me.sailor.demolist.R;
import me.sailor.libcommon.utils.HintUtils;

/**
 * @author sailor
 * @description
 * @time 2018/12/18
 */
public class RockerControlView extends LinearLayout implements AddandSubtractView.ControlListener, View.OnClickListener {
    private RockerView mRockerView;
    private static final String TAG = "RockerControlView";
    private AddandSubtractView mView;
    private AddandSubtractView mView1;

    public RockerControlView(Context context) {
        this(context, null);
    }

    public RockerControlView(Context context, @Nullable @android.support.annotation.Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        initRocker();
    }

    private RockerView.OnShakeListener mRockerListener;

    private void initRocker() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        layoutParams.weight = 4;

        mRockerView = new RockerView(getContext());
        mRockerView.setCallBackMode(RockerView.CallBackMode.CALL_BACK_MODE_STATE_CHANGE);
        initRockerListener();
        mRockerView.setOnShakeListener(RockerView.DirectionMode.DIRECTION_8, mRockerListener);
        mRockerView.setAlpha(0.8f);
        addView(mRockerView, layoutParams);

        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams1.gravity = Gravity.CENTER;
        layoutParams1.weight = 2;

        mView = new AddandSubtractView(getContext());
        mView.setLayoutParams(layoutParams1);
        mView.setControlListener(this);
        mView.setTitle("聚焦");
        addView(mView);

        mView1 = new AddandSubtractView(getContext());
        mView1.setLayoutParams(layoutParams1);
        mView1.setControlListener(this);
        mView1.setTitle("变倍");
        addView(mView1);


        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(0, 20, 5, 0);
        ImageView imageView = new ImageView(getContext());
        imageView.setOnClickListener(this);
        imageView.setImageResource(R.drawable.ic_add);
        imageView.setLayoutParams(layoutParams2);
        addView(imageView);
    }

    private void initRockerListener() {
        mRockerListener = new RockerView.OnShakeListener() {
            @Override
            public void onStart() {
                Log.d(TAG, "onStart: ");
            }

            @Override
            public void direction(RockerView.Direction direction) {
                Log.d(TAG, "direction: " + direction);
                switch (direction) {
                    case DIRECTION_UP:
                        break;
                    case DIRECTION_DOWN:
                        break;
                    case DIRECTION_LEFT:
                        break;
                    case DIRECTION_RIGHT:
                        break;
                    case DIRECTION_UP_LEFT:
                        break;
                    case DIRECTION_UP_RIGHT:
                        break;
                    case DIRECTION_DOWN_LEFT:
                        break;
                    case DIRECTION_DOWN_RIGHT:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFinish() {
                Log.d(TAG, "finish: ");
            }
        };
    }


    @Override
    public void addClick(AddandSubtractView view) {
        if (view == mView) {
            HintUtils.showToast(getContext(), "聚焦+");
        } else if (view == mView1) {
            HintUtils.showToast(getContext(), "变倍+");
        }
    }

    @Override
    public void subtractClick(AddandSubtractView view) {
        if (view == mView) {
            HintUtils.showToast(getContext(), "聚焦-");
        } else if (view == mView1) {
            HintUtils.showToast(getContext(), "变倍-");
        }
    }

    @Override
    public void onClick(View v) {
        this.setVisibility(GONE);
    }
}
