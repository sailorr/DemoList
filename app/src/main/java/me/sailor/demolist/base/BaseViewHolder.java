package me.sailor.demolist.base;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @description
 * @time 2018/11/15
 * @user Administrator
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private View mView;
    private int viewType;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mView = itemView;
        this.views = new SparseArray<>();
        this.viewType = viewType;
    }

    public <T extends View> T getView(int viewId) {
        //通过getView()去获取控件,如果在SparseArray中存在则直接获取,如果不存在则findViewById()然后再插入SparseArray中
        View view = views.get(viewId);
        if (view == null) {
                view = mView.findViewById(viewId);
                views.put(viewId, view);

        }
        return (T) view;
    }

    public View getRootView() {
        return mView;
    }

}
