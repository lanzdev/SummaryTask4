package com.lanzdev.util.validator;

import com.lanzdev.util.Regex;

import java.util.regex.Pattern;

public class UserNameValidator {

    public static boolean validate(String name) {

        Pattern pattern = Pattern.compile(Regex.USER_NAME.getPattern());
        return pattern.matcher(name).matches();
    }
}
