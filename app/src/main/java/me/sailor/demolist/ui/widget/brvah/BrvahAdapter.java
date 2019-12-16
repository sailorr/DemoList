package me.sailor.demolist.ui.widget.brvah;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.sailor.demolist.R;
import me.sailor.demolist.bean.Result;

/**
 * @author Administrator on2019/3/19 11:11
 * @desc
 */
public class BrvahAdapter extends BaseItemDraggableAdapter<Result, BaseViewHolder> {
    public BrvahAdapter(@Nullable List<Result> data) {
        super(R.layout.item_retofit_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Result item) {
        final ImageView imageView = helper.getView(R.id.item_retrofit_img);
        Glide.with(mContext).asBitmap()
                .load(item.getUrl())
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(new SimpleTarget<Bitmap>() {
                    //加载完得到bitmap 先预处理，设置高度
                    @Override
                    public void onResourceReady(@NonNull final Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Log.d("BrvahAdapter", "onResourceReady:height--->"+resource.getHeight());
                        imageView.getLayoutParams().height = resource.getHeight();
                        imageView.setImageBitmap(resource);
//                        imageView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent = new Intent(mContext, PrePhotoActivity.class);
//                                intent.putExtra("url", item.getUrl());
//                                mContext.startActivity(intent);
//                            }
//                        });
                    }
                });
    }
}
