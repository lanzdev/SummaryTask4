package com.lanzdev.domain;


public enum Permission {
    TEACHER, STUDENT, ADMIN;

    public static Permission getPermission(String permission) {
        Permission perm = Permission.valueOf(permission);
        return Permission.valueOf(permission);
    }

    public String getName() {
        return name().toLowerCase();
    }
}