package com.example.pavel.dao;


import java.util.List;

public interface DAO<E> {
    //create
    void add(E e);

    //read
    List<E> getAll();

    E getById(long id);

    //update
    void update(E e);

    //delete
    void remove(E e);
}
