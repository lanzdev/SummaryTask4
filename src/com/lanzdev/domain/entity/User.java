package com.lanzdev.domain.entity;

import com.lanzdev.domain.Identified;
import com.lanzdev.domain.Permission;

import java.io.Serializable;

public class User implements Identified<Integer>, Serializable {

    private static final long serialVersionUID = 2536432234012358753L;

    private Integer id;
    private String login;
    private String password;
    private Permission permission;
    private String firstName;
    private String lastName;
    private Boolean blocked;

    @Override
    public Integer getId( ) {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin( ) {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword( ) {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Permission getPermission( ) {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public String getFirstName( ) {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName( ) {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getBlocked( ) {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString( ) {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", permission=" + permission +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", blocked=" + blocked +
                '}';
    }
}