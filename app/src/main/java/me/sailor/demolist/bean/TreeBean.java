package me.sailor.demolist.bean;

import java.util.List;

/**
 * @author Administrator on2019/4/9 9:46
 * @desc
 */
public class TreeBean {
    private int num;

    private List<Person> mList;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<Person> getList() {
        return mList;
    }

    public void setList(List<Person> list) {
        mList = list;
    }

    class Person {
        String name;
        int age;
        String gender;
    }
}
