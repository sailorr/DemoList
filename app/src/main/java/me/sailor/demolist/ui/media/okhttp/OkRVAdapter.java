package me.sailor.demolist.ui.media.okhttp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import me.sailor.demolist.R;
import me.sailor.demolist.base.BaseRecycleViewAdapter;
import me.sailor.demolist.base.BaseViewHolder;
import me.sailor.demolist.bean.Result;
import me.sailor.demolist.ui.widget.preview.PrePhotoActivity;

/**
 * @author sailor
 * @description
 * @time 2018/11/15
 */
public class OkRVAdapter extends BaseRecycleViewAdapter<Result> {
    private Context mContext;
    private int size;
    private List<Integer> heightArray = new ArrayList();

    public OkRVAdapter(Context context, List<Result> datas, int layoutId) {
        super(datas,layoutId);
        this.mContext = context;
        this.size = datas.size();
    }

    @Override
    protected void bindData(final BaseViewHolder viewHolder, final Result data, final int position) {
        final ImageView imageView = viewHolder.getView(R.id.item_http_img);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext,PrePhotoActivity.class);
//                intent.putExtra("url",data.getUrl());
//                mContext.startActivity(intent);
//            }
//        });
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(mContext).asBitmap()
                .load(data.getUrl())
                .thumbnail(0.5f)
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.anim_loading))
                .into(new SimpleTarget<Bitmap>() {
                    //加载完得到bitmap 先预处理，设置高度
                    @Override
                    public void onResourceReady(@NonNull final Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        imageView.getLayoutParams().height = resource.getHeight();
                        imageView.setImageBitmap(resource);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext,PrePhotoActivity.class);
                                intent.putExtra("url",data.getUrl());
                                mContext.startActivity(intent);
                            }
                        });


                    }
                });


    }


}
