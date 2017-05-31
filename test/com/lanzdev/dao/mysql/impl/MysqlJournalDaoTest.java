package com.lanzdev.dao.mysql.impl;

import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.Journal;
import com.lanzdev.domain.entity.User;
import org.junit.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MysqlJournalDaoTest {

    private static String stringDate = "2000-01-01";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");

    private static Course course;
    private static User student;

    private static Journal journal;
    private static MysqlJournalDao dao;
    private static MysqlCourseDao courseDao;
    private static MysqlUserDao userDao;


    @BeforeClass
    public static void initBeforeClass() throws ParseException {
        dao = new MysqlJournalDao();
        courseDao = new MysqlCourseDao();
        userDao = new MysqlUserDao();

        createInstances();
    }

    private static void createInstances() throws ParseException {

        course = new Course();
        course.setName("TestCourse01");
        course.setSubjectId(1);
        course.setTeacherId(1);
        course.setStartDate(new Date(sdf.parse(stringDate).getTime()));
        course.setExpirationDate(new Date(sdf.parse(stringDate).getTime()));
        course = courseDao.create(course);

        student = new User();
        student.setLogin("student_01");
        student.setPassword("student_01");
        student.setPermission(Permission.STUDENT);
        student.setFirstName("student_01");
        student.setLastName("student_01");
        student = userDao.create(student);
    }

    @AfterClass
    public static void destroyAfterClass() {
        courseDao.delete(course);
        userDao.delete(student);
    }

    @Before
    public void initBeforeTest() {

        journal = new Journal();
        journal.setCourseId(course.getId());
        journal.setStudentId(student.getId());
        journal.setMark(0.0);
    }

    @Test
    public void testCreate( ) {

        Journal created = dao.create(journal);
        Assert.assertEquals(journal.getCourseId(), created.getCourseId());
        Assert.assertEquals(journal.getStudentId(), created.getStudentId());
        dao.delete(created);
    }

    @Test
    public void testGetById( ) {

        Journal created = dao.create(journal);
        Journal gotten = dao.get(created.getId());
        Assert.assertEquals(created.getCourseId(), gotten.getCourseId());
        Assert.assertEquals(created.getStudentId(), gotten.getStudentId());
        dao.delete(created);
    }

    @Test
    public void testGetAll( ) {

        Journal created = dao.create(journal);
        Assert.assertNotEquals(0, dao.getAll().size());
        dao.delete(created);
    }

    @Test
    public void testUpdate( ) {

        Journal created = dao.create(journal);
        Journal gotten = dao.get(created.getId());
        gotten.setMark(gotten.getMark() + 1);
        dao.update(gotten);
        gotten = dao.get(created.getId());
        Assert.assertEquals(created.getCourseId(), gotten.getCourseId());
        Assert.assertEquals(created.getStudentId(), gotten.getStudentId());
        Assert.assertNotEquals(created.getMark(), gotten.getMark());
        dao.delete(created);
    }

    @Test
    public void testDelete( ) {

        Journal created = dao.create(journal);
        int size = dao.getAll().size();
        dao.delete(created);
        Assert.assertEquals(size - 1, dao.getAll().size());
    }
}
