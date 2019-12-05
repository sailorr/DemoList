package me.sailor.libcommon.net.retrofit;

import android.util.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Administrator on2019/2/13 10:03
 * @desc
 */
public class RetrofitManager {
    private Retrofit mRetrofit;

    public static RetrofitManager getInstance() {
        return RetrofitManagerHolder.INSTANCE;
    }

    private static class RetrofitManagerHolder {
        private final static RetrofitManager INSTANCE = new RetrofitManager();
    }

    public void setBaseUrl(String baseUrl) {
        mRetrofit = getRetrofit(baseUrl);
    }


    private OkHttpClient mHttpClient;
    //设置缓存目录
    private final File cacheDirectory = new File("", "httpCache");
    private Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);

    private Retrofit getRetrofit(String baseUrl) {
        if (null == mHttpClient) {
            mHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Log.i("Retrofit", message);
                        }
                    }).setLevel(HttpLoggingInterceptor.Level.BODY))
                    .cache(cache)
                    .build();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mHttpClient)
                .build();
        return retrofit;
    }

    public <T> T getServer(Class<T> server) {
        return mRetrofit.create(server);
    }


}
