package com.lanzdev.util.validator;

public class MarkValidator {

    public static boolean validate(Integer mark) {

        return 0 <= mark && mark <= 5;
    }
}
