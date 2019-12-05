package me.sailor.demolist.ui.system_about.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * @author Administrator on2019/2/28 15:00
 * @desc
 */

@Dao
public interface UserDao {
//    查询
    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE id=:id ")
    User getUser(int id);

    //插入
    @Insert
    void insert(User user);

//    @Insert
//    void insert(User...user);
//
//    @Insert
//    void insert(List<User> userList);

    //删除
    @Query("delete from user WHERE id=:id")
    void delete(int id);

//    @DELETE
//    void delete(User...user);
//
//    @DELETE
//    void delete(List<User> users);

    //更新
    @Update
    void update(User user);

//    @Update
//    void update(User... user);
//
//    @Update
//    void update(List<User> users);
}
