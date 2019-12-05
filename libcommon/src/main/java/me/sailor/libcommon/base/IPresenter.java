package me.sailor.libcommon.base;

/**
 * @author sailor
 * @description
 * @time 2018/11/15
 */
public interface IPresenter<V> {

    /**
     * 绑定view
     * @param view
     */
    void takeView(V view);

    /**
     * 解绑view
     */
    void dropView();
}
