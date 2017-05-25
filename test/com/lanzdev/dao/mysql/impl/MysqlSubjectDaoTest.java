package com.lanzdev.dao.mysql.impl;

import com.lanzdev.domain.entity.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MysqlSubjectDaoTest {

    private static Subject subject;
    private static MysqlSubjectDao dao;

    @BeforeClass
    public static void initBeforeClass() {
        dao = new MysqlSubjectDao();
    }

    @Before
    public void initBeforeTest() {

        subject = new Subject();
        subject.setName("TestSubject01");
    }

    @Test
    public void testCreate( ) {

        Subject created = dao.create(subject);
        Assert.assertEquals(subject.getName(), created.getName());
        dao.delete(created);
    }

    @Test
    public void testGetById( ) {

        Subject created = dao.create(subject);
        Subject gotten = dao.get(created.getId());
        Assert.assertEquals(created.getName(), gotten.getName());
        dao.delete(created);
    }

    @Test
    public void testGetAll( ) {

        Subject created = dao.create(subject);
        Assert.assertNotEquals(0, dao.getAll().size());
        dao.delete(created);
    }

    @Test
    public void testUpdate( ) {

        Subject created = dao.create(subject);
        Subject gotten = dao.get(created.getId());
        gotten.setName(gotten.getName() + "updated");
        dao.update(gotten);
        gotten = dao.get(created.getId());
        Assert.assertNotEquals(created.getName(), gotten.getName());
        dao.delete(created);
    }

    @Test
    public void testDelete( ) {

        Subject created = dao.create(subject);
        int size = dao.getAll().size();
        dao.delete(created);
        Assert.assertEquals(size - 1, dao.getAll().size());
    }
}
