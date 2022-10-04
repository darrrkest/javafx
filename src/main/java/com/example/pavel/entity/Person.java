package com.example.pavel.entity;

public class Person {
    private long id;
    private String name;
    private String secondName;

    public Person() {

    }

    public Person(long id, String name, String secondName) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
    }

    public Person(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + " " + secondName;
    }
}
