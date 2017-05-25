package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseDao;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.User;
import org.apache.log4j.Logger;

import java.util.List;

public class ListNotStartedCoursesCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(ListNotStartedCoursesCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        User student = (User) request.getSession().getAttribute("user");
        LOGGER.trace("Student: " + student.getId());

        CourseDao dao = new MysqlCourseDao();
        List<Course> list = dao.getSelectedNotStartedCourses(student);
        LOGGER.trace("List of not started courses contains " + list.size() + " items.");

        request.setAttribute("courses", list);

        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_VIEW_COURSES_LIST;
    }

    @Override
    protected String doPost( ) {
        return Path.REDIRECT_TO_VIEW_COURSES_LIST;
    }
}
