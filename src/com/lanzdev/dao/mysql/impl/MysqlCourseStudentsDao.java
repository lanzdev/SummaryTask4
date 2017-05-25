package com.lanzdev.dao.mysql.impl;

import com.lanzdev.dao.ConnectionPool;
import com.lanzdev.dao.entity.CourseStudentsDao;
import com.lanzdev.dao.mysql.AbstractMysqlDao;
import com.lanzdev.dao.mysql.Query;
import com.lanzdev.domain.entity.CourseStudents;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlCourseStudentsDao extends AbstractMysqlDao<CourseStudents, Integer>
        implements CourseStudentsDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlCourseStudentsDao.class);

    @Override
    protected String getInsertQuery( ) {
        return Query.INSERT_COURSE_STUDENTS;
    }

    @Override
    protected String getSelectByIdQuery( ) {
        return Query.SELECT_COURSE_STUDENTS_BY_ID;
    }

    @Override
    protected String getSelectAllQuery( ) {
        return Query.SELECT_ALL_COURSE_STUDENTS;
    }

    @Override
    protected String getLastInsertId( ) {
        return Query.SELECT_LAST_INSERT_COURSE_STUDENTS;
    }

    @Override
    protected String getUpdateQuery( ) {
        return Query.UPDATE_COURSE_STUDENTS;
    }

    @Override
    protected String getDeleteQuery( ) {
        return Query.DELETE_COURSE_STUDENTS;
    }

    @Override
    protected List<CourseStudents> parseResultSet(ResultSet rs) {

        List<CourseStudents> list = new ArrayList<>();

        try {
            while (rs.next()) {
                CourseStudents courseStudents = new CourseStudents();
                courseStudents.setId(rs.getInt("course_students_id"));
                courseStudents.setCourseId(rs.getInt("course_id"));
                courseStudents.setStudentId(rs.getInt("student_id"));
                list.add(courseStudents);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while parsing result set.\n", e);
        }

        return list;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement stmt, CourseStudents object) {

        try {
            stmt.setInt(1, object.getCourseId());
            stmt.setInt(2, object.getStudentId());
        } catch (SQLException e) {
            LOGGER.error("Exception while preparing statement for create.\n", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, CourseStudents object) {

        try {
            stmt.setInt(1, object.getCourseId());
            stmt.setInt(2, object.getStudentId());
            stmt.setInt(3, object.getId());
        } catch (SQLException e) {
            LOGGER.error("Exception while preparing statement for update.\n", e);
        }
    }

    @Override
    public List<CourseStudents> getByCourseId(Integer courseId) {

        List<CourseStudents> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Query.SELECT_COURSE_STUDENTS_BY_COURSE_ID)) {

            stmt.setInt(1, courseId);
            list = parseResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            LOGGER.error("Exception while getting course students by course id.\n", e);
        } catch (NullPointerException e) {
            LOGGER.error("NPE exception while preparing statement.\n", e);
        }

        return list;
    }

    @Override
    public List<CourseStudents> getByStudentId(Integer studentId) {

        List<CourseStudents> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Query.SELECT_COURSE_STUDENTS_BY_STUDENT_ID)) {

            stmt.setInt(1, studentId);
            list = parseResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            LOGGER.error("Exception while getting course students by student id.\n", e);
        } catch (NullPointerException e) {
            LOGGER.error("NPE exception while preparing statement.\n", e);
        }

        return list;
    }


}
