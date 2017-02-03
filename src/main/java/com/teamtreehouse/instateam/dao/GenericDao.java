package com.teamtreehouse.instateam.dao;

import java.util.List;
/**
 * Created by GoranB on 2017-02-03.
 */
public interface GenericDao<T> {
    T findById(Long id);
    List<T> findAll();
    void save(T object);
    void delete(T delete);
}
