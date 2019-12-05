package me.sailor.libcommon.net.okhttp;

import java.io.File;

public interface HttpDownCallback {
    void onProgress(int progress);

    void onSuccess(File file);

    void onFailure(int responseCode);

    void onError(Exception e);
}
