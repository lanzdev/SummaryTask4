package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.UserDao;
import com.lanzdev.dao.mysql.impl.MysqlUserDao;
import com.lanzdev.domain.entity.User;
import org.apache.log4j.Logger;

import java.util.List;

public class ListStudentsCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(ListStudentsCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        UserDao dao = new MysqlUserDao();
        List<User> students = dao.getStudents();
        request.setAttribute("users", students);

        LOGGER.trace("Users list contains " + students.size());
        LOGGER.debug("Leaving doGet()");

        return Path.FORWARD_TO_VIEW_STUDENTS_LIST;
    }

    @Override
    protected String doPost( ) {
        return doGet();
    }
}
