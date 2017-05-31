package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.entity.UserDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseDao;
import com.lanzdev.dao.mysql.impl.MysqlUserDao;
import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.User;
import org.apache.log4j.Logger;

import java.util.List;

public class AssignTeacherForCourseCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(AssignTeacherForCourseCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        checkOnError();

        int courseId = Integer.parseInt(request.getParameter("course_id"));
        CourseDao courseDao = new MysqlCourseDao();
        Course course = courseDao.get(courseId);

        if (course != null) {
            LOGGER.trace("User with id: " + course.getId() + " found.");
        }

        UserDao userDao = new MysqlUserDao();
        List<User> teachers = userDao.getTeachers();

        LOGGER.trace("Teachers list contains " + teachers.size() + " items.");

        request.setAttribute("course", course);
        request.setAttribute("teachers", teachers);

        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_ASSIGN_TEACHER_FORM;
    }

    @Override
    protected String doPost( ) {

        LOGGER.debug("Entering doPost()");

        int courseId = Integer.parseInt(request.getParameter("course_id"));
        CourseDao courseDao = new MysqlCourseDao();
        Course course = courseDao.get(courseId);

        int teacherId = Integer.parseInt(request.getParameter("teacher_id"));
        UserDao userDao = new MysqlUserDao();
        User teacher = userDao.get(teacherId);

        if (teacher.getPermission() != Permission.TEACHER) {
            LOGGER.debug("User with id " + teacher.getId() + " is not a teacher.");
        } else {
            course.setTeacherId(teacher.getId());
            courseDao.update(course);
        }

        LOGGER.debug("Leaving doPost()");
        return Path.REDIRECT_TO_VIEW_COURSES_LIST;
    }
}
