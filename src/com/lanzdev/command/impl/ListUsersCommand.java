package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.UserDao;
import com.lanzdev.dao.mysql.impl.MysqlUserDao;
import com.lanzdev.domain.entity.User;
import org.apache.log4j.Logger;

import java.util.List;

public class ListUsersCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(ListUsersCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        UserDao dao = new MysqlUserDao();
        List<User> users = dao.getAll();

        request.setAttribute("users", users);
        LOGGER.trace("Users list contains " + users.size() + " items.");

        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_VIEW_USERS_LIST;
    }

    @Override
    protected String doPost( ) {
        return Path.REDIRECT_TO_VIEW_USERS_LIST;
    }
}
