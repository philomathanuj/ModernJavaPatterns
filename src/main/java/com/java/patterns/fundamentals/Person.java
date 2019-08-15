package com.java.patterns.fundamentals;

public class Person {
    private String name;

    private Integer age;

    public Person(String name){
        this.name = name;
    }

    public Person(String name,Integer age){
        this.name = name;
        this.age = age;
    }
    public Integer getWordsInString(){
        return this.name.split(" ").length;
    }

    public Integer getNameLength(){
        return this.name.length();
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
}
