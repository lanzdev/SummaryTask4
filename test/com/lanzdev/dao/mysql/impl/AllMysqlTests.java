package com.lanzdev.dao.mysql.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MysqlCourseDaoTest.class,
        MysqlCourseStudentsDaoTest.class,
        MysqlJournalDaoTest.class,
        MysqlSubjectDaoTest.class,
        MysqlUserDaoTest.class})
public class AllMysqlTests {

}
