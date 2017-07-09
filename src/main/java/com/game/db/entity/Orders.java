package com.game.db.entity;

import java.io.Serializable;

/**
 * Created by 文江 on 2017/7/9.
 */
public class Orders implements Serializable{
public  Integer id;
public  String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
