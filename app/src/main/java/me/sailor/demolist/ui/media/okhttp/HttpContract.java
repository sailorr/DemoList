package me.sailor.demolist.ui.media.okhttp;

import java.util.List;

import me.sailor.libcommon.base.IPresenter;
import me.sailor.demolist.bean.Result;

/**
 * @author sailor
 * @description
 * @time 2018/11/15
 */
public class HttpContract {
    interface IView {
        void notifyRV(List<Result> results);
    }

    interface Presenter extends IPresenter<IView> {
        void loadData(String url);
    }
}
