package com.example.android.rxweather;

public class PersonTest {
    private String name;
    private int age;

    public PersonTest() {
    }

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

    public PersonTest(String name, int age){
        this.name = name;
        this.age = age;
    }
}
