package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.entity.JournalDao;
import com.lanzdev.dao.entity.UserDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseDao;
import com.lanzdev.dao.mysql.impl.MysqlJournalDao;
import com.lanzdev.dao.mysql.impl.MysqlUserDao;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.Journal;
import com.lanzdev.domain.entity.User;
import org.apache.log4j.Logger;

public class EvaluateCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(EvaluateCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        int courseId = Integer.parseInt(request.getParameter("course_id"));
        CourseDao courseDao = new MysqlCourseDao();
        Course course = courseDao.get(courseId);

        int studentId = Integer.parseInt(request.getParameter("student_id"));
        UserDao userDao = new MysqlUserDao();
        User student = userDao.get(studentId);

        Double mark = Double.parseDouble(request.getParameter("mark"));

        request.setAttribute("course", course);
        request.setAttribute("student", student);
        request.setAttribute("mark", mark);


        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_EVALUATE_FORM;
    }

    @Override
    protected String doPost( ) {

        int courseId = Integer.parseInt(request.getParameter("course_id"));
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        double mark = Double.parseDouble(request.getParameter("mark"));

        CourseDao courseDao = new MysqlCourseDao();
        Course course = courseDao.get(courseId);

        UserDao userDao = new MysqlUserDao();
        User student = userDao.get(studentId);

        JournalDao journalDao = new MysqlJournalDao();
        Journal journal = journalDao.getByCourseAndStudent(course, student);
        if (journal == null) {
            journal = new Journal();
            journal.setCourseId(course.getId());
            journal.setStudentId(student.getId());
            journal.setMark(mark);
            journal = journalDao.create(journal);
        } else {
            journal.setMark(mark);
            journalDao.update(journal);
        }

        LOGGER.debug("Leaving doPost()");
        return Path.REDIRECT_TO_COURSES_LIST_FOR_EVALUATION;
    }
}
