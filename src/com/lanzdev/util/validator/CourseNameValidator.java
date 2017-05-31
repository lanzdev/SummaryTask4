package com.lanzdev.util.validator;

import com.lanzdev.util.Regex;

import java.util.regex.Pattern;

public class CourseNameValidator {

    public static boolean validate(String courseName) {

        Pattern pattern = Pattern.compile(Regex.COURSE_NAME.getPattern());
        return pattern.matcher(courseName).matches();
    }
}
