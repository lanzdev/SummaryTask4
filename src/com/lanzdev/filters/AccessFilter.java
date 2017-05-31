package com.lanzdev.filters;

import com.lanzdev.Path;
import com.lanzdev.domain.Permission;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class AccessFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AccessFilter.class);

    private Map<Permission, List<String>> accessMap = new HashMap<>();
    private List<String> commons = new ArrayList<>();
    private List<String> outOfControl = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        LOGGER.debug("Entering access filter init.");

        accessMap.put(Permission.ADMIN, asList(filterConfig.getInitParameter(Permission.ADMIN.getName())));
        accessMap.put(Permission.TEACHER, asList(filterConfig.getInitParameter(Permission.TEACHER.getName())));
        accessMap.put(Permission.STUDENT, asList(filterConfig.getInitParameter(Permission.STUDENT.getName())));
        LOGGER.trace("Access map contains " + accessMap.size() + " items.");

        commons = asList(filterConfig.getInitParameter("common"));
        LOGGER.trace("Commons list contains " + commons.size() + " items.");

        outOfControl = asList(filterConfig.getInitParameter("out_of_control"));
        LOGGER.trace("Out of control list contains " + outOfControl.size() + " items.");

        LOGGER.debug("Leaving access filter init.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        LOGGER.debug("Entering doFilter()");

        if (isAllowed(servletRequest)) {
            LOGGER.debug("Filter finished");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String errorMessage = "You do not have permission to access the requested command!";
            servletRequest.setAttribute("error_message", errorMessage);
            LOGGER.trace("Set the request attribute: error_message = " + errorMessage);
            servletRequest.getRequestDispatcher(Path.ERROR_PAGE).forward(servletRequest, servletResponse);
        }

        LOGGER.debug("Leaving doFilter()");
    }

    private boolean isAllowed(ServletRequest _request) {

        HttpServletRequest request = (HttpServletRequest) _request;

        String command = request.getParameter("command");
        LOGGER.trace("Command: " + command);
        if (command == null || command.isEmpty()) {
            return false;
        }
        if (outOfControl.contains(command)) {
            return true;
        }
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        Permission permission = (Permission) session.getAttribute("user_permission");
        if (permission == null) {
            return commons.contains(command);
        }
        return accessMap.get(permission).contains(command) || commons.contains(command);
    }

    private List<String> asList(String source) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(source);
        while (st.hasMoreElements()) {
            list.add(st.nextToken());
        }
        return list;
    }

    @Override
    public void destroy( ) {

        /*NOP*/
    }
}
