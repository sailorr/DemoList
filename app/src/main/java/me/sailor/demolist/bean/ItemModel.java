package me.sailor.demolist.bean;

/**
 * @description
 * @time 2018/11/14
 * @user Administrator
 */
public class ItemModel {
    private String title;
    private Class mActivity;

    public ItemModel(String title, Class activity) {
        this.title = title;
        mActivity = activity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getActivity() {
        return mActivity;
    }

    public void setActivity(Class activity) {
        mActivity = activity;
    }
}
