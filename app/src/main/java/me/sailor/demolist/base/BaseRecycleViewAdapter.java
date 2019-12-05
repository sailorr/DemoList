package me.sailor.demolist.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * @author sailor
 * @description
 * @time 2018/11/21
 */
public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> mList;
    private int layoutId;
    private LayoutInflater mInflater;
    protected ItemClickListener mItemClickListener;
    protected ItemLongClickListener mLongClickListener;

    public BaseRecycleViewAdapter(List<T> list, int layoutId) {
        this.mList = list;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mInflater = LayoutInflater.from(viewGroup.getContext());
        BaseViewHolder viewHolder = new BaseViewHolder(mInflater.inflate(layoutId, viewGroup, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        bindData(baseViewHolder, mList.get(i), i);
    }

    protected abstract void bindData(BaseViewHolder holder, T data, int positon);

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    public interface ItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public interface ItemLongClickListener {
        void itemLongClick(View view, int position);
    }

    public void setItemClickListener(ItemClickListener clickListener) {
        this.mItemClickListener = clickListener;
    }

    public void setItemLongClickLisenter(ItemLongClickListener longClickLisenter) {
        this.mLongClickListener = longClickLisenter;
    }



}
