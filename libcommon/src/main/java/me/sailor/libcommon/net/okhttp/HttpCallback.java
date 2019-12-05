package me.sailor.libcommon.net.okhttp;

public interface HttpCallback<T> {
    void onSuccess(T body);

    void onFail(int responseCode);

    void onError(Exception e);
}
