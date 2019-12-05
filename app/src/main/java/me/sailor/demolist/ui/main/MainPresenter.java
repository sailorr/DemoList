package me.sailor.demolist.ui.main;

import java.util.ArrayList;

import me.sailor.demolist.bean.ItemModel;
import me.sailor.demolist.ui.media.MediaActivity;
import me.sailor.demolist.ui.system_about.SystemAboutActivity;
import me.sailor.demolist.ui.widget.WidgetActivity;

/**
 * @author sailor
 * @description
 * @time 2018/11/15
 */
public class MainPresenter implements MainContract.Presenter {
    private MainContract.IView mIView;
    private ArrayList<ItemModel> mModels;

    @Override
    public void loadData() {
        mModels = new ArrayList<>();
        mModels.add(new ItemModel("System_About", SystemAboutActivity.class));
        mModels.add(new ItemModel("Media", MediaActivity.class));
        mModels.add(new ItemModel("WIDGET", WidgetActivity.class));

    }

    @Override
    public void takeView(MainContract.IView view) {
        this.mIView = view;
        mIView.notify(mModels);
    }

    @Override
    public void dropView() {
        mIView = null;
    }

}
