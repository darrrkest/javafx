package com.example.pavel.entity;


public class Animal {
    private long id;
    private String type;
    private String nickname;
    private String color;
    private long owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    public Animal() {

    }

    public Animal(long id, String type, String nickname, String color, long owner) {
        this.id = id;
        this.type = type;
        this.nickname = nickname;
        this.color = color;
        this.owner = owner;
    }

    public Animal(String type, String nickname, String color, long owner) {
        this.type = type;
        this.nickname = nickname;
        this.color = color;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return type + " " + nickname + " " + color;
    }
}
