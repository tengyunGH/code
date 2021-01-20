package com.life.application.basicjava.java8;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author tengyun
 * @date 2021/1/19 16:35
 **/
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String uid;

    private String sex;


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Person() {
    }

    public Person(Long id, String name, String uid, String sex) {
        this.id = id;
        this.name = name;
        this.uid = uid;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", uid='" + uid + '\'' +
            ", sex='" + sex + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
            Objects.equals(name, person.name) &&
            Objects.equals(uid, person.uid) &&
            Objects.equals(sex, person.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, uid, sex);
    }
}
