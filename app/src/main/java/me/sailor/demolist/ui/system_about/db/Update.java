package me.sailor.demolist.ui.system_about.db;

import android.view.View;

/**
 * @author Administrator on2019/5/30 9:24
 * @desc
 */
public class Update {

    public interface updateListener {
        void onclick();

        void update();
    }


    public void setUpdateListener(updateListener updateListener){
        this.mUpdateListener = updateListener;
    }
    updateListener mUpdateListener;

    public Update(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdateListener.onclick();
            }
        });
        mUpdateListener.update();
    }
}
