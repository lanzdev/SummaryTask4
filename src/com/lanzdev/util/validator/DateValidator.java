package com.lanzdev.util.validator;

import java.sql.Date;

public class DateValidator {

    public static boolean validate(Date startDate, Date expirationDate) {

        return expirationDate.getTime() - startDate.getTime() >= 0;
    }
}
