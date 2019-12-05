package me.sailor.demolist.ui.system_about.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * @author Administrator on2019/2/28 15:12
 * @desc
 */

//指定数据库表面，版本号
@Database(entities = {User.class, Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao mUserDao();

    public abstract BookDao mBookDao();
}
