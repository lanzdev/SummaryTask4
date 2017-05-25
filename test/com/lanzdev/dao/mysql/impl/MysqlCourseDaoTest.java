package com.lanzdev.dao.mysql.impl;


import com.lanzdev.domain.entity.Course;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MysqlCourseDaoTest {

    private static String stringDate = "2000-01-01";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
    private static Course course;
    private static MysqlCourseDao dao;

    @BeforeClass
    public static void initBeforeClass() {
        dao = new MysqlCourseDao();
    }

    @Before
    public void initBeforeTest() throws ParseException {

        course = new Course();
        course.setName("TestCourse01");
        course.setSubjectId(1);
        course.setTeacherId(1);
        course.setStartDate(new Date(sdf.parse(stringDate).getTime()));
        course.setExpirationDate(new Date(sdf.parse(stringDate).getTime()));
    }

    @Test
    public void testCreate() {

        Course created = dao.create(course);
        Assert.assertEquals(course.getName(), created.getName());
        dao.delete(created);
    }

    @Test
    public void testGetById() {

        Course created = dao.create(course);
        Course gotten = dao.get(created.getId());
        Assert.assertEquals(created.getName(), gotten.getName());
        dao.delete(created);
    }

    @Test
    public void testGetAll() {

        Course created = dao.create(course);
        Assert.assertNotEquals(0, dao.getAll().size());
        dao.delete(created);
    }

    @Test
    public void testUpdate() {

        Course created = dao.create(course);
        Course gotten = dao.get(created.getId());
        gotten.setName(gotten.getName() + "updated");
        dao.update(gotten);
        gotten = dao.get(created.getId());
        Assert.assertNotEquals(created.getName(), gotten.getName());
        dao.delete(created);
    }

    @Test
    public void testDelete() {

        Course created = dao.create(course);
        int size = dao.getAll().size();
        dao.delete(created);
        Assert.assertEquals(size - 1, dao.getAll().size());
    }
}