package me.sailor.demolist.ui.widget.doublerecycle;

import android.widget.TextView;

import java.util.List;

import me.sailor.demolist.R;
import me.sailor.demolist.base.BaseRecycleViewAdapter;
import me.sailor.demolist.base.BaseViewHolder;

/**
 * @author sailor
 * @description
 * @time 2018/11/28
 */
public class Adapter2 extends BaseRecycleViewAdapter<String> {
    public Adapter2(List<String> datas) {
        super(datas, R.layout.item_recycle2);
    }

    @Override
    protected void bindData(BaseViewHolder viewHolder, String data, int position) {
        TextView textView = viewHolder.getView(R.id.rv2_text);
        textView.setText(data);
    }
}
