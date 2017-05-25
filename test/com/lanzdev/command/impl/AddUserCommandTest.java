package com.lanzdev.command.impl;

import com.lanzdev.ActionType;
import com.lanzdev.DefaultMockito;
import com.lanzdev.Path;
import com.lanzdev.Util;
import com.lanzdev.dao.entity.UserDao;
import com.lanzdev.dao.mysql.impl.MysqlUserDao;
import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.User;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

public class AddUserCommandTest extends DefaultMockito {

    private static AddUserCommand command;

    private static User user;

    private static UserDao dao;

    @BeforeClass
    public static void initBeforeClass() {

        initEnv();
        dao = new MysqlUserDao();
        user = Util.createUser(Permission.STUDENT);

        when(request.getParameter("login")).thenReturn(user.getLogin());
        when(request.getParameter("password")).thenReturn(user.getPassword());
        when(request.getParameter("permission")).thenReturn(user.getPermission().name());
        when(request.getParameter("first_name")).thenReturn(user.getFirstName());
        when(request.getParameter("last_name")).thenReturn(user.getLastName());
    }

    @AfterClass
    public static void destroyAfterClass() {
        User onRemove = Util.getLastInsert(dao.getAll());
        dao.delete(onRemove);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {

        command = new AddUserCommand();
        command.init(context, request, response, ActionType.GET);
        String result = command.execute();
        Assert.assertEquals(Path.FORWARD_TO_ADD_USER_FORM, result);
    }

    @Test
    public void testDoPost() throws ServletException, IOException {

        command = new AddUserCommand();
        command.init(context, request, response, ActionType.POST);
        String result = command.execute();
        Assert.assertEquals(Path.REDIRECT_TO_VIEW_USERS_LIST, result);
    }
}