package com.ra.model.dao;

import java.util.List;

public interface Dao<T> {
    List<T> findAll();
    T findById(Integer id);
    Boolean save(T t);
    void delete(Integer id);
}
