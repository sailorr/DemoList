package me.sailor.demolist.ui.main;

import java.util.List;

import me.sailor.libcommon.base.IPresenter;
import me.sailor.demolist.bean.ItemModel;

/**
 * @author sailor
 * @description
 * @time 2018/11/15
 */
public class MainContract {

    interface IView {
        void notify(List<ItemModel> list);
    }

    interface Presenter extends IPresenter<IView> {
        void loadData();
    }

}
