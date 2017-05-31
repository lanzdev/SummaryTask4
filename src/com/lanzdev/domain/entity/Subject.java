package com.lanzdev.domain.entity;

import com.lanzdev.domain.Identified;

import java.io.Serializable;

/**
 * Representation of subject entity from db.
 * Contains all information about subject entity.
 */
public class Subject implements Identified<Integer>, Serializable{

    private static final long serialVersionUID = -6420726636733755308L;

    private Integer id;
    private String name;

    @Override
    public Integer getId( ) {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName( ) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString( ) {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
