package com.lanzdev.command;

import com.lanzdev.ActionType;
import com.lanzdev.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Ancestor for all command classes. Contains all necessary information for convenient
 * work with resources gotten from servlet. Requires to override methods {@link FrontCommand#doGet()}
 * and {@link FrontCommand#doPost()}
 */
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

    /**
     * Checks is there any errors in request parameters.
     * If there are - attaches attribute to request with error message.
     */
    protected final void checkOnError() {

        if (request.getParameter("error") != null) {
            String error = request.getParameter("error");
            String lang = (String) request.getSession().getAttribute("lang");
            if (lang == null || lang.equals("en")) {
                if (error.equals("not_valid")) {
                    request.setAttribute("error_message", "Input values has failed validation. Try again.");
                }
            } else if (lang.equals("ru")) {
                if (error.equals("not_valid")) {
                    request.setAttribute("error_message", "Входные данных не прошли проверку. Попробуйте заново.");
                }
            }
        }
    }

    /**
     * Method which will be called if {@link ActionType} in {@link FrontCommand#execute()} is GET
     * @return path for next manipulations with it from outside
     */
    protected abstract String doGet();

    /**
     * Method which will be called if {@link ActionType} in {@link FrontCommand#execute()} is POST
     * @return path for next manipulations with it from outside
     */
    protected abstract String doPost();
}
