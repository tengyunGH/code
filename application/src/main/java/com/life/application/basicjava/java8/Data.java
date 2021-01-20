package com.life.application.basicjava.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tengyun
 * @date 2021/1/19 16:35
 **/
public class Data {

    private static List<Person> list = null;

    private static final List<List<Person>> personLists = new ArrayList<>();

    static {
        Person person1 = new Person(1L, "tengyun1", "101577931", "男");
        Person person6 = new Person(6L, "tengyun6", "101577936", "女");
        Person person4 = new Person(4L, "tengyun4", "101577934", "女");
        Person person7 = new Person(7L, "tengyun8", "101577937", "男");
        Person person3 = new Person(3L, "tengyun3", "101577933", "男");
        Person person2 = new Person(2L, "tengyun2", "101577932", "女");
        Person person5 = new Person(5L, "tengyun5", "101577935", "男");
        list = Arrays.asList(person7, person4, person3, person6, person5, person2, person1);
        personLists.add(Arrays.asList(person1, person2));
        personLists.add(Arrays.asList(person3, person4));
        personLists.add(Arrays.asList(person5, person6, person7));
    }

    public static List<Person> getData() {
        return list;
    }

    public static List<List<Person>> getPersonLists () {
        return personLists;
    }
}
