package com.lanzdev.command.impl;

import com.lanzdev.ActionType;
import com.lanzdev.DefaultMockito;
import com.lanzdev.Path;
import com.lanzdev.Util;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.entity.SubjectDao;
import com.lanzdev.dao.entity.UserDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseDao;
import com.lanzdev.dao.mysql.impl.MysqlSubjectDao;
import com.lanzdev.dao.mysql.impl.MysqlUserDao;
import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.Subject;
import com.lanzdev.domain.entity.User;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;

public class AddCourseCommandTest extends DefaultMockito {

    private static AddCourseCommand command;

    private static Course course;
    private static Subject subject;
    private static User teacher;

    private static CourseDao courseDao;
    private static SubjectDao subjectDao;
    private static UserDao userDao;


    @BeforeClass
    public static void initBeforeClass() throws ParseException {

        initEnv();

        courseDao = new MysqlCourseDao();
        subjectDao = new MysqlSubjectDao();
        userDao = new MysqlUserDao();

        subject = Util.createSubject();
        subject = subjectDao.create(subject);
        teacher = Util.createUser(Permission.TEACHER);
        teacher = userDao.create(teacher);
        course = Util.createCourse(subject.getId(), teacher.getId());

        when(request.getParameter("name")).thenReturn(course.getName());
        when(request.getParameter("subject_id")).thenReturn(String.valueOf(course.getSubjectId()));
        when(request.getParameter("teacher_id")).thenReturn(String.valueOf(course.getTeacherId()));
        when(request.getParameter("start_date")).thenReturn(String.valueOf(course.getStartDate()));
        when(request.getParameter("expiration_date")).thenReturn(String.valueOf(course.getExpirationDate()));
    }

    @AfterClass
    public static void destroyAfterClass() {

        Course onRemove = Util.getLastInsert(courseDao.getAll());
        courseDao.delete(onRemove);
        subjectDao.delete(subject);
        userDao.delete(teacher);
    }

    @Test
    public void testDoGet( ) throws ServletException, IOException {

        command = new AddCourseCommand();
        command.init(context, request, response, ActionType.GET);
        String result = command.execute();
        Assert.assertEquals(Path.FORWARD_TO_ADD_COURSE_FORM, result);
    }

    @Test
    public void testDoPost() throws ServletException, IOException {

        command = new AddCourseCommand();
        command.init(context, request, response, ActionType.POST);
        String result = command.execute();
        Assert.assertEquals(Path.REDIRECT_TO_VIEW_COURSES_LIST, result);
    }
}