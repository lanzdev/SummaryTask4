package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseDao;
import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ListCoursesCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(ListCoursesCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        User user = (User) request.getSession().getAttribute("user");
        String adds = request.getParameter("adds");
        List<Course> list = new ArrayList<>();
        if (request.getSession().getAttribute("courses") != null) {
            list =(List<Course>) request.getSession().getAttribute("courses");
        }
        CourseDao dao = new MysqlCourseDao();
        if (adds != null && adds.equals("new")) {

            if (user.getPermission() == Permission.TEACHER) {
                return Path.FORWARD_TO_VIEW_COURSES_LIST;
            }
            else if (user.getPermission() == Permission.STUDENT) {
                list = dao.getNotSubscribed(user);
                request.getSession().setAttribute("is_for_subscribe", true);
            } else {
                list = dao.getAll();
            }
        }
        if (user.getPermission() != Permission.STUDENT
                && request.getSession().getAttribute("last_command").getClass() != ListCoursesSortCommand.class) {
            list = dao.getAll();
        }

        LOGGER.trace("doGet() for user with id " + user.getId());
        request.getSession().setAttribute("courses", list);
        LOGGER.trace("Courses list contains " + list.size());

        LOGGER.debug("Leaving doGet()");

        return Path.FORWARD_TO_VIEW_COURSES_LIST;
    }

    @Override
    protected String doPost( ) {
        return Path.REDIRECT_TO_VIEW_COURSES_LIST;
    }
}
