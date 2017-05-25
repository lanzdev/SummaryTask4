package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseDao;
import com.lanzdev.domain.entity.User;
import com.lanzdev.util.ProgressCortege;
import org.apache.log4j.Logger;

import java.util.List;

public class ListDoneCoursesCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(ListDoneCoursesCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        User student = (User) request.getSession().getAttribute("user");
        LOGGER.trace("Student: " + student.getId());

        CourseDao dao = new MysqlCourseDao();
        List<ProgressCortege> list = dao.getDoneCourses(student);
        LOGGER.trace("List of done courses contains " + list.size() + " items.");

        request.setAttribute("progress_cortege", list);

        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_VIEW_PROGRESS_LIST;
    }

    @Override
    protected String doPost( ) {
        return Path.REDIRECT_TO_VIEW_PROGRESS_LIST;
    }
}
