package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.entity.CourseStudentsDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseStudentsDao;
import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.CourseStudents;
import com.lanzdev.domain.entity.User;
import org.apache.log4j.Logger;

import java.util.List;

public class SubscribeCourseCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(SubscribeCourseCommand.class);

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

        User user = (User) request.getSession().getAttribute("user");
        String lang = (String) request.getSession().getAttribute("lang");


        if (user.getPermission() == Permission.STUDENT) {

            int courseId = Integer.parseInt(request.getParameter("course_id"));
            CourseDao courseDao = new MysqlCourseDao();
            Course course = courseDao.get(courseId);
            if (course == null) {

                if (lang == null || lang.equals("en")) {
                    request.setAttribute("error_message", "Such course doesn't exist.");
                } else {
                    request.setAttribute("error_message", "Такого курса не существует.");
                }
                List<Course> list = courseDao.getNotSubscribed(user);
                request.getSession().setAttribute("courses", list);
            } else {

                CourseStudentsDao courseStudentsDao = new MysqlCourseStudentsDao();
                CourseStudents courseStudents = new CourseStudents();
                courseStudents.setCourseId(course.getId());
                courseStudents.setStudentId(user.getId());
                courseStudents = courseStudentsDao.create(courseStudents);
                List<Course> list = courseDao.getNotSubscribed(user);
                request.setAttribute("courses", list);
                LOGGER.trace("Courses list contains " + list.size() + " items.");
                request.getSession().setAttribute("courses", list);
            }
        }

        LOGGER.debug("Leaving doPost()");

        return Path.REDIRECT_TO_VIEW_COURSES_LIST;
    }
}
