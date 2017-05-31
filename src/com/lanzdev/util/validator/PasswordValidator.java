package com.lanzdev.util.validator;

import com.lanzdev.util.Regex;

import java.util.regex.Pattern;

public class PasswordValidator {

    public static boolean validate(String password) {

        Pattern pattern = Pattern.compile(Regex.PASSWORD.getPattern());
        return pattern.matcher(password).matches();
    }
}
