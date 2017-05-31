package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.mysql.impl.MysqlCourseDao;
import com.lanzdev.domain.entity.User;
import com.lanzdev.util.AssignCortege;
import org.apache.log4j.Logger;

import java.util.List;

public class ListCoursesForEvaluationCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(ListCoursesForEvaluationCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        User user = (User) request.getSession().getAttribute("user");
        CourseDao dao = new MysqlCourseDao();
        List<AssignCortege> assigned = dao.getAssignedCourses(user);

        List<AssignCortege> assignedWithAvgMrk = dao.getAssignedWithAvgMark(user);


        request.getSession().setAttribute("corteges", assigned);
        LOGGER.trace("Cortege list contains " + assigned.size() + " items.");

        request.setAttribute("courses_with_avg_mark", assignedWithAvgMrk);
        LOGGER.trace("Cortege with avg mark contains " + assignedWithAvgMrk.size() + " items.");

        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_COURSES_LIST_FOR_EVALUATION;
    }

    @Override
    protected String doPost( ) {
        return Path.REDIRECT_TO_COURSES_LIST_FOR_EVALUATION;
    }
}
