package me.sailor.libcommon.net.okhttp;

import android.text.TextUtils;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

public class OkhttpClient implements HttpClient {
    private static final String TAG = "OkhttpClient";

    private OkHttpClient mClient;

    private Map<String, List<Cookie>> cookieStore = new HashMap<>();

    public OkhttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //缓存
        builder.cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);

            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        });

        //忽略
        builder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory());
        builder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        mClient = builder.build();
    }

    /**
     * response处理
     *
     * @param callback
     * @param response
     * @param clazz
     * @param <T>
     */
    private <T> void processResponse(HttpCallback callback, Response response, Class<T> clazz) {
        if (response.code() == 200) {
            try {
                String body = response.body().string();
                if (String.class == clazz) {
                    callback.onSuccess(body);
                    return;
                }
                T tClass = new Gson().fromJson(body, clazz);
                callback.onSuccess(tClass);
            } catch (JsonSyntaxException e) {
                callback.onError(e);
            } catch (Exception e) {
                e.printStackTrace();
                callback.onError(e);
            }

        } else {
            callback.onFail(response.code());
        }
    }

    /**
     * POST方法
     *
     * @param url          请求地址
     * @param params       请求参数
     * @param clazz        需要转化的实体类
     * @param httpCallback 回调函数
     * @param <T>
     */

    @Override
    public <T> void POST(String url, Map<String, Object> params, final Class<T> clazz, final HttpCallback httpCallback) {
        String content = "";
        if (params != null) {
            for (Map.Entry entry : params.entrySet()) {
                content += valueOf(entry.getKey()) + "=" + valueOf(entry.getValue()) + "&";
            }
        }
        if (!TextUtils.isEmpty(content)) {
            content = content.substring(0, content.lastIndexOf("&"));
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), content);
        Request request = new Request
                .Builder()
                .url(url)
                .post(body)
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpCallback.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                processResponse(httpCallback, response, clazz);
            }
        });
    }


    /**
     * GET方法
     *
     * @param url          请求地址
     * @param clazz        需要转化的实体类
     * @param httpCallback 回调函数
     * @param <T>
     */

    @Override
    public <T> void GET(String url, final Class<T> clazz, final HttpCallback httpCallback) {
        //建立okhttp request
        Request request = new Request.Builder().url(url).build();
        //将request加入call
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpCallback.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                processResponse(httpCallback, response, clazz);
            }
        });
    }

    @Override
    public void DOWN(String url, final String filename, final HttpDownCallback httpDownCallback) {
        //建立okhttp request
        Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpDownCallback.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream inputStream = null;
                byte[] buffer = new byte[2048];
                int len;

                try {
                    File file = new File(filename);
                    FileOutputStream outputStream = new FileOutputStream(file);
                    inputStream = response.body().byteStream();
                    long total = response.body().contentLength();
                    long sum = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        httpDownCallback.onProgress(progress);
                    }
                    outputStream.flush();
                    httpDownCallback.onSuccess(file);
                } catch (IOException e) {
                    e.printStackTrace();
                    httpDownCallback.onError(e);
                } finally {
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }
        });
    }
}
