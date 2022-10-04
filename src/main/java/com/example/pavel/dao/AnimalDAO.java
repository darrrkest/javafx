package com.example.pavel.dao;

import com.example.pavel.entity.Animal;
import com.example.pavel.entity.Person;

import java.util.List;

public interface AnimalDAO extends DAO<Animal>{
    //create
    void add(Animal animal);

    //read
    List<Animal> getAll();

    Animal getById(long id);

    List<Animal> getByPerson(Person person);

    //ObservableList<Animal> getByPersonList(Person person);


    //update
    void update(Animal animal);

    //delete
    void remove(Animal animal);


}