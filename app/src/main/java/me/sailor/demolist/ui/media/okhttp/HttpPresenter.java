package me.sailor.demolist.ui.media.okhttp;


import com.orhanobut.logger.Logger;

import me.sailor.demolist.bean.JsonRootBean;
import me.sailor.libcommon.net.okhttp.HttpCallback;
import me.sailor.libcommon.net.okhttp.HttpManager;

/**
 * @author sailor
 * @description
 * @time 2018/11/15
 */
public class HttpPresenter implements HttpContract.Presenter {
    private HttpContract.IView mIView;

    @Override
    public void loadData(String url) {
        HttpManager.getInstance().GET(url, JsonRootBean.class, new HttpCallback() {
            @Override
            public void onSuccess(Object body) {
                JsonRootBean imageBean = (JsonRootBean) body;
                mIView.notifyRV(imageBean.getResults());
            }

            @Override
            public void onFail(int responseCode) {
                Logger.d(responseCode);
            }

            @Override
            public void onError(Exception e) {
                Logger.e(e.toString());
            }
        });

    }

    @Override
    public void takeView(HttpContract.IView view) {
        this.mIView = view;
    }

    @Override
    public void dropView() {
        mIView = null;
    }
}
