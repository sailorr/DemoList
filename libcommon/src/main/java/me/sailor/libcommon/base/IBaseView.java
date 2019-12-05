package me.sailor.libcommon.base;

/**
 * @author sailor
 * @description
 * @time 2018/11/15
 */
public interface IBaseView {

    void showToast(String msg);

    void showLoadingDialog();

    void hideLoadingDialog();

    void LOG(Object msg);
}
