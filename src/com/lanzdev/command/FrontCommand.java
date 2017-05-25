package com.lanzdev.command;

import com.lanzdev.ActionType;
import com.lanzdev.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrontCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(FrontCommand.class);

    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ActionType actionType;

    public void init(ServletContext context,
                     HttpServletRequest request,
                     HttpServletResponse response,
                     ActionType actionType) {
        this.context = context;
        this.request = request;
        this.response = response;
        this.actionType = actionType;
    }

    @Override
    public final String execute( ) throws ServletException, IOException {

        LOGGER.debug("Entering execute()");

        String path = null;

        if (actionType == null) {
            path = Path.WELCOME_PAGE;
        } else if (actionType == ActionType.GET) {
            path = doGet();
        } else if (actionType == ActionType.POST) {
            path = doPost();
        }

        LOGGER.debug("Leaving execute(): " + path);

        return path;
    }

    protected final void checkOnError() {

        if (request.getParameter("error") != null) {
            String error = request.getParameter("error");
            LOGGER.warn("request contains error: " + error);
            String errorMessage = "Error: " + error;
            request.setAttribute("errorMessage", errorMessage);
        }
    }

    protected abstract String doGet();
    protected abstract String doPost();
}
