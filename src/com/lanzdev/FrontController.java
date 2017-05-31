package com.lanzdev;

import com.lanzdev.command.CommandInvoker;
import com.lanzdev.command.FrontCommand;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = "/controller")
public class FrontController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(FrontController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        process(request, response, ActionType.POST);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        process(request, response, ActionType.GET);
    }

    private void process(HttpServletRequest request, HttpServletResponse response,
                         ActionType actionType) {

        LOGGER.debug("Entering process(actionType = " + actionType + ")");

        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response, actionType);

        try {
            String path = command.execute();

            request.getSession().setAttribute("last_command", command);
            LOGGER.trace("Last command value set no " + command.getClass());
            if (path == null) {
                LOGGER.warn("Path is null.");
                LOGGER.debug("Redirection to " + Path.WELCOME_PAGE);
                response.sendRedirect(Path.WELCOME_PAGE);
            } else if (actionType == ActionType.GET) {
                LOGGER.debug("Forward to " + path);
                RequestDispatcher disp = request.getRequestDispatcher(path);
                disp.forward(request, response);
            } else {
                LOGGER.debug("Redirect to " + path);
                response.sendRedirect(path);
            }

        } catch (ServletException e) {
            LOGGER.error("Servlet Exception ", e);
        } catch (IOException e) {
            LOGGER.error("IOException ", e);
        }
    }

    private FrontCommand getCommand(HttpServletRequest request) {

        String command = request.getParameter("command");
        return CommandInvoker.getCommand(command);
    }
}
