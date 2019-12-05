package me.sailor.libcommon.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;


/**
 * @author sailor
 * @description progressdialog 封装
 * @time 2018/11/15
 */
public class LoadingDialog {
    private ProgressDialog mDialog;
    private Handler mHandler = new Handler();

    private LoadingDialog(Context context) {
        mDialog = new ProgressDialog(context);
    }

    public void setMessage(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            mDialog.setMessage(msg);
        }
    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    private long showtime;

    public void showtime(long showtime) {
        this.showtime = showtime;
    }

    public boolean isShowing() {
        return mDialog.isShowing();
    }

    public void show() {
        mDialog.show();
        if (showtime > 0) {
            //延迟几秒，取消dialog，回调接口
            mHandler.postDelayed(cancelRunnable, showtime);
        }
    }

    private Runnable cancelRunnable = new Runnable() {
        @Override
        public void run() {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
                if (loadingCallback != null) {
                    loadingCallback.onTimeOut();
                }
            }
        }
    };

    public interface LoadingCallback {
        void onTimeOut();
    }

    private LoadingCallback loadingCallback;

    public void setLoadingCallback(LoadingCallback loadingCallback) {
        this.loadingCallback = loadingCallback;
    }


    /**
     * 构造者模式
     */
    public static class Builder {
        private Context mContext;

        private LoadingDialog mLoadingDialog;

        public Builder(Context context) {
            this.mContext = context;
            this.mLoadingDialog = new LoadingDialog(mContext);
        }


        public Builder setMessage(String msg) {
            mLoadingDialog.setMessage(msg);
            return this;
        }

        public Builder showTime(long time) {
            if (time < 0) {
                return this;
            }
            mLoadingDialog.showtime(time);
            return this;
        }

        public Builder setLoadingCallBack(LoadingCallback callBack) {
            mLoadingDialog.setLoadingCallback(callBack);
            return this;
        }

        public LoadingDialog build() {
            return mLoadingDialog;
        }

        public void show() {
            mLoadingDialog.show();
            return;
        }

    }

}
