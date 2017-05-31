package com.lanzdev.util.validator;

import com.lanzdev.util.Regex;

import java.util.regex.Pattern;

public class LoginValidator {

    public static boolean validate(String login) {

        Pattern pattern = Pattern.compile(Regex.LOGIN.getPattern());
        return pattern.matcher(login).matches();
    }
}
