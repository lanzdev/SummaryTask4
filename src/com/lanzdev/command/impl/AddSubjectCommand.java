package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.SubjectDao;
import com.lanzdev.dao.mysql.impl.MysqlSubjectDao;
import com.lanzdev.domain.entity.Subject;
import com.lanzdev.util.validator.CourseNameValidator;
import org.apache.log4j.Logger;


public class AddSubjectCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(AddSubjectCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        checkOnError();

        LOGGER.debug("Leaving doGet()");
        return Path.FORWARD_TO_ADD_SUBJECT_FORM;
    }

    @Override
    protected String doPost( ) {

        String name = request.getParameter("subject_name");

        boolean valid = CourseNameValidator.validate(name);

        if (valid) {
            LOGGER.trace("Fields: " + name);

            Subject subject = new Subject();
            subject.setName(name);

            SubjectDao dao = new MysqlSubjectDao();
            subject = dao.create(subject);
            request.setAttribute("subject", subject);
            LOGGER.trace("The subject with id " + subject.getId() + " added.");
        } else {
            LOGGER.trace("Fields failed validation.");
            return Path.REDIRECT_TO_ADD_SUBJECT_FORM + "&error=not_valid";
        }

        LOGGER.debug("Leaving doPost()");
        return Path.REDIRECT_TO_VIEW_SUBJECTS_LIST;
    }
}
