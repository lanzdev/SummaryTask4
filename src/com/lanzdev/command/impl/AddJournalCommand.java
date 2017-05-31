package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.JournalDao;
import com.lanzdev.dao.mysql.impl.MysqlJournalDao;
import com.lanzdev.domain.entity.Journal;
import com.lanzdev.util.validator.JournalInputValidator;
import org.apache.log4j.Logger;

public class AddJournalCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(AddJournalCommand.class);

    @Override
    protected String doGet( ) {

        LOGGER.debug("Entering doGet()");

        checkOnError();

        LOGGER.debug("Leaving doGet()");

        return Path.FORWARD_TO_ADD_COURSE_STUDENT_FORM;
    }

    @Override
    protected String doPost( ) {

        LOGGER.debug("Entering doPost()");

        int courseId = Integer.valueOf(request.getParameter("course_id"));
        int studentId = Integer.valueOf(request.getParameter("student_id"));
        double mark = Double.valueOf(request.getParameter("mark"));

        boolean valid = JournalInputValidator.validate();

        if (valid) {
            LOGGER.trace(String.format("Fields: %s, %s, %s",
                    courseId, studentId, mark));

            Journal journal = new Journal();
            journal.setCourseId(courseId);
            journal.setStudentId(studentId);
            journal.setMark(mark);

            JournalDao dao = new MysqlJournalDao();
            journal = dao.create(journal);
            request.setAttribute("journal", journal);
            LOGGER.trace("The journal with id " + journal.getId() + " added.");
        } else {
            LOGGER.trace("Fields failed validation.");
            return Path.REDIRECT_TO_ADD_COURSE_STUDENT_FORM + "&error=not_valid";
        }

        LOGGER.debug("Leaving doPost()");
        return Path.REDIRECT_TO_VIEW_COURSE_STUDENTS_LIST;
    }
}
