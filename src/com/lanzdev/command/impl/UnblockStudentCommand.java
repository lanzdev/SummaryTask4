package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.UserDao;
import com.lanzdev.dao.mysql.impl.MysqlUserDao;
import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.User;
import org.apache.log4j.Logger;

import java.util.List;

public class UnblockStudentCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(UnblockStudentCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        UserDao dao = new MysqlUserDao();
        List<User> blocked = dao.getBlockedStudents();
        request.setAttribute("users", blocked);

        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_VIEW_STUDENTS_LIST;
    }

    @Override
    protected String doPost( ) {

        int studentId = Integer.parseInt(request.getParameter("user_id"));

        UserDao dao = new MysqlUserDao();
        User user = dao.get(studentId);
        if (user.getPermission() == Permission.STUDENT) {
            user.setBlocked(false);
            dao.update(user);
            LOGGER.trace("Unblocked user with id " + user.getId());
        }

        List<User> list = dao.getStudents();
        request.setAttribute("users", list);

        return Path.REDIRECT_TO_VIEW_STUDENTS_LIST;
    }
}
