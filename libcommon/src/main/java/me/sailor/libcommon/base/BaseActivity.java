package me.sailor.libcommon.base;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import me.sailor.libcommon.widget.LoadingDialog;


public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private final String TAG = getClass().getSimpleName();

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Long uiThreadId = Thread.currentThread().getId();
    private LoadingDialog mLoadingDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        initBaseView();
        setContentView(setLayoutId());
        init();
    }

    protected void initBaseView() {
        mLoadingDialog = new LoadingDialog.Builder(this)
                .setMessage("加载中...")
                .setLoadingCallBack(new LoadingDialog.LoadingCallback() {
                    @Override
                    public void onTimeOut() {
                        showToast("网络请求超时！");
                    }
                })
                .showTime(10000)
                .build();
    }

    protected abstract int setLayoutId();

    protected abstract void init();


    @Override
    public void showLoadingDialog() {
        if (mLoadingDialog != null) {
            if (Thread.currentThread().getId() == uiThreadId) {
                if (mLoadingDialog.isShowing()) {
                    return;
                }
                mLoadingDialog.show();
                return;
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (mLoadingDialog.isShowing()) {
                        return;
                    }
                    mLoadingDialog.show();
                }
            });

        }
        return;
    }

    @Override
    public void hideLoadingDialog() {
        if (Thread.currentThread().getId() == uiThreadId) {
            mLoadingDialog.dismiss();
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mLoadingDialog.dismiss();
            }
        });

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void LOG(Object msg) {
        Log.d(TAG, msg.toString());
    }
}
