package me.sailor.demolist.ui.system_about.db;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import me.sailor.libcommon.manager.executor.ExecutorManager;

/**
 * @author Administrator on2019/2/28 15:15
 * @desc
 */
public class DBManager {

    private AppDatabase mDatabase;


    public static DBManager getInstance() {
        return DBManagerHolder.instance;
    }

    static class DBManagerHolder {
        public final static DBManager instance = new DBManager();
    }

    //初始化
    public void init(final Context context, final String name) {
        ExecutorManager.getInstance().singleIO().execute(new Runnable() {
            @Override
            public void run() {
                mDatabase = Room.databaseBuilder(context, AppDatabase.class, name + ".db").build();
            }
        });
    }


    public void addUser(User user) {
        mDatabase.mUserDao().insert(user);
    }

    public void delUser(int id) {
        mDatabase.mUserDao().delete(id);
    }

    public void update(User user) {
        mDatabase.mUserDao().update(user);
    }

    public List<User> queryUser() {
        return mDatabase.mUserDao().getAllUsers();
    }

}
