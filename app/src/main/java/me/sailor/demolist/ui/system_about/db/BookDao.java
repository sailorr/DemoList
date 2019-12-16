package me.sailor.demolist.ui.system_about.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

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
