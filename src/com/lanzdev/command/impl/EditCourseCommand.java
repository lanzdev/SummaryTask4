package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.entity.SubjectDao;
import com.lanzdev.dao.entity.UserDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseDao;
import com.lanzdev.dao.mysql.impl.MysqlSubjectDao;
import com.lanzdev.dao.mysql.impl.MysqlUserDao;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.Subject;
import com.lanzdev.domain.entity.User;
import com.lanzdev.util.Parser;
import com.lanzdev.util.validator.CourseInputValidator;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.List;

public class EditCourseCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(EditCourseCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        checkOnError();

        int courseId = Integer.parseInt(request.getParameter("course_id"));
        LOGGER.trace("Course id " + courseId);
        CourseDao courseDao = new MysqlCourseDao();
        Course course = courseDao.get(courseId);

        SubjectDao subjectDao = new MysqlSubjectDao();
        List<Subject> subjects = subjectDao.getAll();

        UserDao userDao = new MysqlUserDao();
        List<User> teachers = userDao.getTeachers();

        request.setAttribute("course", course);
        request.setAttribute("subjects", subjects);
        request.setAttribute("teachers", teachers);

        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_EDIT_COURSE;
    }

    @Override
    protected String doPost( ) {

        LOGGER.debug("Entering doPost()");

        int courseId = Integer.parseInt(request.getParameter("course_id"));
        String courseName = request.getParameter("course_name");
        int subjectId = Integer.parseInt(request.getParameter("subject_id"));
        Integer teacherId = null;
        try {
            teacherId = Integer.parseInt(request.getParameter("teacher_id"));
        } catch (NumberFormatException e) {
            LOGGER.trace("Teacher id was set to null.");
        }
        Date startDate = Parser.parseDate(request.getParameter("start_date"));
        Date expirationDate = Parser.parseDate(request.getParameter("expiration_date"));

        boolean valid = CourseInputValidator.validate();

        if (valid) {
            LOGGER.trace(String.format("Fields: %s, %s, %s, %s, %s, %s",
                    courseId, courseName, subjectId, teacherId, startDate, expirationDate));

            CourseDao dao = new MysqlCourseDao();
            Course course = dao.get(courseId);
            course.setName(courseName);
            course.setSubjectId(subjectId);
            course.setTeacherId(teacherId);
            course.setStartDate(startDate);
            course.setExpirationDate(expirationDate);
            dao.update(course);

            List<Course> list = dao.getAll();
            request.setAttribute("courses", list);

            SubjectDao subjectDao = new MysqlSubjectDao();
            List<Subject> subjects = subjectDao.getAll();
            request.setAttribute("subjects", subjects);

            UserDao userDao = new MysqlUserDao();
            List<User> teachers = userDao.getTeachers();
            request.setAttribute("teachers", teachers);

            LOGGER.trace("The course with id " + course.getId() + " edited.");
        } else {
            LOGGER.trace("Fields failed validation.");
            return Path.REDIRECT_TO_EDIT_COURSE + "&error=not_valid";
        }

        LOGGER.debug("Leaving doPost()");
        return Path.REDIRECT_TO_VIEW_COURSES_LIST;
    }
}
