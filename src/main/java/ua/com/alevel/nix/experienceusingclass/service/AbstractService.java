package ua.com.alevel.nix.experienceusingclass.service;

import ua.com.alevel.nix.experienceusingclass.data.AbstractData;

import java.util.List;

public interface AbstractService<T extends AbstractData<T>> {

    void saveOrUpdate(T t);
    T findById(Long id);
    List<T> findAll();
    void remove(Long id);
}
