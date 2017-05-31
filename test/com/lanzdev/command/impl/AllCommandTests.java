package com.lanzdev.command.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddCourseCommandTest.class,
        AddSubjectCommandTest.class,
        AddUserCommandTest.class,
        AddJournalCommandTest.class,
        AddUserCommandTest.class
})
public class AllCommandTests {

}