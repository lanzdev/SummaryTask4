package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseDao;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ListCoursesForTeacherCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(ListCoursesForTeacherCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        User user = (User) request.getSession().getAttribute("user");
        List<Course> courses = new ArrayList<>();
        CourseDao courseDao = new MysqlCourseDao();
        courses = courseDao.getByTeacher(user);

        request.getSession().setAttribute("courses", courses);

        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_VIEW_COURSES_LIST;
    }

    @Override
    protected String doPost( ) {
        return Path.REDIRECT_TO_COURSES_LIST_FOR_EVALUATION;
    }
}
