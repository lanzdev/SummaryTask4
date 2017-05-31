package com.lanzdev.command.impl;

import com.lanzdev.command.FrontCommand;
import org.apache.log4j.Logger;

public class LanguageCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(LanguageCommand.class);

    @Override
    protected String doGet( ) {
        return doPost();
    }

    @Override
    protected String doPost( ) {

        String action = "controller?";

        String url = request.getParameter("url");
        String lang = request.getParameter("language");
        LOGGER.trace("url: " + url + ", language: " + lang);

        request.getSession().setAttribute("lang", lang);
        LOGGER.trace("Language has been changed to " + lang);

        if (url.equals("command=Logout") || url.isEmpty()) {
            return null;
        }

        return action + url;
    }
}
