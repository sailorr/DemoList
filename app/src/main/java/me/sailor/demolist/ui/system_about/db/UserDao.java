package me.sailor.demolist.ui.system_about.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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
