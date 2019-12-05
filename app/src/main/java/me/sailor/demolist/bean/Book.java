package me.sailor.demolist.bean;

/**
 * @author Administrator on2019/4/22 11:38
 * @desc
 */
public class Book {
    public String name;
    public String type;
    public int id;

    public Book(String name, int id,String type) {
        this.name = name;
        this.type = type;
        this.id = id;
    }
}
