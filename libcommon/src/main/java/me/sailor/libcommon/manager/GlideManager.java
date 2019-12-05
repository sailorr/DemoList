package me.sailor.libcommon.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import me.sailor.libcommon.R;

public class GlideManager {
    private RequestOptions requestOptions;

    public GlideManager() {
        requestOptions = getRequestOptions();
    }

    public static GlideManager getInstance(){
        return  GlideManagerHolder.INSTANCE;
    }

    static class GlideManagerHolder{
        private static final GlideManager INSTANCE = new GlideManager();
    }

    public void load(Context context,String path, ImageView imageView){
        Glide.with(context)
                .load(path)
                .apply(requestOptions)
                .into(imageView);
    }

    @SuppressLint("CheckResult")
    private RequestOptions getRequestOptions(){
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
        options.placeholder(R.drawable.anim_loading);
        return options;
    }
}
