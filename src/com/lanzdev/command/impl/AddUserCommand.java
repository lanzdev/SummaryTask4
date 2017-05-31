package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.UserDao;
import com.lanzdev.dao.mysql.impl.MysqlUserDao;
import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.User;
import com.lanzdev.util.validator.UserInputValidator;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class AddUserCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(AddUserCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        checkOnError();

        String desiredPermission = request.getParameter("desired_permission");
        Permission[] permissions = Permission.values();
        request.setAttribute("permissions", permissions);

        request.setAttribute("desired_permission", desiredPermission);

        LOGGER.trace("permissions: " + Arrays.toString(permissions));

        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_ADD_USER_FORM;
    }

    @Override
    protected String doPost( ) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Permission permission = Permission.getPermission(request.getParameter("permission"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");

        boolean valid = UserInputValidator.validate();

        if (valid) {
            LOGGER.trace(String.format("Fields: %s, %s, %s, %s, %s",
                    login, password, permission, firstName, lastName));

            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setPermission(permission);
            user.setFirstName(firstName);
            user.setLastName(lastName);

            UserDao dao = new MysqlUserDao();
            user = dao.create(user);
            request.setAttribute("user", user);

            LOGGER.trace("The user with id " + user.getId() + " added.");
        } else {
            LOGGER.trace("Fields failed validation.");
            return Path.REDIRECT_TO_ADD_USER_FORM + "&error=not_valid";
        }

        LOGGER.debug("Leaving doPost()");
        return Path.REDIRECT_TO_VIEW_USERS_LIST;
    }
}
