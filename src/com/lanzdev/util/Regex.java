package com.lanzdev.util;

/**
 * Contains all regexp used in the application
 */
public enum Regex {

    LOGIN("^[a-z0-9]{3,16}$"),
    PASSWORD("^[a-z0-9]{6,18}$"),
    COURSE_NAME("[a-zA-Zа-яА-Я0-9\\s\\|\\\\\\-\"']{6,250}"),
    USER_NAME("[a-zA-Zа-яА-Я\\s]{2,255}"),
    MARK("[0-5]");


    private String pattern;

    Regex(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern( ) {
        return pattern;
    }
}
