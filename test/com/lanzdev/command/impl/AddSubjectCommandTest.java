package com.lanzdev.command.impl;

import com.lanzdev.ActionType;
import com.lanzdev.DefaultMockito;
import com.lanzdev.Path;
import com.lanzdev.Util;
import com.lanzdev.dao.entity.SubjectDao;
import com.lanzdev.dao.mysql.impl.MysqlSubjectDao;
import com.lanzdev.domain.entity.Subject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

public class AddSubjectCommandTest extends DefaultMockito {

    private static AddSubjectCommand command;

    private static Subject subject;
    private static SubjectDao dao;

    @BeforeClass
    public static void initBeforeClass() {

        initEnv();
        dao = new MysqlSubjectDao();
        subject = Util.createSubject();

        when(request.getParameter("name")).thenReturn(subject.getName());
    }

    @AfterClass
    public static void destroyAfterClass() {
        Subject onRemove = Util.getLastInsert(dao.getAll());
        dao.delete(onRemove);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {

        command = new AddSubjectCommand();
        command.init(context, request, response, ActionType.GET);
        String result = command.execute();
        Assert.assertEquals(Path.FORWARD_TO_ADD_SUBJECT_FORM, result);
    }

    @Test
    public void testDoPost() throws ServletException, IOException {

        command = new AddSubjectCommand();
        command.init(context, request, response, ActionType.POST);
        String result = command.execute();
        Assert.assertEquals(Path.REDIRECT_TO_VIEW_SUBJECTS_LIST, result);
    }
}