package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.SubjectDao;
import com.lanzdev.dao.mysql.impl.MysqlSubjectDao;
import com.lanzdev.domain.entity.Subject;
import org.apache.log4j.Logger;

import java.util.List;

public class ListSubjectsCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(ListSubjectsCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");
        checkOnError();

        SubjectDao subjectDao = new MysqlSubjectDao();
        List<Subject> subjects = subjectDao.getAll();

        request.setAttribute("subjects", subjects);
        LOGGER.trace("Subjects list contains " + subjects.size() + " items.");

        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_VIEW_SUBJECTS_LIST;
    }

    @Override
    protected String doPost( ) {
        return Path.REDIRECT_TO_VIEW_SUBJECTS_LIST;
    }
}
