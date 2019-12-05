package me.sailor.demolist.ui.system_about.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * @author Administrator on2019/3/5 10:51
 * @desc
 */
@Dao
public interface BookDao {
    @Insert
    long insertBook(Book book);

    @Query("select * from book where id==:id")
    long queryBook(int id);


}
