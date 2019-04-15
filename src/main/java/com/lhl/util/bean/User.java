package com.lhl.util.bean;

import java.io.Serializable;

/**
 * Serializable 序列化
 * Created by lihongli on 2019/4/12
 */
public class User implements Serializable {

    private int age;

    private String name;

    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{user: age = " + age + ", name = " + name + "}";
    }
}
