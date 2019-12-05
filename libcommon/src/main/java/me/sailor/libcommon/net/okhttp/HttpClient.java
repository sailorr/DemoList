package me.sailor.libcommon.net.okhttp;

import java.util.Map;

public interface HttpClient {

    <T> void POST(String url, Map<String, Object> params, final Class<T> clazz, HttpCallback httpCallback);

    <T> void GET(String url, final Class<T> clazz, HttpCallback httpCallback);

    void DOWN(String url,String fileName, HttpDownCallback httpDownCallback );
}
