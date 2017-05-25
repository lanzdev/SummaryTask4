package com.lanzdev.dao.mysql.impl;

import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.CourseStudents;
import com.lanzdev.domain.entity.User;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MysqlCourseStudentsDaoTest {

    private static String stringDate = "2000-01-01";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");

    private static Course course1;
    private static Course course2;

    private static User student1;
    private static User student2;

    private static MysqlCourseStudentsDao dao;
    private static MysqlCourseDao courseDao;
    private static MysqlUserDao userDao;

    @BeforeClass
    public static void initBeforeClass() throws ParseException {
        dao = new MysqlCourseStudentsDao();
        courseDao = new MysqlCourseDao();
        userDao = new MysqlUserDao();

        createCourses();
        createUsers();
    }

    private static void createCourses() throws ParseException {

        course1 = new Course();
        course1.setName("TestCourse01");
        course1.setSubjectId(1);
        course1.setTeacherId(1);
        course1.setStartDate(new Date(sdf.parse(stringDate).getTime()));
        course1.setExpirationDate(new Date(sdf.parse(stringDate).getTime()));
        course1 = courseDao.create(course1);

        course2 = new Course();
        course2.setName("TestCourse02");
        course2.setSubjectId(1);
        course2.setTeacherId(1);
        course2.setStartDate(new Date(sdf.parse(stringDate).getTime()));
        course2.setExpirationDate(new Date(sdf.parse(stringDate).getTime()));
        course2 = courseDao.create(course2);
    }

    private static void createUsers() {

        student1 = new User();
        student1.setLogin("student_01");
        student1.setPassword("student_01");
        student1.setPermission(Permission.STUDENT);
        student1.setFirstName("student_01");
        student1.setLastName("student_01");
        student1 = userDao.create(student1);

        student2 = new User();
        student2.setLogin("student_02");
        student2.setPassword("student_02");
        student2.setPermission(Permission.STUDENT);
        student2.setFirstName("student_02");
        student2.setLastName("student_02");
        student2 = userDao.create(student2);
    }

    @AfterClass
    public static void destroyAfterClass() {

        courseDao.delete(course1);
        courseDao.delete(course2);

        userDao.delete(student1);
        userDao.delete(student2);
    }

    @Test
    public void testCreate() {

        CourseStudents courseStudents = create(course1.getId(), student1.getId());
        CourseStudents created = dao.create(courseStudents);
        Assert.assertEquals(courseStudents.getCourseId(), created.getCourseId());
        Assert.assertEquals(courseStudents.getStudentId(), created.getStudentId());
        dao.delete(created);
    }

    @Test
    public void testGetById() {

        CourseStudents courseStudents = create(course1.getId(), student1.getId());
        CourseStudents created = dao.create(courseStudents);
        CourseStudents gotten = dao.get(created.getId());
        Assert.assertEquals(created.getCourseId(), gotten.getCourseId());
        Assert.assertEquals(created.getStudentId(), gotten.getStudentId());
        dao.delete(created);
    }

    @Test
    public void testGetByCourseId() {

        CourseStudents courseStudents = create(course1.getId(), student1.getId());
        CourseStudents courseStudents2 = create(course1.getId(), student2.getId());

        CourseStudents created = dao.create(courseStudents);
        CourseStudents created2 = dao.create(courseStudents2);

        int size = dao.getByCourseId(created.getCourseId()).size();
        Assert.assertEquals(2, size);
        dao.delete(created);
        dao.delete(created2);
    }

    @Test
    public void testGetByStudentId() {

        CourseStudents courseStudents = create(course1.getId(), student1.getId());
        CourseStudents courseStudents2 = create(course2.getId(), student1.getId());

        CourseStudents created = dao.create(courseStudents);
        CourseStudents created2 = dao.create(courseStudents2);

        int size = dao.getByStudentId(created.getStudentId()).size();
        Assert.assertEquals(2, size);
        dao.delete(created);
        dao.delete(created2);
    }

    @Test
    public void testUpdate() {

        CourseStudents courseStudents = create(course1.getId(), student1.getId());
        CourseStudents created = dao.create(courseStudents);
        CourseStudents gotten = dao.get(created.getId());
        gotten.setCourseId(course2.getId());
        dao.update(gotten);
        gotten = dao.get(created.getId());
        Assert.assertNotEquals(created.getCourseId(), gotten.getCourseId());
        dao.delete(gotten);
    }

    @Test
    public void testDelete() {

        CourseStudents courseStudents = create(course1.getId(), student1.getId());
        CourseStudents created = dao.create(courseStudents);
        int size = dao.getAll().size();
        dao.delete(created);
        Assert.assertEquals(size - 1, dao.getAll().size());
    }

    private CourseStudents create(int courseId, int studentId) {

        CourseStudents courseStudents = new CourseStudents();
        courseStudents.setCourseId(courseId);
        courseStudents.setStudentId(studentId);
        return courseStudents;
    }
}