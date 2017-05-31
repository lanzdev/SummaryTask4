package com.lanzdev.dao;

import java.util.List;

/**
 * GenericDao is the ancestor for all dao classes.
 * GenericDao contains all main methods CRUD for
 * easy success to data
 * @param <T> is type of target object for DAO
 * @param <PK> is type of primary key of object
 */
public interface GenericDao<T, PK> {

    /**
     * Creates an object and returns already created object
     * @param object object which is going to be created.
     * @return returns created object.
     */
    T create(T object);

    /**
     * Returns object by primary key
     * @param key primary key of object
     * @return return object with suitable key
     */
    T get(PK key);

    /**
     * Returns {@link List list} of all objects of one entity
     * @return list of objects
     */
    List<T> getAll();

    /**
     * Updates object
     * @param object object which is going to be updated
     */
    void update(T object);

    /**
     * Deletes object
     * @param object object which is going to be deleted
     */
    void delete(T object);
}
