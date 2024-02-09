package org.example.repository;

import org.springframework.stereotype.Component;

import java.util.List;


public interface GenericRepository<T, ID> {

    // ID is Long

    //save
    T save(T entity);

    // find
    T findById(ID id);

    // getAll
    List<T> getAll();

    T updateById(ID id, T newEntity);

    void deleteById(ID id);
}
