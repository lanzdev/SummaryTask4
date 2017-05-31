package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseDao;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.User;
import org.apache.log4j.Logger;

import java.util.List;

public class ListCoursesInProcessCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(ListCoursesInProcessCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        User student = (User) request.getSession().getAttribute("user");
        LOGGER.trace("Student: " + student.getId());

        CourseDao dao = new MysqlCourseDao();
        List<Course> list = dao.getSelectedCoursesInProcess(student);
        LOGGER.trace("List of courses in process contains " + list.size() + " items.");

        request.getSession().setAttribute("courses", list);
        request.getSession().setAttribute("is_for_subscribe", false);


        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_VIEW_COURSES_LIST;
    }

    @Override
    protected String doPost( ) {
        return Path.REDIRECT_TO_VIEW_COURSES_LIST;
    }
}
