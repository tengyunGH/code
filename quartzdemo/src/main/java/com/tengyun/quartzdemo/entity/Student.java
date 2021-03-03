package com.tengyun.quartzdemo.entity;

import java.io.Serializable;

/**
 * @author tengyun
 * @date 2021/3/1 13:59
 **/
public class Student implements Serializable {

    private String name;

    private Integer age;

    private Integer gender;

    public Student(String name, Integer age, Integer gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
