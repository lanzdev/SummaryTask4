package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

public class LogoutCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(LogoutCommand.class);

    @Override
    protected String doGet( ) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return Path.WELCOME_PAGE;
    }

    @Override
    protected String doPost( ) {
        return doGet();
    }
}
