package com.tengyun.design.pattern.singleton;

/**
 * 枚举单例模式
 * @author tengyun
 * @date 2021/2/8 15:35
 **/
public enum EnumSingleton {

    /**
     * 单例
     **/
    INSTANCE;
    /**
     * 名称
     **/
    private String name;

    EnumSingleton(){
        this.name = "tengyun";
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

class Test {
    public static void main(String[] args) {
        EnumSingleton instance = EnumSingleton.getInstance();
        String name = instance.getName();
        System.out.println(name);
    }
}
