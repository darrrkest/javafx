package com.example.pavel.dao;

import com.example.pavel.entity.Person;

import java.util.List;

public interface PersonDAO extends DAO<Person> {
    //create
    void add(Person person);


    //read
    List<Person> getAll();

    Person getById(long id);

    //update
    void update(Person person);

    //delete
    void remove(Person person);


}
