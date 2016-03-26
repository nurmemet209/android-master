package com.cn.entity;

import java.io.Serializable;

/**
 * Created by nurmemet on 2016/3/19.
 */
public abstract class BaseEntity implements Serializable{

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    protected long id;

    public abstract String getName();

    @Override
    public boolean equals(Object o) {
        if (o!=null){
            BaseEntity baseEntity=(BaseEntity)o;
            if (baseEntity.getId()==this.getId()){
                return true;
            }
        }
        return false;
    }
}
