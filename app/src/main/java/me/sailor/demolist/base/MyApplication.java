package me.sailor.demolist.base;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import me.sailor.demolist.contant.ContantApi;
import me.sailor.libcommon.net.retrofit.RetrofitManager;

/**
 * @author sailor
 * @description
 * @time 2018/11/15
 */
public class MyApplication extends Application implements Thread.UncaughtExceptionHandler {
    private static Context INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = getApplicationContext();
        configLogger();
        RetrofitManager.getInstance().setBaseUrl(ContantApi.BASE_URL);
//
//        PreviewListenerManager.getInstance().glideListener = new GlideListener() {
//            @Override
//            public void loadLocalImage(ImageView imageView, String path) {
//                Glide.with(imageView).load(path).into(imageView);
//            }
//        };
    }

    public static Context getContext() {
        return INSTANCE;
    }

    public void configLogger() {
//        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
//                .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
//                .methodCount(2)         // (Optional) How many method line to show. Default 2
//                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
////                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
//                .tag("MyTAG")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
//                .build();
//        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        Logger.addLogAdapter(new AndroidLogAdapter());
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {

    }
}
