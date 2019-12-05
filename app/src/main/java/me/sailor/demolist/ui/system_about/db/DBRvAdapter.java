package me.sailor.demolist.ui.system_about.db;

import android.util.Log;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.sailor.demolist.R;

/**
 * @author Administrator on2019/5/29 18:08
 * @desc
 */
public class DBRvAdapter extends BaseQuickAdapter<User, BaseViewHolder> {

    public DBRvAdapter(List<User> data) {
        super(R.layout.item_db, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, User item) {
        Log.d("DBRvAdapter", "convert: "+item.getName());
        TextView viewId = helper.itemView.findViewById(R.id.tv_id);
        TextView viewName = helper.itemView.findViewById(R.id.tv_name);
        TextView viewAge = helper.itemView.findViewById(R.id.tv_age);
        viewId.setText(item.getId()+"");
        viewName.setText(item.getName());
        viewAge.setText(item.getAge()+"");
    }
}
