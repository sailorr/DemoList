package me.sailor.demolist.ui.widget.brvah;

import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import me.sailor.demolist.R;
import me.sailor.demolist.bean.Book;

/**
 * @author Administrator on2019/4/22 16:37
 * @desc
 */
public class FetchAdapter extends BaseQuickAdapter<Book, BaseViewHolder> {

    public FetchAdapter() {
        super(R.layout.item_fetch);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, Book item) {

        TextView name = helper.getView(R.id.tv_name);
        name.setText(item.name);

        TextView tvid = helper.getView(R.id.tv_id);
        tvid.setText(String.valueOf(item.id));
    }

}
