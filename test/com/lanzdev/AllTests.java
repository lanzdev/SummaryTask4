package com.lanzdev;

import com.lanzdev.command.impl.AllCommandTests;
import com.lanzdev.dao.mysql.impl.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllCommandTests.class,
        AllMysqlTests.class
})
public class AllTests {

}
