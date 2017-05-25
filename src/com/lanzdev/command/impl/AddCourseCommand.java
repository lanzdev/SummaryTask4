package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseDao;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.util.validator.CourseInputValidator;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddCourseCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(AddCourseCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        checkOnError();

        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_ADD_COURSE_FORM;
    }

    @Override
    protected String doPost( ) {

        LOGGER.debug("Entering doPost()");

        String courseName = request.getParameter("course_name");
        Integer subjectId = Integer.valueOf(request.getParameter("subject_id"));
        Integer teacherId = Integer.valueOf(request.getParameter("teacher_id"));
        Date startDate = null;
        Date expirationDate = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse(request.getParameter("start_date"));
            startDate = new Date(date.getTime());
            date = sdf.parse(request.getParameter("expiration_date"));
            expirationDate = new Date(date.getTime());
        } catch (ParseException e) {
            LOGGER.error("Exception while parsing date.\n", e);
        }

        boolean valid = CourseInputValidator.validate();

        if (valid) {
            LOGGER.trace(String.format("Fields: %s, %s, %s, %s, %s",
                    courseName, subjectId, teacherId, startDate, expirationDate));

            Course course = new Course();
            course.setName(courseName);
            course.setSubjectId(subjectId);
            course.setTeacherId(teacherId);
            course.setStartDate(startDate);
            course.setExpirationDate(expirationDate);

            CourseDao dao = new MysqlCourseDao();
            course = dao.create(course);
            request.setAttribute("course", course);
            LOGGER.trace("The course with id " + course.getId() + " added.");
        } else {
            LOGGER.trace("Fields failed validation.");
            return Path.REDIRECT_TO_ADD_COURSE_FORM + "&error=not_valid";
        }

        LOGGER.debug("Leaving doPost()");
        return Path.REDIRECT_TO_VIEW_COURSES_LIST;
    }
}
