package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseDao;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.util.validator.CourseInputValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class DeleteCourseCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(DeleteCourseCommand.class);

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

        int courseId = Integer.parseInt(request.getParameter("course_id"));

        boolean valid = CourseInputValidator.validate();

        if (valid) {
            LOGGER.trace("On delete course with id " + courseId);

            CourseDao dao = new MysqlCourseDao();
            Course course = dao.get(courseId);
            dao.delete(course);

            List<Course> list = dao.getAll();
            request.setAttribute("courses", list);
        } else {
            LOGGER.trace("Fields failed validation.");
            return Path.REDIRECT_TO_VIEW_COURSES_LIST + "&error=not_valid";
        }

        LOGGER.debug("Leaving doPost()");
        return Path.REDIRECT_TO_VIEW_COURSES_LIST;
    }
}
