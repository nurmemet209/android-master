package com.cn.entity;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/5/3.
 */
public class Child extends BaseEntity {
    private long id;
    private String name;

    @Override
    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Child> children) {
        this.children = children;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private ArrayList<Child> children;
}
