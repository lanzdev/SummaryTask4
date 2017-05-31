package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseDao;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.util.CourseComparator;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;

public class ListCoursesSortCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(ListCoursesSortCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        checkOnError();

        LOGGER.debug("Leaving doGet()");

        return Path.FORWARD_TO_VIEW_COURSES_LIST;
    }

    @Override
    protected String doPost( ) {

        LOGGER.debug("Entering doPost()");
        CourseDao dao = new MysqlCourseDao();
        List<Course> list = (List<Course>) request.getSession().getAttribute("courses");
        if (list == null) {
            list = dao.getAll();
        }

        LOGGER.trace("List of courses contains " + list.size() + " items.");

        String compareRule = request.getParameter("compare_rule");
        LOGGER.trace("Compare rule: " + compareRule);

        Comparator<Course> comparator = null;
        if (compareRule != null) {
            comparator = CourseComparator.getComparator(compareRule);
        }
        if (comparator != null) {
            list.sort(comparator);
        }

        request.getSession().setAttribute("courses", list);

        LOGGER.debug("Leaving doPost()");

        return Path.REDIRECT_TO_VIEW_COURSES_LIST;
    }

}
