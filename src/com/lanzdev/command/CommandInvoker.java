package com.lanzdev.command;

import com.lanzdev.command.impl.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Class which invokes command by its name.
 */
public class CommandInvoker {

    private static final Logger LOGGER = Logger.getLogger(CommandInvoker.class);

    /**
     * Map containing {@link String} command name and {@link CommandCreator} object
     */
    private static final Map<String, CommandCreator> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("AddCourse", AddCourseCommand::new);
        COMMANDS.put("AddCourseStudents", AddCourseStudentsCommand::new);
        COMMANDS.put("AddJournal", AddJournalCommand::new);
        COMMANDS.put("AddSubject", AddSubjectCommand::new);
        COMMANDS.put("AddUser", AddUserCommand::new);
        COMMANDS.put("AssignTeacherForCourse", AssignTeacherForCourseCommand::new);
        COMMANDS.put("BlockStudent", BlockStudentCommand::new);
        COMMANDS.put("DeleteCourse", DeleteCourseCommand::new);
        COMMANDS.put("DeleteUser", DeleteUserCommand::new);
        COMMANDS.put("EditCourse", EditCourseCommand::new);
        COMMANDS.put("Evaluate", EvaluateCommand::new);
        COMMANDS.put("Language", LanguageCommand::new);
        COMMANDS.put("ListCourses", ListCoursesCommand::new);
        COMMANDS.put("ListCoursesForEvaluation", ListCoursesForEvaluationCommand::new);
        COMMANDS.put("ListCoursesForTeacher", ListCoursesForTeacherCommand::new);
        COMMANDS.put("ListCoursesInProcess", ListCoursesInProcessCommand::new);
        COMMANDS.put("ListCoursesSort", ListCoursesSortCommand::new);
        COMMANDS.put("ListDoneCourses", ListDoneCoursesCommand::new);
        COMMANDS.put("ListNotStartedCourses", ListNotStartedCoursesCommand::new);
        COMMANDS.put("ListStudents", ListStudentsCommand::new);
        COMMANDS.put("ListSubjects", ListSubjectsCommand::new);
        COMMANDS.put("ListUsers", ListUsersCommand::new);
        COMMANDS.put("Login", LoginCommand::new);
        COMMANDS.put("Logout", LogoutCommand::new);
        COMMANDS.put("PersonalArea", PersonalAreaCommand::new);
        COMMANDS.put("SubscribeCourse", SubscribeCourseCommand::new);
        COMMANDS.put("UnblockStudent", UnblockStudentCommand::new);
        COMMANDS.put("UnknownCommand", UnknownCommand::new);
    }

    /**
     *
     * @param commandName name of command which is going to be obtained
     * @return {@link FrontCommand} object defined by commandName if it contains, otherwise
     * returns {@link UnknownCommand}
     */
    public static FrontCommand getCommand(String commandName) {

        LOGGER.debug("Entering getCommand(commandName = " + commandName + ")");

        FrontCommand command;
        if (commandName == null) {
            LOGGER.warn("getCommand() input value is null.");
            command = COMMANDS.get("UnknownCommand").create();
        } else if (!COMMANDS.containsKey(commandName)) {
            LOGGER.warn("getCommand() cannot find command with such name: " + commandName);
            command = COMMANDS.get("UnknownCommand").create();
        } else {
            command = COMMANDS.get(commandName).create();
        }

        LOGGER.debug("Leaving getCommand(): " + command.getClass());
        return command;
    }
}
