package com.lanzdev.dao.mysql.impl;

import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MysqlUserDaoTest {

    private static User user;
    private static MysqlUserDao dao;

    @BeforeClass
    public static void initBeforeClass() {
        dao = new MysqlUserDao();
    }

    @Before
    public void initBeforeTest() {
        user = new User();
        user.setLogin("user_01");
        user.setPassword("user_01");
        user.setPermission(Permission.STUDENT);
        user.setFirstName("user_01");
        user.setLastName("user_01");
    }

    @Test
    public void testCreate( ) {

        User created = dao.create(user);
        Assert.assertEquals(user.getLogin(), created.getLogin());
        Assert.assertEquals(user.getPassword(), created.getPassword());
        Assert.assertEquals(user.getPermission(), created.getPermission());
        Assert.assertEquals(user.getFirstName(), created.getFirstName());
        Assert.assertEquals(user.getLastName(), created.getLastName());
        dao.delete(created);
    }

    @Test
    public void testGetById() {

        User created = dao.create(user);
        User gotten = dao.get(created.getId());
        Assert.assertEquals(created.getLogin(), gotten.getLogin());
        Assert.assertEquals(created.getPassword(), gotten.getPassword());
        Assert.assertEquals(created.getPermission(), gotten.getPermission());
        Assert.assertEquals(created.getFirstName(), gotten.getFirstName());
        Assert.assertEquals(created.getLastName(), gotten.getLastName());
        dao.delete(created);
    }

    @Test
    public void testGetAll() {

        User created = dao.create(user);
        Assert.assertNotEquals(0, dao.getAll().size());
        dao.delete(created);
    }

    @Test
    public void testUpdate() {

        User created = dao.create(user);
        User gotten = dao.get(created.getId());
        gotten.setFirstName(gotten.getFirstName() + "updated");
        dao.update(gotten);
        gotten = dao.get(created.getId());
        Assert.assertEquals(created.getLogin(), gotten.getLogin());
        Assert.assertEquals(created.getPassword(), gotten.getPassword());
        Assert.assertEquals(created.getPermission(), gotten.getPermission());
        Assert.assertNotEquals(created.getFirstName(), gotten.getFirstName());
        Assert.assertEquals(created.getLastName(), gotten.getLastName());
        dao.delete(created);
    }

    @Test
    public void testDelete() {

        User created = dao.create(user);
        int size = dao.getAll().size();
        dao.delete(created);
        Assert.assertEquals(size - 1, dao.getAll().size());
    }
}

