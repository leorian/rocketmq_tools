package com.yx.metaq.demo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: renyueliang
 * Date: 16/10/25
 * Time: 下午5:14
 * To change this template use File | Settings | File Templates.
 */
public class TestMessgeDTO implements Serializable  {

    static final long serialVersionUID = -1L;

    private String name;

    private int age;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
