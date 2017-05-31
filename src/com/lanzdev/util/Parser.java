package com.lanzdev.util;

import org.apache.log4j.Logger;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Parser {

    private static final Logger LOGGER = Logger.getLogger(Parser.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Date parseDate(String source) {
        try {
            java.util.Date date = sdf.parse(source);
            LOGGER.trace("Date successfully parsed.");
            return new Date(date.getTime());
        } catch (ParseException e) {
            LOGGER.error("Error while parsing date: " + source);
        }
        return null;
    }
}
