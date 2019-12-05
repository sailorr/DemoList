package me.sailor.demolist.ui.main;

import android.view.View;
import android.widget.Button;

import java.util.List;

import me.sailor.demolist.R;
import me.sailor.demolist.base.BaseRecycleViewAdapter;
import me.sailor.demolist.base.BaseViewHolder;
import me.sailor.demolist.bean.ItemModel;

/**
 * @description
 * @time 2018/11/14
 * @user Administrator
 */
public class MainRVAdapter extends BaseRecycleViewAdapter<ItemModel>{

    public MainRVAdapter( List<ItemModel> datas, int layoutId) {
        super(datas, layoutId);
    }

    @Override
    protected void bindData(final BaseViewHolder viewHolder, ItemModel data, final int position) {
        final Button button = viewHolder.getView(R.id.item_btn);

        button.setText(data.getTitle());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener!=null){
                    mItemClickListener.onItemClickListener(button,position);
                }
            }
        });
    }
}














