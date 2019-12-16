package me.sailor.demolist.ui.widget.doublerecycle;

import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.sailor.demolist.R;
import me.sailor.demolist.base.BaseRecycleViewAdapter;
import me.sailor.demolist.base.BaseViewHolder;

/**
 * @author sailor
 * @description
 * @time 2018/11/28
 */
public class DoubleAdapter extends BaseRecycleViewAdapter<List<String>> {
    private Context mContext;

    public DoubleAdapter(Context context, List<List<String>> datas) {
        super(datas, R.layout.item_doublerecycle);
        this.mContext = context;
    }

    @Override
    public void bindData(BaseViewHolder viewHolder, List<String> data, int position) {
        TextView title = viewHolder.getView(R.id.doublerecycle_time);
        title.setText("用户"+position);

        RecyclerView recyclerView = viewHolder.getView(R.id.doublerecycle2);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        Adapter2 adapter2 = new Adapter2(data);
        recyclerView.setAdapter(adapter2);

    }
}
