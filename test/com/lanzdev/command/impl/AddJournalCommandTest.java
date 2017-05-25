package com.lanzdev.command.impl;

import com.lanzdev.ActionType;
import com.lanzdev.DefaultMockito;
import com.lanzdev.Path;
import com.lanzdev.Util;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.entity.JournalDao;
import com.lanzdev.dao.entity.SubjectDao;
import com.lanzdev.dao.entity.UserDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseDao;
import com.lanzdev.dao.mysql.impl.MysqlJournalDao;
import com.lanzdev.dao.mysql.impl.MysqlSubjectDao;
import com.lanzdev.dao.mysql.impl.MysqlUserDao;
import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.Journal;
import com.lanzdev.domain.entity.Subject;
import com.lanzdev.domain.entity.User;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;

public class AddJournalCommandTest extends DefaultMockito {

    private static AddJournalCommand command;

    private static Journal journal;
    private static Course course;
    private static Subject subject;
    private static User teacher;
    private static User student;

    private static JournalDao journalDao;
    private static CourseDao courseDao;
    private static SubjectDao subjectDao;
    private static UserDao userDao;

    @BeforeClass
    public static void initBeforeClass() throws ParseException {

        initEnv();

        journalDao = new MysqlJournalDao();
        courseDao = new MysqlCourseDao();
        subjectDao = new MysqlSubjectDao();
        userDao = new MysqlUserDao();

        teacher = Util.createUser(Permission.TEACHER);
        teacher = userDao.create(teacher);
        subject = Util.createSubject();
        subject = subjectDao.create(subject);
        course = Util.createCourse(subject.getId(), teacher.getId());
        course = courseDao.create(course);
        student = Util.createUser(Permission.STUDENT);
        student = userDao.create(student);
        journal = Util.createJournal(course.getId(), student.getId());

        when(request.getParameter("course_id")).thenReturn(String.valueOf(journal.getCourseId()));
        when(request.getParameter("student_id")).thenReturn(String.valueOf(journal.getStudentId()));
        when(request.getParameter("mark")).thenReturn(String.valueOf(journal.getMark()));
    }

    @AfterClass
    public static void destroyAfterClass() {

        Journal onRemove = Util.getLastInsert(journalDao.getAll());
        journalDao.delete(onRemove);
        userDao.delete(student);
        courseDao.delete(course);
        userDao.delete(teacher);
        subjectDao.delete(subject);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {

        command = new AddJournalCommand();
        command.init(context, request, response, ActionType.GET);
        String result = command.execute();
        Assert.assertEquals(Path.FORWARD_TO_ADD_COURSE_STUDENT_FORM, result);
    }

    @Test
    public void testDoPost() throws ServletException, IOException {

        command = new AddJournalCommand();
        command.init(context, request, response, ActionType.POST);
        String result = command.execute();
        Assert.assertEquals(Path.REDIRECT_TO_VIEW_COURSE_STUDENTS_LIST, result);
    }
}