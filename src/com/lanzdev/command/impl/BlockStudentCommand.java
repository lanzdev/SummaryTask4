package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.UserDao;
import com.lanzdev.dao.mysql.impl.MysqlUserDao;
import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.User;
import org.apache.log4j.Logger;

import java.util.List;

public class BlockStudentCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(BlockStudentCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        checkOnError();

        UserDao dao = new MysqlUserDao();
        List<User> users = dao.getUnblockedStudents();

        request.setAttribute("users", users);

        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_VIEW_STUDENTS_LIST;
    }

    @Override
    protected String doPost( ) {

        LOGGER.debug("Entering doPost()");

        int studentId = Integer.parseInt(request.getParameter("user_id"));

        UserDao dao = new MysqlUserDao();
        User user = dao.get(studentId);
        if (user.getPermission() == Permission.STUDENT) {
            user.setBlocked(true);
            dao.update(user);
            LOGGER.trace("Blocked user with id " + user.getId());
        }

        List<User> list = dao.getStudents();
        request.setAttribute("users", list);

        LOGGER.debug("Leaving doPost()");

        return Path.REDIRECT_TO_VIEW_STUDENTS_LIST;
    }
}
