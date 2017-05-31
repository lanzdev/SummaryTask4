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
import com.lanzdev.util.validator.CourseNameValidator;
import com.lanzdev.util.validator.DateValidator;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.List;

public class AddCourseCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(AddCourseCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        checkOnError();

        SubjectDao subjectDao = new MysqlSubjectDao();
        List<Subject> subjects = subjectDao.getAll();

        UserDao userDao = new MysqlUserDao();
        List<User> teachers = userDao.getTeachers();

        request.setAttribute("subjects", subjects);
        request.setAttribute("teachers", teachers);

        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_ADD_COURSE_FORM;
    }

    @Override
    protected String doPost( ) {

        LOGGER.debug("Entering doPost()");

        String courseName = request.getParameter("course_name");
        int subjectId = Integer.valueOf(request.getParameter("subject_id"));
        int teacherId = Integer.valueOf(request.getParameter("teacher_id"));
        Date startDate = Parser.parseDate(request.getParameter("start_date"));
        Date expirationDate = Parser.parseDate(request.getParameter("expiration_date"));

        boolean valid = CourseNameValidator.validate(courseName)
                && DateValidator.validate(startDate, expirationDate);

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

            List<Course> list = dao.getAll();
            request.setAttribute("courses", list);

            LOGGER.trace("The course with id " + course.getId() + " added.");
        } else {
            LOGGER.trace("Fields failed validation.");
            return Path.REDIRECT_TO_ADD_COURSE_FORM + "&error=not_valid";
        }

        LOGGER.debug("Leaving doPost()");
        return Path.REDIRECT_TO_VIEW_COURSES_LIST;
    }
}
