package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.CourseStudentsDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseStudentsDao;
import com.lanzdev.domain.entity.CourseStudents;
import com.lanzdev.util.validator.CourseStudentsValidator;
import org.apache.log4j.Logger;

public class AddCourseStudentsCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(AddCourseStudentsCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        checkOnError();

        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_VIEW_COURSES_LIST;
    }

    @Override
    protected String doPost( ) {

        LOGGER.debug("Entering doPost()");

        Integer courseId = Integer.valueOf(request.getParameter("course_id"));
        Integer studentId = Integer.valueOf(request.getParameter("student_id"));

        boolean valid = CourseStudentsValidator.validate();

        if (valid) {
            LOGGER.trace("Fields: " + courseId + ", " + studentId);

            CourseStudents courseStudents = new CourseStudents();
            courseStudents.setCourseId(courseId);
            courseStudents.setStudentId(studentId);

            CourseStudentsDao dao = new MysqlCourseStudentsDao();
            courseStudents = dao.create(courseStudents);
            request.setAttribute("course_students", courseStudents);
            LOGGER.trace("The course students with id " + courseStudents.getId() + " added.");
        } else {
            LOGGER.trace("Fields failed validation.");
            return Path.REDIRECT_TO_ADD_COURSE_STUDENT_FORM + "&error=not_valid";
        }

        LOGGER.debug("Leaving doPost()");
        return Path.REDIRECT_TO_VIEW_COURSE_STUDENTS_LIST;
    }
}
